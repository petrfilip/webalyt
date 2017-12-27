package cz.upce.webalyt.etl;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RawIncomingMessageRepository extends CrudRepository<RawIncomingMessage, UUID> {

}
