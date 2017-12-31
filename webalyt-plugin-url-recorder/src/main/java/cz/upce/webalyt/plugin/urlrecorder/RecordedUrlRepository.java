package cz.upce.webalyt.plugin.urlrecorder;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecordedUrlRepository extends CrudRepository<RecordedUrl, UUID> {
}
