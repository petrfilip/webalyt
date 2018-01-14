package cz.upce.webalyt.analytics.sessionplayertest.repository;


import cz.upce.webalyt.analytics.sessionplayertest.entity.PageView;
import cz.upce.webalyt.plugin.core.WebalytBaseRepository;

import java.util.List;

public interface PageViewRepository extends WebalytBaseRepository<PageView> {



    List<PageView> findByWebsiteIdAndSessionId(String websiteId, String sessionId);
}
