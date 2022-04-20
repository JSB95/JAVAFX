package controller.myinfo;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import controller.main.Mainpage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class MyinfoPasswordCheck implements Initializable{
	
    @FXML
    private PasswordField txtpassword;
    
    @FXML
    private Button btncomplete;

    @FXML
    void acccomplete(ActionEvent event) {
    	
    	if(txtpassword.getText().equals(Login.member.getMpassword())) {	// �α��� �� ��ü�� �Է��� ���� ����� ������	
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("��й�ȣ ��ġ");
    		alert.setHeaderText("�� ���� ���� �������� �̵��մϴ�.");
    		alert.showAndWait();
    		Mainpage.instance.loadpage("/view/myinfo/myinfoUpdate.fxml");
    	}else{
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("��й�ȣ ����ġ");
    		alert.setHeaderText("��й�ȣ�� Ȯ���ϼ���.");
    		txtpassword.setText(null);
    		alert.showAndWait();
	    }
    	
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { }
}
