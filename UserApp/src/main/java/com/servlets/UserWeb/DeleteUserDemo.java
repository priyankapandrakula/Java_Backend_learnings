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


@WebServlet("/deleteUser")
public class DeleteUserDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
       
    
    public DeleteUserDemo() {
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
		
		String email=request.getParameter("email");
		
		try (Statement st=con.createStatement();)
		{
			
			PrintWriter out=response.getWriter();
			int results=st.executeUpdate("delete from dbtable2 where emila= ' "+email+" ' ");
			
			if(results>0)
			{
				out.println("<h1>USER DELETED SUCCESFULLY</h1>");
			}
			else {
				out.println("<h1>USER FAILED TO DELETE</h1>");
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
