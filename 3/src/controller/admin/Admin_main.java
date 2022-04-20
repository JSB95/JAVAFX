package controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Admin_main implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
    @FXML
    private Label mypage;

    @FXML
    private Label lbllogout;

    @FXML
    private Label lblcadd;

    @FXML
    private Label lblcupdate;

    @FXML
    private Label lblapadd;

    @FXML
    private Label lblapupdate;

    @FXML
    private Label lblradd;

    @FXML
    private Label lblrupdate;

    @FXML
    private BorderPane boderpane;

    @FXML
    void apadd(MouseEvent event) {

    }

    @FXML
    void apupdate(MouseEvent event) {

    }

    @FXML
    void cadd(MouseEvent event) {

    }

    @FXML
    void cupdate(MouseEvent event) {

    }

    @FXML
    void logout(MouseEvent event) {
    	Main.instance.loadpage("/view/login/login.fxml");
    }

    @FXML
    void radd(MouseEvent event) {

    }

    @FXML
    void rupdate(MouseEvent event) {

    }
}
