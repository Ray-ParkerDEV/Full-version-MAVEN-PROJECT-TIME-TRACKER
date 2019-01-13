package commands.implementations.client;

import commands.BasicCommand;
import commands.implementations.admin.BackAdminCommand;
import constants.MessageConstants;
import constants.Parameters;
import constants.PathPageConstants;
import entities.ActivityStatus;
import entities.Tracking;
import manager.ConfigManagerPages;
import org.apache.log4j.Logger;
import services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public class FinishCommand implements BasicCommand {
    private static final Logger logger = Logger.getLogger(BackAdminCommand.class);
    private TrackingService trackingService = (TrackingService) ServiceHelper.getInstance().getService("trackingService");

    /**
     * This method finish the Time counter.
     *
     * @param request - request which will be processed.
     * @return - a page which user will be directed to.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        HttpSession session = request.getSession(false);
        String trackingId = request.getParameter(Parameters.TRACKING_ID);
        try {
            Tracking tracking = trackingService.getTrackingById(trackingId);
            if (tracking.getStatus() == ActivityStatus.IN_PROGRESS) {
                tracking=ClientService.getInstance().setUpTime(tracking);
                tracking.setTimeSwitch(false);
            }
            tracking.setStatus(ActivityStatus.FINISHED);
            trackingService.updateTracking(trackingId, tracking);
            List<Tracking> trackingList = trackingService.getAllTracking();
            trackingService.setAttributeTrackingListToSession(trackingList, session);
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.CLIENT_PAGE_PATH);
        } catch (SQLException e) {
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageConstants.DATABASE_ACCESS_ERROR);
            logger.error(MessageConstants.DATABASE_ACCESS_ERROR);
        }
        return page;
    }
}
