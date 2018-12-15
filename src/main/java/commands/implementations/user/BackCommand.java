package commands.implementations.user;

import commands.BasicCommand;
import constants.Parameters;
import constants.PathPageConstants;
import manager.ConfigManagerPages;
import servlet.Controller;
import utils.RequestParameterIdentifier;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: This class describe action to "back" command.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class BackCommand implements BasicCommand {

    /**
     * This method describes action to "back" command.
     *
     * @param request   - request which will be processed.
     * @return          - a page which user will be directed to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String pageFromRequest = RequestParameterIdentifier.getPageFromRequest(request);
        String page = null;
        if (pageFromRequest != null && pageFromRequest.equals(Parameters.LOGIN)) {
            Controller.flag = true;
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.LOGIN_PAGE_PATH);
        } else if (pageFromRequest != null && pageFromRequest.equals(Parameters.CLIENT)){
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.CLIENT_PAGE_PATH);
        } else if (pageFromRequest != null && pageFromRequest.equals(Parameters.ADMIN)) {
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ADMIN_PAGE_PATH);
        }
        return page;
    }
}