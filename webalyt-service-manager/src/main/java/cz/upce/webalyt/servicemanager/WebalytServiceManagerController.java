package cz.upce.webalyt.servicemanager;

import com.google.gson.Gson;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/webalyt")
public class WebalytServiceManagerController {

    @Autowired
    private WebalytServiceListener webalytServiceListener;

    @RequestMapping(value = "/services")
    public String getRunningServicess() {
        return new Gson().toJson(webalytServiceListener.getRunningServices());
    }

    @RequestMapping(value = "/kafka/topics")
    public String getAllKafkaTopics()  {
        try {
            ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, null);
            List<String> topics = zk.getChildren("/brokers/topics", false);
            return new Gson().toJson(topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/kafka/topics/{topicName}")
    public String getTopicPartitions(@PathVariable String topicName)  {
        try {
            ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, null);
            List<String> brokers = zk.getChildren("/brokers/topics/" + topicName + "/partitions", false);
            return new Gson().toJson(brokers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/kafka/brokers")
    public String getAllKafkaBrokers()  {
        try {
            ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, null);
            List<String> brokers = zk.getChildren("/brokers/ids/0", false);
            return new Gson().toJson(brokers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
