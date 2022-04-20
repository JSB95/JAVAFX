package controller.mainpage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

public class Mainpage_exchange_rate implements Initializable {

    @FXML
    private DatePicker startdate;

    @FXML
    private Button btnsearch;

    @FXML
    private ComboBox<?> cbbstartplace;

    @FXML
    private ComboBox<?> cbbdestination;

    @FXML
    private ChoiceBox<?> cbperson;

    @FXML
    private VBox exchange;

    @FXML
    void date(ActionEvent event) {

    }

    @FXML
    void destination(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void startplace(ActionEvent event) {

    }
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }
    
}
