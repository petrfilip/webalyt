package cz.upce.webalyt.plugin.web.controller;

import cz.upce.webalyt.plugin.web.entity.AnalyticsPlugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    @GetMapping("/index")
    public AnalyticsPlugin getPluginConfiguration() {
        AnalyticsPlugin analyticsPlugin = new AnalyticsPlugin();
        analyticsPlugin.setName("Session player");
        analyticsPlugin.setDefaultOperation("/session");
        analyticsPlugin.setDescription("Very sophisticated session player");
        return analyticsPlugin;
    }
}
