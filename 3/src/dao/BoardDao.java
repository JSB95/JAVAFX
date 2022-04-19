package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import dto.Board;
import dto.MemberView;
import dto.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {
	
	// �ʵ� 
	private Connection conn; // 1. DB ���� Ŭ����
	private PreparedStatement ps; // 2. ����� DB�� SQL ���� �������̽�
	private ResultSet rs; // 3. SQL ��� ���ڵ带 �������� �������̽�
	
	public static BoardDao boardDao = new BoardDao(); // db���� ��ü
	
	//������
	public BoardDao() { // �����ڿ��� �����ϴ� ���� : ��ü ������ �ٷ� db�����ϱ� ���� 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // JDBC ����̺� Ŭ���� ȣ��
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC",
					"root","1234");
		}catch( Exception e ) {}
	}
	// �޼ҵ�
		// 1. �۾��� �޼ҵ�
	public boolean write( Board board) {   
		try {
		// 1. SQL �ۼ�
			String sql = "insert into board(btitle , bcontent , bwrite) values(?,?,?)";
		// 2. SQL ����
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , board.getBtitle() ); 
			ps.setString( 2 , board.getBcontent() ); 
			ps.setString( 3 , board.getBwrite() );
		// 3. SQL ����
			ps.executeUpdate(); // insert , update, delete <----> ps.executeQuery(); // select
		// 4. SQL ���
			return true;
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
		return false;  
	}
		// 2. ��� �� ȣ�� �޼ҵ� [ ��ȯŸ�� : ObservableList
	public ObservableList<Board> list() {
		// * ����Ʈ ���� 
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		try {
		// 1. SQL �ۼ�
				// ���� : order by �ʵ�� asc   [ �ش� �ʵ�� �������� �������� ]
				//		 order by �ʵ�� desc	 [ �ش� �ʵ�� �������� �������� ] 
			String sql = "select * from board order by bnum desc";
		// 2. SQL ����
			ps = conn.prepareStatement(sql);
		// 3. SQL ����
			rs = ps.executeQuery(); //select
		// 4. SQL ���
			// rs : �˻� ����� ���ڵ带 �ϳ��� ��ȸ[��������=next()]
				// rs.next() : �˻������ ���� ���ڵ� 
				// rs.get�ڷ���( �ش��ʵ������ȣ ) : �� ���ڵ忡�� �ش� �ʵ��� ������ ��������
			while( rs.next() ) { // ���� ���ڵ尡 ���������� �ݺ�
				// 1. ��üȭ
				Board board = new Board( rs.getInt(1) ,
						rs.getString(2),
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5), 
						rs.getInt(6));
				// 2. �ش� ��ü�� ����Ʈ�� ��� 
				boardlist.add(board); 
			}
			// ������ ����Ʈ ��ȯ 
			return boardlist;
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
		return null; // ���н� null
	}
		// 3. �� ���� �޼ҵ�
	public boolean delete( int bnum ) { 
		try {
			String sql = "delete from board where bnum=?"; // 1. SQL �ۼ�
			ps = conn.prepareStatement(sql); // 2. SQL ����
			ps.setInt( 1 , bnum);
			ps.executeUpdate(); // 3. SQL ���� 
			return true; // 4. SQL ���
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
		return false; 
	}
	public static void viewSave() {
		try {
			FileOutputStream outputStream = new FileOutputStream("D:/viewlist.txt");
			for(MemberView temp : controller.board.Board.mview) {
				String Save = temp.getId()+","+temp.getBnum()+","+temp.getDate()+"\n";
				outputStream.write(Save.getBytes());
			}
		}
		catch(Exception e) {
			
		}
	}
	public static void viewLoad() {
		try {
			FileInputStream inputStream = new FileInputStream("D:/viewlist.txt");
			byte[] bytes = new byte[1024]; 
			inputStream.read(bytes); 
			String file = new String(bytes); 
			String[]list = file.split("\n"); 
			int i = 0;
			for(String temp : list) {
				if( i+1 == list.length ) {
					break; 
				}
				String[] filed = temp.split(",");
				MemberView temp2 = new MemberView(filed[0],Integer.parseInt(filed[1]),filed[2]);
				controller.board.Board.mview.add(temp2);
				i++;
			}
			
		}
		catch(Exception e) {
		}
	}
	public boolean view(int num,int view,String id) {
		BoardDao.viewLoad();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String today = sdf.format(new Date());
			for(MemberView temp : controller.board.Board.mview) {
				if(temp.getId().equals(id) && temp.getBnum() == num) {
					if(temp.getDate().equals(today)) {
						break;
					}
					else if((!temp.getDate().equals(today))){
						System.out.println("��ȸ�� ����");
						temp.setDate(today);
						String sql = "update board set bview=? where bnum=?";
						// 2. sql ����
						ps = conn.prepareStatement(sql);
						int new_view = view + 1;
						controller.board.Board.board.setBview(new_view);
						ps.setInt(1, new_view);
						System.out.println(new_view);
						ps.setInt(2, num);
						// 3. SQL ����
						ps.executeUpdate();
						BoardDao.viewSave();
						break;
					}
				}
			}
			// 1. SQL �ۼ�
			// select * from ���̺�� where ����=( �ʵ�� = �� )
			
			
			return true;
		}
		catch(Exception e) {
			System.out.println("[SQL ��ȸ�� ���� ] "+ e);
		}
		return false;
	}
		// 4. �� ���� �޼ҵ�
	public boolean update( int bnum , String title , String content ) {
		try {
		// 1. SQL �ۼ�
			String sql = "update board set btitle=? , bcontent=? where bnum=?";
		// 2. SQL ����
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , title );
			ps.setString( 2 , content );
			ps.setInt( 3 , bnum );
		// 3. SQL ����
			ps.executeUpdate();
		// 4. SQL ���
			return true;
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
		return false; 
	}
	
	// 5. ��� �ۼ� �޼ҵ�[ ȸ������, �۾��� ���� ]
	public boolean rwrite( Reply reply ) {
		try {
			String sql = "insert into reply(rcontent,rwrite,bnum)values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString( 1 , reply.getRcontent() );
			ps.setString( 2 , reply.getRwrite() );
			ps.setInt( 3 , reply.getBnum() );
			ps.executeUpdate();
			return true;
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
		return false;
	}
	
	// 6. �ش� �Խù��� ��۵� ȣ�� �޼ҵ� 
	public ObservableList<Reply> replylist( int bnum ){
		
		ObservableList<Reply> replylist = FXCollections.observableArrayList();
		
		try {
			String sql = "select * from reply where bnum =? order by rnum desc";
			ps = conn.prepareStatement(sql);
			ps.setInt( 1 , bnum);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				Reply reply = new Reply(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getInt(5));
				replylist.add(reply);
			}
			
			return replylist;
			
		}catch(Exception e ) { System.out.println( "[SQL ����]"+e  ); }
			return null;
	}
}





































