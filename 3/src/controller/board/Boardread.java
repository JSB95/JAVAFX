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
    		alert.setTitle("�˸�");
    		alert.setHeaderText("���� ������ �Է��� �ּ���.");
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
		
		
		// ���� ���̺� ���� ��¥�� �α��� �� ������� ������ null�� ������ ������ ��ȸ�� �ø���, ������ �ƹ��͵� ���ϴ� �ڵ�.
			// ���߿� �ٽ� �ϼ��Ұ�.
//    	if ( !(BoardDao.boardDao.nullreplycheck( Login.member.getMid(), board.getBnum() ) ) ) {
//			// NOT ����Ʈ�� �ٿ������� �ݴ�� �ؼ��Ұ� ->  �����߿� ������ null AND �ۼ���ID=����� AND �ۼ���¥=curdate() => if�� �̽���
//		BoardDao.boardDao.viewcountup(board.getBview()+1, board.getBnum());	// DB�� ��ȸ�� 1 �÷��ֱ�
//		board.setBview(board.getBview()+1);	// ��ü �� �޸𸮿� ��ȸ�� 1 �÷��ֱ�
//		Reply writeNullReply = new Reply(0, null, Login.member.getMid(), null, board.getBnum());	// null����(=�÷��� ����) �ۼ��ϱ� ���� ��üȭ
//		BoardDao.boardDao.rwrite(writeNullReply);	// ���� �ۼ�
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setHeaderText(Login.member.getMid()+"���� "+board.getBtitle()+" ���� ���� ó�� ��ȸ�ϼ̽��ϴ�.");
//		alert.showAndWait();
//		}
		
		lbltitle.setText(board.getBtitle());
		txtcontent.setText(board.getBcontent());
		if(board.getBimgurl()!=null) 
			imgshow.setImage(new Image(board.getBimgurl()));
    	
		if(board.getBsnapshoturl()!=null) 	// ������� ¥�ٰ� �Ͻ�����, �� ���� �������̹��� ���ÿ� �����ؾ���.
			// �ȱ׷��� �� �� �ʹ����� ����
			imgsnaphot.setImage(new Image(board.getBsnapshoturl()));
    	
		
		
		
	
		
	}
}
