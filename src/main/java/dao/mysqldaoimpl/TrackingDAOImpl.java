package dao.mysqldaoimpl;

import connection.ConnectionPool;
import constants.MessageConstants;
import constants.Parameters;
import constants.QueriesDB;
import dao.interfacesdao.TrackingDAO;
import entities.Activity;
import entities.ActivityStatus;
import entities.Tracking;
import entities.User;
import exceptions.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackingDAOImpl implements TrackingDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private volatile static TrackingDAOImpl instance;

    private TrackingDAOImpl() {
    }

    /**
     * Singleton realization with "Double Checked Locking & Volatile" principle for high performance and thread safety.
     *
     * @return - an instance of the class.
     */
    public static TrackingDAOImpl getInstance() {
        if (instance == null) {
            synchronized (TrackingDAOImpl.class) {
                if (instance == null) {
                    instance = new TrackingDAOImpl();
                }
            }
        }
        return instance;
    }

    /**
     * This method deletes an existing record (row) in a database table.
     *
     * @param id         - id number of the current entity which will be deleted.
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    @Override
    public void deleteTrackingByUserId(int id, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(QueriesDB.DELETE_TRACKING_BY_USER_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } finally {
            ConnectionPool.closeStatement(statement);
        }
    }

    /**
     * This method deletes an existing record (row) in a database table.
     *
     * @param id         - id number of the current entity which will be deleted.
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    @Override
    public void deleteTrackingById(int id, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(QueriesDB.DELETE_TRACKING_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } finally {
            ConnectionPool.closeStatement(statement);
        }
    }

    /**
     * This method reads and returns information from a record (row) of a database table.
     *
     * @param id         - id number of the record (row) in the database table..
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     * @return - an entity from a database table according to the incoming id number.
     */
    @Override
    public Tracking getTrackingById(int id, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Tracking tracking = new Tracking();
        try {
            statement = connection.prepareStatement(QueriesDB.GET_TRACKING_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                createTracking(resultSet, tracking);
            }
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } finally {
            ConnectionPool.closeResultSet(resultSet);
            ConnectionPool.closeStatement(statement);
        }
        return tracking;
    }

    /**
     * An additional method.
     * This method creates entity of Tracking class from data received from ResultSet.
     *
     * @param resultSet - a database result "row" with required values.
     * @param tracking      - the entity of User with "null" value for setting corresponding values.
     * @return - created user with fields.
     * @throws SQLException
     */
    private Tracking createTracking(ResultSet resultSet, Tracking tracking) throws SQLException {
        tracking.setTrackingId(resultSet.getInt(Parameters.TRACKING_ID));
        tracking.setUser(UserDAOImpl.getInstance().createUser(resultSet,new User()));
        tracking.setActivity(ActivityDAOImpl.getInstance().createActivity(resultSet,new Activity()));
        switch (resultSet.getString(Parameters.STATUS)) {
            case Parameters.NEW_ACTIVITY:
                tracking.setStatus(ActivityStatus.NEW_ACTIVITY);
                break;
            case Parameters.IN_PROGRESS:
                tracking.setStatus(ActivityStatus.IN_PROGRESS);
                break;
            case Parameters.PAUSE:
                tracking.setStatus(ActivityStatus.PAUSE);
                break;
            case Parameters.FINISHED:
                tracking.setStatus(ActivityStatus.FINISHED);
                break;
            case Parameters.STOP:
                tracking.setStatus(ActivityStatus.STOP);
                break;
        }
        tracking.setTime(resultSet.getInt(Parameters.TIME));
        return tracking;
    }

    /**
     * This method updates an existing record (row) in a database table.
     *
     * @param tracking   - the current entity of user which will be updated.
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    @Override
    public void updateTrackingStatusAndTimeByID(Tracking tracking, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(QueriesDB.UPDATE_TRACKING_STATUS_AND_TIME_BY_ID);
            statement.setString(1, tracking.getStatus().toString());
            statement.setString(2, tracking.getTime().toString());
            statement.setString(3, tracking.getTrackingId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } finally {
            ConnectionPool.closeStatement(statement);
        }
    }

    /**
     * This method updates an existing record (row) in a database table.
     *
     * @param tracking   - the current entity of user which will be updated.
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    @Override
    public void updateTrackingStatusByID(Tracking tracking, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(QueriesDB.UPDATE_TRACKING_STATUS_BY_ID);
            statement.setString(1, tracking.getStatus().toString());
            statement.setString(3, tracking.getTrackingId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } finally {
            ConnectionPool.closeStatement(statement);
        }
    }

    /**
     * This method creates and inserts an entity in a database table.
     *
     * @param tracking       - the current user which has been created.
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    @Override
    public void add(Tracking tracking, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(QueriesDB.ADD_TRACKING);
            statement.setString(1, String.valueOf(tracking.getUser().getUserId()));
            statement.setString(2, String.valueOf(tracking.getActivity().getActivityId()));
            statement.setString(3, tracking.getStatus().toString());
            statement.setString(4, String.valueOf(tracking.getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } finally {
            ConnectionPool.closeStatement(statement);
        }
    }

    /**
     * This method reads and returns information from all records (rows) of a database table.
     *
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     * @return - list of all entities from a database table.
     */
    @Override
    public List<Tracking> getAll(Connection connection) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Tracking> trackings = new ArrayList<>();
        try {
            statement = connection.prepareStatement(QueriesDB.GET_ALL_TRACKING);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                trackings.add(createTracking(resultSet, new Tracking()));
            }
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } finally {
            ConnectionPool.closeResultSet(resultSet);
            ConnectionPool.closeStatement(statement);
        }
        return trackings;
    }
}
