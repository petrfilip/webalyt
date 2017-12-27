package cz.upce.webalyt.hintmanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HintManagerController {

    RestTemplate restTemplate = new RestTemplate();

//    @Value("${webalyt.endpoint.url}")
    private String serverEndpoint;

//    @KafkaListener(topics = "${hint-topic}")
    public void forwardHintToServer(Object hint) {
        forwardMessage(HintSourceEnum.REALTIME, hint);
    }

    private void forwardMessage(HintSourceEnum hintSource, Object hint) {
        HttpEntity<Object> objectHttpEntity = new HttpEntity<>(hint);

        //todo save to the database

        ResponseEntity<Object> exchange = restTemplate.exchange(serverEndpoint, HttpMethod.POST, objectHttpEntity, Object.class);
    }

}
