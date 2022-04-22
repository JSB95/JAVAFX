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

public class Findpw implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
    @FXML
    private TextField txtname;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtphone;

    @FXML
    private Button btnfindpw;

    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	Main.instance.loadpage("/view/login/Login.fxml");
    }

    @FXML
    void findpw(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String p = txtphone.getText();
    	String pattern = "(^[0-9]{3})([0-9]{3,4})([0-9]{4})$";
    	String phone =p.replaceAll(pattern, "$1-$2-$3");
    	String password = MemberDao.memberDao.findpw(txtname.getText(),txtid.getText(), phone);
    	if(password!=null) {
    		alert.setHeaderText("비밀번호 찾기 성공");
    		alert.setContentText("회원님의 비밀번호는 : "+password+" 입니다.");
    		alert.showAndWait();
    	}else {
    		alert.setHeaderText("일치하는 회원정보가 없습니다.");
    		alert.showAndWait();
    	}
    }
    
	
	
}
