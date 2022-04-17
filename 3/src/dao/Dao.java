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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC","root","1234");
		} catch (Exception e) { System.out.println("Dao con initializing Exception : "+e);}
	}
}
