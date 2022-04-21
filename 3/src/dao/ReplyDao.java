package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import controller.login.Login;
import dto.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReplyDao extends Dao {
	
	public static ReplyDao replyDao = new ReplyDao();
	// ��� �ۼ� �޼ҵ�
	public boolean wirte(Reply reply) {
		try {
			String sql = "insert into test.reply(rcontent,rwrite,bnum) values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, reply.getRcontent());
			ps.setString(2, reply.getRwrite());
			ps.setInt(3, reply.getBnum());
			ps.executeUpdate();
			return true;
		} catch(Exception e) {
			System.out.println("��۾��� SQL ���� : "+e);
		}
		return false;
		
	}
	
	// ��� �ҷ����� �޼ҵ�
	public ObservableList<Reply> list() {
		ObservableList<Reply> replylist = FXCollections.observableArrayList();
		try {
			String sql = "select * from  test.reply";
			ps=con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(5)==controller.board.Board.board.getBnum()) {
					Reply reply = new Reply(
							rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getInt(5));
					replylist.add(reply);
				}
			}
			return replylist;
		} catch(Exception e) {
			System.out.println("��� �ҷ����� SQL ���� : "+e);
		}
		return null;
	}
	
	// ��� ���� �޼ҵ�
	public boolean replyupdate(int rnum, String rcontent) {
		try {
			String sql = "update  test.reply set rcontent=? where rnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, rcontent);
			ps.setInt(2, rnum);
			ps.executeUpdate();
			return true;
		} catch(Exception e) {
			System.out.println("��� ���� SQL ���� : "+e);
		}
		return false;
	}
	
	// ��� ���� �޼ҵ�
	public boolean replydelete(int rnum) {
		try {
			String sql = "delete from  test.reply where rnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, rnum);
			ps.executeUpdate();
			return true;
		} catch(Exception e) {
			System.out.println("��ۻ��� SQL ���� : "+e);
		}
		
		return false;
	}
	
}