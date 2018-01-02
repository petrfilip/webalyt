package cz.upce.webalyt.plugin.resizerecorder;


import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecordedSizeRepository extends CrudRepository<RecordedSize, UUID> {
}
