package controller.myinfo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import dao.MemberDao;
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
import javafx.scene.control.TextInputDialog;

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
		Member m = MemberDao.memberDao.getMember(Login.member.getMnum());
		if(txtpassword.getText().length()<1 && txtpasswordconfirm.getText().length()<1){
			txtpassword.setText(m.getMpassword());
			txtpasswordconfirm.setText(m.getMpassword());

		}

    	if(txtpassword.getText().equals(txtpasswordconfirm.getText() ) ){
    		if(txtphone.getText()!=null && txtcard.getText()!=null && txtpassport.getText()!=null) {
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("확인");
        		alert.setHeaderText("정보를 수정하시겠습니까?");
        		Optional<ButtonType> result = alert.showAndWait();
        		if(result.get() == ButtonType.OK) {
        			Member member = new Member(m.getMnum(), m.getMid(), txtpassword.getText(), m.getMname(), txtphone.getText(), txtpassport.getText(), txtcard.getText(), m.getMsince(), m.getMpoint());
        			MyinfoDao.myinfodao.update(member);
        			Login.member=MemberDao.memberDao.getMember(Login.member.getMnum());
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("완료");
            		alert.setHeaderText("내 정보 수정이 완료되었습니다.");
            		txtpassword.setText(null);
            		txtpasswordconfirm.setText(null);
            		alert.showAndWait();
            		Main.instance.loadpage("/view/main/Mainpage.fxml");
            		
            		
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
	@FXML
	void accdelete(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("탈퇴 재확인");
		dialog.setHeaderText("정말 탈퇴하시겠습니까?");
		dialog.setContentText("탈퇴하시려면 '탈퇴' 를 입력해주세요->");
		Optional<String> result = dialog.showAndWait();
		
		System.out.println(result);
		try {
			if(result.get().equals("탈퇴")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("탈퇴 완료");
				alert.setHeaderText("탈퇴처리가 완료되었습니다.");
				MyinfoDao.myinfodao.delete(Login.member.getMnum());		
				alert.showAndWait();
				Login.member=null;
				Main.instance.loadpage("/view/login/login.fxml");
			}else if(!(result.get().equals("탈퇴"))){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("불일치");
				alert.setHeaderText("탈퇴 문자를 잘못 입력하셨습니다.");
				alert.showAndWait();
			}
		} catch (Exception e) {System.out.println("Myinfoupdate_TextInputDialog_exception : "+e+"result : "+result);
		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	txtphone.setText(Login.member.getMphone());
    	txtcard.setText(Login.member.getMcard());
    	txtpassport.setText(Login.member.getMpassport());


	}

}
