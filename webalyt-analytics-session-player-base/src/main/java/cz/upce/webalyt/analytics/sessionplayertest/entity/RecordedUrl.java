package cz.upce.webalyt.analytics.sessionplayertest.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
public class RecordedUrl extends WebalytEntity {

    private String fullUrl;
    private String pathName;
    private String hash;
    private String search;
}
