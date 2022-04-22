package controller.admin;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.AplaneDao;
import dao.RouteDao;
import dto.Aplane;
import dto.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Routeadd implements Initializable {

	public Route route;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btndelete.setVisible(false);
		rtable.setVisible(false);
		ObservableList<Aplane> aplanes = AplaneDao.aplaneDao.getairplane();
		HashSet<String> list = new HashSet<>();
		for(Aplane temp : aplanes) {
			list.add(temp.getAname());
		}
		ObservableList<String> alist = FXCollections.observableArrayList(list);
		cbbaname.setItems(alist);
		if(Admin_main.instance.update) {
			btnadd.setText("수정");
			btndelete.setVisible(true);
			lblmenuname.setText("항공일지 수정/삭제");
			rtable.setVisible(true);
			ObservableList<Route> rlist = RouteDao.routeDao.getrlist();
			TableColumn tc = rtable.getColumns().get(0);
			tc.setCellValueFactory(new PropertyValueFactory<>("aname"));
			tc = rtable.getColumns().get(1);
			tc.setCellValueFactory(new PropertyValueFactory<>("rdeparture"));
			tc = rtable.getColumns().get(2);
			tc.setCellValueFactory(new PropertyValueFactory<>("rdestination"));
			tc = rtable.getColumns().get(3);
			tc.setCellValueFactory(new PropertyValueFactory<>("rflightTime"));
			tc = rtable.getColumns().get(4);
			tc.setCellValueFactory(new PropertyValueFactory<>("rbaseprice"));
			tc = rtable.getColumns().get(5);
			tc.setCellValueFactory(new PropertyValueFactory<>("rdeparturedate"));
			rtable.setItems(rlist);
			
			rtable.setOnMouseClicked(e->{
				try {
					route = rtable.getSelectionModel().getSelectedItem();
					txtstart.setText(route.getRdeparture());
					txtend.setText(route.getRdestination());
					txtftime.setText(route.getRflightTime());
					txtprice.setText(Integer.toString(route.getRbaseprice()));
					int i=0;
					for(String temp : alist) {
						if(temp.equals(route.getAname())) {
							break;
						}
						i++;
					}
					String year = route.getRdeparturedate().split(" ")[0].split("-")[0];
					System.out.println(route.getRdeparturedate().split(" ")[0].split("-")[0]);
					txtyear.setText(year);
					String month = route.getRdeparturedate().split(" ")[0].split("-")[1];
					txtmonth.setText(month);
					String day = route.getRdeparturedate().split(" ")[0].split("-")[2];
					txtday.setText(day);
					String hour = route.getRdeparturedate().split(" ")[1].split(":")[0];
					txthour.setText(hour);
					String min = route.getRdeparturedate().split(" ")[1].split(":")[1];
					txtmin.setText(min);
					
				} catch(Exception ee) {}
			});
		}
	
		
	}
	

	

    @FXML
    private Label lblmenuname;

    @FXML
    private TextField txtstart;

    @FXML
    private TextField txtend;

    @FXML
    private TextField txtftime;

    @FXML
    private TextField txtprice;

    @FXML
    private ComboBox<String> cbbaname;

    @FXML
    private TextField txtyear;

    @FXML
    private TextField txtmonth;

    @FXML
    private TextField txtday;

    @FXML
    private TextField txthour;

    @FXML
    private TextField txtmin;

    @FXML
    private Button btnadd;

    @FXML
    private Button btndelete;

    @FXML
    private TableView<Route> rtable;

    @FXML
    void add(ActionEvent event) {
    	String aname = cbbaname.getValue();
    	String departure = txtstart.getText();
    	String destination = txtend.getText();
    	String flighttime = txtftime.getText();
    	
    	if(Admin_main.instance.update) {
    		
    	}else {
    		
    	}
    }

    @FXML
    void delete(ActionEvent event) {
    	String table = "route";
    	int pk = route.getRnum();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("정말 삭제하시겠습니까?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get()==ButtonType.OK) {
    		boolean result = AplaneDao.aplaneDao.delete(table, pk);
        	if(result) {
        		Alert alert2 = new Alert(AlertType.INFORMATION);
        		alert2.setHeaderText("삭제가 완료되었습니다.");
        		alert2.showAndWait();
        		Admin_main.instance.loadpage("/view/admin/routeadd.fxml");
        	}
    	}
    }
	
}
