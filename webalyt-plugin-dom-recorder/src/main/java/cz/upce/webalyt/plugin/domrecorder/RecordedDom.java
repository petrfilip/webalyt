package cz.upce.webalyt.plugin.domrecorder;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;
import org.springframework.data.cassandra.mapping.Table;

@Data
@Table
public class RecordedDom extends WebalytEntity {

    private String fullUrl;
    private String pathName;
    private String hash;
    private String search;
}
