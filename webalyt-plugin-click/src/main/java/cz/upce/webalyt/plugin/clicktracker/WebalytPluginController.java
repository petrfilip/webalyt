package cz.upce.webalyt.plugin.clicktracker;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebalytPluginController extends SimpleMessageProcessor<Click> {

    @Autowired
    private ClickRepository clickRepository;

    @Override
    protected Class<Click> getEntity() {
        return Click.class;
    }

    @Override
    protected void processMessage(Click object) {
        //object.setId(UUID.randomUUID());
        clickRepository.save(object);
    }
}
