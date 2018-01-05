package cz.upce.webalyt.plugin.urlrecorder.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnalyticsPlugin implements Serializable { //todo - move to core package

    private String name;

    private String description;

    private String defaultOperation;

    private String serverAddress;
}
