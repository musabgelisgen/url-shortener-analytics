package analytics.repositories;

import analytics.models.DeviceUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceUsage, Integer> {
    List<DeviceUsage> findDeviceUsageByDay(Long day);
}
