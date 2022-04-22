package dao;

import dto.Member;

public class MyinfoDao extends Dao {
	
	public static MyinfoDao myinfodao = new MyinfoDao();	// MypageDao�� �޸𸮿� �������·� ���ֽ�Ŵ

	// 1. ȸ�� ����
		// ȸ�� Ż��� ���
	public void delete(int mnum) {
		try {
			String sql="delete from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();	// �α��� �� ����ڸ� Ż�� �����ϴ� ���ǹ� �ʿ� ��
		} catch (Exception e) {System.out.println("MyinfoDao - delete exception : "+e);}
	}
	
	// 2. ȸ�� ���� ������Ʈ
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
