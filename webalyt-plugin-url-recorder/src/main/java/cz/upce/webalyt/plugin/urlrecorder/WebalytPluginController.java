package cz.upce.webalyt.plugin.urlrecorder;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebalytPluginController extends SimpleMessageProcessor<RecordedUrl> {

    @Autowired
    private RecordedUrlRepository recordedUrlRepository;

    @Override
    protected Class<RecordedUrl> getEntity() {
        return RecordedUrl.class;
    }

    @Override
    protected void processMessage(RecordedUrl object) {
        recordedUrlRepository.save(object);
    }
}
