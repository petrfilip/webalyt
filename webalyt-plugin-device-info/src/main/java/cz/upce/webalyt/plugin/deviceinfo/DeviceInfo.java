package cz.upce.webalyt.plugin.deviceinfo;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;
import org.springframework.data.cassandra.mapping.Table;

@Data
@Table
public class DeviceInfo extends WebalytEntity {

    private Integer sizeScreenW;
    private Integer sizeScreenH;
    private Integer sizeDocW;
    private Integer sizeDocH;
    private Integer sizeClientW;
    private Integer sizeClientH;
    private Integer sizeInW;
    private Integer sizeInH;
    private Integer sizeAvailW;
    private Integer sizeAvailH;
    private Double zoom;
    private Integer scrColorDepth;
    private Integer scrPixelDepth;

}
