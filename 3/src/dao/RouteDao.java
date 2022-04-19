package dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

import dto.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RouteDao extends Dao {

	public static RouteDao routeDao = new RouteDao();
	
	// 1. 항공일지 추가
	public boolean newroute(Route route) {
		try {
			String sql = "insert into route(pname,rdeparture,rdestination,rflightTime,rdepartureDate,rbasePrice) values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, route.getAname());
			ps.setString(2, route.getRdeparture());
			ps.setString(3, route.getRdestination());
			ps.setString(4, route.getRflightTime());
			ps.setString(5, route.getRdeparturedate());
			ps.setInt(6, route.getRbaseprice());
			ps.executeUpdate();
			return true;
		} catch(Exception e) {System.out.println("항공일지 추가 실패  : "+e);}
		return false;
	}
	
	
	// 2. 항공일지번호로 항공일지 불러오기
	public Route numgetroute(int rnum) {
		try {
			String sql = "select * from route where rnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, rnum);
			rs = ps.executeQuery();
			if(rs.next()) {
				Route route = new Route(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(7), 
						rs.getInt(6));
				return route;
			}
		} catch(Exception e) { System.out.println("항공일지 불러오기 실패 : "+e);}
		return null;
	}
	
	// 3. 출발지,도착지로 항공일지 불러오기
	public ObservableList<Route> getroute(String departure, String destination) {
		try {
			ObservableList<Route> list = FXCollections.observableArrayList();
			String sql = "select * from route where rdeparture=? and rdestination=? order by rbaseprice";
			ps = con.prepareStatement(sql);
			ps.setString(1, departure);
			ps.setString(2, destination);
			rs = ps.executeQuery();
			while(rs.next()) {
				Route route = new Route(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(7), 
						rs.getInt(6));
				list.add(route);
			}
			return list;
		} catch(Exception e) { System.out.println("항공일지2 불러오기 실패 : "+e);}
		return null;
	}
	
	// 4. 출발지,도착지 가져오기
	public HashSet<String> getdeparture(String column){
		try {
			HashSet<String> list = new HashSet<>();
			String sql = "select "+column+" from route";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch(Exception e) {System.out.println("출발지,도착지 가져오기 오류 : "+e);}
		return null;
	}
	
	// 5. 비행기명으로 항공사이름 가져오기
	public String getcname(String aname) {
		try {
			String sql = "select cnum from airplane where aname=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, aname);
			rs = ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps2;
				ResultSet rs2;
				String sql2 = "select cname from company where cnum=?";
				ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, rs.getInt(1));
				rs2 = ps2.executeQuery();
				if(rs2.next()) {
					return rs2.getString(1);
				}
			}
		} catch(Exception e) {System.out.println("항공사 이름 가져오기 오류 : "+e);}
		
		return null;
	}
	
	// 6. 선택된 좌석 불러오기
	public ArrayList<String> getseat(int rnum){
		try {
			ArrayList<String> seatlist = new ArrayList<>();
			String sql = "select tseatnum from ticket where rnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, rnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				seatlist.add(rs.getString(1));
			}
			return seatlist;
		} catch(Exception e) {
			System.out.println("좌석 불러오기 오류 : "+e);
		}
		return null;
	}
	
	
}
