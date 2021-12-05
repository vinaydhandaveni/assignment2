package in.ac.vce;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Greports
 */
@WebServlet("/Greports")
public class Greports extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Greports() {
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
		PrintWriter out=response.getWriter();
		String sql = "SELECT city,ticketsbooked,sum(ticketprice*ticketsbooked) as total FROM shows GROUP BY CITY" ;
		String sql1 = "SELECT MOVIE,sum(ticketprice*ticketsbooked) as total FROM shows GROUP BY MOVIE" ;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			//loads class into JVM once loaded object is created this is driver object
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/booking","root","");
			Statement s = con.createStatement();
			/*ResultSet rs=s.executeQuery("select date from show where city=hiddencity and movie=movie1");
			out.println("dates ");
			while(rs.next()){
			out.println(rs.getString(1));
			}*/String htmlRespone = "<html>";
	        htmlRespone += "TotalRevenue and TicketsBooked in a City<br/><table border=2><tr><td>City</td> <td>Tickets Booked</td> <td>Total Revenue</td></tr> ";      
			ResultSet rs1=s.executeQuery(sql);
			
			while(rs1.next()){
				
					htmlRespone+="<tr/>"+"<td>"+rs1.getString(1)+"</td>"+"<td>"+rs1.getString(2)+"<td>"+rs1.getString(3)+"</td></tr>";
			
			}
			htmlRespone+="</table></html>";
		
			htmlRespone += "<br/> Maximum Revenue for a Movie<br/>"
					+ "<table border=2><tr><td>Movie</td> <td>Maximum Revenue</td></tr> ";
	ResultSet rs2=s.executeQuery(sql1);
			
			while(rs2.next()){
				
					htmlRespone+="<tr/>"+"<td>"+rs2.getString(1)+"</td>"+"<td>"+rs2.getString(2)+"</td>"+"</tr>";
			
			}
			htmlRespone+="</table></html>";
			out.println(htmlRespone);
			
		out.println("ADD Details");
		RequestDispatcher rd=request.getRequestDispatcher("/adding.html");
		rd.include(request, response);}
		



			 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

}
