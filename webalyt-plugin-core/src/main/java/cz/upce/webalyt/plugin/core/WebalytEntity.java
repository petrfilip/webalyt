package cz.upce.webalyt.plugin.core;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class WebalytEntity implements Comparable<WebalytEntity>, Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private String pageViewId;

    @Column(columnDefinition = "DATETIME(3)")
    private Date timestamp;

    @Override
    public int compareTo(WebalytEntity o) {
        if (o.getTimestamp().after(o.getTimestamp()))
            return -1;
        else
            return 1;
    }
}