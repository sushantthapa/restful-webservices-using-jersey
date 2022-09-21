package com.vogella.jersey.first1;

import java.sql.Connection;
import java.sql.DriverManager;

public class Utilities {
	
	public static Connection getDbConnection() {
		Connection con = null;
		try {
			System.out.println("1111111111111");
			Class.forName("org.postgresql.Driver").newInstance();
			con =DriverManager.getConnection("jdbc:postgresql://192.168.1.63/niblremit","postgres","");
			con.setAutoCommit(true);
			
		}
		catch(Exception err)
		{
			System.out.println(err);
		}
		return con;
	}

}
