package cz.upce.webalyt.plugin.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;

public abstract class SimpleMessageProcessor<T extends WebalytEntity> {

    private final ParameterizedType parameterizedType;

    private long messageProcessingDelay;

    private final Gson gson;



    public SimpleMessageProcessor() {



        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();
        parameterizedType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{getEntity()};
            }

            @Override
            public Type getRawType() {
                return Collection.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    @KafkaListener(topics = "${spring.kafka.consumer.group-id}")
    public void receive(ConsumerRecord<?, ?> record) {
        messageProcessingDelay = record.timestamp() - new Date().getTime();
        System.out.println(messageProcessingDelay);

        Collection<T> collection = gson.fromJson(String.valueOf(record.value()), parameterizedType);
        if (collection.size() > 0) {
            for (T object : collection) {
                object.setDeviceId(record.key().toString());
                object.setTimestamp(new Date()); //todo vzít datum ze zprávy -- record.timestamp()
                processMessage(object);
            }
            System.out.println(collection.size());
        }
    }

    protected abstract Class<T> getEntity();

    protected abstract void processMessage(T object);

    public long getMessageProcessingDelay() { //todo dát někam na rest rozhraní, aby bylo přístupné
        return messageProcessingDelay;
    }
}
