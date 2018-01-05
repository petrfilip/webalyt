package cz.upce.webalyt.plugin.urlrecorder.controller;

import cz.upce.webalyt.plugin.urlrecorder.entity.Click;
import cz.upce.webalyt.plugin.urlrecorder.entity.DeviceInfo;
import cz.upce.webalyt.plugin.urlrecorder.repository.ClickRepository;
import cz.upce.webalyt.plugin.urlrecorder.repository.DeviceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class AnalyticsController {

    @Autowired
    private ClickRepository clickRepository;

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        Iterable<Click> all = clickRepository.findAll();
        Iterable<DeviceInfo> all1 = deviceInfoRepository.findAll();
        return "session-list";
    }

    @GetMapping("/session-player/{id}")
    public String sessionPlayer(@PathVariable String id, Map<String, Object> model) {
        return "session-player";
    }
}
