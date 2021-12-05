package cookie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LastAccessTimeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current time and format it
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = format.format(date);
        // Establish cookie，Record the latest access time
        Cookie cookie = new Cookie("lastAccessTime", currentTime);
        // cookie Storage time 24 hours
        cookie.setMaxAge(60 * 60 * 24);
        // Preservation cookie
        response.addCookie(cookie);

        // Get the cookie
        String lastAccessTime = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie coo : cookies){
                if("lastAccessTime".equals(coo.getName())){
                    lastAccessTime = coo.getValue();
                }
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        if(lastAccessTime==null){
            response.getWriter().write("This is your first visit");
        }else{
            response.getWriter().write("Your last visit was:"+lastAccessTime);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
