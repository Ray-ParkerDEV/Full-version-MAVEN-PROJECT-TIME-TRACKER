package commands.implementations.user;

import commands.BasicCommand;
import commands.implementations.admin.ChosePageCommand;
import constants.MessageConstants;
import constants.Parameters;
import constants.PathPageConstants;
import entities.Activity;
import entities.Tracking;
import entities.User;
import manager.ConfigManagerPages;
import org.apache.log4j.Logger;
import services.ActivityService;
import services.TrackingService;
import services.UserService;
import utils.RequestParameterIdentifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: This class describes actions of login logic.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class LoginCommand implements BasicCommand {
    private final static Logger logger = Logger.getLogger(LoginCommand.class);

    /**
     * This method describes the logon logic. The method uses methods of the RequestParameterIdentifier and AdminService
     * classes and works according to the following steps:
     * - getting an user object from request object using login and password saved in the corresponding request
     * object using the <i>getUserLoginPasswordFromRequest(...)<i/> method;
     * - checking user's authorization using the <i>checkUserAuthorization(...)</i> method;
     * - if the user is authorized the user's are created using the <i>getUserByLogin(...)<i/> method;
     * - generating the page according to the user's type (client or admin).
     *
     * @param request - request which will be processed.
     * @return - a page which user will be directed to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = RequestParameterIdentifier.getUserLoginPasswordFromRequest(request);
        HttpSession session = request.getSession(false);
//        String language = session.getAttribute("language").toString();
//        session.invalidate();
//        session = request.getSession(true);
        try {
            if (UserService.getInstance().checkUserAuthorization(user.getLogin(), user.getPassword())) {
                List<Activity> activityAdminList = ActivityService.getInstance().getAllActivities();
                List<Tracking> trackingList = TrackingService.getInstance().getAllTracking();
                List<User> userList = UserService.getInstance().getAllUser();
                int itemsPerPage = ChosePageCommand.itemsPerPage;
                List<String> numbersPages = UserService.getInstance().getNumbersPages(userList, itemsPerPage);
                String lastPage = String.valueOf(numbersPages.size());
                String currentPage = "1";
                UserService.getInstance().setPaginationAttributeToSession(numbersPages, lastPage, currentPage,
                        itemsPerPage, session);
                user = UserService.getInstance().getUserByLogin(user.getLogin());
                UserService.getInstance().setAttributeToSession(activityAdminList, trackingList, userList, session);
                switch (user.getUserType().getUserType()) {
                    case "admin":
                        User adminUser = user;
                        UserService.getInstance().setAttributeAdminToSession(adminUser, session);
                        page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ADMIN_PAGE_PATH);
                        break;
                    case "client":
                        User clientUser = user;
                        UserService.getInstance().setAttributeClientToSession(clientUser, session);
                        page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.CLIENT_PAGE_PATH);
                        break;
                    default:
                        page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.CLIENT_PAGE_PATH);
                        break;
                }
                logger.info(MessageConstants.SUCCESS_LOGIN);
            } else {
                page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.INDEX_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_LOGIN_PASSWORD, MessageConstants.WRONG_LOGIN_OR_PASSWORD);
            }
        } catch (SQLException e) {
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageConstants.DATABASE_ACCESS_ERROR);
            logger.error(MessageConstants.DATABASE_ACCESS_ERROR);
        }
        return page;
    }
}