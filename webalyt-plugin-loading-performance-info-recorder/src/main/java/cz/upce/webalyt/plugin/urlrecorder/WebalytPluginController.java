package cz.upce.webalyt.plugin.urlrecorder;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import cz.upce.webalyt.plugin.urlrecorder.entity.LoadingPerformance;
import cz.upce.webalyt.plugin.urlrecorder.repository.LoadingPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebalytPluginController extends SimpleMessageProcessor<LoadingPerformance> {

    @Autowired
    private LoadingPerformanceRepository loadingPerformanceRepository;

    @Override
    protected Class<LoadingPerformance> getEntity() {
        return LoadingPerformance.class;
    }

    @Override
    protected void processMessage(LoadingPerformance object) {
        loadingPerformanceRepository.save(object);
    }
}
