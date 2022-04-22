package dao;

import java.util.ArrayList;

import dto.Ticket;

public class TicketDao extends Dao {

	public static TicketDao ticketDao = new TicketDao();
	
	// 1. 티켓 정보 DB에 저장
	public boolean ticketsave(Ticket ticket) {
		try {
			String sql = "insert into ticket(tprice,mnum,rnum,tseatnum,tseatclass) values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, ticket.getTprice());
			ps.setInt(2, ticket.getMnum());
			ps.setInt(3, ticket.getRnum());
			ps.setString(4, ticket.getTseatnum());
			ps.setString(5, ticket.getTseatclass());
			ps.executeUpdate();
			return true;
		} catch(Exception e) {
			System.out.println("티켓 저장 실패 : "+e);
		}
		return false;
	}
	
	// 2. 항공권 정보 불러오기
	public ArrayList<Ticket> getticket(int mnum) {
		try {
			ArrayList<Ticket> tlist = new ArrayList<>();
			String sql = "select * from ticket where mnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				Ticket ticket = new Ticket(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				tlist.add(ticket);
			}
			return tlist;
		} catch(Exception e) { System.out.println("항공권 정보 불러오기 실패 : "+e);}
		return null;
	}
	
	// 3. 선택된 좌석 불러오기
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
