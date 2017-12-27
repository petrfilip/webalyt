package cz.upce.webalyt.plugin.core;

import lombok.Data;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class WebalytEntity {

    @PrimaryKey
    private WebalytPrimaryKey webalytPrimaryKey = new WebalytPrimaryKey();

    public String getDeviceId() {
        return webalytPrimaryKey.getDeviceId();
    }

    public void setDeviceId(String deviceId) {
        webalytPrimaryKey.setDeviceId(deviceId);
    }

    public Date getTimestamp() {
        return webalytPrimaryKey.getTimestamp();
    }

    public void setTimestamp(Date timestamp) {
        webalytPrimaryKey.setTimestamp(timestamp);
    }

}