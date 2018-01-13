package cz.upce.webalyt.plugin.urlrecorder.controller;

import cz.upce.webalyt.plugin.urlrecorder.entity.PageView;
import cz.upce.webalyt.plugin.urlrecorder.repository.PageViewRepository;
import cz.upce.webalyt.plugin.urlrecorder.service.SessionCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class AnalyticsController {


    @Autowired
    private SessionCollectorService sessionCollectorService;

    @Autowired
    private PageViewRepository pageViewRepository;

    @GetMapping("/")
    public String welcome(Model model) {
        Iterable<PageView> all = pageViewRepository.findAll();
        model.addAttribute("sessionIds", all);
        return "session-list";
    }

    @GetMapping("/session-player/{sessionId}")
    public String sessionPlayer(@PathVariable String sessionId, Map<String, Object> model) {
        List<PageView> pageViews = pageViewRepository.findByWebsiteIdAndSessionId("100", sessionId);
        model.put("pageviews",pageViews);
        model.put("sessionId", sessionId);
        return "session-player";
    }
}
