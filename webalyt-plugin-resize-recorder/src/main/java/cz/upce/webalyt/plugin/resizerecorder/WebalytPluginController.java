package cz.upce.webalyt.plugin.resizerecorder;

import cz.upce.webalyt.analytics.sessionplayertest.entity.RecordedSize;
import cz.upce.webalyt.analytics.sessionplayertest.repository.RecordedSizeRepository;
import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
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
