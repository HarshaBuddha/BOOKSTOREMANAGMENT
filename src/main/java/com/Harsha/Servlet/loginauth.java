package com.Harsha.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loginlink")
public class loginauth extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String username= req.getParameter("username");
		String password= req.getParameter("password");
		String uname="harsha";
		String pass="password";
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html><body>");
		if (uname.equals(username) && pass.equals(password)) {
			res.sendRedirect("Retailer.html");
		}
		else {
			out.println("<h1>login was not  successful</h1>");
			out.println("<p>Wrong credientials<p>");
			out.println("</body></html>");
		}	
	}
	

}
