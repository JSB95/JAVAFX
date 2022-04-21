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
    		// ���̺�信 ���� �ڷ��� 
    
    // ** ��� ���̺� �޼ҵ� [ �޼ҵ�ȭ ��Ų ���� : ������ ���̺� ȣ���ϱ� ���� ]
    public void replytableshow() {
    	// 1. ���� �Խù� ��ȣ 
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
    	
    	// 4. ���̺�信 ����Ʈ �־��ֱ� 
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
        		alert.setHeaderText("������ �Ϸ�Ǿ����ϴ�.");
        		alert.showAndWait();
        		btnredel.setVisible(false);
        		btnreupdate.setVisible(false);
        		btnrewrite.setText("����ۼ�");
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
        		alert.setHeaderText("����� �ۼ� �Ǿ����ϴ�.");
        		alert.showAndWait();
            	Mainpage.instance.loadpage("/view/board/boardview.fxml");
        	}
    	}
    	
    }
    boolean updatecheck = true;
    
    @FXML
    void accreupdate(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	
    	if(updatecheck) { // ���� ����
    		btnreupdate.setVisible(false);
    		alert.setHeaderText("��� ������ �����Ϸ� ��ư �����ּ���");
    		alert.showAndWait();
    		btnrewrite.setText("���� �Ϸ�");
    		reupdatecheck=true;
    		replytableshow();
    	} else {
        
    	}
    }
    @FXML
    void redel(ActionEvent event) {
    	if(reply.getRwrite().equals(Login.member.getMid())) {
    		// 1. ��� �޼��� �˸�
        	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setHeaderText("���� �����Ͻðڽ��ϱ�?");
        	Optional<ButtonType> optional = alert.showAndWait();
        	// 2. Ȯ�� ��ư ��������
        	if(optional.get()==ButtonType.OK) {
        		// 3. ����ó��
        		boolean result = ReplyDao.replyDao.replydelete(reply.getRnum());
        		if(result) {
        			Alert alert2 = new Alert(AlertType.INFORMATION);
            		alert2.setHeaderText("������ �Ϸ�Ǿ����ϴ�.");
            		alert2.showAndWait();
        			reply=null;
        			Mainpage.instance.loadpage("/view/board/boardview.fxml");
        		}else {
        			System.out.println("�������� DB����");
        		}
        	}
		}
    	else {
    		System.out.println("�����Ұ�");
    	}
    	
    }

    boolean upcheck = true; // ���� ��ư ����ġ ����
    
    @FXML
    void update(ActionEvent event) {
    	Alert alert = new Alert( AlertType.INFORMATION );
    	if( upcheck  ) { // ���� ����
	    	alert.setHeaderText("�Խñ� ������ ���� �Ϸ� ��ư �����ּ���");
	    	alert.showAndWait();
	    	txttitle.setEditable(true);
			txtcontent.setEditable(true);
			btnupdate.setText("�����Ϸ�");
			upcheck = false;
    	}else { // ���� �Ϸ�
    		// dbó��
    		BoardDao.boardDao.update(
    				controller.board.Board.board.getBnum() ,
    				txttitle.getText() ,
    				txtcontent.getText() );
    		
    		alert.setHeaderText("������ �Ϸ� �Ǿ����ϴ�.");
	    	alert.showAndWait();
	    	txttitle.setEditable(false);
			txtcontent.setEditable(false);
			btnupdate.setText("����");
			upcheck = true;
    	}
    }
    @FXML
    void back(ActionEvent event) {
    	Mainpage.instance.loadpage("/view/board/board.fxml");

    }

    @FXML
    void delete(ActionEvent event) {
    	// 1. ��� �޽��� �˸�
    	Alert alert = new Alert(AlertType.CONFIRMATION); // Ȯ�� / ��Ұ� �ִ� ��ư
    		alert.setHeaderText("�ش� �Խù� �����ұ��?");
    	Optional<ButtonType> optional = alert.showAndWait(); // showAndWait() �޼ҵ��� ��ȯŸ�� => ������ ��ư
    		// Optional Ŭ���� : null�� ��ü�� �����ϴ� Ŭ����
    	if( optional.get() == ButtonType.OK ) {  // 2. Ȯ�� ��ư ��������
    		// 3. ���� ó�� ����
    		BoardDao.boardDao.delete( 
    				controller.board.Board.board.getBnum()   );
    		// 4. ������ ��ȯ
        	Mainpage.instance.loadpage("/view/board/board.fxml");
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		replytableshow();
		
		Board board = controller.board.Board.board; // board��Ʈ�ѳ� ���̺��� ���õ� ��ü ȣ�� 
		// �� ��Ʈ�ѿ� ���õ� board�� ������ �����ϱ� 
		lblwrite.setText( "�ۼ��� : " + board.getBwrite() );
		lbldate.setText( "�ۼ��� : " + board.getBdate() );
		lblview.setText( "��ȸ�� : " + board.getBview() );
		txttitle.setText( board.getBtitle() );
		txtcontent.setText( board.getBcontent() );
		// ���࿡ �Խù� �ۼ��� �� ����α��ε� id�� �������� ������
		if( ! board.getBwrite().equals( Login.member.getMid() ) ) { // !:����
			btndelete.setVisible(false); // ��ư �����
			btnupdate.setVisible(false); // false = ��ư ����� true = ��ư ���̱�
		}
		// ���� �� ������ ���� ���ϰ� ���� ����
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























