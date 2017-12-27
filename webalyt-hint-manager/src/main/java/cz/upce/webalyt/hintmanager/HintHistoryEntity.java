package cz.upce.webalyt.hintmanager;


import lombok.Data;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Data
public class HintHistoryEntity {

    private UUID id;

    private String pluginId;

    private Date timestamp;

    private Map<String, Object> hintContent;

}
