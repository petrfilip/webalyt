package cz.upce.webalyt.webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BaseKafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseKafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${maintopic}")
    private String kafkaTopic;

    public void send(String data) {
        System.out.println("sending data='{" + data + "}' to topic='{" + kafkaTopic + "}'");
        kafkaTemplate.send(kafkaTopic, data);
    }
}
