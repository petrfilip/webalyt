package cz.upce.webalyt.plugin.urlrecorder.repository;

import cz.upce.webalyt.plugin.core.WebalytBaseRepository;
import cz.upce.webalyt.plugin.urlrecorder.entity.DeviceInfo;

import java.util.List;

public interface DeviceInfoRepository extends WebalytBaseRepository<DeviceInfo> {

//    @Query("SELECT distinct deviceId, pageViewId FROM deviceinfo")
//    List<DeviceInfo> findDistinctDeviceId();

    @Override
    List<DeviceInfo> findAll();
}
