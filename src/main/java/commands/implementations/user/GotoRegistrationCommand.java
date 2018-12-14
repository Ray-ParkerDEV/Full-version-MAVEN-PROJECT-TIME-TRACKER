package commands.implementations.user;

import commands.BasicCommand;
import constants.PathPageConstants;
import manager.ConfigManagerPages;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: This class describes action to redirect guest to the registration page.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class GotoRegistrationCommand implements BasicCommand {

    /**
     * This method describes action to redirect guest to the registration page.
     *
     * @param request   - request which will be processed.
     * @return          - a page which user will be directed to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigManagerPages.getInstance().getProperty(PathPageConstants.REGISTRATION_PAGE_PATH);
    }
}