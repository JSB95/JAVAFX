package controller.select;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Business implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;

    @FXML
    private Button btnselect;
    
    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	Selectseatclass.instance.loadpage("/view/select/class.fxml");
    }

    @FXML
    void select(ActionEvent event) {

    }
}
