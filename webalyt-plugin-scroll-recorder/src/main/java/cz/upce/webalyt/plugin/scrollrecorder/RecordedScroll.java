package cz.upce.webalyt.plugin.scrollrecorder;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table
public class RecordedScroll extends WebalytEntity {

    private Integer top;

    private Integer left;

}

