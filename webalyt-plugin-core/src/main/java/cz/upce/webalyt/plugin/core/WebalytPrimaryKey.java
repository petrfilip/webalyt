package cz.upce.webalyt.plugin.core;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public class WebalytPrimaryKey implements Serializable {


}
