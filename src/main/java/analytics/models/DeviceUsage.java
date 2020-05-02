package analytics.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "device_usage")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceUsage implements Serializable {

    // https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
    private static final long serialVersionUID = 2095302179301203988L;

    public DeviceUsage() {
    }

    public DeviceUsage(String deviceName, Long visitCount, Long day) {
        this.deviceName = deviceName;
        this.visitCount = visitCount;
        this.day = day;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usage_id")
    private Integer usageId;

    @Column(name = "device_name")
    private String deviceName;

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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public boolean isSameType(DeviceUsage other) {
        return getDeviceName().equals(other.getDeviceName());
    }
}
