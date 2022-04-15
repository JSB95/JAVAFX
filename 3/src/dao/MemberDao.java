package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.cj.jdbc.ClientPreparedStatement;
import com.mysql.cj.protocol.Resultset;

public class MemberDao {
	
	private Connection con;
	private ClientPreparedStatement ps;
	private Resultset rs;
	
	// 기본 생성자
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234");
		} catch (Exception e) {System.out.println("memberDao DB주소 연동 예외 발생 : "+e);}
	}
	
	// 메서드
	
	// 1. 
	
}
