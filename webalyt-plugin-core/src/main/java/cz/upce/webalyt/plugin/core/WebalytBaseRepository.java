package cz.upce.webalyt.plugin.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface WebalytBaseRepository<T extends WebalytEntity> extends CrudRepository<T, UUID> {

    List<T> findByPageViewId(@Param("pageviewid") String pageViewId);

}
