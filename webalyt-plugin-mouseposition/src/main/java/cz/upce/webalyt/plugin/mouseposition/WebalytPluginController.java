package cz.upce.webalyt.plugin.mouseposition;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class WebalytPluginController extends SimpleMessageProcessor<MousePosition> {

    private Cache<String, Object> cache;

    @Autowired
    private MousePositionRepository mousePositionRepository;

    @Override
    protected Class<MousePosition> getEntity() {
        return MousePosition.class;
    }

    public WebalytPluginController() {
        super();
        cache = CacheBuilder
                .newBuilder()
                .expireAfterAccess(24, TimeUnit.HOURS)
                .build();

    }

    @Override
    protected void processMessage(MousePosition object) {
        //object.setId(UUID.randomUUID());
        mousePositionRepository.save(object);
//        hint(object);
    }


    protected void hint(MousePosition object) {
        Integer ifPresent = (Integer) cache.getIfPresent(object.getDeviceId());
        if (ifPresent == null) {
            cache.put(object.getDeviceId(), 0);
            ifPresent = 0;
        } else {
            ifPresent++;
            cache.put(object.getDeviceId(), ifPresent);
        }

        if (ifPresent > 100) {
            System.out.println("HINTED");
            cache.put(object.getDeviceId(), 0);
        }

    }


}
