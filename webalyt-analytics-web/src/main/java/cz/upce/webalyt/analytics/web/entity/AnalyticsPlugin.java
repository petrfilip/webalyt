package cz.upce.webalyt.analytics.web.entity;

import lombok.Data;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.io.Serializable;
import java.util.Set;

@Data
public class AnalyticsPlugin implements Serializable{

    private String name;

    private String description;

    private String defaultOperation;

    private String serverAddress;

    private Set<RequestMappingInfo> requestMappingInfos;

}
