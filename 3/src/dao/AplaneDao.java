package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Aplane;

public class AplaneDao extends Dao {

	public static AplaneDao aplaneDao = new AplaneDao();
	
	// 1. 비행기명으로 비행기 정보 가져오기
	public Aplane getaplane(String aname) {
		try {
			String sql = "select * from airplane where aname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, aname);
			rs = ps.executeQuery();
			if(rs.next()) {
				Aplane aplane = new Aplane(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
				return aplane;
			}
		} catch(Exception e) { System.out.println("비행기 정보 가져오기 오류 : "+e);}
		
		
		return null;
	}
	
	// 2. 좌석배율 가져오기
	public double getratio(int cnum,String seatclass) {
		try {
			if(seatclass.equals("economy")) {
				return 1;
			}else {
				String sql = "select p"+seatclass+"seatratio from price where cnum="+cnum;
				ps = con.prepareStatement(sql);
				rs =ps.executeQuery();
				if(rs.next()) {
					return rs.getDouble(1);
				}
			}
			
		} catch(Exception e) { System.out.println("배율 가져오기 오류 : "+e);}
		return 1;
	}
	
	// 3. 항공사 추가하기
	public boolean addcompany(String cname,String cphone,double firstseatratio, double businessseatratio) {
		try {
			String sql = "insert into company(cname,cphone) values(?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, cname);
			ps.setString(2, cphone);
			ps.executeUpdate();
			
			PreparedStatement ps2;
			ResultSet rs2;
			String sql2 = "select cnum company where cname="+cname;
			ps2 = con.prepareStatement(sql2);
			rs2 = ps2.executeQuery();
			if(rs2.next()) {
				PreparedStatement ps3;
				String sql3 = "insert into price(pfirstseatratio,pbusinessseatratio,cnum) values(?,?,?)";
				ps3 = con.prepareStatement(sql3);
				ps3.setDouble(1, firstseatratio);
				ps3.setDouble(2, businessseatratio);
				ps3.setInt(3, rs2.getInt(1));
				ps3.executeUpdate();
			}
		} catch(Exception e) {System.out.println("항공사 추가 오류 : "+ e);}
		return false;
	}
	
	// 4. 비행기 추가하기
	
}
