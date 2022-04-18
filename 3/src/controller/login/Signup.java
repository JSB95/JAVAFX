package controller.login;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
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
    		alert.setHeaderText("아이디의 길이는 4~10 사이로 입력해주세요.");
    		alert.showAndWait();
    		txtid.setText("");
    		txtid.requestFocus();
    		return;
    	}
		if(!Pattern.matches(pattern, id)){
			alert.setHeaderText("아이디에 특수문자,공백,한글은 포함될 수 없습니다.");
			alert.showAndWait();
			txtid.setText("");
    		txtid.requestFocus();
			return ;
		}
    	boolean result = MemberDao.memberDao.check(column, id);
    	if(result==false) {
    		System.out.println("중복체크 통과");
    		Alert alert2 = new Alert(AlertType.CONFIRMATION);
    		alert2.setHeaderText("사용 가능한 아이디입니다. 사용하시겠습니까?");
    		Optional<ButtonType> optional = alert2.showAndWait();
    		if(optional.get()==ButtonType.OK) {
    			txtid.setDisable(true);
    			btnidcheck.setDisable(true);
    		
    		}
    	}else {
    		alert.setHeaderText("해당 아이디는 사용중입니다. 다른 아이디를 입력해주세요.");
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
			alert.setHeaderText("전화번호 형식 오류");
			alert.showAndWait();
			txtphone.setText("");
    		txtphone.requestFocus();
			return ;
		}
    	boolean result = MemberDao.memberDao.check(column, phone);
    	if(result==false) {
    		alert.setHeaderText("사용 가능한 휴대전화번호 입니다.");
    		alert.showAndWait();
    		txtphone.setText(phone);
    		txtphone.setDisable(true);
			btnphonecheck.setDisable(true);
    	} else {
    		alert.setHeaderText("해당 전화번호는 사용중입니다. 다른 전화번호를 입력해주세요.");
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
    	///////////// 유효성 검사 ////////////////
//    	if(!btnidcheck.isDisable()) {
//    		alert.setHeaderText("아이디 중복체크 버튼을 눌러주세요.");
//    		alert.showAndWait();
//    		return;
//    	}
//    	if(password.length()<1 || passwordconfirm.length()<1) {
//    		alert.setHeaderText("비밀번호를 입력해주세요.");
//    		alert.showAndWait();
//    		return;
//    	}
//    	if(!password.equals(passwordconfirm)) {
//    		alert.setHeaderText("비밀번호가 비밀번호 확인과 일치하지 않습니다.");
//    		alert.showAndWait();
//    		return;
//    	}
//    	 Pattern pwpattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
//    	 Matcher pwmatcher = pwpattern.matcher(password);
//    	 if(!pwmatcher.find()) {
//    		alert.setHeaderText("비밀번호는 영문과 특수문자, 숫자를 포함하며 8자 이상이어야 합니다.");
//     		alert.showAndWait();
//     		return;
//    	 }
//    	if(name.length()<1) {
//    		alert.setHeaderText("이름을 입력해주세요.");
//    		alert.showAndWait();
//    		return;
//    	}
//    	String namepattern = "^[a-z|A-Z|\\s]*$";
//    	if(!Pattern.matches(namepattern, name)){
//    		alert.setHeaderText("이름에는 한글,숫자,특수문자가 들어갈 수 없습니다.");
//    		alert.showAndWait();
//    		return;
//    	}
//    	if(!btnphonecheck.isDisable()) {
//    		alert.setHeaderText("전화번호 중복체크 버튼을 눌러주세요.");
//    		alert.showAndWait();
//    		return;
//    	}
//    	String cardpattern = "^[0-9]{16}$";
//    	if(!Pattern.matches(cardpattern, card)){
//    		alert.setHeaderText("카드번호 형식 오류");
//    		alert.showAndWait();
//    		return;
//    	}
//    	if(passport.length()<1) {
//    		alert.setHeaderText("여권번호를 입력해주세요.");
//    		alert.showAndWait();
//    		return;
//    	}
    	///////// 여권번호 유효성 체크 추후 추가 /////////////
    	Member member = new Member(0, id, password, name, phone, passport, card);
    	boolean result = MemberDao.memberDao.signup(member);
    	if(result) {
    		alert.setHeaderText("회원가입이 완료되었습니다.");
    		alert.setContentText("로그인 페이지로 넘어갑니다.");
    		alert.showAndWait();
    		Main.instance.loadpage("/view/login/Login.fxml");
    	}else {
    		alert.setHeaderText("회원가입 실패! : DB 오류 [관리자에게 문의]");
    		alert.showAndWait();
    		return;
    	}
    }
	
	
	
}
