package services;

import constants.Parameters;
import dao.daofactory.DaoFactory;
import dao.interfacesdao.UserDAO;
import entities.Activity;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: This class describes actions that admin performs.
 * This class contains methods that implement work with transaction support.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class AdminService {

    private final static Logger logger = Logger.getLogger(AdminService.class);
    private volatile static AdminService instance;
    public List<String> clientNameList;
    private DaoFactory mySqlFactory;
    private UserDAO userDAO;

    private AdminService() {
        clientNameList = new ArrayList<>();
        mySqlFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
        userDAO = mySqlFactory.getUserDao();
    }

    public List<String> getUserNameList() {
        return clientNameList;
    }

    public void setUserNameList(List<String> userNameList) {
        clientNameList = userNameList;
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
     * This method gets activity name that admin tries to add to current client.
     * This method receives activity name from request
     * and sets this values to the corresponding fields of the activity.
     *
     * @param request - an object of request with necessary parameters.
     * @return - user object with updated fields.
     */
    public String geActivityNameFromRequest(HttpServletRequest request) {
        String activityName = request.getParameter(Parameters.ACTIVITY_NAME);
        return activityName;
    }

    /**
     * This method creates a new activity caused by admin action on admin page.
     * This method receives activity name from request
     * and sets this values to the corresponding fields of the activity.
     *
     * @param request - an object of request with necessary parameters.
     * @return - user object with updated fields.
     */
    public Activity geActivityFromRequest(HttpServletRequest request) {
        Activity activity = new Activity();
        String activityName = request.getParameter(Parameters.ACTIVITY_NAME);
        if (activityName != null && !activityName.isEmpty()) {
            activity.setActivityName(activityName);
        }
        return activity;
    }

    /**
     * This method checks if the fields of the form are filled.
     *
     * @param request - an object of request with necessary parameters.
     * @return - boolean value of the condition.
     */
    public boolean areFieldsFilled(HttpServletRequest request) {
        if (!request.getParameter(Parameters.ACTIVITY_NAME).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}