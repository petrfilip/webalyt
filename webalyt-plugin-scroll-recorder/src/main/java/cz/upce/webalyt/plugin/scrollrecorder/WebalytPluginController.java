package cz.upce.webalyt.plugin.scrollrecorder;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebalytPluginController extends SimpleMessageProcessor<RecordedScroll> {

    @Autowired
    private RecordedScrollRepository recordedScrollRepository;

    @Override
    protected Class<RecordedScroll> getEntity() {
        return RecordedScroll.class;
    }

    @Override
    protected void processMessage(RecordedScroll object) {
        recordedScrollRepository.save(object);
    }
}
