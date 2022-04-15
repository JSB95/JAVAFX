package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {

	PreparedStatement ps;
	Connection con;
	ResultSet rs;
	
	public Dao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC")
		} catch(Exception e) {
			System.out.println("DAO error :" + e);
		}
	}
	
	
	
}
