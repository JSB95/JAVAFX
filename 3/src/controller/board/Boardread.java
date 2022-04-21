package controller.board;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.mainpage.Mainpage;
import dao.BoardDao;
import dto.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Boardread implements Initializable{
	Board board =  Boardcon.boardinstance;

    @FXML
    private Label lbltitle;
    
    @FXML
    private Button btndelete;

    @FXML
    private Button btnback;

    @FXML
    private Button btnupdate;

    @FXML
    private TextArea txtcontent;

    @FXML
    private ImageView imgshow;

    @FXML
    private ImageView imgsnaphot;

    @FXML
    private TextField txtreply;

    @FXML
    private Button btnreply;

    @FXML
    private TableView<?> tablereply;

    @FXML
    void accback(ActionEvent event) {
    	Mainpage.instance.loadmainmenu("/view/board/board.fxml");
    }

    @FXML
    void accreply(ActionEvent event) {
    	if(txtreply.getText().equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("알림");
    		alert.setHeaderText("덧글 내용을 입력해 주세요.");
    		alert.showAndWait();
    	}else {
    		System.out.println(txtreply.getText());
    	}
    }
    
    @FXML
    void accdelete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("확인");
		alert.setHeaderText("글을 수정하시겠습니까?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		BoardDao.boardDao.delete(Boardcon.boardinstance.getBnum());
    		alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("완료");
    		alert.setHeaderText("글 삭제가 완료되었습니다.");
    		alert.showAndWait();
        	Mainpage.instance.loadmainmenu("/view/board/board.fxml");

    	}
    }

    @FXML
    void accupdate(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("수정하기");
    	alert.setHeaderText("글을 수정하시겠습니까?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		Mainpage.instance.loadmainmenu("/view/board/board_write.fxml");
    	}else return;
    	
    }

    @FXML
    void imgclicked(MouseEvent event) {
    	// 별 기능 없음.
    }

    @FXML
    void snapshotsclicked(MouseEvent event) {
    	if(board.getBsnapshoturl()!=null) {
    		String url = board.getBsnapshoturl();
    		// 새 스테이지 띄우는건 좀 있다 하자 
    	}
    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		// 리플 테이블에 오늘 날짜로 로그인 한 사용자의 내용이 null인 리플이 없으면 조회수 올리고, 있으면 아무것도 안하는 코드.
			// 나중에 다시 완성할것.
//    	if ( !(BoardDao.boardDao.nullreplycheck( Login.member.getMid(), board.getBnum() ) ) ) {
//			// NOT 게이트를 붙였음으로 반대로 해석할것 ->  리플중에 내용이 null AND 작성자ID=사용자 AND 작성날짜=curdate() => if문 미실행
//		BoardDao.boardDao.viewcountup(board.getBview()+1, board.getBnum());	// DB에 조회수 1 올려주기
//		board.setBview(board.getBview()+1);	// 객체 내 메모리에 조회수 1 올려주기
//		Reply writeNullReply = new Reply(0, null, Login.member.getMid(), null, board.getBnum());	// null리플(=플래그 역할) 작성하기 위해 객체화
//		BoardDao.boardDao.rwrite(writeNullReply);	// 리플 작성
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setHeaderText(Login.member.getMid()+"님은 "+board.getBtitle()+" 글을 오늘 처음 조회하셨습니다.");
//		alert.showAndWait();
//		}
		
		lbltitle.setText(board.getBtitle());
		txtcontent.setText(board.getBcontent());
		if(board.getBimgurl()!=null) 
			imgshow.setImage(new Image(board.getBimgurl()));
    	
		if(board.getBsnapshoturl()!=null) 	
			imgsnaphot.setImage(new Image(board.getBsnapshoturl()));
		
		txtcontent.setEditable(false);
//////////////////////////////////////////////////////////////////////////////////////////		
//		로그인한 사용자의 mnum과 글의 mnum이 같을때만 글수정 버튼, 글삭제 활성화. 코드 작성해야함.
//////////////////////////////////////////////////////////////////////////////////////////			
	}
}
