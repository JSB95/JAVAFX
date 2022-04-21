package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import dao.BoardDao;
import dto.Board;
import dto.Reply;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    void accupdate(ActionEvent event) {
    	
    }

    @FXML
    void imgclicked(MouseEvent event) {

    }

    @FXML
    void snapshotsclicked(MouseEvent event) {
    	if(board.getBsnapshoturl()!=null) {
    		board.getBsnapshoturl();
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
    	
		if(board.getBsnapshoturl()!=null) 	// 여기까지 짜다가 일시정지, 글 쓸때 스냅샷이미지 로컬에 저장해야함.
			// 안그러면 내 돈 너무많이 깨짐
			imgsnaphot.setImage(new Image(board.getBsnapshoturl()));
    	
		
		
		
	
		
	}
}
