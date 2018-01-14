package cz.upce.webalyt.analytics.sessionplayertest.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Click extends WebalytEntity {

    private String domPath;

    private Integer x;

    private Integer y;

}
