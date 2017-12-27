package cz.upce.webalyt.servicemanager;

import lombok.Data;

@Data
public class WebalytPluginConfigModel {

    private String serviceType;

    private String objectId;

    private String kafkaTopic;

}
