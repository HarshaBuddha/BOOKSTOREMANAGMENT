package com.Harsha.Servlet;

import java.io.IOException;
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
@WebServlet("/editurl")
public class editServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String bookname=req.getParameter("BookName");
		String bookEdition=req.getParameter("BookEdition");
		float bookPrice=Float.parseFloat(req.getParameter("BookPrice"));
		String query="UPDATE BOOKDATA set BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? WHERE id=?";
		
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
			st.setString(2, bookEdition);
			st.setFloat(3, bookPrice);
			st.setInt(4,id);
			int count=st.executeUpdate();
			if (count==1)
				pw.println("<h2>Record is edited Successfully</h2>");
			else
				pw.println("<h2>Record is not edited</h2>");
		}
		catch(SQLException e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
			}
		pw.println("<a href='Booklist'>Book list</a>");
		}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		doGet(req,resp);
	}
}
