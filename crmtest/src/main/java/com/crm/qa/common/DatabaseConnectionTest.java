package com.crm.qa.common;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest 
{
	
	public static void main (String args[])
	{
		//URL
		//String url ="jdbc:microsoft:sqlserver://web-db-staging.ccofarlz80tm.us-east-1.rds.amazonaws.com:1433;LOCK_MODE=0;databasename=web_db";
		String url = "jdbc:sqlserver://tst-cobornssql.ccofarlz80tm.us-east-1.rds.amazonaws.com:1433;DatabaseName=web_db;sendStringParametersAsUnicode=false"; 
		

		Connection con;
		
		//String query ="SELECT * from tcn_orRequest";
		String query ="SELECT username FROM customer WHERE username like 'test%' order by customer.username desc";

		Statement stmt;
		String userId = "qvo";
		String passWord = "tuanVU05";

		try
		{
			con = DriverManager.getConnection(url,userId,passWord);
			stmt = con.createStatement();
			stmt.setMaxRows(1);
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int rowCount =1;
			while(rs.next())
			{
				System.out.println("Row " + rowCount + ": ");
				for(int i=1; i <=columnCount; i++)
				{
					System.out.print(" Column Name: " +  rsmd.getColumnName(i)+ ": ");
					System.out.println(rs.getString(i));
					
				}
				System.out.println("");
				rowCount++;
			}
			stmt.close();
			con.close();
		}
		catch(SQLException ex)
		{
			System.err.print("SQLException: ");
			System.err.println(ex.getMessage());
		}
		
	}

}

