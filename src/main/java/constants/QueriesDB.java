package constants;

/**
 * Description: This class contains the sql-queries to the current database. The queries are formatted in constant view.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class QueriesDB {

    /*Queries to work with user database table.*/
    public static final String CHECK_AUTHORIZATION = "SELECT login, password FROM user WHERE login = ? AND password = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM user JOIN userType ON user.userTypeId = \n" +
            "    userType.userTypeId  WHERE login = ? ";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE userId = ?";
    public static final String ADD_USER_CLIENT = "INSERT INTO user (firstName, surName, login, password, userTypeId)" +
            "VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE userId = ?";
    public static final String UPDATE_USER_BY_ID = "UPDATE user SET firstName = ?, surname = ? " +
            "login = ?, password = ?, userType = ?  WHERE userId = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    /*Queries to work with userType database table.*/
    public static final String ADD_USER_TYPE = "INSERT INTO UserType (userType) VALUES (?);";
    public static final String UPDATE_USER_TYPE_BY_ID = "UPDATE UserType SET userType=? WHERE userTypeId=?;";
    public static final String DELETE_USER_TYPE_BY_ID = "DELETE FROM userType WHERE userTypeId = ?";
    public static final String GET_USER_TYPE_BY_ID = "SELECT * FROM userType WHERE userTypeId = ?";
    public static final String GET_ALL_USERS_TYPE = "SELECT * FROM userType";
    public static final String GET_USER_TYPE_BY_TYPE = "SELECT * FROM userType WHERE userType = ?";

    /*Queries to work with activity database table.*/
    public static final String ADD_ACTIVITY = "INSERT INTO activity (activity) VALUES (?);";
    public static final String UPDATE_ACTIVITY_BY_ID = "UPDATE activity SET activity=? WHERE activityId=?;";
    public static final String DELETE_ACTIVITY_BY_ID = "DELETE FROM activity WHERE activityId = ?";
    public static final String GET_ACTIVITY_BY_ID = "SELECT * FROM activity WHERE activityId = ?";
    public static final String GET_ALL_ACTIVITIES = "SELECT * FROM activity";
    /*Queries to work with tracking database table.*/
    public static final String DELETE_TRACKING_BY_USER_ID = "DELETE FROM tracking WHERE userId = ?";
    public static final String DELETE_TRACKING_BY_ID = "DELETE FROM tracking WHERE trackingId = ?";
    public static final String GET_TRACKING_BY_ID = "SELECT * FROM tracking JOIN user ON tracking.userId = user.userId\n" +
            "    JOIN activity ON tracking.activityId = activity.activityId \n" +
            "    JOIN status ON tracking.statusId = status.statusId \n" +
            "    JOIN userType ON user.userTypeId = userType.userTypeId WHERE trackingId = ?";
    public static final String UPDATE_TRACKING_STATUS_AND_TIME_BY_ID = "UPDATE tracking SET statusId = ?, time = ? " +
            "WHERE trackingId=?;";
    public static final String UPDATE_TRACKING_STATUS_BY_ID = "UPDATE tracking SET statusId = ? WHERE trackingId=?;";
    public static final String ADD_TRACKING = "INSERT INTO tracking (userId, activityId, statusId, time)" +
            "VALUES (?, ?, ?, ?)";
    public static final String GET_ALL_TRACKING = "SELECT * FROM tracking JOIN user ON tracking.userId = user.userId\n" +
            "    JOIN activity ON tracking.activityId = activity.activityId \n" +
            "    JOIN status ON tracking.statusId = status.statusId \n" +
            "    JOIN userType ON user.userTypeId = userType.userTypeId ;";

}
