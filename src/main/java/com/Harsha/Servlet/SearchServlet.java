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
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query="SELECT * FROM BOOKDATA WHERE bookname=? and bookedition=?";
		String bookname=req.getParameter("bookname");
		String bookedition=req.getParameter("bookedition");
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
			st.setString(1,bookname);
			st.setString(2,bookedition);	
			ResultSet rs=st.executeQuery();
			pw.println("<html>");
			pw.println("<link rel='stylesheet' href='css/bootstrap.css'>");
			pw.println("<body class='container-fluid' style='width:rem;'>");
			pw.println("<h2 class='bg-danger text-white text-center'>BOOKS LIST</h2>");
			pw.println("<table border='1' class='table table-hover'");
			pw.println("<tr>");
			pw.println("<th>BOOK ID</th>");
			pw.println("<th>BOOK NAME</th>");
			pw.println("<th>BOOK EDITION</th>");
			pw.println("<th>BOOK PRICE</th>");
			pw.println("<th>EDIT</th>");
			pw.println("<th>DELETE</th>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getFloat(4)+"</td>");
				pw.println("<td><a href='editScreen?id="+rs.getInt(1)+"'>edit</a></td>");
				pw.println("<td><a href='deleteRecord?id="+rs.getInt(1)+"'>delete</a></td>");	
				pw.println("</tr>");	
			}
			pw.println("</table>");
			pw.println("<a href='Retailer.html'>Home</a>");
			pw.println("</body></html>");
		}
		catch(SQLException e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
			}
		}	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		doGet(req,resp);
	}
}
