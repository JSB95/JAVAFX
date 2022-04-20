package dao;

import dto.Member;

public class MyinfoDao extends Dao {
	
	public static MyinfoDao myinfodao = new MyinfoDao();	// MypageDao를 메모리에 정적형태로 상주시킴

	// 1. 회원 삭제
		// 회원 탈퇴시 사용
	public void delete(int mnum) {
		try {
			String sql="delete from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();	// 로그인 된 사용자만 탈퇴가 가능하니 조건문 필요 ㄴ
		} catch (Exception e) {System.out.println("MyinfoDao - delete exception : "+e);}
	}
	
	// 2. 회원 정보 업데이트
		// Myinfoupdate_accupdate
	public void update(Member member) {
		try {
			String sql="update member set mpassword=?, mphone=?, mpassport=?, mcard=? where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getMpassword());
			ps.setString(2, member.getMphone());
			ps.setString(3, member.getMpassport());
			ps.setString(4, member.getMcard());
			ps.setInt(5, member.getMnum());
			ps.executeUpdate();
		} catch (Exception e) {System.out.println("MyinfoDao - update exception : "+e);}
	}
	
	
}
