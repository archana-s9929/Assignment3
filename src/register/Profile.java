package register;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.Blob;

/**
 * Servlet implementation class profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		
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

		if(session!=null) {
			String userid=(String)session.getAttribute("uid");

			out.println("<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Insert title here</title>\r\n" + 
					"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
					"<link href=\"css/style.css\" type=\"text/css\" rel=\"stylesheet\">\r\n" + 
					"<script src=\"https://kit.fontawesome.com/a076d05399.js\"></script>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<div class=\"container-float bg\">\r\n" + 
					"\r\n" + 
					"<nav>\r\n" + 
					"	<input type=\"checkbox\" id=\"check\">\r\n" + 
					"	<label for=\"check\" class=\"checkbtn\">\r\n" + 
					"		<i class=\"fas fa-bars\"></i>\r\n" + 
					"	</label>\r\n" + 
					"	<label class=\"logo\">AB Institute\r\n" + 
					"	</label>\r\n" + 
					"	\r\n" + 
					 
					"	<ul>\r\n" + 
					"		<li><a href=\"home.html\">home</a></li>\r\n" + 
					"		<li><a href=\"Profile\">Profile</a></li>\r\n" + 
					"		<li><a href=\"Logout\">Logout</a></li>\r\n" + 
					"	</ul>\r\n" + 
					"</nav>\r\n" +"	<h2>Your Profile<br><br/>" );
					
//			out.println("<a href='Profile'>profile</a>");
//			out.println("<a href='Logout'>Logout</a>");
			try {
				PreparedStatement ps=conn.prepareStatement("select * from login where userid=?");
				ps.setString(1, userid);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					out.println("<div class=\"card\">"+
					  "<img src=\"ppid.jpg\" style=\"width:20%\">"+
					  "<div class=\"container\">");
					out.println("Full name: "+rs.getNString(1)+" "+rs.getNString(2)+"<br/>");
//					Blob image =null;
//					byte[] imgData =null;
//					image = (Blob) rs.getBlob(6);
//					imgData = image.getBytes(1,(int)image.length());
//					response.setContentType("image/jpeg");
//					OutputStream os= response.getOutputStream();
//					os.write(imgData);
						 
					out.println("e-mail id :   "+rs.getNString(3)+"</h2>\r\n" + 
											  "</div>\r\n" + 
												"</div>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			out.println("</div>\r\n" + 
					"<section>\r\n" + 
					"</section>\r\n" + 
					"</body>");
		}
		else {
			response.sendRedirect("index.html");
		}
	}


}
