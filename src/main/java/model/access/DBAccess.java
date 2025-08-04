package model.access;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBAccess {
	private List<String> dbs = new ArrayList<>();
	
	public DBAccess() {
		init();
	}
	
	public static void main(String[] args) {
		DBAccess db = new DBAccess();
		System.out.println( db.dbs.size());
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(dbs.get(0));
			con = DriverManager.getConnection(dbs.get(1), dbs.get(2), dbs.get(3));
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException();
		} catch(SQLException e) {
			throw new IllegalStateException();
		}
		return con;
	}
	
	private void init() { // 초기화
		InputStream in = getClass().getClassLoader().getResourceAsStream("rdbms.properties");
		Properties pps = new Properties();
		try {
			pps.load(in);
			
			String driver = pps.getProperty("mysql.driver");
			String url = pps.getProperty("mysql.url");
			String username = pps.getProperty("mysql.username");
			String password = pps.getProperty("mysql.password");
			
			dbs.add(0, driver);
			dbs.add(1, url);
			dbs.add(2, username);
			dbs.add(3, password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException: " + e.getMessage());
		} finally {
			try {
				in.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
		/* Log */
		System.out.println("Access 초기화 완료");
	}
}
