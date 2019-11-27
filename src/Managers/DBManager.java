package Managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBManager {
	
	private static DBManager instance = new DBManager();
	
	public static DBManager getInstance() {
		return instance;
	}
	
	private DBManager() {
		
	}
	
	private Connection getConnection() throws Exception {
		
		
		Connection con = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "gameuser";
		String pass = "gameuser";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}
	
	public void insertScore (String name, int enemies, int stage, int score) {
		return;
	}
	
	public ArrayList<String> seletScore () {
		return null;
	}
}
