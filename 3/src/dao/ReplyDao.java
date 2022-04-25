package dao;

<<<<<<< HEAD
=======
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import controller.login.Login;
>>>>>>> parent of 0c620d7 (board.fxml)
import dto.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

<<<<<<< HEAD
public class ReplyDao extends Dao{
	
	public static ReplyDao replyDao = new ReplyDao();
	
	// 1. ���� ����
	public boolean replywrite(Reply reply) {
		try {
			String sql = "insert into reply(bnum, mnum, replycontent, replyid)values(?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setInt(1, reply.getBnum());
			ps.setInt(2, reply.getMnum());
			ps.setString(3, reply.getReplycontent());
			ps.setString(4, reply.getReplyid());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("ReplyDao_replywrite_Exception : "+e);}
		return false;
	}
	
	// 2. ���� �ҷ�����
	public ObservableList<Reply> replylist(int bnum) {
		ObservableList<Reply> replylist = FXCollections.observableArrayList();
		
		try {
		String sql = "select * from reply where bnum = ?";
		ps=con.prepareStatement(sql);
		ps.setInt(1, bnum);
		rs = ps.executeQuery();
		while(rs.next()) {
			// ������ ������ ���� �ƴҶ��� ��üȭ ����
			if( (rs.getString(4)!=null)) {
			Reply reply = new Reply(rs.getInt(1), rs.getInt(2), rs.getInt(3), 
					rs.getString(4), rs.getString(5), rs.getString(6));
			replylist.add(reply);
			}
		}
		return replylist;
		} catch (Exception e) {System.out.println("ReplyDao_replylist_Exception : "+e);}
		return null;
	}
	
	// 3. ���� ����
	public void replymodify(int replynum, String replycontent) {
		try {
			String sql = "update reply set replycontent=? where reply=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, replycontent);
			ps.setInt(2, replynum);
			ps.executeUpdate();
		} catch (Exception e) {System.out.println("ReplyDao_replymodify_Exception : "+e);}
		
	}
	
	// 4. �Խñ� ��ȸ�� �ø��� ���� �����ϱ� ���� �������̺��� �÷��� ã��
	public boolean nullreplycheck(int bnum, int mnum){
		String sql = "select * from reply where rcontent is null and bnum=? and substring_index(rdate, ' ', 1)=curdate() and mnum = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.setInt(2, mnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (Exception e) {System.out.println("ReplyDao_nullreplyhceck_Exception : "+e);}
		return false;
	}
	
	// 5. ��ȸ�� ī��Ʈ ��
	public void viewcountup(int count, int bnum) {
		System.out.println("ī��Ʈ ��!");
		try {
			String sql = "update board set bview=? where bnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, count);
			ps.setInt(2, bnum);
			ps.executeUpdate();
		} catch (Exception e) {System.out.println("ReplyDao_viewcountup_Exception : "+e);}
	}
	
	// 6. ���� ����
	public void delete(int rnum) {
		try {
			String sql = "delete from reply where replynum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, rnum);
			ps.executeUpdate();
		} catch (Exception e) {System.out.println("ReplyDao_idsearchforreplytable_Exception : "+e);}
	}
}
=======
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
>>>>>>> parent of 0c620d7 (board.fxml)
