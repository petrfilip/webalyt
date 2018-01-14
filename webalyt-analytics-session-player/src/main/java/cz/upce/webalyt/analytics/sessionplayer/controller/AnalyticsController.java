package cz.upce.webalyt.analytics.sessionplayer.controller;

import cz.upce.webalyt.analytics.sessionplayer.service.SessionCollectorService;
import cz.upce.webalyt.analytics.sessionplayertest.entity.PageView;
import cz.upce.webalyt.analytics.sessionplayertest.repository.PageViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/session-player")
public class AnalyticsController {


    @Autowired
    private SessionCollectorService sessionCollectorService;

    @Autowired
    private PageViewRepository pageViewRepository;

    @GetMapping("")
    public String welcome(Model model) {
        Iterable<PageView> all = pageViewRepository.findAll();
        model.addAttribute("sessionIds", all);
        return "session-list";
    }

    @GetMapping("/{sessionId}")
    public String sessionPlayer(@PathVariable String sessionId, Map<String, Object> model) {
        List<PageView> pageViews = pageViewRepository.findByWebsiteIdAndSessionId("100", sessionId);
        model.put("pageviews",pageViews);
        model.put("sessionId", sessionId);
        return "session-player";
    }
}
