package controller.select;

import java.net.URL;
import java.util.ResourceBundle;

import controller.main.Mainpage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Pay implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		for(String temp : Class.seat) {
			System.out.println();
			
			txtname.appendText(temp);
		}
		
		
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
    	Mainpage.instance.loadpage("/view/select/selectseatclass.fxml");
    }

    @FXML
    void pay(ActionEvent event) {

    }
	
}
