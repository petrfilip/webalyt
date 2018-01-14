package cz.upce.webalyt.analytics.sessionplayertest.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
public class PerformanceTiming extends WebalytEntity {
    private long connectEnd;
    private long connectStart;
    private long domComplete;
    private long domContentLoadedEventEnd;
    private long domContentLoadedEventStart;
    private long domInteractive;
    private long domLoading;
    private long domainLookupEnd;
    private long domainLookupStart;
    private long fetchStart;
    private long loadEventEnd;
    private long loadEventStart;
    private long navigationStart;
    private long redirectEnd;
    private long redirectStart;
    private long requestStart;
    private long responseEnd;
    private long responseStart;
    private long secureConnectionStart;
    private long unloadEventEnd;
    private long unloadEventStart;
}
