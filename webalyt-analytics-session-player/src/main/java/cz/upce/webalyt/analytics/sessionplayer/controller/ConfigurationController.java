package cz.upce.webalyt.analytics.sessionplayer.controller;

import cz.upce.webalyt.analytics.sessionplayer.entity.AnalyticsPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
public class ConfigurationController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/index") //todo move to core package -- bind property from properities file
    public AnalyticsPlugin getPluginConfiguration() {
        AnalyticsPlugin analyticsPlugin = new AnalyticsPlugin();
        analyticsPlugin.setName("Session player");
        analyticsPlugin.setDefaultOperation("/session-player/");
        analyticsPlugin.setDescription("Very sophisticated session player");
        //analyticsPlugin.setRequestMappingInfos(requestMappingHandlerMapping.getHandlerMethods().keySet());
        return analyticsPlugin;
    }


}
