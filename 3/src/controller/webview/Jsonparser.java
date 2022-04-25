package controller.webview;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Jsonparser {
	public static void main(String[] args) {
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
		        String name = obj.getString("name");					// �迭 i�� �ε������� key(name)�� value�� ����
//		        String url = obj.getString("global_code");
		        System.out.println("name(" + i + "): " + name);
//		        System.out.println("url(" + i + "): " + url);
		        System.out.println();
		        searchlist.add(i, name);
		    }
			
			
			
		} catch (Exception e) {System.out.println(e);}
	}
}
