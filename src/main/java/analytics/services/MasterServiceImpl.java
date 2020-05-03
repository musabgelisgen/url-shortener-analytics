package analytics.services;

import analytics.repositories.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private MasterRepository masterRepository;

    @Override
    public Map<String, Long> getLinkCounts() {
        List<Map<String, Long>> counts = masterRepository.getLinkCounts();

        Long chromeCount = 0L;
        Long firefoxCount = 0L;
        Long ieCount = 0L;
        Long safariCount = 0L;
        Long otherBrowserCount = 0L;

        Long windowsCount = 0L;
        Long osxCount = 0L;
        Long linuxCount = 0L;
        Long androidCount = 0L;
        Long iosCount = 0L;
        Long otherOsCount = 0L;

        Long computerCount;
        Long mobileCount;

        for (Map<String, Long> map : counts) {
            chromeCount += map.get("chrome");
            firefoxCount += map.get("firefox");
            ieCount += map.get("ie");
            safariCount += map.get("safari");
            otherBrowserCount += map.get("otherBrowser");
            windowsCount += map.get("windows");
            linuxCount += map.get("linux");
            androidCount += map.get("android");
            iosCount += map.get("ios");
            otherOsCount += map.get("otherOs");
            osxCount += map.get("osx");
        }

        computerCount = windowsCount + osxCount + linuxCount + otherOsCount;
        mobileCount = androidCount + iosCount;

        Map<String, Long> result = new HashMap<>();

        result.put("chromeCount", chromeCount);
        result.put("firefoxCount", firefoxCount);
        result.put("ieCount", ieCount);
        result.put("safariCount", safariCount);
        result.put("otherBrowserCount", otherBrowserCount);
        result.put("windowsCount", windowsCount);
        result.put("linuxCount", linuxCount);
        result.put("osxCount", osxCount);
        result.put("androidCount", androidCount);
        result.put("iosCount", iosCount);
        result.put("otherOsCount", otherOsCount);
        result.put("computerCount", computerCount);
        result.put("mobileCount", mobileCount);

        return result;
    }
}
