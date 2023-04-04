package com.servlets.UserWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listuser")
public class ListUserDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
    
    public ListUserDemo() {
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
	
	protected void doGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		try (Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from dbtable2");)
		{
			
			while(rs.next())
			{
				String fname=rs.getString(1);
				String lname=rs.getString(2);
				String email=rs.getString(3);
				out.println(fname+" "+lname+" "+email);
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
