package cz.upce.webalyt.plugin.urlrecorder.controller;

import cz.upce.webalyt.plugin.urlrecorder.entity.AnalyticsPlugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    @GetMapping("/index") //todo move to core package -- bind property from properities file
    public AnalyticsPlugin getPluginConfiguration() {
        AnalyticsPlugin analyticsPlugin = new AnalyticsPlugin();
        analyticsPlugin.setName("Session player");
        analyticsPlugin.setDefaultOperation("/session");
        analyticsPlugin.setDescription("Very sophisticated session player");
        return analyticsPlugin;
    }
}
