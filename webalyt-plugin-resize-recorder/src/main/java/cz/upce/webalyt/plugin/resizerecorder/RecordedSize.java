package cz.upce.webalyt.plugin.resizerecorder;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;
import org.springframework.data.cassandra.mapping.Table;

@Data
@Table
public class RecordedSize extends WebalytEntity {

    private String orientation;
    private Integer width;
    private Integer height;
    private Double zoom;


}
