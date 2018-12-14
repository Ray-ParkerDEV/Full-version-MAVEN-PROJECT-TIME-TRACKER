package entities;

import java.io.Serializable;

/**
 * Description: This class describes all user's activities.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class Activity extends BaseEntity implements Serializable {

    private Integer activityId;
    private String activityName;
    private ActivityStatus status;
    private Integer durationTime;

    public Activity() {
    }

    public Activity(Integer activityId, String activityName,
                    ActivityStatus status, Integer durationTime) {
        this.activityName = activityName;
        this.activityId = activityId;
        this.status = status;
        this.durationTime = durationTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (activityId != null ? !activityId.equals(activity.activityId) : activity.activityId != null) return false;
        if (activityName != null ? !activityName.equals(activity.activityName) : activity.activityName != null)
            return false;
        if (status != activity.status) return false;
        return durationTime != null ? durationTime.equals(activity.durationTime) : activity.durationTime == null;
    }

    @Override
    public int hashCode() {
        int result = activityId != null ? activityId.hashCode() : 0;
        result = 31 * result + (activityName != null ? activityName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (durationTime != null ? durationTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", status=" + status +
                ", durationTime=" + durationTime +
                '}';
    }
}
