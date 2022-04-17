package controller.main;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Pay implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
    @FXML
    private TextField txtname;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtphone;

    @FXML
    private Button btnpay;

    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	Main.instance.loadpage("/view/main/Searchpage.fxml");
    }

    @FXML
    void pay(ActionEvent event) {

    }
	
}
