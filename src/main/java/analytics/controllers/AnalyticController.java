package analytics.controllers;

import analytics.models.BrowserUsage;
import analytics.models.DeviceUsage;
import analytics.models.OsUsage;
import analytics.services.AnalyticService;
import analytics.services.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/")
@Controller
public class AnalyticController {

    @Autowired
    private AnalyticService analyticService;

    @Autowired
    private MasterService masterService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateAnalysis(@RequestBody Map<String, Object> body) {

        if (!body.containsKey("date") || body.get("date") == null) {
            return ResponseEntity.badRequest().body("Date should be given.");
        }

        if (!(body.get("date") instanceof Integer)) {
            return ResponseEntity.badRequest().body("Wrong time format.");
        }

        Long date = Long.valueOf((Integer) body.get("date"));
        Map<String, Long> counts = masterService.getLinkCounts();

        List<BrowserUsage> browserUsages = new ArrayList<>();
        List<DeviceUsage> deviceUsages = new ArrayList<>();
        List<OsUsage> osUsages = new ArrayList<>();

        browserUsages.add(new BrowserUsage("Chrome", counts.get("chromeCount"), date));
        browserUsages.add(new BrowserUsage("Firefox", counts.get("firefoxCount"), date));
        browserUsages.add(new BrowserUsage("IE", counts.get("ieCount"), date));
        browserUsages.add(new BrowserUsage("Safari", counts.get("safariCount"), date));
        browserUsages.add(new BrowserUsage("Other", counts.get("otherBrowserCount"), date));

        deviceUsages.add(new DeviceUsage("Computer", counts.get("computerCount"), date));
        deviceUsages.add(new DeviceUsage("Mobile", counts.get("mobileCount"), date));

        osUsages.add(new OsUsage("Android", counts.get("androidCount"), date));
        osUsages.add(new OsUsage("iOS", counts.get("iosCount"), date));
        osUsages.add(new OsUsage("Windows", counts.get("windowsCount"), date));
        osUsages.add(new OsUsage("OsX", counts.get("osxCount"), date));
        osUsages.add(new OsUsage("Linux", counts.get("linuxCount"), date));
        osUsages.add(new OsUsage("Other", counts.get("otherOsCount"), date));

        analyticService.saveBrowserUsages(browserUsages);
        analyticService.saveDeviceUsages(deviceUsages);
        analyticService.saveOsUsages(osUsages);

        Map<String, Object> result = new HashMap<>();
        result.put("BrowserUsages", browserUsages);
        result.put("DeviceUsages", deviceUsages);
        result.put("OsUsages", osUsages);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/count/browser")
    public ResponseEntity<?> getBrowserCountBetweenDays(@RequestParam Map<String, String> params) {

        if (!params.containsKey("startDate") || !params.containsKey("endDate") || params.get("startDate") == null || params.get("endDate") == null) {
            return ResponseEntity.badRequest().body("Wrong time format.");
        }

        Long startDate = null;
        Long endDate = null;

        try {
            startDate = Long.parseLong(params.get("startDate"));
            endDate = Long.parseLong(params.get("endDate"));
        }
        catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Wrong time format.");
        }

        return ResponseEntity.ok(analyticService.getBrowserUsageBetweenDays(startDate, endDate));
    }

    @GetMapping("/count/os")
    public ResponseEntity<?> getOsCountBetweenDays(@RequestParam Map<String, String> params) {

        if (!params.containsKey("startDate") || !params.containsKey("endDate") || params.get("startDate") == null || params.get("endDate") == null) {
            return ResponseEntity.badRequest().body("Wrong time format.");
        }

        Long startDate = null;
        Long endDate = null;

        try {
            startDate = Long.parseLong(params.get("startDate"));
            endDate = Long.parseLong(params.get("endDate"));
        }
        catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Wrong time format.");
        }

        return ResponseEntity.ok(analyticService.getOsUsageBetweenDays(startDate, endDate));
    }

    @GetMapping("/count/device")
    public ResponseEntity<?> getDeviceCountBetweenDays(@RequestParam Map<String, String> params) {

        if (!params.containsKey("startDate") || !params.containsKey("endDate") || params.get("startDate") == null || params.get("endDate") == null) {
            return ResponseEntity.badRequest().body("Wrong time format.");
        }

        Long startDate = null;
        Long endDate = null;

        try {
            startDate = Long.parseLong(params.get("startDate"));
            endDate = Long.parseLong(params.get("endDate"));
        }
        catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Wrong time format.");
        }

        return ResponseEntity.ok(analyticService.getDeviceUsageBetweenDays(startDate, endDate));
    }

}
