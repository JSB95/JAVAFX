package controller.myinfo;

import java.net.URL;
import java.util.ResourceBundle;

import controller.mainpage.Mainpage;
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
    	
//    	if(txtpassword.getText().equals(Login.member.getMpassword)) {	// 로그인 한 객체와 입력한 값과 비번이 같으면
    	if(txtpassword.getText().equals("password")) {	
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("비밀번호 일치");
    		alert.setHeaderText("내 정보 수정 페이지로 이동합니다.");
    		alert.showAndWait();
    		Mainpage.instance.loadmainmenu("/view/myinfo/myinfoUpdate.fxml");
    	}else{
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("비밀번호 불일치");
    		alert.setHeaderText("비밀번호를 확인하세요.");
    		txtpassword.setText(null);
    		alert.showAndWait();
    	}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { }
}
