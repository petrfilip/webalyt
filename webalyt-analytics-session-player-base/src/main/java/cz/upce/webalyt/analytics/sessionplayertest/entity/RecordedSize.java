package cz.upce.webalyt.analytics.sessionplayertest.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
public class RecordedSize extends WebalytEntity {

    private String orientation;
    private Integer width;
    private Integer height;
    private Double zoom;


}
