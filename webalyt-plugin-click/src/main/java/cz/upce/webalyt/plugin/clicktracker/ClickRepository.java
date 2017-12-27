package cz.upce.webalyt.plugin.clicktracker;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClickRepository extends CrudRepository<Click, UUID> {
}
