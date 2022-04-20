package controller.webview;

import java.io.FileInputStream;

public class Filereader {
	
	public static Filereader filereader = new Filereader();
	
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
}
