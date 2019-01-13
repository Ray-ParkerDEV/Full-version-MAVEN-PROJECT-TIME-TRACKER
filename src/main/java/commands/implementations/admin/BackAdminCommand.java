package commands.implementations.admin;

import commands.BasicCommand;
import constants.MessageConstants;
import constants.Parameters;
import constants.PathPageConstants;
import entities.Activity;
import entities.Tracking;
import entities.User;
import manager.ConfigManagerPages;
import org.apache.log4j.Logger;
import services.ActivityService;
import services.ServiceHelper;
import services.TrackingService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: This class describes  back actions in admin page overviewClient.jsp.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class BackAdminCommand implements BasicCommand {
    private static final Logger logger = Logger.getLogger(BackAdminCommand.class);
    private ActivityService activityService = (ActivityService) ServiceHelper.getInstance().getService("activityService");
    private UserService userService = (UserService) ServiceHelper.getInstance().getService("userService");
    private TrackingService trackingService = (TrackingService) ServiceHelper.getInstance().getService("trackingService");

    /**
     * This method describes back logic that allows go back to previous page.
     *
     * @param request - request which will be processed.
     * @return - a page which user will be directed to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String page ;
        try {
            List<Activity> activityAdminList = activityService.getAllActivities();
            List<Tracking> trackingList = trackingService.getAllTracking();
            List<User> userList = userService.getAllUser();
            userService.setAttributeToSession(activityAdminList, trackingList, userList, session);
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ADMIN_PAGE_PATH);
        } catch (SQLException e) {
            request.setAttribute(Parameters.ERROR_DATABASE, MessageConstants.DATABASE_ACCESS_ERROR);
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ERROR_PAGE_PATH);
            logger.error(MessageConstants.DATABASE_ACCESS_ERROR);
        }
        return page;
    }
}