package controller.login;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import controller.Main;
import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Signup implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	

    @FXML
    private TextField txtid;

    @FXML
    private Button btnidcheck;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private PasswordField txtconfirmpw;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtphone;

    @FXML
    private Button btnphonecheck;

    @FXML
    private TextField txtcardname;

    @FXML
    private TextField txtpassportnum;

    @FXML
    private Button btnsignup;

    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	Main.instance.loadpage("/view/login/Login.fxml");
    }

    @FXML
    void idcheck(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String column="mid";
    	String id = txtid.getText();
    	String pattern = "^[0-9|a-z|A-Z]*$";
    	if(id.length()<4 || id.length()>10) {
    		alert.setHeaderText("���̵��� ���̴� 4~10 ���̷� �Է����ּ���.");
    		alert.showAndWait();
    		txtid.setText("");
    		txtid.requestFocus();
    		return;
    	}
		if(!Pattern.matches(pattern, id)){
			alert.setHeaderText("���̵� Ư������,����,�ѱ��� ���Ե� �� �����ϴ�.");
			alert.showAndWait();
			txtid.setText("");
    		txtid.requestFocus();
			return ;
		}
    	boolean result = MemberDao.memberDao.check(column, id);
    	if(result==false) {
    		System.out.println("�ߺ�üũ ���");
    		Alert alert2 = new Alert(AlertType.CONFIRMATION);
    		alert2.setHeaderText("��� ������ ���̵��Դϴ�. ����Ͻðڽ��ϱ�?");
    		Optional<ButtonType> optional = alert2.showAndWait();
    		if(optional.get()==ButtonType.OK) {
    			txtid.setDisable(true);
    			btnidcheck.setDisable(true);
    		
    		}
    	}else {
    		alert.setHeaderText("�ش� ���̵�� ������Դϴ�. �ٸ� ���̵� �Է����ּ���.");
    		alert.showAndWait();
    		txtid.setText("");
    		txtid.requestFocus();
    	}
    }

    @FXML
    void phonecheck(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String column="mphone";
    	String p = txtphone.getText();
    	
    	String pattern = "(^[0-9]{3})([0-9]{3,4})([0-9]{4})$";
    	String phone = p.replaceAll(pattern, "$1-$2-$3");
    	
    	if(!Pattern.matches(pattern, p)){
			alert.setHeaderText("��ȭ��ȣ ���� ����");
			alert.showAndWait();
			txtphone.setText("");
    		txtphone.requestFocus();
			return ;
		}
    	boolean result = MemberDao.memberDao.check(column, phone);
    	if(result==false) {
    		alert.setHeaderText("��� ������ �޴���ȭ��ȣ �Դϴ�.");
    		alert.showAndWait();
    		txtphone.setText(phone);
    		txtphone.setDisable(true);
			btnphonecheck.setDisable(true);
    	} else {
    		alert.setHeaderText("�ش� ��ȭ��ȣ�� ������Դϴ�. �ٸ� ��ȭ��ȣ�� �Է����ּ���.");
    		alert.showAndWait();
    		txtphone.setText("");
    		txtphone.requestFocus();
    	}
    }

    @FXML
    void signup(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String id = txtid.getText();
    	String password = txtpassword.getText();
    	String passwordconfirm = txtconfirmpw.getText();
    	String name = txtname.getText();
    	String card = txtcardname.getText();
    	String passport = txtpassportnum.getText();
    	String phone = txtphone.getText();
    	if(!btnidcheck.isDisable()) {
    		alert.setHeaderText("���̵� �ߺ�üũ ��ư�� �����ּ���.");
    		alert.showAndWait();
    		return;
    	}
    	if(!password.equals(passwordconfirm)) {
    		alert.setHeaderText("��й�ȣ�� ��й�ȣ Ȯ�ΰ� ��ġ���� �ʽ��ϴ�.");
    		alert.showAndWait();
    		return;
    	}
    	///////// ��й�ȣ ��ȿ�� üũ ���� �߰� /////////////
    	///////// �̸� ��ȿ�� üũ ���� �߰� /////////////
    	if(!btnphonecheck.isDisable()) {
    		alert.setHeaderText("��ȭ��ȣ �ߺ�üũ ��ư�� �����ּ���.");
    		alert.showAndWait();
    		return;
    	}
    	///////// ī���ȣ ��ȿ�� üũ ���� �߰� /////////////
    	///////// ���ǹ�ȣ ��ȿ�� üũ ���� �߰� /////////////
    	Member member = new Member(0, id, password, name, phone, passport, card);
    	boolean result = MemberDao.memberDao.signup(member);
    	if(result) {
    		alert.setHeaderText("ȸ�������� �Ϸ�Ǿ����ϴ�.");
    		alert.setContentText("�α��� �������� �Ѿ�ϴ�.");
    		alert.showAndWait();
    		Main.instance.loadpage("/view/login/Login.fxml");
    	}else {
    		alert.setHeaderText("ȸ������ ����! : DB ���� [�����ڿ��� ����]");
    		alert.showAndWait();
    		return;
    	}
    }
	
	
	
}
