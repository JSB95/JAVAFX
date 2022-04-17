package controller.main;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import controller.login.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Mainpage implements Initializable {

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
    private Label lbllogout;

    @FXML
    private Label lblmdelete;

    @FXML
    private DatePicker startdate;

    @FXML
    private Button btnsearch;

    @FXML
    private ComboBox<?> cbbstartplace;

    @FXML
    private ComboBox<?> cbbdestination;

    @FXML
    private VBox exchange;

    @FXML
    void search(ActionEvent event) {
    	Main.instance.loadpage("/view/main/Searchpage.fxml");
    }
	
    @FXML
    void logout() {
    	Login.member = null;
    	Main.instance.loadpage("/view/login/Login.fxml");
    }
}
