package dao;

import dto.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao extends Dao{

	public static BoardDao boardDao = new BoardDao(); // Boarddao class를 정적 형태로 메모리에 적재
	String sql;
	
	// 1. 글쓰기 메서드
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
	
	// 2. 전체 글 목록 가져오기 메서드
	public ObservableList<Board> list()	{
		// * 리스트 선언
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		try {
			sql = "select * from test.board";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {	// 다음 레코드가 없을 때 까지 반복
				Board board = new Board(rs.getInt(1), rs.getInt(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				boardlist.add(board);
			}
			return boardlist;
		} catch (Exception e) {System.out.println("BoadrdDao_list_method_exception : "+e);}
		return null;
	}
	// 3. 글 삭제 메서드
	public void delete(int bnum) {
		try {
			sql = "delete from board where bnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
		} catch (Exception e) {System.out.println("BoardDao_delete_method_exception : "+e);}
	}
	
	// 4. 글 수정 메서드
	public boolean update(Board board) {
		try {
			sql = "update board set btitle=?, blocation=?, bsnapshoturl=?, bimgurl=? where bnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getBtitle());
			ps.setString(2, board.getBlocation());
			ps.setString(3, board.getBsnapshoturl());
			ps.setString(4, board.getBimgurl());
			ps.setInt(5, board.getBnum());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("BoardDao_update_method_exception : "+e);}
		return false;
	}
	
	// 5. 글 찾기 메서드
	
	// 7. 핫게시글 2개 뽑아내기 메서드
	
	
	
}
