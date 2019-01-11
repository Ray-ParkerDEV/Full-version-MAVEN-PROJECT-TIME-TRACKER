package services;

import connection.ConnectionPool;
import connection.TransactionHandler;
import constants.Parameters;
import dao.daofactory.DaoFactory;
import dao.interfacesdao.UserDAO;
import entities.Activity;
import entities.Tracking;
import entities.User;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: This class describes actions on the user object.
 * This class contains methods that implement work with transaction support.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class UserService {
    private volatile static UserService instance;
    private DaoFactory mySqlFactory;
    private UserDAO userDAO;
    private Connection connection = ConnectionPool.getInstance().getConnection();

    private UserService() throws SQLException {
        mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        userDAO = mySqlFactory.getUserDao();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Singleton realization with "Double Checked Locking & Volatile" principle for high performance and thread safety.
     *
     * @return - an instance of the class.
     */
    public static UserService getInstance() throws SQLException {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    return instance = new UserService();
                }
            }
        }
        return instance;
    }

    /**
     * This method checks if the user's login and password are correct. This method implements work with transaction support.
     *
     * @param login    - incoming user's login.
     * @param password - incoming user's password.
     * @return - boolean value of the condition if the user is authorized or not.
     * @throws SQLException
     */
    public boolean checkUserAuthorization(String login, String password) throws SQLException {
        final boolean[] isAuthorized = new boolean[1];
        TransactionHandler.runInTransaction(connection ->
                isAuthorized[0] = userDAO.isAuthorized(login, password, connection), connection
        );
        return isAuthorized[0];
    }

    /**
     * This method receives user object. This method implements work with transaction support.
     *
     * @param login - entered login.
     * @return - User object.
     */
    public User getUserByLogin(String login) throws SQLException {
        final User[] user = new User[1];
        TransactionHandler.runInTransaction(connection ->
                user[0] = userDAO.getByLogin(login, connection),
                ConnectionPool.getInstance().getConnection()
        );
        return user[0];
    }

    /**
     * This method receives user object. This method implements work with transaction support.
     *
     * @param overviewUserId - entered id.
     * @return - User object.
     */
    public User getUserById(String overviewUserId) throws SQLException {
        final User[] user = new User[1];
        TransactionHandler.runInTransaction(connection ->
                user[0] = userDAO.getById(overviewUserId, connection),
                ConnectionPool.getInstance().getConnection()
        );
        return user[0];
    }

    /**
     * This method updates user object. This method implements work with transaction support.
     *
     * @param user - an user which fields will be updated.
     * @throws SQLException
     */
    public void updateUser(User user) throws SQLException {
        TransactionHandler.runInTransaction(connection ->
                userDAO.update(user, connection),
                ConnectionPool.getInstance().getConnection()
        );
    }

    /**
     * This method checks the uniqueness of the user. This method implements work with transaction support.
     *
     * @param user - an user object with fields will be checked.
     * @return - boolean value of the condition.
     * @throws SQLException
     */
    public boolean isUniqueUser(User user) throws SQLException {
        final boolean[] isUnique = new boolean[1];
        TransactionHandler.runInTransaction(connection ->
                isUnique[0] = userDAO.checkUniqueUser(user.getLogin(), connection),
                ConnectionPool.getInstance().getConnection()
        );
        return isUnique[0];
    }

    /**
     * This method registers new user of application in DB. This method implements work with transaction support.
     *
     * @param user - a new user which will be registered.
     * @throws SQLException
     */
    public void registerUser(User user) throws SQLException {
        TransactionHandler.runInTransaction(connection ->
                userDAO.add(user, connection),
                ConnectionPool.getInstance().getConnection()
        );
    }
    /**
     * This method receives all Users from database. This method implements work with transaction support.
     *
     * @return - a list of activities from the database.
     * @throws SQLException
     */
    public List<User> getAllUser() throws SQLException {
        final List<User>[] userList = new List[1];
        TransactionHandler.runInTransaction(connection ->
                userList[0] = userDAO.getAll(connection),
                ConnectionPool.getInstance().getConnection()
        );
        return userList[0];
    }

    /**
     * This method divides an array of clients per each page.
     *
     * @return - a amount of pages for pagination.
     */
    public List<String> getNumbersPages(List<User> userList, int itemsPerPage) {
        List<String> pagesList = new ArrayList<>();
        int fullPage = (userList.size()-1) / itemsPerPage; // -1 admin
        int partPage = (userList.size()-1) % itemsPerPage == 0 ? 0 : 1; // -1 admin
        int numbersPages = fullPage + partPage;
        for (int i = 1; i <= numbersPages; i++) {
            pagesList.add(String.valueOf(i));
        }
        return pagesList;
    }

    /**
     * An additional accessory method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeAdminToSession(User adminUser, HttpSession session) {
        session.setAttribute(Parameters.ADMIN_USER, adminUser);
    }

    /**
     * An additional accessory method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeOverviewUserToSession(User overviewUser, HttpSession session) {
        session.setAttribute(Parameters.OVERVIEWUSER, overviewUser);
    }

    /**
     * An additional accessory method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeClientToSession(User clientUser, HttpSession session) {
        session.setAttribute(Parameters.CLIENTUSER, clientUser);
    }

    /**
     * An additional overloaded method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeToSession(List<Activity> activityAdminList, List<Tracking> trackingList,
                                      List<User> userList, HttpSession session) {
        session.setAttribute(Parameters.ACTIVITY_ADMIN_LIST, activityAdminList);
        session.setAttribute(Parameters.TRACKING_LIST, trackingList);
        session.setAttribute(Parameters.USER_LIST, userList);
    }

    /**
     * An additional overloaded method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeToSession(List<Tracking> trackingList,
                                      List<User> userList, HttpSession session) {
        session.setAttribute(Parameters.TRACKING_LIST, trackingList);
        session.setAttribute(Parameters.USER_LIST, userList);
    }

    /**
     * An additional overloaded method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setPaginationAttributeToSession(List<String> numbersPages, String lastPage, String currentPage,
                                                int itemsPerPage, HttpSession session) {
        session.setAttribute(Parameters.NUMBERSPAGES, numbersPages);
        session.setAttribute(Parameters.LASTPAGE, lastPage);
        session.setAttribute(Parameters.CURRENTPAGE, currentPage);
        session.setAttribute(Parameters.ITEMSPERPAGE, itemsPerPage);
    }
}