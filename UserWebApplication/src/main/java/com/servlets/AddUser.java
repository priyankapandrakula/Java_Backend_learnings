package com.servlets;

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


@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private Connection con=null;
       
    
    public AddUser() {
        super();
        
    }
   
    public void init()
    {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/db2","root","ThinkPad@66");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		try( 
			Statement st=con.createStatement();
		)
		
		{
			int result=st.executeUpdate("insert into userdetails values( '  "+fname+" ',  ' "+lname+" ', ' "+email+" ',  ' "+password+" ' )");
			PrintWriter out=response.getWriter();
			if(result>0)
			{
				out.println("<h1>user created succesfully</h1>");
			}
			else {
				out.println("<h1>user failed to create</h1>");
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
				
	}
	public void destroy()
	{
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
