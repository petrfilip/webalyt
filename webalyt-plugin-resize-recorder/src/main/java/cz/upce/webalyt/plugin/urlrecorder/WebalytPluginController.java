package cz.upce.webalyt.plugin.urlrecorder;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import cz.upce.webalyt.plugin.urlrecorder.entity.RecordedSize;
import cz.upce.webalyt.plugin.urlrecorder.repository.RecordedSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebalytPluginController extends SimpleMessageProcessor<RecordedSize> {

    @Autowired
    private RecordedSizeRepository recordedSizeRepository;

    @Override
    protected Class<RecordedSize> getEntity() {
        return RecordedSize.class;
    }

    @Override
    protected void processMessage(RecordedSize object) {
        recordedSizeRepository.save(object);
    }
}
