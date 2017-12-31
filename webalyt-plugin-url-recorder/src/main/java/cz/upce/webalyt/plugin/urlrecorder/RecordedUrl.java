package cz.upce.webalyt.plugin.urlrecorder;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;
import org.springframework.data.cassandra.mapping.Table;

@Data
@Table
public class RecordedUrl extends WebalytEntity {

    private String fullUrl;
    private String pathName;
    private String hash;
    private String search;
}
