package cz.upce.webalyt.analytics.web.controller;

import cz.upce.webalyt.analytics.web.entity.AnalyticsPlugin;
import cz.upce.webalyt.analytics.web.service.AnalyticsPluginCollectorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class MainController {

    private Map<String, AnalyticsPlugin> routeTable = new HashMap<>();


    @Autowired
    private AnalyticsPluginCollectorService analyticsPluginCollectorService;

    @GetMapping("/**")
    public String welcome(HttpServletRequest request, Map<String, Object> model) {
        List<AnalyticsPlugin> plugins = analyticsPluginCollectorService.collectAnalyticsPlugin();
        for (AnalyticsPlugin plugin : plugins) {
            routeTable.put(StringUtils.remove(plugin.getDefaultOperation(), '/'), plugin);
        }
        model.put("availablePlugins", plugins);
        return "index";
    }

    @PostMapping("/**")
    public String router(HttpServletRequest request, Map<String, Object> model) {

        String[] requestURI = request.getRequestURI().split("/");
        AnalyticsPlugin targetModule = routeTable.get(requestURI[1]);
        StringBuilder originUrl = new StringBuilder();
        for (String s : requestURI) {
            originUrl.append("/");
            originUrl.append(s);
        }

        model.put("content", targetModule.getServerAddress() + originUrl.toString());
        return "content";
    }


}