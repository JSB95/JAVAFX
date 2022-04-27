package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Start extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("항공예매 프로그램");
		stage.show();
		
		// * 외부폰트 설정
		// 1. 폰트 로드
		Font.loadFont( getClass().getResourceAsStream("ImcreSoojin.ttf"), 15);
		// 2. 현대 scene에 외부 스타일시트 적용
		scene.getStylesheets().add( getClass().getResource("application.css").toExternalForm() );
		
		Image image = new Image("/img/logo1.png");
		stage.getIcons().add(image);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
