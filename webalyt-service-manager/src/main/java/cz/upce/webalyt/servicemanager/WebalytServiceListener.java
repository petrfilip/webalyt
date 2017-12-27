package cz.upce.webalyt.servicemanager;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebalytServiceListener {

    private Map<String, InstanceInfo> runningServices = new HashMap<>();

    @EventListener
    void handle(EurekaInstanceRegisteredEvent event) {
        if (!runningServices.containsKey(event.getInstanceInfo().getInstanceId()) && event.getInstanceInfo().getStatus().equals(InstanceInfo.InstanceStatus.UP)) {
            System.out.println("Registered: " + event.getInstanceInfo().getInstanceId());
            runningServices.put(event.getInstanceInfo().getInstanceId(), event.getInstanceInfo());

            try {
                String webalytUrl = "http://" + event.getInstanceInfo().getIPAddr() + ":" + event.getInstanceInfo().getPort() + "/webalyt";
                ResponseEntity<WebalytPluginConfigModel> exchange = new RestTemplate().exchange(webalytUrl, HttpMethod.GET, HttpEntity.EMPTY, WebalytPluginConfigModel.class);
                WebalytPluginConfigModel body = exchange.getBody();
                System.out.println(body);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    @EventListener
    void handle(EurekaInstanceCanceledEvent event) {
        if (runningServices.containsKey(event.getServerId())) {
            System.out.println("Canceled: " + event.getServerId());
            runningServices.remove(event.getServerId());
        }
    }


    public Map<String, InstanceInfo> getRunningServices() {
        return runningServices;
    }
}
