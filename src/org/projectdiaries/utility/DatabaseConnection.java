package org.projectdiaries.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private Connection conn;
	
	public DatabaseConnection(String url, String user, String password) {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	this.conn = DriverManager.getConnection(url, user, password);
        }
        catch(ClassNotFoundException cfe) {
        	cfe.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.conn;
	}

}
