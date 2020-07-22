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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String mail=request.getParameter("mailid");
		String uid=request.getParameter("uid");
		String pass=request.getParameter("pass");
		
		Connection conn=null;
		Statement stmt=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String conString="jdbc:mysql://localhost:3306/userdb";
		
		try {
			conn=DriverManager.getConnection(conString,"root","Archana29@sql");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps=conn.prepareStatement("insert into userdb.login values(?,?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, mail);
			ps.setString(4, uid);
			ps.setString(5, pass);
			int i=ps.executeUpdate();
			if(i>0)
				out.println("registered successfully");

			request.getRequestDispatcher("index.html").include(request, response);
			
		} catch (SQLException e1) {
			out.println("try another username");
			request.getRequestDispatcher("login.html").include(request, response);
			e1.printStackTrace();
		}
	
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
