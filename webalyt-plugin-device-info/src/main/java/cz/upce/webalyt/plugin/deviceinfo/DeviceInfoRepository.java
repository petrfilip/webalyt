package cz.upce.webalyt.plugin.deviceinfo;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DeviceInfoRepository extends CrudRepository<DeviceInfo, UUID> {
}
