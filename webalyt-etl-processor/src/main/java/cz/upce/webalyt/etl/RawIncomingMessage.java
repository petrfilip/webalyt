package cz.upce.webalyt.etl;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table
@Data
@Entity
public class RawIncomingMessage {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date timestamp;

    @Column(columnDefinition = "TEXT")
    private String message;

}
