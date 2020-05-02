package analytics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import analytics.models.BrowserUsage;

import java.util.List;

@Repository
public interface BrowserRepository extends JpaRepository<BrowserUsage, Integer> {
    List<BrowserUsage> findBrowserUsageByDay(Long day);
}
