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


@WebServlet("/addUser")
public class AddUserDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
       
    
    public AddUserDemo() {
        super();
        
    }
    public void init()
    {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/db2","root","ThinkPad@66");
			
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try (Statement st=con.createStatement();)
		{
			
			PrintWriter out=response.getWriter();
			int results=st.executeUpdate("insert into dbtable2 values(' "+fname+" ', ' "+lname+" ', ' "+email+" ', ' "+password+" ' ) ");
			
			if(results>0)
			{
				out.println("<h1>USER CREATED SUCCESFULLY</h1>");
			}
			else {
				out.println("<h1>USER FAILED TO CREATE</h1>");
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
