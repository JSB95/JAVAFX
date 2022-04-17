package dao;

import dto.Member;

public class MyinfoDao extends Dao {
	
	public static MyinfoDao mypagedao = new MyinfoDao();	// MypageDao�� �޸𸮿� �������·� ���ֽ�Ŵ

	public MyinfoDao() {}	// �� ������, �ʿ������ ���߿� �����
	
	// 1. �� ���� ��ȸ		// db���� ������ �ʿ䰡 ��������
		// ���������� - ȸ������ ��ȸ(������ ����) ������
		// �� �ֳ�??
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
	
	// 2. ȸ�� ����
		// ȸ�� Ż��� ���
	public boolean delete(int mnum) {
		try {
			String sql="delete from member where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();	// �α��� �� ����ڸ� Ż�� �����ϴ� ���ǹ� �ʿ� ��
			return true;
		} catch (Exception e) {System.out.println("MyinfoDao - delete exception : "+e);}
		return false;
	}
	
	
}
