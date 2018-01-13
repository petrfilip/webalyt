package cz.upce.webalyt.plugin.urlrecorder.repository;


import cz.upce.webalyt.plugin.core.WebalytBaseRepository;
import cz.upce.webalyt.plugin.urlrecorder.entity.PageView;

import java.util.List;

public interface PageViewRepository extends WebalytBaseRepository<PageView> {



    List<PageView> findByWebsiteIdAndSessionId(String websiteId, String sessionId);
}
