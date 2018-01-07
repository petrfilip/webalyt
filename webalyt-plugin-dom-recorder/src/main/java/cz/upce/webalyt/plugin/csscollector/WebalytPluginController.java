package cz.upce.webalyt.plugin.csscollector;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import cz.upce.webalyt.plugin.urlrecorder.entity.RecordedDom;
import cz.upce.webalyt.plugin.urlrecorder.repository.RecordedDomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebalytPluginController extends SimpleMessageProcessor<RecordedDom> {

    @Autowired
    private RecordedDomRepository recordedDomRepository;

    @Override
    protected Class<RecordedDom> getEntity() {
        return RecordedDom.class;
    }

    @Override
    protected void processMessage(RecordedDom object) {
        RecordedDom save = recordedDomRepository.save(object);
        System.out.println(save);
    }
}