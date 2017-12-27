package cz.upce.webalyt.plugin.core;

import lombok.Data;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;

@Data
@PrimaryKeyClass
public class WebalytPrimaryKey implements Serializable {


    @PrimaryKeyColumn( ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String deviceId;

    @PrimaryKeyColumn( ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date timestamp;
}
