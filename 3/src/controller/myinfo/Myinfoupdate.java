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
    			alert.setTitle("Ȯ��");
        		alert.setHeaderText("������ �����Ͻðڽ��ϱ�?");
        		Optional<ButtonType> result = alert.showAndWait();
        		if(result.get() == ButtonType.OK) {
        			Member member = new Member(m.getMnum(), m.getMid(), txtpassword.getText(), m.getMname(), txtphone.getText(), txtpassport.getText(), txtcard.getText(), m.getMsince(), m.getMpoint());
        			MyinfoDao.myinfodao.update(member);
        			Login.member=MemberDao.memberDao.getMember(Login.member.getMnum());
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("�Ϸ�");
            		alert.setHeaderText("�� ���� ������ �Ϸ�Ǿ����ϴ�.");
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
    		alert.setTitle("��й�ȣ ����ġ");
    		alert.setHeaderText("��й�ȣ�� Ȯ���ϼ���.");
    		txtpassword.setText(null);
    		txtpasswordconfirm.setText(null);
    		alert.showAndWait();
    	}
	}
	@FXML
	void accdelete(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Ż�� ��Ȯ��");
		dialog.setHeaderText("���� Ż���Ͻðڽ��ϱ�?");
		dialog.setContentText("Ż���Ͻ÷��� 'Ż��' �� �Է����ּ���->");
		Optional<String> result = dialog.showAndWait();
		if(result.get().equals("Ż��")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ż�� �Ϸ�");
			alert.setHeaderText("Ż��ó���� �Ϸ�Ǿ����ϴ�.");
			MyinfoDao.myinfodao.delete(Login.member.getMnum());		
			alert.showAndWait();
			Login.member=null;
			Main.instance.loadpage("/view/login/login.fxml");
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����ġ");
			alert.setHeaderText("Ż�� ���ڸ� �߸� �Է��ϼ̽��ϴ�.");
			alert.showAndWait();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	txtphone.setText(Login.member.getMphone());
    	txtcard.setText(Login.member.getMcard());
    	txtpassport.setText(Login.member.getMpassport());


	}

}
