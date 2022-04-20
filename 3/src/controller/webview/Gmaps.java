package controller.webview;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class Gmaps {
	public class Mapsearchfield{
		String name;
		String formatted_address;
		String global_code;
		public Mapsearchfield(String name, String formatted_address, String global_code) {
			this.name = name;
			this.formatted_address = formatted_address;
			this.global_code = global_code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getFormatted_address() {
			return formatted_address;
		}
		public void setFormatted_address(String formatted_address) {
			this.formatted_address = formatted_address;
		}
		public String getGlobal_code() {
			return global_code;
		}
		public void setGlobal_code(String global_code) {
			this.global_code = global_code;
		}
	}
	
	public static Gmaps gmaps = new Gmaps();
	
	public String getAPIkey() {
		try {
			FileInputStream fileInputStream = new FileInputStream("C:\\JAVAlibrary\\gmapsKEY.txt");
			byte[] bytes = new byte[fileInputStream.available()];
			fileInputStream.read(bytes);
			String key = new String(bytes);
			fileInputStream.close();
			return key;
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	String converter(String korean, boolean send ) {	// 구글로 보낼때는 UTF-8로, 받을때는 MS949로 변환(MS949변환은 쓸일 없을듯??? 되는지도 모름)
		String decoded;
    	try {
    		if(send) {	// 보내는경우 : true
    			decoded = URLEncoder.encode(korean, "UTF-8");
	    		return decoded;
    		}else{		// 받는 경우 : false
    			decoded = URLEncoder.encode(korean, "MS949");
        		return decoded;
    		}
		} catch (UnsupportedEncodingException e1) {e1.printStackTrace();}
    	return null;
    }
	
	 public ArrayList<Mapsearchfield> gmapsquery(String UrlData) {
			String totalUrl = UrlData;
			System.out.println(totalUrl);
			URL url;
			HttpsURLConnection conn;
		    
			String responseData = "";
			String returnData = "";
			BufferedReader br = null;
			StringBuffer sb = null;
			
			try {
				url = new URL(totalUrl);	
				conn = (HttpsURLConnection) url.openConnection();
				conn.connect();
				
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));	
				sb = new StringBuffer();	       
				while ((responseData = br.readLine()) != null) {
					sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
				}
		 
				//메소드 호출 완료 시 반환하는 변수에 버퍼 데이터 삽입 실시
				returnData = sb.toString(); 
				
				//http 요청 응답 코드 확인 실시
				String responseCode = String.valueOf(conn.getResponseCode());
				System.out.println("http 응답 코드 : "+responseCode);
				System.out.println("http 응답 데이터 : "+returnData);
				
				ArrayList<Mapsearchfield> resultlist = jsonparser(returnData);
				return resultlist;
			} catch (IOException e) {e.printStackTrace();}finally{ 
				//http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
				try {
					if (br != null) {
						br.close();	
					}						
				} catch (IOException e) {e.printStackTrace();}
			}
			return null;
		}
	 
	 public ArrayList<Mapsearchfield> jsonparser(String json){
	    	try {
				JSONObject jsonObject = new JSONObject(json);
				JSONArray jsonArray = jsonObject.getJSONArray("results");	// 객체화 된 json에서 배열 추출, 구글이 리턴하는 배열명은 'results'
				ArrayList<Mapsearchfield> searchlist = new ArrayList<Mapsearchfield>();
				for (int i = 0; i < jsonArray.length(); i++) {
			        JSONObject obj = jsonArray.getJSONObject(i);
			        String name = obj.getString("name");	
			        System.out.println("name(" + i + "): " + name);// 배열 i번 인덱스에서 key(name)의 value값 저장
			        String formatted_address = obj.getString("formatted_address");	
			        System.out.println("formatted_address(" + i + "): " + formatted_address);
			        
			        JSONObject rootobj = obj.optJSONObject("plus_code");	// 하위오브젝트 plus_code의 키에 접근하기 위해 새로운 JSONObject 선언
			        String global_code;
			        if(rootobj != null) {
				        global_code = rootobj.getString("global_code");	// 하위 키 global_code의 value 빼오기
				        System.out.println("formatted_address(" + i + "): " + global_code);
			        }else {
			        	global_code = null;
			        	System.out.println("global_code(" + i + "): " + global_code);
			        }

			        System.out.print("\n");
			        Mapsearchfield tmp = new Mapsearchfield(name, formatted_address, global_code);
			        searchlist.add(i, tmp);
			    }
				return searchlist;
			} catch (Exception e) {System.out.println(e);}
	    	return null;
	    }
	
}
