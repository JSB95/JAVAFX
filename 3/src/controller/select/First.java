package controller.select;

import java.net.URL;
import java.util.ResourceBundle;

import controller.main.Mainpage;
import controller.main.Searchpage;
import dao.AplaneDao;
import dto.Aplane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class First implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(vbox.getChildren().isEmpty()==false) {
			vbox.getChildren().remove(0);
		}
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10));
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		Aplane aplane = AplaneDao.aplaneDao.getaplane(Searchpage.route.getAname());
		int i=0;
		for(int row=0; row<aplane.getAfirstSeatCount(); row++) {
			ImageView imageView = new ImageView("/img/first.png");
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);
			Button button = new Button(null,imageView);
			button.setText((i+1)+"");
			button.setId(i+"");
			button.setOnAction(e ->{
				int num = Integer.parseInt(e.toString().split("'")[1]);
				String seat = "F-"+num;
				Class.seat.add(seat);
				if(Class.seat!=null) {
					for(int j=0; j<Class.seat.size(); j++) {
						System.out.println(Class.seat.get(j));
					}
				}
				
			});
			gridPane.add(button, row, 1);
			i++;
		}
		vbox.getChildren().add(gridPane);
	}

	
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;

    @FXML
    private Button btnselect;
    
    @FXML
    private Button btnback;

    
    @FXML
    void back(ActionEvent event) {
    	Selectseatclass.instance.loadpage("/view/select/class.fxml");
    }

    @FXML
    void select(ActionEvent event) {
    	Mainpage.instance.loadpage("/view/select/pay.fxml");
    }
	
}
