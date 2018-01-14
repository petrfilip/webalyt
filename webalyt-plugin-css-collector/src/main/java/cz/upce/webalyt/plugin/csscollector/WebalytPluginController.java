package cz.upce.webalyt.plugin.csscollector;

import cz.upce.webalyt.analytics.sessionplayertest.entity.CollectedCss;
import cz.upce.webalyt.analytics.sessionplayertest.repository.CollectedCssRepository;
import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebalytPluginController extends SimpleMessageProcessor<CollectedCss> {

    @Autowired
    private CollectedCssRepository collectedCssRepository;

    @Override
    protected Class<CollectedCss> getEntity() {
        return CollectedCss.class;
    }

    @Override
    protected void processMessage(CollectedCss object) {
        collectedCssRepository.save(object);
    }
}
