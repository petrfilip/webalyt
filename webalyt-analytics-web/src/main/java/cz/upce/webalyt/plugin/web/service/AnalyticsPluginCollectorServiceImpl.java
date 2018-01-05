package cz.upce.webalyt.plugin.web.service;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import cz.upce.webalyt.plugin.web.entity.AnalyticsPlugin;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsPluginCollectorServiceImpl implements AnalyticsPluginCollectorService {

    private static final String CONFIGURATION_URL_PATH = "/index";

    @Override
    public List<AnalyticsPlugin> collectAnalyticsPlugin() {
        List<AnalyticsPlugin> plugins = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8989/services", HttpMethod.GET, HttpEntity.EMPTY, String.class);
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


        Gson gson = gsonBuilder.create();
        HashMap<String, InstanceInfo> instanceInfoHashMap = gson.fromJson(body, listType);
        for (Map.Entry<String, InstanceInfo> entry : instanceInfoHashMap.entrySet()) {
            if (entry.getValue().getAppName().startsWith("ANALYTICS")) {
                String url = "http://" + entry.getValue().getIPAddr() + ":" + entry.getValue().getPort();
                String urlToConfiguration = url + CONFIGURATION_URL_PATH;
                System.out.println(urlToConfiguration);
                ResponseEntity<AnalyticsPlugin> analyticsPluginResponseEntity = restTemplate.exchange(urlToConfiguration, HttpMethod.GET, HttpEntity.EMPTY, AnalyticsPlugin.class);
                if (analyticsPluginResponseEntity.getBody() != null) {
                    System.out.println(analyticsPluginResponseEntity.getBody().toString());
                    AnalyticsPlugin body1 = analyticsPluginResponseEntity.getBody();
                    body1.setServerAddress(url);
                    plugins.add(analyticsPluginResponseEntity.getBody());
                }
            }
        }
        return plugins;
    }
}
