package controller.main;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Mainpage implements Initializable {

	public static Mainpage instance;
	public Mainpage() {instance=this;}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
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
    	loadpage("/view/MainLayout.fxml");
    }
	
	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			boderpane.setCenter(parent);
		} catch(Exception e) { System.out.println("main ERROR :" + e); }
		
	}
}