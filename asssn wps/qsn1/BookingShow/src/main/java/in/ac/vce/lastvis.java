package in.ac.vce;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class lastvis
 */
@WebServlet("/lastvis")
public class lastvis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lastvis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
