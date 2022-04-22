package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Findid implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
    @FXML
    private TextField txtname;

    @FXML
    private TextField txtphone;

    @FXML
    private Button btnfindid;

    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	Main.instance.loadpage("/view/login/Login.fxml");
    }

    @FXML
    void findid(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String p = txtphone.getText();
    	String pattern = "(^[0-9]{3})([0-9]{3,4})([0-9]{4})$";
    	String phone =p.replaceAll(pattern, "$1-$2-$3");
    	String id = MemberDao.memberDao.findid(txtname.getText(), phone);
    	if(id!=null) {
    		alert.setHeaderText("���̵� ã�� ����");
    		alert.setContentText("ȸ������ ���̵�� : "+id+" �Դϴ�.");
    		alert.showAndWait();
    	}else {
    		alert.setHeaderText("��ġ�ϴ� ȸ�������� �����ϴ�.");
    		alert.showAndWait();
    	}
    }
	
}