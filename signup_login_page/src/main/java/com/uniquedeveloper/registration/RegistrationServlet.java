package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String umobile = request.getParameter("contact");
		String upwd = request.getParameter("pass");
		Connection con=null;
		RequestDispatcher dispatcher = null;
		try {
			 Class.forName("org.postgresql.Driver");
		        con = DriverManager.getConnection(
		                "jdbc:postgresql://localhost:5432/Login_SignUp_Portal?useSSL=false", "postgres", "1234");
		       PreparedStatement pst = con.prepareStatement("insert into users(username,password,email,phone_no) values(?,?,?,?)");
		       pst.setString(1, uname);
		       pst.setString(2, upwd);
		       pst.setString(3, uemail);
		       pst.setString(4, umobile);
		       
		       int count = pst.executeUpdate();
		       dispatcher = request.getRequestDispatcher("registration.jsp");
	            if (count > 0) {
	                request.setAttribute("status", "success");
	            }
	            dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
				try{
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
	}
}
