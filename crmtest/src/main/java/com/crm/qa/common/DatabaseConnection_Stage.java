package com.crm.qa.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection_Stage
{
	private Connection con;
	private String url = "jdbc:sqlserver://stg-cobornssql.ccofarlz80tm.us-east-1.rds.amazonaws.com:1433;DatabaseName=web_db;sendStringParametersAsUnicode=false"; 
	private String userName = "";
	private String password = "";
	private ResultSet rs;
	private Statement stmt;
	private ResultSetMetaData rsmd;
	private static String query;
	private String nextUserName;
	private java.sql.Date CurrentDate;
	private String giftCerticateId;
	private Properties props;

	public DatabaseConnection_Stage() {
		try {
			getPropValue();
			userName=props.getProperty("db_username");
			password=props.getProperty("db_password");
			
		} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
		
	}

    private Properties getPropValue() throws IOException {
    	props=new Properties();
     	try {
     		props.load(new FileInputStream("./Properties/CommonProps_Stage.properties"));
     		
    	} catch (IOException e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
     	return props;
    	
    }

	private int getNextUserNameSuffix(String userName) {
		String suffix="";
		if(userName !=null && userName.length()==8 ) {
			//System.out.println("Length ->" +userName.length());
			//System.out.println("Current username ->" +userName);
			suffix=userName.substring(userName.length()-3, userName.length());
			//System.out.println("Last numbers in username: " + suffix);
		}
		
		return (Integer.parseInt(suffix))+1;
	}
	
	public String getNextUserName() {

		query ="SELECT username FROM customer WHERE username like 'SMSTG%' order by customer.username desc";
		
		try
		{
			con = DriverManager.getConnection(url,userName,password);
			stmt = con.createStatement();
			stmt.setMaxRows(1);
			 rs = stmt.executeQuery(query);
			 rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int rowCount =1;
			while(rs.next())
			{
				//System.out.println("Row " + rowCount + ": ");
				for(int i=1; i <=columnCount; i++)
				{
					//System.out.print(" Column Name: " +  rsmd.getColumnName(i)+ ": ");
					//System.out.println(rs.getString(i));
					
					nextUserName=rs.getString(i);
					//getNextUserNameSuffix(nextUserName);
					//System.out.println("Next UserName: " +nextUserName);
					
					nextUserName=nextUserName.substring(0,5) + getNextUserNameSuffix(nextUserName) ;
					//System.out.println("Next UserName: " +nextUserName);
					
				}
				//System.out.println("");
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
		
		return nextUserName;

	}
	

	public String getGiftCerticateId(String GiftCertificateUsername) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR,1);
	    CurrentDate = new java.sql.Date(calendar.getTime().getTime());
	    
		//GiftCerticicateUsername
		//gift_certificates.PURCHASING_USERNAME='cdtestgc'
		query ="SELECT certificate_id FROM gift_certificates WHERE  gift_certificates.STATUS = 'Active' AND gift_certificates.ACTIVE_DATE <'" 
		+ CurrentDate + "'" + " AND " + "gift_certificates.PURCHASING_USERNAME='" + GiftCertificateUsername + "'";
		//System.out.println("query-->" + query);

		
		try
		{
			con = DriverManager.getConnection(url,userName,password);
			stmt = con.createStatement();
			stmt.setMaxRows(1);
			 rs = stmt.executeQuery(query);
			 rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int rowCount =1;
			while(rs.next())
			{
				//System.out.println("Row " + rowCount + ": ");
				for(int i=1; i <=columnCount; i++)
				{
					//System.out.print(" Column Name: " +  rsmd.getColumnName(i)+ ": ");
					//System.out.println(rs.getString(i));
					
					giftCerticateId=rs.getString(i);
					//getNextUserNameSuffix(nextUserName);

				}
				//System.out.println("");
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
		
		
		return giftCerticateId;

	}
	
	
}

