package controller.main;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Mainpage implements Initializable {

	public static Mainpage instance;
	public Mainpage() {instance=this;}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblmname.setText(Login.member.getMid() + "�� ȯ���մϴ�.");
		lblmpoint.setText("���� ����Ʈ : "+Login.member.getMpoint() + "��");
		
	}
	
    @FXML
    private Label lblhome;
    
    @FXML
    private Label lblmname;

    @FXML
    private Label lblmpoint;
	
    @FXML
    private Label mypage;

    @FXML
    private Label lblmim;

    @FXML
    private Label lblmupdate;

    @FXML
    private Label lblmat;

    @FXML
    private Label lblsearch;

    @FXML
    private Label lblboard;

    @FXML
    private Label lblexchange;

    @FXML
    private Label lbllogout;

    @FXML
    private BorderPane boderpane;

    @FXML
    void logout(MouseEvent event) {
    	Login.member = null;
    	Main.instance.loadpage("/view/login/Login.fxml");
    }
    
    @FXML
    void search(MouseEvent e) {
    	loadpage("/view/main/searchpage.fxml");
    }
    
    @FXML
    void exchange(MouseEvent e) {
    	
    }
    
    @FXML
    void mim(MouseEvent e) {
    	loadpage("/view/myinfo/myinfo.fxml");
    }
    
    @FXML
    void mupdate(MouseEvent e) {
    	loadpage("/view/myinfo/myinfoPasswordCheck.fxml");
    }
    
    @FXML
    void board(MouseEvent e) {
    	loadpage("/view/board/board.fxml");
    }
    
    @FXML
    void mat(MouseEvent e) {
    	loadpage("/view/main/myticket.fxml");
    }
    
    @FXML
    void home(MouseEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("�˸�");
    	alert.setHeaderText("�ʱ� �������� ���ư��ϴ�.");
    	alert.showAndWait();
    	Main.instance.loadpage("/view/main/Mainpage.fxml");
    }
	
	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			boderpane.setCenter(parent);
		} catch(Exception e) { System.out.println("main ������ �ҷ����� ���� :" + e); }
		
	}
}
