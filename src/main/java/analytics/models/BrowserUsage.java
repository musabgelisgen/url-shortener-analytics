package analytics.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "browser_usage")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrowserUsage implements Serializable {

    // https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
    private static final long serialVersionUID = 2095302179301203988L;

    public BrowserUsage() {
    }

    public BrowserUsage(String browserName, Long visitCount, Long day) {
        this.browserName = browserName;
        this.visitCount = visitCount;
        this.day = day;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usage_id")
    private Integer usageId;

    @Column(name = "browser_name")
    private String browserName;

    @Column(name = "visit_count")
    private Long visitCount;

    @Column(name = "day")
    private Long day;

    public Integer getUsageId() {
        return usageId;
    }

    public void setUsageId(Integer usageId) {
        this.usageId = usageId;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public boolean isSameType(BrowserUsage other) {
        return getBrowserName().equals(other.getBrowserName());
    }
}
