package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.mainpage.Mainpage;
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
    	System.out.println("accsearch");
    }

    @FXML
    void accwrite(ActionEvent event) {
    	Mainpage.instance.loadmainmenu("/view/board/board_write.fxml");
    }

    @FXML
    void hotcontent1(MouseEvent event) {
    	System.out.println("hotcontent1");

    }

    @FXML
    void hotcontent2(MouseEvent event) {
    	System.out.println("hotcontent2");

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	System.out.println("initialized");

    }

}
