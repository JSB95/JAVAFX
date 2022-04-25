package controller.board;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.login.Login;
import controller.main.Mainpage;
import dao.BoardDao;
import dao.ReplyDao;
import dto.Board;
import dto.Reply;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Boardview implements Initializable {
	public static Reply reply;
	
    @FXML
    private Button btnrewrite;

    @FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontent;

    @FXML
    private Button btnback;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;
    
    @FXML
    private Button btnreupdate;
    
    @FXML
    private Button btnredel;

    @FXML
    private Label lblwrite;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblview;

    @FXML
    private TextArea txtrecontent;
    

    @FXML
    private TableView<Reply> replytable;
    		// 테이블뷰에 넣을 자료형 
    
    // ** 댓글 테이블 메소드 [ 메소드화 시킨 이유 : 여러번 테이블 호출하기 위해 ]
    public void replytableshow() {
    	// 1. 현재 게시물 번호 
    	int bnum = controller.board.Board.board.getBnum();
    	// 2.
    	ObservableList<Reply> replylist = BoardDao.boardDao.replylist( bnum );
    	// 3. 
    	TableColumn tc = replytable.getColumns().get(0);
    	tc.setCellValueFactory( new PropertyValueFactory<>("rnum") );
    	 tc = replytable.getColumns().get(1);
    	tc.setCellValueFactory( new PropertyValueFactory<>("rwrite") );
    	 tc = replytable.getColumns().get(2);
    	tc.setCellValueFactory( new PropertyValueFactory<>("rdate") );
    	 tc = replytable.getColumns().get(3);
    	tc.setCellValueFactory( new PropertyValueFactory<>("rcontent") );
    	
    	// 4. 테이블뷰에 리스트 넣어주기 
    	replytable.setItems(replylist);
    }
    boolean reupdatecheck = false;
 
    @FXML
    void rewrite(ActionEvent event) {
    	if(reupdatecheck) {
    		String rcontent = txtrecontent.getText();
        	int rnum = reply.getRnum();
        	boolean result = BoardDao.boardDao.reupdate(rnum, rcontent);
        	if(result) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setHeaderText("수정이 완료되었습니다.");
        		alert.showAndWait();
        		btnredel.setVisible(false);
        		btnreupdate.setVisible(false);
        		btnrewrite.setText("댓글작성");
        		reupdatecheck=false;
            	Mainpage.instance.loadpage("/view/board/boardview.fxml");
        		
        	}
    	}else {
    		String recontent = txtrecontent.getText();
        	String rwrite = Login.member.getMid();
        	int bnum = controller.board.Board.board.getBnum();
        	
        	Reply reply = new Reply(0, recontent, rwrite, null, bnum);
        	boolean result = ReplyDao.replyDao.wirte(reply);
        	if(result) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setHeaderText("댓글이 작성 되었습니다.");
        		alert.showAndWait();
            	Mainpage.instance.loadpage("/view/board/boardview.fxml");
        	}
    	}
    	
    }
    boolean updatecheck = true;
    
    @FXML
    void accreupdate(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	
    	if(updatecheck) { // 수정 시작
    		btnreupdate.setVisible(false);
    		alert.setHeaderText("댓글 수정후 수정완료 버튼 눌러주세요");
    		alert.showAndWait();
    		btnrewrite.setText("수정 완료");
    		reupdatecheck=true;
    		replytableshow();
    	} else {
        
    	}
    }
    @FXML
    void redel(ActionEvent event) {
    	if(reply.getRwrite().equals(Login.member.getMid())) {
    		// 1. 경고 메세지 알림
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setHeaderText("정말 삭제하시겠습니까?");
        	Optional<ButtonType> optional = alert.showAndWait();
        	// 2. 확인 버튼 눌렀을때
        	if(optional.get()==ButtonType.OK) {
        		// 3. 삭제처리
        		boolean result = ReplyDao.replyDao.replydelete(reply.getRnum());
        		if(result) {
        			Alert alert2 = new Alert(AlertType.INFORMATION);
            		alert2.setHeaderText("삭제가 완료되었습니다.");
            		alert2.showAndWait();
        			reply=null;
        			Mainpage.instance.loadpage("/view/board/boardview.fxml");
        		}else {
        			System.out.println("삭제실패 DB오류");
        		}
        	}
		}
    	else {
    		System.out.println("삭제불가");
    	}
    	
    }

    boolean upcheck = true; // 수정 버튼 스위치 변수
    
    @FXML
    void update(ActionEvent event) {
    	Alert alert = new Alert( AlertType.INFORMATION );
    	if( upcheck  ) { // 수정 시작
	    	alert.setHeaderText("게시글 수정후 수정 완료 버튼 눌러주세요");
	    	alert.showAndWait();
	    	txttitle.setEditable(true);
			txtcontent.setEditable(true);
			btnupdate.setText("수정완료");
			upcheck = false;
    	}else { // 수정 완료
    		// db처리
    		BoardDao.boardDao.update(
    				controller.board.Board.board.getBnum() ,
    				txttitle.getText() ,
    				txtcontent.getText() );
    		
    		alert.setHeaderText("수정이 완료 되었습니다.");
	    	alert.showAndWait();
	    	txttitle.setEditable(false);
			txtcontent.setEditable(false);
			btnupdate.setText("수정");
			upcheck = true;
    	}
    }
    @FXML
    void back(ActionEvent event) {
    	Mainpage.instance.loadpage("/view/board/board.fxml");

    }

    @FXML
    void delete(ActionEvent event) {
    	// 1. 경고 메시지 알림
    	Alert alert = new Alert(AlertType.CONFIRMATION); // 확인 / 취소가 있는 버튼
    		alert.setHeaderText("해당 게시물 삭제할까요?");
    	Optional<ButtonType> optional = alert.showAndWait(); // showAndWait() 메소드의 반환타입 => 선택한 버튼
    		// Optional 클래스 : null를 객체로 저장하는 클래스
    	if( optional.get() == ButtonType.OK ) {  // 2. 확인 버튼 눌렀을때
    		// 3. 삭제 처리 진행
    		BoardDao.boardDao.delete( 
    				controller.board.Board.board.getBnum()   );
    		// 4. 페이지 전환
        	Mainpage.instance.loadpage("/view/board/board.fxml");
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		replytableshow();
		
		Board board = controller.board.Board.board; // board컨트롤내 테이블에서 선택된 객체 호출 
		// 각 컨트롤에 선택된 board의 데이터 설정하기 
		lblwrite.setText( "작성자 : " + board.getBwrite() );
		lbldate.setText( "작성일 : " + board.getBdate() );
		lblview.setText( "조회수 : " + board.getBview() );
		txttitle.setText( board.getBtitle() );
		txtcontent.setText( board.getBcontent() );
		// 만약에 게시물 작성자 와 현재로그인된 id와 동일하지 않으면
		if( ! board.getBwrite().equals( Login.member.getMid() ) ) { // !:부정
			btndelete.setVisible(false); // 버튼 숨기기
			btnupdate.setVisible(false); // false = 버튼 숨기기 true = 버튼 보이기
		}
		// 제목 과 내용을 수정 못하게 수정 금지
		txttitle.setEditable(false);
		txtcontent.setEditable(false);
		
		replytable.setOnMouseClicked(e ->{
			reply = replytable.getSelectionModel().getSelectedItem();
			if(reply.getRwrite().equals(Login.member.getMid())) {
				btnreupdate.setVisible(true);
				btnredel.setVisible(true);
			}
			else {
				btnredel.setVisible(false);
				btnreupdate.setVisible(false);
			}
		});
	}
}