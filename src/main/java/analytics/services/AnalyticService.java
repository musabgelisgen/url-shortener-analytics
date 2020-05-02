package analytics.services;

import analytics.models.BrowserUsage;
import analytics.models.DeviceUsage;
import analytics.models.OsUsage;

import java.util.List;

public interface AnalyticService {

    List<BrowserUsage> getBrowserUsageByDay(Long day);
    List<DeviceUsage> getDeviceUsageByDay(Long day);
    List<OsUsage> getOsUsageByDay(Long day);

    List<BrowserUsage> getBrowserUsageBetweenDays(Long startDate, Long endDate);
    List<DeviceUsage> getDeviceUsageBetweenDays(Long startDate, Long endDate);
    List<OsUsage> getOsUsageBetweenDays(Long startDate, Long endDate);

    void saveBrowserUsages(List<BrowserUsage> browserUsages);
    void saveDeviceUsages(List<DeviceUsage> deviceUsages);
    void saveOsUsages(List<OsUsage> osUsages);
}