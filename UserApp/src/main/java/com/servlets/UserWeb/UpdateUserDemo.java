package com.servlets.UserWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateUser")
public class UpdateUserDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
    
    public UpdateUserDemo() {
        super();
        
    }

	public void init()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/db2","root","ThinkPad@66");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		PrintWriter out=response.getWriter();
		try (Statement st=con.createStatement();)
		{
			int result=st.executeUpdate("update dbtable2 set password= '"+password+"' where emila=' "+email+" ' ");
			if(result>0)
			{
				out.println("<h1>USER UPDATED SUCCESFULLY</h1>");
			}
			else {
				out.println("<h1>ERROR IN UPDATING</h1>");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void destroy()
	{
		if(con!=null)
		{
			try {
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

}
