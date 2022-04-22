package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Login implements Initializable {

	public static Member member;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
	
    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtid;

    @FXML
    private Button btnlogin;

    @FXML
    private Label lblfindid;

    @FXML
    private Label lblfindpassword;

    @FXML
    private Label lblsignup;

    @FXML
    void login(ActionEvent event) {
    	login();
    }
    
    public void signup(MouseEvent e) {
    	Main.instance.loadpage("/view/login/Signup.fxml");
    }
    
    @FXML
    void enterlogin(ActionEvent event) {
    	login();
    }
    
	void login() {
		String id = txtid.getText();
    	String password = txtpassword.getText();
    	boolean result = MemberDao.memberDao.login(id, password);
    	if(result) {
    		member = MemberDao.memberDao.getMember(id);
    		Main.instance.loadpage("/view/main/Mainpage.fxml");
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("일치하는 회원정보가 없습니다.");
    		alert.showAndWait();
    	}
	}
	
	@FXML
	void findid() {
		Main.instance.loadpage("/view/login/findid.fxml");
	}
	
	@FXML
	void findpw() {
		Main.instance.loadpage("/view/login/findpw.fxml");
	}
	
}