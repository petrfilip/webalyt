package cz.upce.webalyt.etl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;

@Component
public class BaseKafkaReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseKafkaReceiver.class);

    private final RawIncomingMessageRepository repository;

    private CountDownLatch latch = new CountDownLatch(1);

    private long messageProcessingDelay;

    @Autowired
    private ClassifiedKafkaSender kafkaSender;

    @Autowired
    public BaseKafkaReceiver(RawIncomingMessageRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${maintopic}")
    public void receive(ConsumerRecord<?, ?> record) {

        messageProcessingDelay = record.timestamp() - new Date().getTime();
        System.out.println(messageProcessingDelay);
        RawIncomingMessage rawIncomingMessage = new RawIncomingMessage();
        rawIncomingMessage.setId(UUID.randomUUID()); //todo tady má být stále stejné ID, nikoliv random
        rawIncomingMessage.setTimestamp(new Date());
        rawIncomingMessage.setMessage(String.valueOf(record.value()));
        repository.save(rawIncomingMessage); //todo - tady by se mělo ukládat na hadoop -- zatím uložení jen do DB

        //todo dodělat logiku na odeslání do kafky (do správných témat)
        Map<String, Object> retMap = new Gson().fromJson(
                String.valueOf(record.value()), new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );

        String deviceId = ((Map)((List)retMap.get("WCdevId")).get(0)).get("fp").toString(); //todo: co je fp?

        for (Map.Entry<String, Object> entry : retMap.entrySet()) {
            kafkaSender.send(entry.getKey(), deviceId, new Gson().toJson(entry.getValue()));
        }

        System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());

        LOGGER.info("received data='{}'", record.toString());
        latch.countDown();
    }

    public long getMessageProcessingDelay() {
        return messageProcessingDelay;
    }
}
