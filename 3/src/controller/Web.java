package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web implements Initializable{
	
    @FXML
    private WebView web;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	web = new WebView();
//    	WebEngine engine = web.getEngine();
    	web.getEngine().load("http://google.com");
    }
    
}
