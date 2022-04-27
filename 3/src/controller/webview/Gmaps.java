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
		/*
		 * name : 검색 결과의 명칭
		 * formatted_address : 해당(검색대상이 위치한)국가의 체계로 변환된 주소
		 * place_id : 지도 스냅샷에 저장할 링크에 쓰일 정확한 검색대상
		 * latitude : 지도 스냅샷 찍을때 url에 넣을 위도
		 * longtitude : 스냅샷 찍을때 쓸 경도
		 */
		String name;
		String formatted_address;
		String place_id;
		String latitude;
		String longitude;
		public Mapsearchfield(String name, String formatted_address,String place_id, String latitude, String longitude) {
			this.name = name;
			this.formatted_address = formatted_address;
			this.place_id = place_id;
			this.latitude = latitude;
			this.longitude = longitude;
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
		public String getPlace_id() {
			return place_id;
		}
		public void setPlace_id(String place_id) {
			this.place_id = place_id;
		}
		public void setFormatted_address(String formatted_address) {
			this.formatted_address = formatted_address;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
	}
	
	public static Gmaps gmaps = new Gmaps();
	
	public String getAPIkey() {
		try {
//			FileInputStream fileInputStream = new FileInputStream("C:\\JAVAlibrary\\gmapsKEY.txt");
			FileInputStream fileInputStream = new FileInputStream("C:\\gmapsKEY.txt");
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
				//http 요청 및 응답 완료 후 BufferedReader를 닫기
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
			        String place_id = obj.getString("place_id");	
			        System.out.println("place_id(" + i + "): " + place_id);

			        
			        obj = obj.optJSONObject("geometry");	// 하위오브젝트 plus_code의 키에 접근하기 위해 새로운 JSONObject 선언
			        obj = obj.optJSONObject("location");
				    String latitude = obj.getDouble("lat")+"";	// 하위 키 global_code의 value 빼오기
				    String longitude = obj.getDouble("lng")+"";	// 구글에서 리턴하는 위도 경도는 float형태이나 변환하기 커찮으니 그냥 String으로 바로 변환 저장.
				    System.out.println("latitude(" + i + "): " + latitude);
				    System.out.println("longitude(" + i + "): " + longitude);
			        System.out.print("\n");
			        Mapsearchfield tmp = new Mapsearchfield(name, formatted_address, place_id, latitude, longitude);
			        searchlist.add(i, tmp);
			    }
				return searchlist;
			} catch (Exception e) {System.out.println(e);}
	    	return null;
	    }
	
}
