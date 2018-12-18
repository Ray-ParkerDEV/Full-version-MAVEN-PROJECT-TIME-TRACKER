package session;

import servlet.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionLogic {

    public static HttpSession getSession(HttpServletRequest request) {
        HttpSession session = null;
        if (Controller.flag) {
            session = request.getSession();
            int timeLive = 15*60;
            session.setMaxInactiveInterval(timeLive);
            Controller.flag = false;
        } else {
            session = request.getSession(false);
        }
        return session;
    }

    public static boolean isSessionAlive(HttpSession session) {
        if (session == null) {
            return false;
        }
        return true;
    }
}