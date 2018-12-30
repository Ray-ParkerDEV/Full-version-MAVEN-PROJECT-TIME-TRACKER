package commands.implementations.client;

import commands.BasicCommand;
import commands.implementations.admin.CreateActivityCommand;
import constants.MessageConstants;
import constants.Parameters;
import constants.PathPageConstants;
import entities.Tracking;
import entities.User;
import manager.ConfigManagerPages;
import org.apache.log4j.Logger;
import services.TrackingService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


public class AddCommand implements BasicCommand {
    private static final Logger logger = Logger.getLogger(CreateActivityCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(false);
        String userId = request.getParameter(Parameters.USER_ID);
        try {
            User overviewUser = UserService.getInstance().getUserById(userId);
            overviewUser.setRequestAdd(true);
            UserService.getInstance().updateUser(overviewUser);
            List<User> userList = UserService.getInstance().getAllUser();
            List<Tracking> trackingList = TrackingService.getInstance().getAllTracking();
            UserService.getInstance().setAttributeOverviewUserToSession(overviewUser, session);
            UserService.getInstance().setAttributeToSession(trackingList, userList, session);
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.CLIENT_PAGE_PATH);
            logger.info(MessageConstants.SUCCESS_ADD_REQUEST);
        } catch (SQLException e) {
            request.setAttribute(Parameters.ERROR_DATABASE, MessageConstants.DATABASE_ACCESS_ERROR);
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ERROR_PAGE_PATH);
            logger.error(MessageConstants.DATABASE_ACCESS_ERROR);
        }
        return page;
    }
}
