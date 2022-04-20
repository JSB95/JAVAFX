package controller.mainpage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Mainpage implements Initializable {

	@FXML
    private BorderPane borderpane;
	
	public static Mainpage instance; 
	
	public Mainpage() {instance = this;}
	
	
	public void loadsidemenu(String url) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(url));
			borderpane.setLeft(parent);
		} catch (Exception e) {System.out.println("loadsidemenu exception : "+e);}
	}
	
	public void loadmainmenu(String url) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(url));
			borderpane.setCenter(parent);
		} catch (Exception e) {System.out.println("Mainpage_loadpage exception : "+e);}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadsidemenu("/view/mainpage/Mainpage_sidemenu.fxml");
		loadmainmenu("/view/mainpage/Mainpage_exchange_rate.fxml");
	}
	
}
