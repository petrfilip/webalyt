package cz.upce.webalyt.plugin.deviceinfo;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoController extends SimpleMessageProcessor<DeviceInfo> {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Override
    protected Class<DeviceInfo> getEntity() {
        return DeviceInfo.class;
    }

    @Override
    protected void processMessage(DeviceInfo object) {
        deviceInfoRepository.save(object);
    }
}
