package entities;

import java.io.Serializable;

/**
 * Description: This class contains the user and his activity.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class Tracking extends BaseEntity implements Serializable {
    private Integer trackingId;
    private User user;
    private Activity activity;

    public Tracking() {
    }

    public Tracking(Integer trackingId, User user, Activity activity) {
        this.trackingId = trackingId;
        this.user = user;
        this.activity = activity;
    }

    public Integer getId() {
        return trackingId;
    }

    public void setId(Integer id) {
        this.trackingId = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tracking tracking = (Tracking) o;

        if (trackingId != null ? !trackingId.equals(tracking.trackingId) : tracking.trackingId != null) return false;
        if (user != null ? !user.equals(tracking.user) : tracking.user != null) return false;
        return activity != null ? activity.equals(tracking.activity) : tracking.activity == null;
    }

    @Override
    public int hashCode() {
        int result = trackingId != null ? trackingId.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (activity != null ? activity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "id=" + trackingId +
                ", user=" + user +
                ", activity=" + activity +
                '}';
    }
}
