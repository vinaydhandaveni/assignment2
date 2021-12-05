package in.ac.vce;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddingS
 */
@WebServlet("/AddingS")
public class AddingS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddingS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String country=request.getParameter("country");
		String city=request.getParameter("city");
		String movie=request.getParameter("movie");
		String date=request.getParameter("date");
		String ticketprice=request.getParameter("ticketprice");
		String ticketsbooked=request.getParameter("ticketsbooked");
	try {
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		   date=sdf2.format(sdf.parse(date));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	
	try {
		Class.forName("org.mariadb.jdbc.Driver");
		
		//loads class into JVM once loaded object is created this is driver object
		Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/booking","root","");
		PreparedStatement s1 = con.prepareStatement("Insert into shows values(?,?,?,?,?");
	  s1.setString(1,country);
	  s1.setString(2,city);
	  s1.setString(3,movie);
	  s1.setString(4,date);
	  s1.setString(5,ticketprice);
	  s1.setString(6,ticketsbooked);}
	catch(Exception e) {
	}
	}

}
