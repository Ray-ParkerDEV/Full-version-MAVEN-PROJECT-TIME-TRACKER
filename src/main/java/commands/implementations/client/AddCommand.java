package commands.implementations.client;

import commands.BasicCommand;
import constants.MessageConstants;
import constants.Parameters;
import constants.PathPageConstants;
import manager.ConfigManagerPages;
import services.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddCommand implements BasicCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(false);
        String userId = request.getParameter(Parameters.USER_ID);
        ClientService.getInstance().setAttributeUserRequestAddToSession(MessageConstants.WAIT_FOR_ADMIN_RESPONSE,session);
        page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.CLIENT_PAGE_PATH);
        return page;
    }
}
