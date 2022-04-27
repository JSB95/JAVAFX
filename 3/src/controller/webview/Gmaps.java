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
		 * name : �˻� ����� ��Ī
		 * formatted_address : �ش�(�˻������ ��ġ��)������ ü��� ��ȯ�� �ּ�
		 * place_id : ���� �������� ������ ��ũ�� ���� ��Ȯ�� �˻����
		 * latitude : ���� ������ ������ url�� ���� ����
		 * longtitude : ������ ������ �� �浵
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
	
	String converter(String korean, boolean send ) {	// ���۷� �������� UTF-8��, �������� MS949�� ��ȯ(MS949��ȯ�� ���� ������??? �Ǵ����� ��)
		String decoded;
    	try {
    		if(send) {	// �����°�� : true
    			decoded = URLEncoder.encode(korean, "UTF-8");
	    		return decoded;
    		}else{		// �޴� ��� : false
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
					sb.append(responseData); //StringBuffer�� ������� ������ ���������� ���� �ǽ�
				}
		 
				//�޼ҵ� ȣ�� �Ϸ� �� ��ȯ�ϴ� ������ ���� ������ ���� �ǽ�
				returnData = sb.toString(); 
				
				//http ��û ���� �ڵ� Ȯ�� �ǽ�
				String responseCode = String.valueOf(conn.getResponseCode());
				System.out.println("http ���� �ڵ� : "+responseCode);
				System.out.println("http ���� ������ : "+returnData);
				
				ArrayList<Mapsearchfield> resultlist = jsonparser(returnData);
				return resultlist;
			} catch (IOException e) {e.printStackTrace();}finally{ 
				//http ��û �� ���� �Ϸ� �� BufferedReader�� �ݱ�
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
				JSONArray jsonArray = jsonObject.getJSONArray("results");	// ��üȭ �� json���� �迭 ����, ������ �����ϴ� �迭���� 'results'
				ArrayList<Mapsearchfield> searchlist = new ArrayList<Mapsearchfield>();
				for (int i = 0; i < jsonArray.length(); i++) {
			        JSONObject obj = jsonArray.getJSONObject(i);
			        String name = obj.getString("name");	
			        System.out.println("name(" + i + "): " + name);// �迭 i�� �ε������� key(name)�� value�� ����
			        String formatted_address = obj.getString("formatted_address");	
			        System.out.println("formatted_address(" + i + "): " + formatted_address);
			        String place_id = obj.getString("place_id");	
			        System.out.println("place_id(" + i + "): " + place_id);

			        
			        obj = obj.optJSONObject("geometry");	// ����������Ʈ plus_code�� Ű�� �����ϱ� ���� ���ο� JSONObject ����
			        obj = obj.optJSONObject("location");
				    String latitude = obj.getDouble("lat")+"";	// ���� Ű global_code�� value ������
				    String longitude = obj.getDouble("lng")+"";	// ���ۿ��� �����ϴ� ���� �浵�� float�����̳� ��ȯ�ϱ� Ŀ������ �׳� String���� �ٷ� ��ȯ ����.
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
