package cz.upce.webalyt.analytics.web.controller;

import cz.upce.webalyt.analytics.web.entity.AnalyticsPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
public class ConfigurationController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/index")
    public AnalyticsPlugin getPluginConfiguration() {
        AnalyticsPlugin analyticsPlugin = new AnalyticsPlugin();
        analyticsPlugin.setName("Webalyt");
        analyticsPlugin.setDefaultOperation("/");
        analyticsPlugin.setDescription("Base webalyt page");
        //analyticsPlugin.setRequestMappingInfos(requestMappingHandlerMapping.getHandlerMethods().keySet());
        return analyticsPlugin;
    }
}
