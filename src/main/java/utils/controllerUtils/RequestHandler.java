package utils.controllerUtils;

import commands.BasicCommand;
import commands.factory.CommandsFactory;
import constants.PathPageConstants;
import manager.ConfigManagerPages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: This class contains method that handles a request from a *.jsp page and defines a command
 * according to the parameter from request.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class RequestHandler extends HttpServlet {

    public RequestHandler() {
    }

    /**
     * This method defines a command from request that will be executed.
     * This method follows the next steps:
     *      - defines the command that received from a *.jsp page;
     *      - calls the implemented <i>execute()</i> method and passes parameters to the handler class,
     *          which is related to a particular command;
     *      - redirects to the required page, it also might be an error page if the required page are not found.
     *
     * @param request       - an object of request from a client.
     * @param response      - an object of response from Controller.
     * @throws ServletException
     * @throws IOException
     */
    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandsFactory factory = CommandsFactory.getInstance();
        BasicCommand command = factory.defineCommand(request);
        String page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.INDEX_PAGE_PATH);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}