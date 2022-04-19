package dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import controller.login.Login;
import dto.Member;


public class MemberDao extends Dao {

	public static MemberDao memberDao = new MemberDao();
	
	// 1. 회원가입
	public boolean signup(Member member) {
		try {
			String sql = "insert into member(mid,mpassword,mphone,mcard,mname,mpassport,msince) values(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getMid());
			ps.setString(2, member.getMpassword());
			ps.setString(3, member.getMphone());
			ps.setString(4, member.getMcard());
			ps.setString(5, member.getMname());
			ps.setString(6, member.getMpassport());
			ps.setString(7, member.getMsince());
			ps.executeUpdate();
			return true;
			
		} catch(Exception e) { System.out.println("회원가입 SQL 오류 : "+ e); }
		return false;
	}
	// 2. 중복체크
	public boolean check(String column,String value) {
		try {
			String sql = "select * from member where "+column+"=\""+value+"\"";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(Exception e) {System.out.println("중복체크 오류 : "+e);	}
		return false;
	}
	
	// 3. 로그인
	public boolean login(String id, String password) {
		try {
			String sql = "select * from member where mid=? and mpassword=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(Exception e) { System.out.println("login error : "+ e); }
		return false;
	}
	
	// 4. 아이디로 회원정보 호출
	public Member getMember(String id) {
		try {
			String sql = "select * from member where mid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Member member = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(7), rs.getString(5),rs.getString(8),rs.getString(9),rs.getInt(10));
				return member;
			}
		} catch(Exception e) { System.out.println("회원정보 호출 오류 : "+ e); }
		return null;
	}
	
	// 5. 아이디 찾기
	public String findid(String name, String phone) {
		try {
			String sql = "select * from member where mname=? and mphone=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(2);
			}
		} catch(Exception e) { System.out.println("아이디 찾기 오류 : " + e); }
		return null;
	}
	// 6. 비밀번호 찾기
	public String findpw(String name,String id, String phone) {
		try {
			String sql = "select * from member where mname=? and mid=? and mphone=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
			ps.setString(3, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(3);
			}
		} catch(Exception e) { System.out.println("비밀번호 찾기 오류 : " + e); }
		return null;
	}

	
	
}
