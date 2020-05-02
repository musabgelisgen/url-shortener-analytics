package analytics.services;

import analytics.models.BrowserUsage;
import analytics.models.DeviceUsage;
import analytics.models.OsUsage;
import analytics.repositories.BrowserRepository;
import analytics.repositories.DeviceRepository;
import analytics.repositories.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticServiceImpl implements AnalyticService {

    @Autowired
    private BrowserRepository browserRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private OsRepository osRepository;

    @Override
    public List<BrowserUsage> getBrowserUsageByDay(Long day) {
        return browserRepository.findBrowserUsageByDay(day);
    }

    @Override
    public List<DeviceUsage> getDeviceUsageByDay(Long day) {
        return deviceRepository.findDeviceUsageByDay(day);
    }

    @Override
    public List<OsUsage> getOsUsageByDay(Long day) {
        return osRepository.findOsUsageByDay(day);
    }

    @Override
    public List<BrowserUsage> getBrowserUsageBetweenDays(Long startDate, Long endDate) {
        List<BrowserUsage> startDateUsages = browserRepository.findBrowserUsageByDay(startDate);
        List<BrowserUsage> endDateUsage = browserRepository.findBrowserUsageByDay(endDate);

        for (BrowserUsage first : startDateUsages) {
            for (BrowserUsage second : endDateUsage) {
                if (first.isSameType(second)) {
                    second.setVisitCount(second.getVisitCount() - first.getVisitCount());
                }
            }
        }

        return endDateUsage;
    }

    @Override
    public List<DeviceUsage> getDeviceUsageBetweenDays(Long startDate, Long endDate) {
        List<DeviceUsage> startDateUsages = deviceRepository.findDeviceUsageByDay(startDate);
        List<DeviceUsage> endDateUsage = deviceRepository.findDeviceUsageByDay(endDate);

        for (DeviceUsage first : startDateUsages) {
            for (DeviceUsage second : endDateUsage) {
                if (first.isSameType(second)) {
                    second.setVisitCount(second.getVisitCount() - first.getVisitCount());
                }
            }
        }

        return endDateUsage;
    }

    @Override
    public List<OsUsage> getOsUsageBetweenDays(Long startDate, Long endDate) {
        List<OsUsage> startDateUsages = osRepository.findOsUsageByDay(startDate);
        List<OsUsage> endDateUsage = osRepository.findOsUsageByDay(endDate);

        for (OsUsage first : startDateUsages) {
            for (OsUsage second : endDateUsage) {
                if (first.isSameType(second)) {
                    second.setVisitCount(second.getVisitCount() - first.getVisitCount());
                }
            }
        }

        return endDateUsage;
    }

    @Override
    public void saveBrowserUsages(List<BrowserUsage> browserUsages) {
        browserRepository.saveAll(browserUsages);
    }

    @Override
    public void saveDeviceUsages(List<DeviceUsage> deviceUsages) {
        deviceRepository.saveAll(deviceUsages);
    }

    @Override
    public void saveOsUsages(List<OsUsage> osUsages) {
        osRepository.saveAll(osUsages);
    }
}
