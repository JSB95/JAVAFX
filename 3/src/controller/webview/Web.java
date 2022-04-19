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
    
    String converter(String korean, boolean send ) {	// ���۷� �������� UTF-8��, �������� MS949�� ��ȯ
    	try {
    		if(send) {	// �����°�� : true
	    		result = URLEncoder.encode(korean, "UTF-8");
	    		return result;
    		}else{		// �޴� ��� : false
    			result = URLEncoder.encode(korean, "MS949");
        		return result;
    		}
		} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}
    	return null;
    }
    
    public void loadpage(String search) {
//    	engine.load(getClass().getResource("/googlemap.html").toString());	// ���� �ּ� ȣ���
    	String convert = converter(search, true);
    	String jsonform = "{\"name\" : \""+search+"\"}";
    	httpconnection("https://maps.googleapis.com/maps/api/place/textsearch/json?query="+convert+"&key=APIKEY", jsonform);
    	
    	
    }
    public void httpconnection(String UrlData, String ParamData) {
		
		//http ��û �� �ʿ��� url �ּҸ� ���� ����
		String totalUrl = UrlData;
		System.out.println(totalUrl);
		
		//http ����� �ϱ����� ��ü ���� �ǽ�
		URL url = null;
		HttpsURLConnection conn = null;
	    
		//http ��� ��û �� ���� ���� �����͸� ��� ���� ����
		String responseData = "";	    	   
		BufferedReader br = null;
		StringBuffer sb = null;
	    
		//�޼ҵ� ȣ�� ������� ��ȯ�ϱ� ���� ����
		String returnData = "";
	 
		try {
			//�Ķ���ͷ� ���� url�� ����� connection �ǽ�
			url = new URL(totalUrl);	
			conn = (HttpsURLConnection) url.openConnection();
	             
//			//http ��û �ǽ�
			conn.connect();

			
			//http ��û �� ���� ���� �����͸� ���ۿ� �״´�
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));	
			
			byte[] bytes = new byte[conn.getInputStream().available()];
			System.out.println(conn.getInputStream().available());
			System.out.println("=======================================");
			
			conn.getInputStream().read(bytes);
			String form = new String(bytes);
			JSONObject jsonObject = new JSONObject(form);	//json�� ��üȭ
//			System.out.println(jsonObject);
			JSONArray jsonArray = jsonObject.getJSONArray("results");	// ��üȭ �� json���� �迭 ����
			ArrayList<String> searchlist = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject obj = jsonArray.getJSONObject(i);
		        String name = obj.getString("name");	
		        System.out.println("name(" + i + "): " + name);// �迭 i�� �ε������� key(name)�� value�� ����
//		        String global_code = obj.getString("global_code");

//		        System.out.println("global_code(" + i + "): " + global_code);
		        System.out.print("\n");
		        searchlist.add(i, name);
		    }
			
			
			
			
			
			
			
//			
//			sb = new StringBuffer();	       
//			while ((responseData = br.readLine()) != null) {
//				sb.append(responseData); //StringBuffer�� ������� ������ ���������� ���� �ǽ�
//			}
//	 
//			//�޼ҵ� ȣ�� �Ϸ� �� ��ȯ�ϴ� ������ ���� ������ ���� �ǽ�
//			returnData = sb.toString(); 
//			
//			//http ��û ���� �ڵ� Ȯ�� �ǽ�
//			String responseCode = String.valueOf(conn.getResponseCode());
//			System.out.println("http ���� �ڵ� : "+responseCode);
//			System.out.println("http ���� ������ : "+returnData);
	 
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
			//http ��û �� ���� �Ϸ� �� BufferedReader�� �ݾ��ݴϴ�
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
			byte[] bytes = new byte[fileInputStream.available()];	// �о���� ���̽����� ũ�⸸ŭ ����Ʈ �迭 ����
			fileInputStream.read(bytes);
			String test = new String(bytes);	// ����Ʈ�迭�� ���ڿ��� ��ȯ
//			System.out.println(test);
			fileInputStream.close();			// fileInputStream ����
//			System.out.println(test.length());
//			System.out.println("---------------------");
			
			
			JSONObject jsonObject = new JSONObject(test);	//json�� ��üȭ
//			System.out.println(jsonObject);
			JSONArray jsonArray = jsonObject.getJSONArray("results");	// ��üȭ �� json���� �迭 ����
			ArrayList<String> searchlist = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
		        JSONObject obj = jsonArray.getJSONObject(i);
		        String name = obj.getString("name");	
		        System.out.println("name(" + i + "): " + name);// �迭 i�� �ε������� key(name)�� value�� ����
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
    	engine.load("https://www.google.com/maps/@?api=1&map_action=map&query=��õ����");

    }
}


