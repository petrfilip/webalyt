package cz.upce.webalyt.plugin.web.controller;

import cz.upce.webalyt.plugin.web.entity.AnalyticsPlugin;
import cz.upce.webalyt.plugin.web.service.AnalyticsPluginCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {

    @Autowired
    private AnalyticsPluginCollectorService analyticsPluginCollectorService;

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        List<AnalyticsPlugin> plugins = analyticsPluginCollectorService.collectAnalyticsPlugin();
        model.put("availablePlugins", plugins);
        System.out.println(plugins);
        model.put("time", new Date());
        model.put("message", "hello");
        return "welcome";
    }

}