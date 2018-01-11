package cz.upce.webalyt.plugin.urlrecorder.controller;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import cz.upce.webalyt.plugin.urlrecorder.repository.DeviceInfoRepository;
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
    private DeviceInfoRepository deviceInfoRepository;

    @Autowired
    private SessionCollectorService sessionCollectorService;

    @GetMapping("/")
    public String welcome(Model model) {
//        List<WebalytPrimaryKey> distinctDeviceInfo = deviceInfoRepository.findDistinctDeviceId().stream().map(WebalytEntity::getWebalytPrimaryKey).collect(Collectors.toList());
        List<? extends WebalytEntity> all = deviceInfoRepository.findAll();
        model.addAttribute("deviceIds", all);
        return "session-list";
    }

    @GetMapping("/session-player/{id}")
    public String sessionPlayer(@PathVariable String id, Map<String, Object> model) {
        model.put("sessionId", id);
        return "session-player";
    }
}
