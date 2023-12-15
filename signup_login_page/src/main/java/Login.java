	
	
	import java.io.IOException;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.*;
	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.*;
	
	@WebServlet("/Login")
	public class Login extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String uemail = request.getParameter("username");
	        String upwd = request.getParameter("password");
	
	        HttpSession session = request.getSession();
	        Connection con = null;
	        try {
	            Class.forName("org.postgresql.Driver");
	            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Login_SignUp_Portal?useSSL=false", "postgres", "1234");
	            PreparedStatement pst = con.prepareStatement("select * from users where username= ? and password = ? ");
	            pst.setString(1, uemail);
	            pst.setString(2, upwd);
	            ResultSet rs = pst.executeQuery();
//	    
//	            if (rs.next()) {
//	                session.setAttribute("status", "success");
//	            	response.sendRedirect("index.jsp?status=success&name]=" + rs.getString("username"));
////	                session.setAttribute("name", rs.getString("username"));
////	                response.sendRedirect("index.jsp");
////	                dispatcher = request.getRequestDispatcher("index.jsp");
//	            } else {
//	                session.setAttribute("status", "failed");
//	                response.sendRedirect("login.jsp?status=failed");
////	                response.sendRedirect("login.jsp");
//
////	                dispatcher = request.getRequestDispatcher("login.jsp");
////	                session.removeAttribute("status");
//	            }
//	            dispatcher.forward(request, response);
	            
	            if (rs.next()) {
	                session.setAttribute("status", "success");	
	                session.setAttribute("name", rs.getString("username"));
	                response.sendRedirect("index.jsp?name=" + rs.getString("username"));	
	            } else {
	                session.setAttribute("status", "failed");
	                response.sendRedirect("login.jsp?status=failed");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
	
	
