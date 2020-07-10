package com.revature.app.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {

	private static ConnectionUtil cu = null;
	private static Properties prop;
	private ConnectionUtil() {
		prop = new Properties();
		try {
			
			//We really need this file so we're searching the class in this project for a file named "database.properties"
			
			InputStream dbProperties = ConnectionUtil.class.getClassLoader().getResourceAsStream("database.properties");
			prop.load(dbProperties);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (cu == null)
			cu = new ConnectionUtil();
		return cu;
	}
	
	public Connection getConnection() {
		//Declaring our connection here!
		Connection conn = null;
		try {
			Class.forName(prop.getProperty("drv"));
			conn = DriverManager.getConnection(
					prop.getProperty("aws"),
					prop.getProperty("usr"),
					prop.getProperty("psw")
					);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}

