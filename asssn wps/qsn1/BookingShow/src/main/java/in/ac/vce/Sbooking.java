package in.ac.vce;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Sbooking
 */
@WebServlet("/Sbooking")
public class Sbooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sbooking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String city=request.getParameter("city");
		String Movie=request.getParameter("Movie");
		String date1=request.getParameter("date");
		//String hiddencity = request.getParameter("city1");
		response.setContentType("text/html"); 
		/*out.println("<script type='text/javascript'>");
		RequestDispatcher rd= request.getRequestDispatcher("C:\\Users\\DELL\\eclipse-workspace\\BookingShow\\src\\main\\webapp\\WEB-INF\\Book.js");
		rd.include(request,response);
		out.println("</script>"); */
		String sql = "SELECT * FROM shows" ;
		try {
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		   date1=sdf2.format(sdf.parse(date1));
		} catch (Exception e) {
		    e.printStackTrace();
		}

		try {
		Class.forName("org.mariadb.jdbc.Driver");
		
		//loads class into JVM once loaded object is created this is driver object
		Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/booking","root","");
		Statement s = con.createStatement();
		/*ResultSet rs=s.executeQuery("select date from show where city=hiddencity and movie=movie1");
		out.println("dates ");
		while(rs.next()){
		out.println(rs.getString(1));
		}*/String htmlRespone = "<html> <body >";
        htmlRespone += "Movie Details at a date"+date1+"<br/><table border=2><tr> <td>Country</td><td>City</td> <td>MovieName</td> <td>Date</td></tr> ";      
		ResultSet rs1=s.executeQuery(sql);
		
		while(rs1.next()){
			if(rs1.getString(4).equals(date1)) {
				htmlRespone+="<tr/>"+"<td>"+rs1.getString(1)+"</td>"+"<td>"+rs1.getString(2)+"</td>"+"<td>"+
			rs1.getString(3)+"</td>"+"<td>"+rs1.getString(4)+"</td>"+"</tr>";
		}
		}
		htmlRespone+="</table></br>";
	
		htmlRespone += "Dates at while movie "+Movie+" is played <br/><table>"; 
     rs1=s.executeQuery(sql);
		while(rs1.next()) {
		
			if(rs1.getString(2).equals(city)) {
				
				htmlRespone+="<tr><td>"+rs1.getString(4)+"</td></tr>";}
			
		}
		
		htmlRespone+="</table></body></html>";
		out.println(htmlRespone);
		RequestDispatcher rd=request.getRequestDispatcher("/reports.html");
		rd.include(request, response);

		}


		 catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		 
}
}