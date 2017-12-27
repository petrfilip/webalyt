package cz.upce.webalyt.plugin.loadingperformance;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table
public class LoadingPerformance extends WebalytEntity {

    private Double userTime;

    private Double dns;

    private Double connection;

    private Double requestTime;

    private Double fetchTime;

}

