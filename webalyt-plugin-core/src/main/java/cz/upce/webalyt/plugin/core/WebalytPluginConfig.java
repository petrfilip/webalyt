package cz.upce.webalyt.plugin.core;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

//import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@Data
//@ConfigurationProperties("webalyt")
public class WebalytPluginConfig {

    @NotNull
    private String serviceType;

    @NotNull
    private String objectId;

    @NotNull
    private String kafkaTopic;


}
