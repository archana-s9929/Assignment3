package register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String uid=request.getParameter("uid");
		String pass=request.getParameter("pass");
		HttpSession session=request.getSession();
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String conString="jdbc:mysql://localhost:3306/userdb";
		
		try {
			conn=DriverManager.getConnection(conString,"root","Archana29@sql");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps=conn.prepareStatement("select * from login where userid!='' and userid=? and pswd=?");
			ps.setString(1, uid);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				out.print("logged in Successfully");
				session.setAttribute("uid",uid);
				request.getRequestDispatcher("home.html").include(request, response);
			}
			else {
				out.print("<p>Incorrect Username/Password</p>");
				request.getRequestDispatcher("index.html").include(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
