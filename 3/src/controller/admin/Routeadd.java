package controller.admin;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
		cbbaname.getSelectionModel().selectFirst();
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
					cbbaname.getSelectionModel().select(i);
					
					String year = route.getRdeparturedate().split(" ")[0].split("-")[0];
					String month = route.getRdeparturedate().split(" ")[0].split("-")[1];
					String day = route.getRdeparturedate().split(" ")[0].split("-")[2];
					String hour = route.getRdeparturedate().split(" ")[1].split(":")[0];
					String min = route.getRdeparturedate().split(" ")[1].split(":")[1];
					txtyear.setText(year);
					txtmonth.setText(month);
					txtday.setText(day);
					txtmin.setText(min);
					txthour.setText(hour);
					
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
    	try {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	String aname = cbbaname.getValue();
        	String departure = txtstart.getText();
        	String destination = txtend.getText();
        	String flighttime = txtftime.getText();
        	int price;
        	if(txtprice.getText().isEmpty()) {
        		price=0;
        	}else {
        		price = Integer.parseInt(txtprice.getText());
        	}
        	String date = txtyear.getText()+"-"+txtmonth.getText()+"-"+txtday.getText()+" "+txthour.getText()+":"+txtmin.getText();
        	Route route = new Route(0, aname, departure, destination, flighttime, date, price);
        	
        	System.out.println(aname);
        	System.out.println(departure);
        	System.out.println(destination);
        	System.out.println(flighttime);
        	System.out.println(price);
        	System.out.println(date);
        	
        	if(txtday.getText().length()<1 || txtend.getText().length()<1 || txtftime.getText().length()<1 ||
        		txthour.getText().length()<1 || txtmin.getText().length()<1 || txtmonth.getText().length()<1 ||
        		price==0 || txtstart.getText().length()<1 || txtyear.getText().length()<1) {
        		alert.setHeaderText("입력하지 않은 값이 있습니다.");
        		alert.showAndWait();
        		return;
        	}
        	
        	String pattern2 = "^[0-9]*$";
        	if(!(Pattern.matches(pattern2, txtmonth.getText()) || Pattern.matches(pattern2, txtday.getText()) || Pattern.matches(pattern2, txtyear.getText()) || Pattern.matches(pattern2, txthour.getText()) ||
        			Pattern.matches(pattern2, txtmin.getText()) )) {
        		alert.setHeaderText("날짜,시간에는 숫자만 입력가능합니다.");
        		alert.showAndWait();
        		return;
        	}
        	if(Integer.parseInt(txtmonth.getText())<1 || Integer.parseInt(txtmonth.getText())>12 ) {
        		alert.setHeaderText("월은 1~12 사이 숫자만 입력 가능합니다.");
        		alert.showAndWait();
        		return;
        	}
        	if(Integer.parseInt(txtday.getText())<1 || Integer.parseInt(txtday.getText())>31 ) {
        		alert.setHeaderText("일은 1~31 사이 숫자만 입력 가능합니다.");
        		alert.showAndWait();
        		return;
        	}
        	if(Integer.parseInt(txthour.getText())<1 || Integer.parseInt(txthour.getText())>23 ) {
        		alert.setHeaderText("시는 1~23 사이 숫자만 입력 가능합니다.");
        		alert.showAndWait();
        		return;
        	}
        	if(Integer.parseInt(txtmin.getText())<1 || Integer.parseInt(txtmin.getText())>59 ) {
        		alert.setHeaderText("분은 1~59 사이 숫자만 입력 가능합니다.");
        		alert.showAndWait();
        		return;
        	}
        	String pattern = "(^\\d{2})\\:(\\d{2})$";
        	if(!Pattern.matches(pattern, txtftime.getText())) {
        		alert.setHeaderText("비행시간은 oo:oo 형식으로 입력하셔야 합니다.");
        		alert.showAndWait();
        		return;
        	}
        	
        	if(!Pattern.matches(pattern2, txtprice.getText() )) {
        		alert.setHeaderText("가격은 숫자만 입력이 가능합니다.");
        		alert.showAndWait();
        		return;
        	}
        	
        	if(Admin_main.instance.update) {
        		boolean result = RouteDao.routeDao.updateroute(route);
        		if(result) {
        			alert.setHeaderText("항공일지 수정이 완료되었습니다.");
        			alert.showAndWait();
        			Admin_main.instance.loadpage("/view/admin/routeadd.fxml");
        		}else {
        			alert.setHeaderText("항공일지 수정에 실패했습니다. - DB오류");
        			alert.showAndWait();
        		}
        		
        		
        	}else {
        		boolean result = RouteDao.routeDao.addroute(route);
        		if(result) {
        			alert.setHeaderText("항공일지 추가가 완료되었습니다.");
        			alert.showAndWait();
        			Admin_main.instance.loadpage("/view/admin/routeadd.fxml");
        		}else {
        			alert.setHeaderText("항공일지 추가에 실패했습니다. - DB오류");
        			alert.showAndWait();
        		}
        	}
    	} catch(Exception e) {System.out.println(e);}
    	
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