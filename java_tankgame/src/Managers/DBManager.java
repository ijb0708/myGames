package Managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManager {
	
	private static DBManager instance = new DBManager();
	
	private ArrayList<scoreBean> scores = new ArrayList<scoreBean>();
	
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
		
		String sql = "insert into gamescore values(?, ?, ?, ?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, enemies);
			pstmt.setInt(3, stage);
			pstmt.setInt(4, score);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public ArrayList<scoreBean> seletScore () {
		String sql = "select name, stage from gamescore order by stage desc";
		scores.clear();
		
		try {
			Connection con = getConnection();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				scoreBean sb = new scoreBean();
				sb.setName(rs.getString(1));
				sb.setStage(rs.getInt(2));
				scores.add(sb);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return scores;
	}
}
