package cz.upce.webalyt.plugin.loadingperformance;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
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
        //object.setId(UUID.randomUUID());
        //loadingPerformanceRepository.save(object);
        System.out.println(object);
    }
}