package services;

import connection.TransactionHandler;
import constants.Parameters;
import dao.daofactory.DaoFactory;
import dao.interfacesdao.TrackingDAO;
import entities.Tracking;
import entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class TrackingService {
    private final static Logger logger = Logger.getLogger(UserService.class);
    private volatile static TrackingService instance;
    private DaoFactory mySqlFactory;
    private TrackingDAO trackingDAO;

    private TrackingService() {
        mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        trackingDAO = mySqlFactory.getTrackingDao();
    }

    /**
     * Singleton realization with "Double Checked Locking & Volatile" principle for high performance and thread safety.
     *
     * @return - an instance of the class.
     */
    public static TrackingService getInstance() {
        if (instance == null) {
            synchronized (TrackingService.class) {
                if (instance == null) {
                    return instance = new TrackingService();
                }
            }
        }
        return instance;
    }

    /**
     * This method add new tracking entity in DB. This method implements work with transaction support.
     *
     * @param tracking - a new tracking which will be registered.
     * @throws SQLException
     */
    public void registerTracking(Tracking tracking) throws SQLException {
        TransactionHandler.runInTransaction(connection ->
                trackingDAO.add(tracking, connection)
        );
    }

    /**
     * This method receives all activities from database which belongs corresponding user.
     * This method implements work with transaction support.
     *
     * @return - a list of activities from the database.
     * @throws SQLException
     */
    public List<Tracking> getTrackingByClientId(User user) throws SQLException {
        final List<Tracking>[] activityList = new List[1];
        TransactionHandler.runInTransaction(connection ->
                activityList[0] = trackingDAO.getTrackingByClientId(user,connection)
        );
        return activityList[0];
    }

    /**
     * This method receives all trackings from database. This method implements work with transaction support.
     *
     * @return - a list of activities from the database.
     * @throws SQLException
     */
    public List<Tracking> getAllTracking() throws SQLException {
        final List<Tracking>[] activityList = new List[1];
        TransactionHandler.runInTransaction(connection ->
                activityList[0] = trackingDAO.getAll(connection)
        );
        return activityList[0];
    }

    /**
     * An additional accessory method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeTrackingListToSession(List<Tracking> trackingList, HttpSession session) {
        session.setAttribute(Parameters.TRACKING_LIST,trackingList);
    }


}
