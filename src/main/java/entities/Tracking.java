package entities;

import java.io.Serializable;

/**
 * Description: This class contains the user and his activity.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class Tracking extends BaseEntity implements Serializable {
    private Integer trackingId;
    private User user;
    private Activity activity;
    private ActivityStatus status;
    private UserRequest userRequest;
    private Integer time;


    public Tracking() {
    }

    public Tracking(Integer trackingId, User user, Activity activity,
                    ActivityStatus status, UserRequest userRequest, Integer time) {
        this.trackingId = trackingId;
        this.user = user;
        this.activity = activity;
        this.status = status;
        this.userRequest = userRequest;
        this.time = time;

    }

    public Tracking(User user, Activity activity, ActivityStatus status, UserRequest userRequest, Integer time) {
        this.user = user;
        this.activity = activity;
        this.status = status;
        this.userRequest = userRequest;
        this.time = time;

    }

    public Integer getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(Integer trackingId) {
        this.trackingId = trackingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tracking tracking = (Tracking) o;

        if (trackingId != null ? !trackingId.equals(tracking.trackingId) : tracking.trackingId != null) return false;
        if (user != null ? !user.equals(tracking.user) : tracking.user != null) return false;
        if (activity != null ? !activity.equals(tracking.activity) : tracking.activity != null) return false;
        if (status != tracking.status) return false;
        if (time != null ? !time.equals(tracking.time) : tracking.time != null) return false;
        return userRequest == tracking.userRequest;
    }

    @Override
    public int hashCode() {
        int result = trackingId != null ? trackingId.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (userRequest != null ? userRequest.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "trackingId=" + trackingId +
                ", user=" + user +
                ", activity=" + activity +
                ", status=" + status +
                ", time=" + time +
                ", userRequest=" + userRequest +
                '}';
    }
}
