package org.projectdiaries.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.projectdiaries.utility.DatabaseConnection;

public class MyDbConnectionListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		DatabaseConnection db = (DatabaseConnection) sc.getAttribute("db");
		Connection conn = db.getConnection();
		try {
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		DatabaseConnection db = new DatabaseConnection(url, user, password);
		sc.setAttribute("db", db);
		System.out.println("Database Connection Initialized Successfully");	
	}

}
