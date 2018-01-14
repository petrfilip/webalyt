package cz.upce.webalyt.analytics.sessionplayertest.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table
@Entity
public class CollectedCss extends WebalytEntity {

    private String cssName;

//    @Lob //todo - uložit css
//    private byte[] css;

    private String compressionAlg;

}
