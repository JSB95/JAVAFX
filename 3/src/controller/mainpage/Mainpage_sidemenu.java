package controller.mainpage;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Mainpage_sidemenu implements Initializable{

    @FXML
    private AnchorPane sidemenu;

    @FXML
    private Label lblmim;

    @FXML
    private Label lblmupdate;

    @FXML
    private Label lbltest;

    @FXML
    private Label lblboard;

    @FXML
    private Label lblmdelete;

    @FXML
    void clickboard(MouseEvent event) {
    	Mainpage.instance.loadmainmenu("/view/board/board.fxml");
    }
    
    @FXML
    void myinfo(MouseEvent event) {
    	Mainpage.instance.loadmainmenu("/view/myinfo/myinfo.fxml");
    }
    
    @FXML
    void updateinfo(MouseEvent event) {
    	// ������ �����ϱ� fxml�� �̵�.
    	
    	Mainpage.instance.loadmainmenu("/view/myinfo/myinfoPasswordCheck.fxml");
    	System.out.println("test1");
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("sidemenu initialized");
	}
	
    @FXML
    void test(MouseEvent event) {
    	Mainpage.instance.loadmainmenu("/view/mainpage/web.fxml");
    }
	
}
