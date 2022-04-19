package dao;

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
				String sql = "select p"+seatclass+"seatratio from price where ="+cnum;
				ps = con.prepareStatement(sql);
				rs =ps.executeQuery();
				if(rs.next()) {
					return rs.getDouble(1);
				}
			}
			
		} catch(Exception e) { System.out.println("배율 가져오기 오류 : "+e);}
		return 1;
	}
	
}
