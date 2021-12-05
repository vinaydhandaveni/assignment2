package in.ac.vce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class lastvisited
 */
@WebServlet("/lastvisited")
public class lastvisited extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lastvisited() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current time and format it
   java.util.Date date=new java.util.Date();
   PrintWriter out=response.getWriter();
   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   String currentTime=format.format(date);
   Cookie cookie=new Cookie("lastAccessTime",currentTime);
   cookie.setMaxAge(60*60*24);
   response.addCookie(cookie);
   String lastAccessTime=null;
   Cookie[] cookies=request.getCookies();
   if(cookies!=null) {
	   for(Cookie coo:cookies) {
		   if("lastAcesssTime".equals(coo.getName())) {
			 lastAccessTime=coo.getValue();  
		   }
	   }
   }

        response.setContentType("text/html");
        if(lastAccessTime==null){
           out.println("This is your first visit");;
            
        }else{
            out.println("Your last visit was:"+lastAccessTime);
        }
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


}}
