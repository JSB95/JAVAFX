package dao;

import java.sql.DriverManager;

import dto.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao extends Dao{

	public static BoardDao boardDao = new BoardDao(); // Boarddao class�� ���� ���·� �޸𸮿� ����
	String sql;
	
	// 1. �۾��� �޼���
	public boolean wrtite(Board board) {
		try {
			sql = "insert board(mnum, btitle, bcontent, blocation, bsnapshoturl, bimgurl) values(?, ?, ?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, board.getBnum());
			ps.setString(2, board.getBtitle());
			ps.setString(3, board.getBcontent());
			ps.setString(4, board.getBlocation());
			ps.setString(5, board.getBsnapshoturl());
			ps.setString(6, board.getBimgurl());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("BoardDao_wrtite_method_exception : "+e);}
		return false;
	}
	
	// 2. ��ü �� ��� �������� �޼���
	public ObservableList<Board> list()	{
		// * ����Ʈ ����
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		try {
			sql = "select * from test.board";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {	// ���� ���ڵ尡 ���� �� ���� �ݺ�
				Board board = new Board(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				boardlist.add(board);
			}
			return boardlist;
		} catch (Exception e) {System.out.println("BoadrdDao_list_method_exception : "+e);}
		return null;
	}
	// 3. �� ���� �޼���
	
	// 4. �� ���� �޼���
	
	// 5. �� ã�� �޼���
	
	// 6. �� ���� �޼���
	
	// 7. �ְԽñ� 2�� �̾Ƴ��� �޼���
	
	
	
}
