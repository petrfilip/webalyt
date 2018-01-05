package cz.upce.webalyt.plugin.web.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnalyticsPlugin implements Serializable{

    private String name;

    private String description;

    private String defaultOperation;

    private String serverAddress;
}
