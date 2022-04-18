package controller.webview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web implements Initializable{
	
    @FXML
    private TextField txturl;

    @FXML
    private Button btnconnect;

    @FXML
    private Button btnhome;

    @FXML
    private WebView mywebview;

    @FXML
    void accconnect(ActionEvent event) {
    	
    	loadpage();
    }

    @FXML
    void acchome(ActionEvent event) {
    	engine.load(getClass().getResource("/googlemap.html").toString());
    }
    
    private WebEngine engine;
    
    public void loadpage() {
//    	engine.load(getClass().getResource("/googlemap.html").toString());	// 로컬 주소 호출용
    	engine.load("http://"+txturl.getText());
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	engine = mywebview.getEngine();
    	engine.load(getClass().getResource("/googlemap.html").toString());
    }
    
    
    
}


