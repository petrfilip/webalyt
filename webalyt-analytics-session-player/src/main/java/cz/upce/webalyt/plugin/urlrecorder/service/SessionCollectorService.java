package cz.upce.webalyt.plugin.urlrecorder.service;

import cz.upce.webalyt.plugin.core.WebalytEntity;

import java.util.List;

public interface SessionCollectorService {

    List<WebalytEntity> getByPageViewId(String pageViewId);
}
