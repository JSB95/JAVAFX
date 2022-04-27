package controller.select;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import controller.main.Mainpage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Class implements Initializable {

	public static HashSet<String> seat = new HashSet<>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(seat!=null) {
			seat.clear();
		}

	}
    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	controller.main.Mainpage.instance.loadpage("/view/main/Searchpage.fxml");
    }
	
    @FXML
    void business(MouseEvent event) {
    	Selectseatclass.instance.loadpage("/view/select/business.fxml");
    }

    @FXML
    void economy(MouseEvent event) {
    	Selectseatclass.instance.loadpage("/view/select/economy.fxml");
    }

    @FXML
    void first(MouseEvent event) {
    	Selectseatclass.instance.loadpage("/view/select/first.fxml");
    }
}
