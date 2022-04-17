package controller.myinfo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.MyinfoDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Myinfoupdate implements Initializable {

	@FXML
	private PasswordField txtpassword;

	@FXML
	private PasswordField txtpasswordconfirm;

	@FXML
	private TextField txtphone;

	@FXML
	private TextField txtcard;

	@FXML
	private TextField txtpassport;

	@FXML
	void accupdate(ActionEvent event) {
		if(txtpassword.getText()==null && txtpasswordconfirm.getText()==null){
//			txtpassword.setText(Login.member.getMpassword);
//			txtpasswordconfirm.setText(Login.member.getMpassword);
			txtpassword.setText("password");
			txtpasswordconfirm.setText("password");
		}

    	if(txtpassword.getText().equals(txtpasswordconfirm.getText() ) ){
    		if(txtphone.getText()!=null && txtcard.getText()!=null && txtpassport.getText()!=null) {
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("확인");
        		alert.setHeaderText("정보를 수정하시겠습니까?");
        		Optional<ButtonType> result = alert.showAndWait();
        		if(result.get() == ButtonType.OK) {
        			Member member = new Member(1, "godoklife", "password", "김아무개", "010-1111-2222", "M11112222", "1111-2222-3333-4444");
//        			Member member = new Member(Login.member.getMnum, null, null, null, null, null, null)
        			MyinfoDao.myinfodao.update(member);
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("완료");
            		alert.setHeaderText("내 정보 수정이 완료되었습니다.");
            		txtpassword.setText(null);
            		txtpasswordconfirm.setText(null);
            		alert.showAndWait();
            		
        		}else {
        			txtpassword.setText(null);
            		txtpasswordconfirm.setText(null);
        		}
        		
    		}
    	}else if(txtpassword.getText()!=null && txtpasswordconfirm.getText()!=null){
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("비밀번호 불일치");
    		alert.setHeaderText("비밀번호를 확인하세요.");
    		txtpassword.setText(null);
    		txtpasswordconfirm.setText(null);
    		alert.showAndWait();
    	}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//    	txtphone.setText(Login.member.getMphone);
//    	txtcard.setText(Login.member.getMcard);
//    	txtpassport.setText(Login.member.getMpassport);

		txtphone.setText("010-1234-5678");
		txtcard.setText("5409-2605-6666-6666");
		txtpassport.setText("M12345678");
	}

}
