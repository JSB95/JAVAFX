package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Boardcon implements Initializable{
	
    @FXML
    private TableView<?> boardtable;

    @FXML
    private ImageView imghotphoto1;

    @FXML
    private Label lblhottitle1;

    @FXML
    private Label lblhotwriter1;

    @FXML
    private ImageView imghotphoto2;

    @FXML
    private Label lblhottitle2;

    @FXML
    private Label lblhotwriter2;

    @FXML
    private Button btnwrite;

    @FXML
    private TextField txtsearch;

    @FXML
    private Button btnsearch;

    @FXML
    void accsearch(ActionEvent event) {

    }

    @FXML
    void accwrite(ActionEvent event) {

    }

    @FXML
    void hotcontent1(MouseEvent event) {

    }

    @FXML
    void hotcontent2(MouseEvent event) {

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }

}
