package cz.upce.webalyt.plugin.urlrecorder;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import cz.upce.webalyt.plugin.urlrecorder.entity.Click;
import cz.upce.webalyt.plugin.urlrecorder.repository.ClickRepository;
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
