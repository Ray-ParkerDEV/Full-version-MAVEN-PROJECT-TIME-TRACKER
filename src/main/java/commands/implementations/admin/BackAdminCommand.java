package commands.implementations.admin;

import commands.BasicCommand;
import constants.PathPageConstants;
import manager.ConfigManagerPages;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: This class describes  back actions in admin page overviewClient.jsp.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class BackAdminCommand implements BasicCommand {

    /**
     *
     * @param request - request which will be processed.
     * @return - a page which user will be directed to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ADMIN_PAGE_PATH);
        return page;
    }
}