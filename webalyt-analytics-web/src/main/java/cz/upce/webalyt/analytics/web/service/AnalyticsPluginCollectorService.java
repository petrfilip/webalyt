package cz.upce.webalyt.analytics.web.service;

import cz.upce.webalyt.analytics.web.entity.AnalyticsPlugin;

import java.util.List;

public interface AnalyticsPluginCollectorService {

    List<AnalyticsPlugin> collectAnalyticsPlugin();
}
