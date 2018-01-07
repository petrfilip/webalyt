package cz.upce.webalyt.plugin.urlrecorder;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import cz.upce.webalyt.plugin.urlrecorder.entity.ScrollTracker;
import cz.upce.webalyt.plugin.urlrecorder.repository.ScrollTrackerRepository;
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
