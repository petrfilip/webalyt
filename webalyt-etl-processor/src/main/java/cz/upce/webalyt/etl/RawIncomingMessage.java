package cz.upce.webalyt.etl;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table
@Data
@Entity
public class RawIncomingMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private UUID id;

    private Date timestamp;

    private String message;

}
