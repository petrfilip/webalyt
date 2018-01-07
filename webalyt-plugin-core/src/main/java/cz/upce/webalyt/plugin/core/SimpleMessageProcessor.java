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
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
        messageProcessingDelay = new Date().getTime() - record.timestamp();
        System.out.println("Delay: " + messageProcessingDelay);

        Collection<T> collection = gson.fromJson(String.valueOf(record.value()), parameterizedType);
        if (!collection.isEmpty()) {
            for (T object : collection) {
                object.setPageViewId(record.key().toString()); //todo vzít ze zprávy
                processMessage(object);
            }
            System.out.println("Incoming count of data: " + collection.size());
        }
    }

    protected abstract Class<T> getEntity();

    protected abstract void processMessage(T object);

    public long getMessageProcessingDelay() { //todo dát někam na rest rozhraní, aby bylo přístupné
        return messageProcessingDelay;
    }
}
