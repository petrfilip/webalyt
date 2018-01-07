package cz.upce.webalyt.plugin.urlrecorder.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
public class LoadingPerformance extends WebalytEntity {

    private Double userTime;

    private Double dns;

    private Double connection;

    private Double requestTime;

    private Double fetchTime;

}

