package cz.upce.webalyt.analytics.sessionplayertest.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
public class DeviceInfo extends WebalytEntity {

    private String deviceId;

    private Integer sizeScreenW;
    private Integer sizeScreenH;
    private Integer sizeDocW;
    private Integer sizeDocH;
    private Integer sizeClientW;
    private Integer sizeClientH;
    private Integer sizeInW;
    private Integer sizeInH;
    private Integer sizeAvailW;
    private Integer sizeAvailH;
    private Double zoom;
    private Integer scrColorDepth;
    private Integer scrPixelDepth;

    private String fullUserAgent;
    private String browser;
    private Integer browserMajorVersion;
    private String browserVersion;
    private String os;
    private String osVersion;
    private Boolean isCookieEnabled;
    private Boolean isMobile;

}
