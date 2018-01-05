package cz.upce.webalyt.plugin.urlrecorder;

import cz.upce.webalyt.plugin.core.SimpleMessageProcessor;
import cz.upce.webalyt.plugin.urlrecorder.entity.DeviceInfo;
import cz.upce.webalyt.plugin.urlrecorder.repository.DeviceInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
