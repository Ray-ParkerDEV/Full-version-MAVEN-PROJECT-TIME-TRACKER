package dao.mysqldaoimpl;

import connection.ConnectionPool;
import constants.MessageConstants;
import constants.Parameters;
import constants.QueriesDB;
import dao.interfacesdao.TrackingDAO;
import entities.*;
import exceptions.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
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
    public void deleteTrackingById(Integer id, Connection connection) throws DAOException {
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
    public Tracking getTrackingById(String id, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Tracking tracking = new Tracking();
        try {
            statement = connection.prepareStatement(QueriesDB.GET_TRACKING_BY_ID);
            statement.setString(1, id);
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
     * @param tracking  - the entity of User with "null" value for setting corresponding values.
     * @return - created user with fields.
     * @throws SQLException
     */
    private Tracking createTracking(ResultSet resultSet, Tracking tracking) throws SQLException {
        tracking.setTrackingId(resultSet.getInt(Parameters.TRACKING_ID_DB));
        tracking.setUser(UserDAOImpl.getInstance().createUser(resultSet, new User()));
        tracking.setActivity(ActivityDAOImpl.getInstance().createActivity(resultSet, new Activity()));
        switch (resultSet.getString(Parameters.STATUS_NAME_DB)) {
            case Parameters.NEW_ACTIVITY_DB:
                tracking.setStatus(ActivityStatus.NEW_ACTIVITY);
                break;
            case Parameters.IN_PROGRESS_DB:
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
        String userRequest = resultSet.getString(Parameters.USER_REQUEST_NAME_DB);
        if (!resultSet.wasNull()) {
            switch (userRequest) {
                case Parameters.ADD:
                    tracking.setUserRequest(UserRequest.ADD);
                    break;
                case Parameters.REMOVE:
                    tracking.setUserRequest(UserRequest.REMOVE);
                    break;
            }
        } else {
            tracking.setUserRequest(null);
        }
        tracking.setElapsedTime(resultSet.getString(Parameters.TIME));
        tracking.setTimeStart(resultSet.getLong(Parameters.TIME_START_DB));
        tracking.setTimeStop(resultSet.getLong(Parameters.TIME_STOP_DB));
        tracking.setDifferenceTime(resultSet.getLong(Parameters.DIFFERENCE_TIME_DB));
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
            statement.setString(2, tracking.getElapsedTime());
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
    public void updateTrackingById(String id, Tracking tracking, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(QueriesDB.UPDATE_TRACKING);
            Integer status = null;
            switch (tracking.getStatus().toString().toLowerCase()) {
                case Parameters.NEW_ACTIVITY_DB:
                    status = 1;
                    break;
                case Parameters.IN_PROGRESS_DB:
                    status = 2;
                    break;
                case Parameters.PAUSE:
                    status = 3;
                    break;
                case Parameters.FINISHED:
                    status = 4;
                    break;
                case Parameters.STOP:
                    status = 5;
                    break;
            }
            Integer userRequest = null;
            if (tracking.getUserRequest() != null) {
                switch (tracking.getUserRequest().toString().toLowerCase()) {
                    case Parameters.ADD:
                        userRequest = 1;
                        break;
                    case Parameters.REMOVE:
                        userRequest = 2;
                        break;
                }
                statement.setInt(2, userRequest);
            } else{
                statement.setNull(2, Types.INTEGER);
            }
            statement.setInt(1, status);
            statement.setString(3, tracking.getElapsedTime());
            statement.setString(4, tracking.getTimeStart().toString());
            statement.setString(5, tracking.getTimeStop().toString());
            statement.setString(6, tracking.getDifferenceTime().toString());
            statement.setString(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } catch (NullPointerException e) {
            logger.error(MessageConstants.NULLPOINTEREXEPTIONS, e);
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
     * @param tracking   - the current user which has been created.
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    @Override
    public void add(Tracking tracking, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(QueriesDB.ADD_TRACKING);
            statement.setString(1, String.valueOf(tracking.getUser().getUserId()));
            statement.setString(2, String.valueOf(tracking.getActivity().getActivityId()));
            statement.setString(3, "1");
            statement.setNull(4, Types.VARCHAR);
            statement.setString(5, String.valueOf(tracking.getElapsedTime()));
            statement.setString(6, "0");
            statement.setString(7, "0");
            statement.setString(8, "0");
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } catch (NullPointerException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
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
        List<Tracking> tracking = new ArrayList<>();
        try {
            statement = connection.prepareStatement(QueriesDB.GET_ALL_TRACKING);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tracking.add(createTracking(resultSet, new Tracking()));
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
     * This method reads and returns information from all records (rows) of a database table.
     *
     * @param user       - user which rows will have retrieved from tracking table in DB.
     * @param connection - the current connection to a database. Transmitted from the service module to provide transactions.
     * @return - list of all entities from a database table.
     */
    @Override
    public List<Tracking> getTrackingByClientId(User user, Connection connection) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Tracking> trackingList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(QueriesDB.GET_ALL_TRACKING_BY_CLIENT_ID);
            statement.setString(1, String.valueOf(user.getUserId()));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                trackingList.add(createTracking(resultSet, new Tracking()));
            }
        } catch (SQLException e) {
            logger.error(MessageConstants.EXECUTE_QUERY_ERROR, e);
            throw new DAOException(MessageConstants.EXECUTE_QUERY_ERROR, e);
        } finally {
            ConnectionPool.closeResultSet(resultSet);
            ConnectionPool.closeStatement(statement);
        }
        return trackingList;
    }
}
