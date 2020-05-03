package analytics.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Lazy(false)
public class AnalyticsScheduler {

    @Value("${server.port}")
    private String port;

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleAggregatorService(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:" + port + "/generate";

        Date date = new Date();
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);

        Map<String, Object> map = new HashMap<>();
        map.put("date", date.getTime()/1000);
        restTemplate.postForEntity(url, map, Object.class);
    }
}
