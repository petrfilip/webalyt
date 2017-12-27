package cz.upce.webalyt.etl;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table
@Data
public class RawIncomingMessage {

    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private Date timestamp;

    private String message;

}
