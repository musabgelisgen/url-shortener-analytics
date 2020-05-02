package analytics.repositories;

import analytics.models.OsUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsRepository extends JpaRepository<OsUsage, Integer> {
    List<OsUsage> findOsUsageByDay(Long day);
}
