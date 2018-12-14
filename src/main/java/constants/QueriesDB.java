package constants;

/**
 * Description: This class contains the sql-queries to the current database. The queries are formatted in constant view.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class QueriesDB {

    /*Queries to work with users database table.*/
    public static final String CHECK_AUTHORIZATION = "SELECT login, password FROM user WHERE login = ? AND password = ?";
    //public static final String GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM user JOIN usertype ON user.userTypeId = \n" +
            "    usertype.userTypeId  WHERE login = ? ";
    public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static final String ADD_USER_CLIENT = "INSERT INTO user (firstname, surname, login, password, user_type_id)" +
                                                            "VALUES (?, ?, ?, ?, ?)";
    public static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    public static final String UPDATE_USER_BY_ID = "UPDATE user SET firstname = ?, surname = ? " +
                                                    "login = ?, password = ?, user_type = ?  WHERE id = ?";
    public static final String GET_ALL_USERS = "SELECT * FROM user";

}
