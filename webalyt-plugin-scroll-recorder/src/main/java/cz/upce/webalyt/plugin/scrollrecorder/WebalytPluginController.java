package cz.upce.webalyt.plugin.scrollrecorder;

import cz.upce.webalyt.analytics.sessionplayertest.entity.ScrollTracker;
import cz.upce.webalyt.analytics.sessionplayertest.repository.ScrollTrackerRepository;
import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebalytPluginController extends SimpleMessageProcessor<ScrollTracker> {

    @Autowired
    private ScrollTrackerRepository scrollTrackerRepository;

    @Override
    protected Class<ScrollTracker> getEntity() {
        return ScrollTracker.class;
    }

    @Override
    protected void processMessage(ScrollTracker object) {
        scrollTrackerRepository.save(object);
    }
}
