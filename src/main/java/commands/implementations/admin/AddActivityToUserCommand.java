package commands.implementations.admin;

import commands.BasicCommand;
import constants.MessageConstants;
import constants.Parameters;
import constants.PathPageConstants;
import entities.Tracking;
import manager.ConfigManagerPages;
import org.apache.log4j.Logger;
import services.ActivityService;
import services.TrackingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static entities.Activity.activityNameList;

/**
 * Description: This describes actions of registration new user.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class AddActivityToUserCommand implements BasicCommand {
    private static final Logger logger = Logger.getLogger(CreateActivityCommand.class);

    /**
     * This method describes the adding new activities logic.
     * The method uses methods of the RequestParameterIdentifier and AdminService.
     *
     * @param request - request which will be processed.
     * @return - a page which user will be directed to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(false);
        String addActivityName = request.getParameter(Parameters.ACTIVITY_NAME);
        Tracking tracking = new Tracking();
        try {
//            if (session.isNew()) {
//                //we have to update data from tracking table DB
//                activityNameList = ActivityService.getInstance().getAllActivityNames();
//            }
//            if (ActivityService.getInstance().isUniqueActivityName(activity,activityNameList)) {
//                activityNameList.add(activity);
            TrackingService.getInstance().registerTracking(tracking);
            ActivityService.getInstance().setActivityNameListToSession(activityNameList, session);
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ADMIN_PAGE_PATH_CLIENT_OVERVIEW);
            logger.info(MessageConstants.SUCCESS_ADDING_ACTIVITY);
//            } else {
//                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageConstants.ACTIVITY_EXISTS);
//                page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ADMIN_PAGE_PATH);
//            }
        } catch (SQLException e) {
            request.setAttribute(Parameters.ERROR_DATABASE, MessageConstants.DATABASE_ACCESS_ERROR);
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ERROR_PAGE_PATH);
            logger.error(MessageConstants.DATABASE_ACCESS_ERROR);
        }
        return page;
    }
}