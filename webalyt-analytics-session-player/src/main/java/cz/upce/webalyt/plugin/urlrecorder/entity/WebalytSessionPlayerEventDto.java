package cz.upce.webalyt.plugin.urlrecorder.entity;

import cz.upce.webalyt.plugin.core.WebalytEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class WebalytSessionPlayerEventDto implements Serializable {

    private String eventType;

    private WebalytEntity eventData;

    public WebalytSessionPlayerEventDto() {
    }

    public WebalytSessionPlayerEventDto(String eventType, WebalytEntity eventData) {
        this.eventType = eventType;
        this.eventData = eventData;
    }
}
