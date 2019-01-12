package commands.implementations.client;

import commands.BasicCommand;
import constants.MessageConstants;
import constants.Parameters;
import constants.PathPageConstants;
import entities.ActivityStatus;
import entities.Tracking;
import entities.User;
import manager.ConfigManagerPages;
import services.ClientService;
import services.TrackingService;
import timer.Time;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: This describes actions of registration new user.
 * <p>
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class StartTimeCommand implements BasicCommand {

    /**
     * This method start te Time counter.
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
            Tracking tracking = TrackingService.getInstance().getTrackingById(trackingId);
            User trackingUser = tracking.getUser();
            List<Tracking> trackingList = TrackingService.getInstance().getAllTracking();
            if (ClientService.getInstance().ifUserHasNoOpenActivity(trackingUser, trackingList)) {
                Time.getInstance().start();
                tracking = TrackingService.getInstance().getTrackingById(trackingId);
                tracking.setTimeSwitch(true);
                TrackingService.getInstance().updateTracking(trackingId, tracking);
                TrackingService.getInstance().setStatusAndTimeStartTracking(trackingId,
                        ActivityStatus.IN_PROGRESS.toString(), Time.getInstance().getStartTime());
                trackingList = TrackingService.getInstance().getAllTracking();
                TrackingService.getInstance().setAttributeTrackingListToSession(trackingList, session);
                page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.CLIENT_PAGE_PATH);
            } else {
                Tracking activeTracking = ClientService.getInstance().getActiveTracking(trackingList);
                activeTracking = ClientService.getInstance().setUpDifferenceTime(activeTracking);
                TrackingService.getInstance().updateTracking(activeTracking.getTrackingId().toString(), activeTracking);
                trackingList = TrackingService.getInstance().getAllTracking();
                TrackingService.getInstance().setAttributeTrackingListToSession(trackingList, session);
                request.setAttribute("duplicateStart", "true");
                request.setAttribute("trackingId", trackingId);
                page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.CLIENT_PAGE_PATH);
            }
        } catch (SQLException e) {
            page = ConfigManagerPages.getInstance().getProperty(PathPageConstants.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageConstants.DATABASE_ACCESS_ERROR);
        }
        return page;
    }
}
