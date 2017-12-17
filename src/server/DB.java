package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {
	private final static DB db = new DB();
	private Connection con;

	public Connection getConnection() {
		return con;
	}

	private DB() {
		con = null;
		String url = "jdbc:mysql://localhost:3306/killagram";
		String user = "root";
		String pass = "12345";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DB Connect success");
	}

	public static DB getInstance() {
		return db;
	}
	
	public List<Food> getFoodList(String tableName) {
		String sql = "SELECT * FROM " +tableName;
		List<Food> foodList = new ArrayList<Food>();
		try {
			Statement stmt =  con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()) {
				foodList.add(new Food(result.getString(1),result.getString(2),result.getFloat(3)));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodList;
	}
	
	public boolean checkID(String id) {
		String sql = "select * from 사용자 where id=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id); 
			ResultSet result = pstmt.executeQuery();
			if(result.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public User checkUser(String id, String password) {
		String sql = "select * from 사용자 where id=? and password=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id); 
			pstmt.setString(2, password);
			ResultSet result = pstmt.executeQuery();
			if(result.next())
				return new User(result.getString(1),result.getString(2),result.getString(3),
						result.getInt(4),result.getString(5),result.getFloat(6),result.getFloat(7),result.getFloat(8));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean insertUser(User user) {
		String sql = "INSERT INTO 사용자 (id,password,name,age,gender,height,weight,goal_weight) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,user.getId()); 
			pstmt.setString(2,user.getPassword()); 
			pstmt.setString(3,user.getName()); 
			pstmt.setInt(4,user.getAge()); 
			pstmt.setString(5,user.getGender()); 
			pstmt.setFloat(6, user.getHeight()); 
			pstmt.setFloat(7,user.getWeight()); 
			pstmt.setFloat(8,user.getGoal_weight()); 
			int result = pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
