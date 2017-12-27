package cz.upce.webalyt.webserver;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * First layer of the webalyt architecture
 * This class is used only for receiving of messages.
 * Messages are converted to JSON and send to Kafka.
 */
@RestController
public class WebserverController {


    @Autowired
    private BaseKafkaSender sender;


    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "application/json")
    public void process(@RequestBody String payload) throws Exception {
        System.out.println(payload);
        sender.send(payload);
    }

    @RequestMapping(value = "/script.js", method = RequestMethod.GET)
    public String getPluginJavascripts() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8989/services", HttpMethod.GET, HttpEntity.EMPTY,
                String.class);
        String body = exchange.getBody();
        Type listType = new TypeToken<HashMap<String, InstanceInfo>>() {
        }.getType();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {

            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            public boolean shouldSkipClass(Class<?> clazz) {
                return clazz == DataCenterInfo.class;
            }
        });


        StringBuilder jsCode = new StringBuilder();


        Gson gson = gsonBuilder.create();
        HashMap<String, InstanceInfo> instanceInfoHashMap = gson.fromJson(body, listType);
        for (Map.Entry<String, InstanceInfo> entry : instanceInfoHashMap.entrySet()) {
            if (entry.getValue().getAppName().startsWith("PLUGIN")) {
                String url = "http://" + entry.getValue().getIPAddr() + ":" + entry.getValue().getPort() + "/files";
                System.out.println(url);
                ResponseEntity<List<String>> files = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<String>>() {
                });
                if (files.getBody() != null) {
                    System.out.println(files.getBody().toString());
                    for (String fileName : files.getBody()) {
                        String fileUrl = url + "/" + fileName;
                        ResponseEntity<String> exchange1 = restTemplate.exchange(fileUrl, HttpMethod.GET, HttpEntity.EMPTY, String.class);
                        jsCode.append("//start " + fileUrl);
                        jsCode.append(System.getProperty("line.separator"));
                        jsCode.append(exchange1.getBody());
                        jsCode.append(System.getProperty("line.separator"));
                        jsCode.append("//end " + fileUrl);
                        jsCode.append(System.getProperty("line.separator"));
                    }
                }
            }
        }

        try {
            jsCode.append(System.getProperty("line.separator"));
            jsCode.append(readFileFromClassPath("/webalyt/fingerprint2.min.js"));
            jsCode.append(System.getProperty("line.separator"));
            jsCode.append(readFileFromClassPath("/webalyt/main.js"));
            jsCode.append(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsCode.toString();

    }

    static String readFileFromClassPath(String path) throws IOException {
        InputStream stream = null;
        String s;
        try {
            stream = new ClassPathResource(path).getInputStream();
            s = IOUtils.toString(stream);
        } finally {
            IOUtils.closeQuietly(stream);
        }
        return s;
    }
}
