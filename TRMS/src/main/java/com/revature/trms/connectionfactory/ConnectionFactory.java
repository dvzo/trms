package com.revature.trms.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static ConnectionFactory cf = null;
	private static boolean build = true;
	private static final String URL="jdbc:oracle:thin:@trms.csir9vtt6sbq.us-east-1.rds.amazonaws.com:1521:ORCL";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "password";

	private ConnectionFactory() {

	}

	public static synchronized ConnectionFactory getInstance() {
		if (build) {
			cf = new ConnectionFactory();
			build = false;
		}
		return cf;

	}

	public Connection getConnection() {
		Connection conn = null;
		try {
//
//			Properties prop = new Properties();
//			try {
//				prop.load(new FileReader("datasource.properties"));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USERNAME,PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

}