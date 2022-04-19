package controller.main;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;

import dao.RouteDao;
import dto.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Searchpage implements Initializable {

	public static Route route;
	
	public static int person;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String rdeparture = "rdeparture";
		String rdestination = "rdestination";
		HashSet<String> departure = RouteDao.routeDao.getdeparture(rdeparture);
		HashSet<String> destination = RouteDao.routeDao.getdeparture(rdestination);
		ObservableList<String> combox1 = FXCollections.observableArrayList(departure);
		cbbstartplace.setItems(combox1);
		ObservableList<String> combox2 = FXCollections.observableArrayList(destination);
		cbbdestination.setItems(combox2);
		ObservableList<Integer> choicebox = FXCollections.observableArrayList();
		for(int i=1; i<11; i++) {
			choicebox.add(i);
		}
		cbbperson.setItems(choicebox);
		searchtable.setOnMouseClicked(e ->{
			int selectrnum = searchtable.getSelectionModel().getSelectedItem().getRnum();
			route = RouteDao.routeDao.numgetroute(selectrnum);
			Mainpage.instance.loadpage("/view/select/selectseatclass.fxml");
		});
		cbbperson.getSelectionModel().selectFirst();
		cbbstartplace.getSelectionModel().selectFirst();
		cbbdestination.getSelectionModel().selectFirst();
	}
	

    @FXML
    private DatePicker startdate;

    @FXML
    private Button btnsearch;

    @FXML
    private ComboBox<String> cbbstartplace;

    @FXML
    private ComboBox<String> cbbdestination;

    @FXML
    private ComboBox<Integer> cbbperson;
    
    @FXML
    private TableView<Route> searchtable;
    
    @FXML
    private Button btnback;
    
    LocalDate date;

    @FXML
    void search(ActionEvent event) {
    	date = startdate.getValue();
    	Alert alert = new Alert(AlertType.INFORMATION);
    	person = cbbperson.getValue();
    	if(date==null) {
    		alert.setHeaderText("날짜를 선택해주세요.");
    		alert.showAndWait();
    		return;
    	}
    	searchtableshow();
    }
    
    void searchtableshow() {
    	String departure = cbbstartplace.getValue();
    	String destination = cbbdestination.getValue();
    	ObservableList<Route> searchlist = FXCollections.observableArrayList();
    	ObservableList<Route> slist = RouteDao.routeDao.getroute(departure, destination);
    	for(int i=0; i<slist.size(); i++) {
    		String cname = RouteDao.routeDao.getcname(slist.get(i).getAname());
    		Route route = new Route(
    				slist.get(i).getRnum(), 
        			slist.get(i).getAname(), 
        			cname, 
        			"도착시간", 
        			slist.get(i).getRflightTime(), 
        			slist.get(i).getRdeparturedate(), 
        			slist.get(i).getRbaseprice());
    		searchlist.add(route);
    	}

    	TableColumn tc = searchtable.getColumns().get(0);
    	tc.setCellValueFactory(new PropertyValueFactory<>("rdeparture"));
    	
    	tc = searchtable.getColumns().get(1);
    	tc.setCellValueFactory(new PropertyValueFactory<>("aname"));
    	
    	tc = searchtable.getColumns().get(2);
    	tc.setCellValueFactory(new PropertyValueFactory<>("rdeparturedate"));
    	
    	tc = searchtable.getColumns().get(3);
    	tc.setCellValueFactory(new PropertyValueFactory<>("rdestination"));
    	
    	tc = searchtable.getColumns().get(4);
    	tc.setCellValueFactory(new PropertyValueFactory<>("rflightTime"));
    	
    	tc = searchtable.getColumns().get(5);
    	tc.setCellValueFactory(new PropertyValueFactory<>("rbaseprice"));
    	
    	searchtable.setItems(searchlist);
    }
	
}
