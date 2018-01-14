package cz.upce.webalyt.plugin.clickrecorder;

import cz.upce.webalyt.analytics.sessionplayertest.entity.Click;
import cz.upce.webalyt.analytics.sessionplayertest.repository.ClickRepository;
import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebalytPluginController extends SimpleMessageProcessor<Click> {

    @Autowired
    private ClickRepository clickRepository;

    @Override
    protected Class<Click> getEntity() {
        return Click.class;
    }

    @Override
    protected void processMessage(Click object) {
        clickRepository.save(object);
    }
}
