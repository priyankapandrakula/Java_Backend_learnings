
package com.databaseDemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseDemo {
	public static void main(String[]args)
	{
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost/db2","root","ThinkPad@66");
			st=con.createStatement();
			rs=st.executeQuery("select * from dbtable");
			
			while(rs.next())
			{
				int id=rs.getInt(1);
				String fname=rs.getString(2);
				String city=rs.getString(3);
				System.out.println(id+"   "+fname+"   "+city);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			if (con!=null) {
			try {
				con.close();
				st.close();
				rs.close();
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
}
			
			
		