package controller.admin;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Admin_main implements Initializable {

	public static Admin_main instance;
	public Admin_main() {instance = this;}
	
	public boolean update = false;
	
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
    	update = false;
    	loadpage("/view/admin/airplaneadd.fxml");
    }

    @FXML
    void apupdate(MouseEvent event) {
    	update = true;
    	loadpage("/view/admin/airplaneadd.fxml");
    }

    @FXML
    void cadd(MouseEvent event) {
    	update = false;
    	loadpage("/view/admin/companyadd.fxml");
    }

    @FXML
    void cupdate(MouseEvent event) {
    	update = true;
    	loadpage("/view/admin/companyadd.fxml");
    }

    @FXML
    void logout(MouseEvent event) {
    	Main.instance.loadpage("/view/login/login.fxml");
    }

    @FXML
    void radd(MouseEvent event) {
    	update=false;
    	loadpage("/view/admin/routeadd.fxml");
    }

    @FXML
    void rupdate(MouseEvent event) {
    	update=true;
    	loadpage("/view/admin/routeadd.fxml");
    }
    
    public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(page));
			boderpane.setCenter(parent);
		} catch(Exception e) { System.out.println("admin ������ �ҷ����� ���� :" + e); }
		
	}
    
}
