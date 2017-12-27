package cz.upce.webalyt.plugin.mouseposition;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MousePositionRepository extends CrudRepository<MousePosition, UUID> {

}
