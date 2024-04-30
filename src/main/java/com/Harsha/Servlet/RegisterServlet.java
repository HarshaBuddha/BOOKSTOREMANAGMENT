package com.Harsha.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query="INSERT INTO BOOKDATA(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES (?,?,?)";
		String bookname=req.getParameter("bookname");
		String bookedition=req.getParameter("bookedition");
		float bookprice=Float.parseFloat(req.getParameter("bookprice"));
		
		PrintWriter pw=resp.getWriter();
		resp.setContentType("text/html");
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","root");
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, bookname);
			st.setString(2, bookedition);
			st.setFloat(3, bookprice);
			int count=st.executeUpdate();
			if (count==1)
				pw.println("<h2>Book is Registered Successfully</h2>");
			else
				pw.println("<h2>Book is not  Registered</h2>");
		}
		catch(SQLException e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
			}
		pw.println("<a href='Home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='Booklist'>Book list</a>");
		}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		doGet(req,resp);
	}

}
