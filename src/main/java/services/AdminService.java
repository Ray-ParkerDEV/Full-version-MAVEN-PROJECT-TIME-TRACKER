package services;

import connection.TransactionHandler;
import constants.Parameters;
import dao.daofactory.DaoFactory;
import dao.interfacesdao.UserDAO;
import entities.Activity;
import entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Description: This class describes actions that admin performs.
 * This class contains methods that implement work with transaction support.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class AdminService {

    private final static Logger logger = Logger.getLogger(AdminService.class);
    private volatile static AdminService instance;
    private DaoFactory mySqlFactory;
    private UserDAO userDAO;

    private AdminService() {
        mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        userDAO = mySqlFactory.getUserDao();
    }

    /**
     * Singleton realization with "Double Checked Locking & Volatile" principle for high performance and thread safety.
     *
     * @return - an instance of the class.
     */
    public static AdminService getInstance() {
        if (instance == null) {
            synchronized (AdminService.class) {
                if (instance == null) {
                    return instance = new AdminService();
                }
            }
        }
        return instance;
    }
    /**
     * This method creates a new user of application. This method is used during registration operation.
     * This method receives first name, surname, login and password from request
     * and sets this values to the corresponding fields of the user.
     *
     * @param request - an object of request with necessary parameters.
     * @return - user object with updated fields.
     */
    public  User geActivityFromRequest(HttpServletRequest request) {
        Activity activity = new Activity();
        String activityName = request.getParameter(Parameters.ACTIVITY_NAME);
        if ((activityName != null && !activityName.isEmpty())
                & (surName != null && !surName.isEmpty())
                & (login != null && !login.isEmpty())
                & (password != null && !password.isEmpty())) {
            user.setFirstName(firstName);
            user.setSurName(surName);
            user.setLogin(login);
            user.setPassword(password);
            //user.setUserType(getByType);
        }
        return user;
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
            user[0] = userDAO.getByLogin(login,connection)
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
                userDAO.update(user, connection)
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
        final boolean [] isUnique = new boolean[1];
        TransactionHandler.runInTransaction(connection ->
                isUnique[0] = userDAO.checkUniqueUser(user.getLogin(), connection)
        );
        return isUnique[0];
    }

    /**
     * This method registers new user of application. This method implements work with transaction support.
     *
     * @param user - a new user which will be registered.
     * @throws SQLException
     */
    public void registerUser(User user) throws SQLException {
        TransactionHandler.runInTransaction(connection ->
                userDAO.add(user, connection)
        );
    }

    /**
     * An additional accessory method that provides work with some attributes of the object of http session.
     * This method sets user's parameters to the session.
     *
     * @param session - an object of the current session.
     */
    public void setAttributeToSession(User user, HttpSession session) {
        session.setAttribute(Parameters.USER, user);
        session.setAttribute(Parameters.USER_TYPE, String.valueOf(user.getUserType()));
    }

}