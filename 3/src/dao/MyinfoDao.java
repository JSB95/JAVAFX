package dao;

import dto.Member;

public class MyinfoDao extends Dao {
	
	public static MyinfoDao myinfodao = new MyinfoDao();	// MypageDao를 메모리에 정적형태로 상주시킴

	public MyinfoDao() {}	// 빈 생성자, 필요없으면 나중에 지울것
	
	// 1. 내 정보 조회		// db에서 가져올 필요가 있으려나
		// 마이페이지 - 회원정보 조회(내정보 보기) 페이지
		// 또 있나??
	public Member myinfopage (int mnum) {
		try {
			String sql = "select * from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			if(rs.next()) {
				Member member = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7));
				return member;
			}
		} catch (Exception e) {System.out.println("MyinfoDao - myinfopage exception : "+e);}
		return null;
	}
	
	// 2. 회원 삭제
		// 회원 탈퇴시 사용
	public void delete(int mnum) {
		try {
			String sql="delete from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();	// 로그인 된 사용자만 탈퇴가 가능하니 조건문 필요 ㄴ
		} catch (Exception e) {System.out.println("MyinfoDao - delete exception : "+e);}
	}
	
	// 3. 회원 정보 업데이트
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
