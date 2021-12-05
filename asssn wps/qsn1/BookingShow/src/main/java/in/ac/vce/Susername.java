package in.ac.vce;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Susername
 */
@WebServlet("/Susername")
public class Susername extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Susername() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		Cookie c1 =new Cookie("username",username);
		Cookie c2=new Cookie("email",email);
		response.addCookie(c1);
		response.addCookie(c2);
		RequestDispatcher rd=request.getRequestDispatcher("/booking.html");
		rd.include(request, response);
		
		
	}

}
