package cz.upce.webalyt.plugin.domrecorder;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecordedDomRepository extends CrudRepository<RecordedDom, UUID> {
}
