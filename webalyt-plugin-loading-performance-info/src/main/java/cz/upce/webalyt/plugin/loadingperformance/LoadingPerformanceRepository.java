package cz.upce.webalyt.plugin.loadingperformance;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LoadingPerformanceRepository extends CrudRepository<LoadingPerformance, UUID> {
}
