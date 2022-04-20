package dao;

import java.util.ArrayList;

import dto.Ticket;

public class TicketDao extends Dao {

	public static TicketDao ticketDao = new TicketDao();
	
	// 1. Ƽ�� ���� DB�� ����
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
			System.out.println("Ƽ�� ���� ���� : "+e);
		}
		return false;
	}
	
	// 2. �װ��� ���� �ҷ�����
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
		} catch(Exception e) { System.out.println("�װ��� ���� �ҷ����� ���� : "+e);}
		return null;
	}
	
}
