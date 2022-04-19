package dao;

import dto.Ticket;

public class TicketDao extends Dao {

	public static TicketDao ticketDao = new TicketDao();
	// 티켓 정보 DB에 저장
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
	
}
