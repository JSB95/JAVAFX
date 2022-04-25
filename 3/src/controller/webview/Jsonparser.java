package controller.webview;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Jsonparser {
	public static void main(String[] args) {
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
		        String name = obj.getString("name");					// 배열 i번 인덱스에서 key(name)의 value값 저장
//		        String url = obj.getString("global_code");
		        System.out.println("name(" + i + "): " + name);
//		        System.out.println("url(" + i + "): " + url);
		        System.out.println();
		        searchlist.add(i, name);
		    }
			
			
			
		} catch (Exception e) {System.out.println(e);}
	}
}
