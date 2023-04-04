

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class personal_Info  {
	public static void main(String[]args) {
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
	
	try {
		con=DriverManager.getConnection("jdbc:mysql://localhost/db2","root","ThinkPad@66");
		st=con.createStatement();
		rs=st.executeQuery("select * from personalDetails");
		while(rs.next())
		{
			int id=rs.getInt(1);
			String fname=rs.getString(2);
			String lname=rs.getString(3);
			String email=rs.getString(4);
			System.out.println(id+" "+fname+" "+lname+" "+email);
			
			
		}
		
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
			st.close();
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	}
}


