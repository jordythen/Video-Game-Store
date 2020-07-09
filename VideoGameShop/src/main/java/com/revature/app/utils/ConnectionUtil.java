package com.revature.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ConnectionUtil {
	public static Logger log = Logger.getLogger(ConnectionUtil.class);

    private static ConnectionUtil cu;
    private static Properties prop;

    private ConnectionUtil() {
        prop = ResourceUtil.getProperties("database.properties");
    }

    public static synchronized ConnectionUtil getInstance() {
        if (cu == null)
            cu = new ConnectionUtil();
        return cu;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
			Class.forName(prop.getProperty("drv"));
            conn = DriverManager.getConnection(
                    prop.getProperty("aws"),
                    prop.getProperty("usr"),
                    prop.getProperty("psw")
            );
        } catch (ClassNotFoundException ex) {
            log.error("JDBC driver not found! Was ojdbc.jar not loaded?", ex);
        } catch (SQLException ex) {
            log.error("Could not connect to database", ex);
        }
        return conn;
    }

}
