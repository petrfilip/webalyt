package cz.upce.webalyt.plugin.scrollrecorder;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecordedScrollRepository extends CrudRepository<RecordedScroll, UUID> {
}
