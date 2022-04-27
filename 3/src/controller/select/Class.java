package controller.select;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private Label drag_business;

    @FXML
    private Label drag_first;

    @FXML
    private Label drag_economy;
	
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
    

    @FXML
    void drag(MouseEvent event) {
    	drag_first.setVisible(true);
    }
    

    @FXML
    void release(MouseEvent event) {
    	drag_first.setVisible(false);
    }
    

    
    @FXML
    void drag_economy(MouseEvent event) {
    	drag_economy.setVisible(true);
    }
    
    @FXML
    void exit_economy(MouseEvent event) {
    	drag_economy.setVisible(false);
    }
    
    @FXML
    void drag_business(MouseEvent event) {
    	drag_business.setVisible(true);
    }
    
    @FXML
    void exit_business(MouseEvent event) {
    	drag_business.setVisible(false);
    }
}
