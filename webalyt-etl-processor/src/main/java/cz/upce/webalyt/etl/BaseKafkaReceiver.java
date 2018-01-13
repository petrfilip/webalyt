package cz.upce.webalyt.etl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class BaseKafkaReceiver {

    @Autowired
    private ClassifiedKafkaSender kafkaSender;

    @Autowired
    private PageViewRepository pageViewRepository;

    @Autowired
    private RawIncomingMessageRepository rawIncomingMessageRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseKafkaReceiver.class);

    private Cache<String, PageView> lastPageViews = CacheBuilder.newBuilder().maximumSize(10000).expireAfterAccess(30, TimeUnit.MINUTES).build();

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private CountDownLatch latch = new CountDownLatch(1);

    private long messageProcessingDelay;

    @KafkaListener(topics = "${maintopic}")
    public void receive(ConsumerRecord<?, ?> record) {

        messageProcessingDelay = record.timestamp() - new Date().getTime();
        System.out.println(messageProcessingDelay);
        saveRawIncomingMessage(record);

        Map<String, Object> retMap = new Gson().fromJson(
                String.valueOf(record.value()), new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );

        String pageViewId = (String) ((Map) retMap.get("pageView")).get("pageViewId");
        saveNewPageView(retMap, pageViewId);
        redirectMessagesForProcessing(retMap, pageViewId);

        System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());

        LOGGER.info("received data='{}'", record.toString());
        latch.countDown();
    }

    private void saveNewPageView(Map<String, Object> retMap, String pageViewId) {
        if (lastPageViews.getIfPresent(pageViewId) == null) {
            PageView pageView = convertToPageView((Map) retMap.get("pageView"));
            pageViewRepository.save(pageView);
            lastPageViews.put(pageViewId, pageView);
        }
    }

    private void redirectMessagesForProcessing(Map<String, Object> retMap, String pageViewId) {
        for (Map.Entry<String, Object> entry : retMap.entrySet()) {
            kafkaSender.send(entry.getKey(), pageViewId, new Gson().toJson(entry.getValue()));
        }
    }

    private void saveRawIncomingMessage(ConsumerRecord<?, ?> record) {
        RawIncomingMessage rawIncomingMessage = new RawIncomingMessage();
        rawIncomingMessage.setTimestamp(new Date());
        rawIncomingMessage.setMessage(String.valueOf(record.value()));
        rawIncomingMessageRepository.save(rawIncomingMessage);
    }

    private PageView convertToPageView(Map o) {
        try {
            PageView pageView = new PageView();
            pageView.setTimestamp(dateFormat.parse((String) o.get("timestamp")));
            pageView.setPageViewId((String) o.get("pageViewId"));
            pageView.setSessionId((String) o.get("sessionId"));
            pageView.setUrl((String) o.get("url"));
            pageView.setWebsiteId((String) o.get("websiteId"));
            return pageView;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getMessageProcessingDelay() {
        return messageProcessingDelay;
    }
}
