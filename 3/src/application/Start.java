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
		stage.setTitle("�װ����� ���α׷�");
		stage.show();
		
		// * �ܺ���Ʈ ����
		// 1. ��Ʈ �ε�
		Font.loadFont( getClass().getResourceAsStream("ImcreSoojin.ttf"), 15);
		// 2. ���� scene�� �ܺ� ��Ÿ�Ͻ�Ʈ ����
		scene.getStylesheets().add( getClass().getResource("application.css").toExternalForm() );
		
		Image image = new Image("/img/logo1.png");
		stage.getIcons().add(image);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
