package cz.upce.webalyt.plugin.web.service;

import cz.upce.webalyt.plugin.web.entity.AnalyticsPlugin;

import java.util.List;

public interface AnalyticsPluginCollectorService {

    List<AnalyticsPlugin> collectAnalyticsPlugin();
}
