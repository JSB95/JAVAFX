package controller.webview;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;

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
    
    String result;
    
    boolean send;
    
    String converter(String korean, boolean send ) {	// 구글로 보낼때는 UTF-8로, 받을때는 MS949로 변환
    	try {
    		if(send) {	// 보내는경우 : true
	    		result = URLEncoder.encode(korean, "UTF-8");
	    		return result;
    		}else{		// 받는 경우 : false
    			result = URLEncoder.encode(korean, "MS949");
        		return result;
    		}
		} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}
    	return null;
    }
    
    public void loadpage(String search) {
//    	engine.load(getClass().getResource("/googlemap.html").toString());	// 로컬 주소 호출용
    	String convert = converter(search, true);
    	String jsonform = "{\"name\" : \""+search+"\"}";
    	httpconnection("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+convert+"&key=APIKEY", jsonform);
    	
    	
    }
    public void httpconnection(String UrlData, String ParamData) {
		
		//http 요청 시 필요한 url 주소를 변수 선언
		String totalUrl = UrlData;
		System.out.println(totalUrl);
		
		//http 통신을 하기위한 객체 선언 실시
		URL url = null;
		HttpsURLConnection conn = null;
	    
		//http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
		String responseData = "";	    	   
		BufferedReader br = null;
		StringBuffer sb = null;
	    
		//메소드 호출 결과값을 반환하기 위한 변수
		String returnData = "";
	 
		try {
			//파라미터로 들어온 url을 사용해 connection 실시
			url = new URL(totalUrl);	
			conn = (HttpsURLConnection) url.openConnection();
	             
//			//http 요청 실시
			conn.connect();

			
			//http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));	
			
			byte[] bytes = new byte[conn.getInputStream().available()];
			System.out.println(conn.getInputStream().available());
			System.out.println("=======================================");
			
			conn.getInputStream().read(bytes);
			String form = new String(bytes);
			JSONObject jsonObject = new JSONObject(form);	//json의 객체화
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
			
			
			
			
			
			
			
//			
//			sb = new StringBuffer();	       
//			while ((responseData = br.readLine()) != null) {
//				sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
//			}
//	 
//			//메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
//			returnData = sb.toString(); 
//			
//			//http 요청 응답 코드 확인 실시
//			String responseCode = String.valueOf(conn.getResponseCode());
//			System.out.println("http 응답 코드 : "+responseCode);
//			System.out.println("http 응답 데이터 : "+returnData);
	 
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
			//http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
			try {
				if (br != null) {
					br.close();	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	 		
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
    	engine.load("https://www.google.com/maps/@?api=1&map_action=map&query=인천공항");

    }
}


