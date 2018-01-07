package cz.upce.webalyt.plugin.core;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public class WebalytEntity implements Comparable<WebalytEntity> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private UUID uuid;

    private String pageViewId;

    private Date timestamp;

    @Override
    public int compareTo(WebalytEntity o) {
        if (o.getTimestamp().after(o.getTimestamp()))
            return -1;
        else
            return 1;
    }
}