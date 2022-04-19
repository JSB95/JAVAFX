package controller.webview;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Web implements Initializable{
	
    @FXML
    private TextField txtsearch;

    @FXML
    private Button btnsearch;

    @FXML
    private Button btnhome;

    @FXML
    private WebView mywebview;

    @FXML
    void accsearch(ActionEvent event) {
    	String search = txtsearch.getText();
    	txtsearch.setText("");
    	loadpage(search);
    }

    @FXML
    void acchome(ActionEvent event) {
    	engine.load(getClass().getResource("/googlemap.html").toString());
    	}
    
    private WebEngine engine;
    
    private File file = new File("c:\\adb\\searchdata.json");
    
    public void loadpage(String search) {
//    	engine.load(getClass().getResource("/googlemap.html").toString());	// 로컬 주소 호출용
    	engine.setUserDataDirectory(file);
    	engine.load("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+search+"&key=APIKEY");
    	
    }
    

    
    public ArrayList<String> jsonparser(String json){
    	try {
			FileInputStream fileInputStream = new FileInputStream("c:\\adb\\incheon.json");
			byte[] bytes = new byte[fileInputStream.available()];	// 읽어들인 제이슨파일 크기만큼 바이트 배열 선언
			fileInputStream.read(bytes);
			String test = new String(bytes);	// 바이트배열을 문자열로 변환
//			System.out.println(test);
			fileInputStream.close();			// fileInputStream 종료
//			System.out.println(test.length());
//			System.out.println("---------------------");
			
			
			JSONObject jsonObject = new JSONObject(test);	//json의 객체화
//			System.out.println(jsonObject);
			JSONArray jsonArray = jsonObject.getJSONArray("results");	// 객체화 된 json에서 배열 추출
			ArrayList<String> searchlist = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject obj = jsonArray.getJSONObject(i);
		        String name = obj.getString("name");	
		        System.out.println("name(" + i + "): " + name);// 배열 i번 인덱스에서 key(name)의 value값 저장
//		        String global_code = obj.getString("global_code");

//		        System.out.println("global_code(" + i + "): " + global_code);
		        System.out.print("\n");
		        searchlist.add(i, name);
		    }
			return searchlist;
		} catch (Exception e) {System.out.println(e);}
    	return null;
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	engine = mywebview.getEngine();
//    	engine.load(getClass().getResource("/googlemap.html").toString());
//    	engine.load("https://www.google.com/maps/@?api=1&map_action=map&query=인천공항");

    }
}


