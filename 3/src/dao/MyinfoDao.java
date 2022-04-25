package dao;

import dto.Member;

public class MyinfoDao extends Dao {
	
	public static MyinfoDao myinfodao = new MyinfoDao();	// MypageDao�� �޸𸮿� �������·� ���ֽ�Ŵ

	public MyinfoDao() {}	// �� ������, �ʿ������ ���߿� �����
	
	// 1. �� ���� ��ȸ		// db���� ������ �ʿ䰡 ��������
		// ���������� - ȸ������ ��ȸ(������ ����) ������
		// �� �ֳ�??
	public Member myinfopage (String mid) {
		try {
			String sql = "select * from member where mid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if(rs.next()) {
				Member member = new Member(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				return member;
			}
		} catch (Exception e) {System.out.println("MyinfoDao - myinfopage exception : "+e);}
		return null;
	}
	
	// 2. ȸ�� ����
		// ȸ�� Ż��� ���
	public void delete(int mnum) {
		try {
			String sql="delete from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();	// �α��� �� ����ڸ� Ż�� �����ϴ� ���ǹ� �ʿ� ��
		} catch (Exception e) {System.out.println("MyinfoDao - delete exception : "+e);}
	}
	
	// 3. ȸ�� ���� ������Ʈ
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
