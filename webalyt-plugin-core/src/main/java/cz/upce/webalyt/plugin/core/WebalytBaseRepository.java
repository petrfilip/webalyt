package cz.upce.webalyt.plugin.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NoRepositoryBean
public interface WebalytBaseRepository<T extends WebalytEntity> extends CrudRepository<T, Long> {

    List<T> findByPageViewId(@Param("pageviewid") String pageViewId);

}
