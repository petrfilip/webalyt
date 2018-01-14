package cz.upce.webalyt.analytics.sessionplayertest.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
public class RecordedDom extends WebalytEntity {

    @Column(columnDefinition = "TEXT")
    private String mutation;


}
