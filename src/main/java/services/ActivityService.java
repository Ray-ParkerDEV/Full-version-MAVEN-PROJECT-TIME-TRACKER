package services;

import connection.TransactionHandler;
import constants.Parameters;
import dao.daofactory.DaoFactory;
import dao.interfacesdao.ActivityDAO;
import entities.Activity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Description: This class describes actions on the user object.
 * This class contains methods that implement work with transaction support.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class ActivityService {

    private final static Logger logger = Logger.getLogger(UserService.class);
    private volatile static ActivityService instance;
    private DaoFactory mySqlFactory;
    private ActivityDAO activityDAO;

    private ActivityService() {
        mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        activityDAO = mySqlFactory.getActivityDao();
    }

    /**
     * Singleton realization with "Double Checked Locking & Volatile" principle for high performance and thread safety.
     *
     * @return - an instance of the class.
     */
    public static ActivityService getInstance() {
        if (instance == null) {
            synchronized (ActivityService.class) {
                if (instance == null) {
                    return instance = new ActivityService();
                }
            }
        }
        return instance;
    }

    /**
     * This method add new activity in DB. This method implements work with transaction support.
     *
     * @param activity - a new user which will be registered.
     * @throws SQLException
     */
    public void createActivityDB(Activity activity) throws SQLException {
        TransactionHandler.runInTransaction(connection ->
                activityDAO.add(activity, connection)
        );
    }

    /**
     * This method receives user object. This method implements work with transaction support.
     *
     * @param name - entered login.
     * @return - User object.
     */
    public Activity getActivityBaName(String name) throws SQLException {
        final Activity[] activity = new Activity[1];
        TransactionHandler.runInTransaction(connection ->
                activity[0] = activityDAO.getByName(name, connection)
        );
        return activity[0];
    }

    /**
     * This method updates user object. This method implements work with transaction support.
     *
     * @param activity - an user which fields will be updated.
     * @throws SQLException
     */
    public void updateActivity(Activity activity) throws SQLException {
        TransactionHandler.runInTransaction(connection ->
                activityDAO.update(activity, connection)
        );
    }

    /**
     * An additional accessory method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeToSession(Activity activity, HttpSession session) {
        session.setAttribute(Parameters.ACTIVITY, activity);
    }

}