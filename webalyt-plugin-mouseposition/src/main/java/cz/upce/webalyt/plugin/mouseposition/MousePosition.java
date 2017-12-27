package cz.upce.webalyt.plugin.mouseposition;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;
import org.springframework.data.cassandra.mapping.Table;

@Data
@Table
public class MousePosition extends WebalytEntity {

    private Integer x;

    private Integer y;

}
