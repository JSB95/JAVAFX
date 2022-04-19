package controller.select;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import controller.main.Mainpage;
import controller.main.Searchpage;
import dao.AplaneDao;
import dao.RouteDao;
import dto.Aplane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class First implements Initializable {
	
	int j=0;
	
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
			ToggleButton button = new ToggleButton(null,imageView);
			button.setText((i+1)+"");
			button.setId(i+"");
			ArrayList<String> seatlist = RouteDao.routeDao.getseat(Searchpage.route.getRnum());
			if(seatlist!=null) {
				for(String temp : seatlist) {
					if(("F-"+button.getText()).equals(temp) ) {
						button.setDisable(true);
					}
				}
			}
		
			button.setOnAction(e ->{
				try {
					int num = Integer.parseInt(e.toString().split("'")[1]);
					String seat = "F-"+num;
					boolean duplicate = true;
					
					if(Searchpage.person==j) {
						for(String temp : Class.seat) {
							if(temp.equals(seat)) {
								Class.seat.remove(seat);
								duplicate = false;
								j--;
							}
						}
						if(duplicate) {
							Alert alert = new Alert(AlertType.INFORMATION);
							button.setSelected(false);
							alert.setHeaderText("더 이상 선택할 수 없습니다.");
							alert.showAndWait();
							return;
						}
					}
					else if(Searchpage.person>j) {
						for(String temp : Class.seat) {
							if(temp.equals(seat)) {
								Class.seat.remove(seat);
								duplicate = false;
								j--;
							}
						}
						if(duplicate) {
							Class.seat.add(seat);
							j++;
							
						}
					}
				} catch(Exception a) {System.out.println(a);}

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