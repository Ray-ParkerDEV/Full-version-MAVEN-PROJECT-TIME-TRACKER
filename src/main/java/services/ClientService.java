package services;

import constants.Parameters;
import entities.Tracking;
import timer.Time;

import javax.servlet.http.HttpSession;

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
     * This method sets user's parameters on Add new activity button to the session. It's like a flag, that prevents
     * duplicating request during updating page.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeUserRequestAddToSessionTrue(HttpSession session) {
        session.setAttribute(Parameters.USER_REQUEST_ADD, "true");
    }

    /**
     * This method sets user's parameters on Add new activity button to the session. It's like a flag, that prevents
     * duplicating request during updating page.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeUserRequestAddToSessionFalse(HttpSession session) {
        session.setAttribute(Parameters.USER_REQUEST_ADD, "false");
    }
}
