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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Boardwrite implements Initializable{

	@FXML
    private Button btnwrite;

    @FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontent;

    @FXML
    private Button btnback;

    @FXML
    private TextField txtsearch;

    @FXML
    private Button btnsearch;

    @FXML
    private TextField txtimglocation;

    @FXML
    private Button btnsearch1;

    @FXML
    void accback(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("�ڷΰ���");
    	alert.setHeaderText("�ۼ��� ����ϰ� �ڷ� ���ðڽ��ϱ�?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
        	Mainpage.instance.loadmainmenu("/view/board/board.fxml");
    	}else {
    		return;		// ����ڰ� �ڷ� ���ðڽ��ϱ�?? ->> ��� ��ư ������ ��
    	}
    }

    @FXML
    void accsearch(ActionEvent event) {
    	// ���࿡ ����ڰ� ���� ��� ������ �˻���ư -> ���� ��ư���� ��ư�� �ؽ�Ʈ�� ������� �ٲٱ�.
    	String search =txts
    }
    
    String selectedlocation;	// ����ڰ� ������ ���� ������ ����. ���� ���� �ȵ�.
    String key = controller.webview.Filereader.filereader.getAPIkey();	// �� ��Ʈ�� ���Ϸ� ����ִ� ���۸ʽ� api Ű
    @FXML
    void accwrite(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("�Խ��ϱ�");
    	alert.setHeaderText("���� �Խ��Ͻðڽ��ϱ�?");
    	Optional<ButtonType> result = alert.showAndWait();
////////////////////////////////////////////////////////////////////////////////////////////////////
    	if(result.get() == ButtonType.OK) {
    		Board board = new Board(0, 0, txttitle.getText(), txtcontent.getText(), 
    				selectedlocation, null, 0);		
    			// �ڵ� ������ �� �α��� �� ������� ȸ����ȣ ���;���. �������� �ٲ� �� ����.
////////////////////////////////////////////////////////////////////////////////////////////////////
    		BoardDao.boardDao.wrtite(board);
    		alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("�Ϸ�");
    		alert.setHeaderText("�� �Խð� �Ϸ�Ǿ����ϴ�.");
    		alert.showAndWait();
        	Mainpage.instance.loadmainmenu("/view/board/board.fxml");
    	}else {
    		return;	// ����ڰ� ���� �Խ��Ͻðڽ��ϱ�?  ->> ��� ��ư ������ ��
    	}
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }
}
