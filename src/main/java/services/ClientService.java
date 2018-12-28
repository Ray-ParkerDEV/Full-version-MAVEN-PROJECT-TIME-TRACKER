package services;

import constants.Parameters;
import entities.Tracking;
import timer.Time;

import javax.servlet.http.HttpSession;
import java.util.List;

public class ClientService {
    private volatile static ClientService instance;

    private ClientService() {

    }

    /**
     * Singleton realization with "Double Checked Locking & Volatile" principle for high performance and thread safety.
     *
     * @return - an instance of the class.
     */
    public static ClientService getInstance() {
        if (instance == null) {
            synchronized (ClientService.class) {
                if (instance == null) {
                    return instance = new ClientService();
                }
            }
        }
        return instance;
    }

    /**
     * Method for setting parameters to Time Tracking.
     * @param tracking - the tracking entity which will be updated.
     *
     * @return - an instance of the class.
     */
    public Tracking setUpTime(Tracking tracking){
        Time.getInstance().setStartTime(tracking.getTimeStart());
        Time.getInstance().setDifference(tracking.getDifferenceTime());
        Time.getInstance().stop();
        tracking.setElapsedTime(Time.getInstance().getElapsedTime());
        tracking.setTimeStop(Time.getInstance().getStopTime());
        tracking.setDifferenceTime(Time.getInstance().getDifference());
        return tracking;
    }

    /**
     * An additional accessory method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeUserRequestAddToSession(List<String> userRequestAdd, HttpSession session) {
        session.setAttribute(Parameters.USER_REQUEST_ADD, userRequestAdd);
    }



}
