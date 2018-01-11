package cz.upce.webalyt.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClassifiedKafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifiedKafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String kafkaTopic,  String pageViewId, String data) {
        int partitionCount = kafkaTemplate.partitionsFor(kafkaTopic).size();
        System.out.println("sending data='{" + data + "}' to topic='{" + kafkaTopic + "} to partition = {" + partitionCount + "}'");
        kafkaTemplate.send(kafkaTopic, partition(pageViewId.hashCode(), partitionCount), pageViewId, data);
    }

    public int partition(int key, int a_numPartitions) {
        int partition = 0;
        partition = Math.abs(key) % a_numPartitions;
        return partition;
    }
}
