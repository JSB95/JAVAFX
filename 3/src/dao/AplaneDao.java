package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

import dto.Aplane;
import dto.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AplaneDao extends Dao {

	public static AplaneDao aplaneDao = new AplaneDao();
	
	// 1. ���������� ����� ���� ��������
	public Aplane getaplane(String aname) {
		try {
			String sql = "select * from airplane where aname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, aname);
			rs = ps.executeQuery();
			if(rs.next()) {
				Aplane aplane = new Aplane(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				return aplane;
			}
		} catch(Exception e) { System.out.println("����� ���� �������� ���� : "+e);}
		return null;
	}
	
	// 2. �¼����� ��������
	public double getratio(int cnum,String seatclass) {
		try {
			if(seatclass.equals("economy")) {
				return 1;
			}else {
				String sql = "select c"+seatclass+"seatratio from company where cnum="+cnum;
				ps = con.prepareStatement(sql);
				rs =ps.executeQuery();
				if(rs.next()) {
					return rs.getDouble(1);
				}
			}
			
		} catch(Exception e) { System.out.println("���� �������� ���� : "+e);}
		return 1;
	}
	
	// 3. �װ��� �߰��ϱ�
	public boolean addcompany(String cname,String cphone,double firstseatratio, double businessseatratio) {
		try {
			String sql = "insert into company(cname,cphone,cfirstseatratio,cbusinessseatratio) values(?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, cname);
			ps.setString(2, cphone);
			ps.setDouble(3, firstseatratio);
			ps.setDouble(4, businessseatratio);
			ps.executeUpdate();

			return true;
			
		} catch(Exception e) {System.out.println("�װ��� �߰� ���� : "+ e);}
		return false;
	}
	
	// 4. ����� �߰��ϱ�
	public boolean addairplane(Aplane aplane) {
		try {
			String sql = "insert into airplane(aname,cnum,afirstseatcount,abusinessseatcount,aeconomyseatcount) values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, aplane.getaname());
			ps.setInt(2, aplane.getCnum());
			ps.setInt(3, aplane.getAfirstSeatCount());
			ps.setInt(4, aplane.getAbusinessSeatCount());
			ps.setInt(5, aplane.getAeconomySeatCount());
			ps.executeUpdate();
			
			return true;
		}catch(Exception e) {System.out.println("����� �߰� ���� : "+e);}
		return false;
	}
	
	// 5. �װ���/�װ����� �����ϱ�
	public boolean delete(String table, int pknum) {
		try {
			String sql = "delete from "+table+" where "+table.charAt(0)+"num="+pknum;
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			if(table.equals("company")) {
				PreparedStatement ps2;
				String sql2 = "delete from airplane where cnum="+pknum;
				ps2=con.prepareStatement(sql2);
				ps2.executeUpdate();
			}
			return true;
		} catch(Exception e) {System.out.println("�����ϱ� ���� : "+e);}
		return false;
	}
	

	// 6. �װ��� �����ϱ�
	public boolean cupdate(Company company) {
		try {
			String sql = "update company set cname=?, cphone=?, cfirstseatratio=?, cbusinessseatratio=? where cnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, company.getCname());
			ps.setString(2, company.getCphone());
			ps.setDouble(3, company.getCfirstSeatRatio());
			ps.setDouble(4, company.getCbusinessSeatRatio());
			ps.setInt(5, company.getCnum());
			ps.executeUpdate();
			return true;
		} catch(Exception e) {System.out.println("�װ��� ���� ���� : "+e);}
		return false;
	}
	
	// 7. ��� �װ��� ���� �ҷ�����
	public ObservableList<Company> getcompany(){
		try {
			ObservableList<Company> clist = FXCollections.observableArrayList();
			String sql = "select * from company";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Company company = new Company(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
				clist.add(company);
			}
			return clist;
		
		} catch(Exception e) {System.out.println("��� �װ��� ���� �ҷ����� ���� : "+e);}
		return null;
	}
	
	// 8. �װ�������� �װ����ȣ �ҷ�����
	public int getcnum(String cname) {
		try {
			String sql = "select cnum from company where cname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cname);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch(Exception e) {System.out.println("�װ����ȣ �ҷ����� ���� : "+e);}
		return 0;
	}

	// 9. ����� �����ϱ�
	public boolean aupdate(Aplane aplane,String aname) {
		try {
			String sql = "update airplane set aname=?, cnum=?, afirstseatcount=?, abusinessseatcount=?, aeconomyseatcount=? where aname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, aplane.getaname());
			ps.setInt(2, aplane.getCnum());
			ps.setInt(3, aplane.getAfirstSeatCount());
			ps.setInt(4, aplane.getAbusinessSeatCount());
			ps.setInt(5, aplane.getAeconomySeatCount());
			ps.setString(6, aname);
			ps.executeUpdate();
			return true;
		} catch(Exception e) {System.out.println("�װ��� ���� ���� : "+e);}
		return false;
	}
	
	// 10. ��� ����� �ҷ�����
	public ObservableList<Aplane> getairplane(){
		ObservableList<Aplane> alist = FXCollections.observableArrayList();
		try {
			
			String sql = "select * from airplane";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				String cname = RouteDao.routeDao.getcname(rs.getString(1));
				Aplane aplane = new Aplane(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),cname );
				alist.add(aplane);
			}
			return alist;
		
		} catch(Exception e) {System.out.println("��� ����� �ҷ����� ���� : "+e);}
		return null;
	}
	// 11. �װ���� �ҷ�����
	public HashSet<String> getlist(){
		try {
			HashSet<String> list = new HashSet<>();
			String sql = "select cname from company";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch(Exception e) {System.out.println("�װ���� �ҷ����� ���� : "+e);}
		return null;
	}
	
	// 12. ����� ����
	public boolean adelete(String aname) {
		try {
			String sql = "delete from airplane where aname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, aname);
			ps.executeUpdate();
			return true;
		} catch(Exception e) {System.out.println("����� ���� ���� : "+e);}
	
		return false;
	}
	
	
	
	
}
