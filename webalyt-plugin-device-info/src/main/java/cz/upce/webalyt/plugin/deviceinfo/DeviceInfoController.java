package cz.upce.webalyt.plugin.deviceinfo;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoController extends SimpleMessageProcessor<DeviceInfo> {
    @Override
    protected Class<DeviceInfo> getEntity() {
        return DeviceInfo.class;
    }

    @Override
    protected void processMessage(DeviceInfo object) {
        System.out.println(object);
    }
}
