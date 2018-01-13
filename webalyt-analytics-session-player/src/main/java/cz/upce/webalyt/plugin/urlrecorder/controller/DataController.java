package cz.upce.webalyt.plugin.urlrecorder.controller;

import cz.upce.webalyt.plugin.urlrecorder.entity.WebalytSessionPlayerEventDto;
import cz.upce.webalyt.plugin.urlrecorder.service.SessionCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DataController {

    @Autowired
    private SessionCollectorService sessionCollectorService;

    @CrossOrigin
    @GetMapping("/pageview-activities/{id}")
    public List<WebalytSessionPlayerEventDto> getSessionActivityById(@PathVariable String id) {
        return sessionCollectorService.getByPageViewId(id)
                .stream()
                .map(item -> new WebalytSessionPlayerEventDto(item.getClass().getSimpleName(), item))
                .collect(Collectors.toList());
    }

}
