package cz.upce.webalyt.analytics.sessionplayertest.repository;

import cz.upce.webalyt.analytics.sessionplayertest.entity.DeviceInfo;
import cz.upce.webalyt.plugin.core.WebalytBaseRepository;

import java.util.List;

public interface DeviceInfoRepository extends WebalytBaseRepository<DeviceInfo> {

//    @Query("SELECT distinct deviceId, pageViewId FROM deviceinfo")
//    List<DeviceInfo> findDistinctDeviceId();

    @Override
    List<DeviceInfo> findAll();
}
