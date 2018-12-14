package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author Artem
 */
public class SessionLogic {

    public static void print(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        try {
            PrintWriter out = response.getWriter();
            session.setAttribute("URL", request.getRequestURL());
            out.write("Counter:");
            out.write(String.valueOf(sessionCounter(session)));
            out.write("<br/> Time" + new Date(session.getCreationTime()));
            out.write("</br> Last access" + new Date(session.getLastAccessedTime()));
            out.write("<br> Seesin Id" + session.getId());
            
            int interval = 10;//60*30;
            session.setMaxInactiveInterval(interval);
            
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error: " + ex);
        }
    }

    private static int sessionCounter(HttpSession session) {
        Integer counter = (Integer) session.getAttribute("counter");
        if (counter == null) {
            session.setAttribute("counter", 1);
            return 1;
        } else {
            counter++;
            session.setAttribute("counter", counter);
            return counter;
        }
    }

    public static void noActive(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write("<br> Time:" + new Date(session.getCreationTime()));
            out.write("<br> Session alive");
            out.flush();
            out.close();
        } catch (NullPointerException e){
            if (out!= null){
                out.print("Session disable");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("error i/o:" + ex);
        }
        
    }
}
