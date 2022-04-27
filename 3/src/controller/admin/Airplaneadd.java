package controller.admin;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import dao.AplaneDao;
import dao.RouteDao;
import dto.Aplane;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class Airplaneadd implements Initializable {

	public Aplane aplane;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btndelete.setVisible(false);
		atable.setVisible(false);
		HashSet<String> company = AplaneDao.aplaneDao.getlist();
		ObservableList<String> combo = FXCollections.observableArrayList(company);
		cbbcname.setItems(combo);
		if(Admin_main.instance.update) {
			btndelete.setVisible(true);
			atable.setVisible(true);
			btnadd.setText("수정");
			lblmenuname.setText("비행기 수정/삭제");
		
			ObservableList<Aplane> alist = AplaneDao.aplaneDao.getairplane();
			TableColumn tc = atable.getColumns().get(0);
			tc.setCellValueFactory(new PropertyValueFactory<>("astate"));
			tc = atable.getColumns().get(1);
			tc.setCellValueFactory(new PropertyValueFactory<>("aname"));
			tc = atable.getColumns().get(2);
			tc.setCellValueFactory(new PropertyValueFactory<>("afirstSeatCount"));
			tc = atable.getColumns().get(3);
			tc.setCellValueFactory(new PropertyValueFactory<>("abusinessSeatCount"));
			tc = atable.getColumns().get(4);
			tc.setCellValueFactory(new PropertyValueFactory<>("aeconomySeatCount"));
			atable.setItems(alist);
			
			atable.setOnMouseClicked(e->{
				try {
					aplane = atable.getSelectionModel().getSelectedItem();
					txtaname.setText(aplane.getaname());
					
					txtfseatcount.setText(Integer.toString(aplane.getAfirstSeatCount()));
					txtbseatcount.setText(Integer.toString(aplane.getAbusinessSeatCount()));
					txteseatcount.setText(Integer.toString(aplane.getAeconomySeatCount()));
					String c =RouteDao.routeDao.getcname(aplane.getAname());
					int k=0;
					for(int i=0; i<combo.size(); i++) {
						if(combo.get(i).equals(c)) {
							k=i;
						}
					}
					cbbcname.getSelectionModel().select(k);
				} catch(Exception ee) {}
				
			});
		}else {
			return;
		}
	}
	
    @FXML
    private Label lblmenuname;

    @FXML
    private ComboBox<String> cbbcname;

    @FXML
    private TextField txtaname;

    @FXML
    private TextField txtfseatcount;

    @FXML
    private TextField txtbseatcount;

    @FXML
    private Button btnadd;

    @FXML
    private Button btndelete;

    @FXML
    private TableView<Aplane> atable;

    @FXML
    private TextField txteseatcount;

    @FXML
    void add(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String cname = cbbcname.getValue();
    	String aname = txtaname.getText();
    	int fcount;
    	if(txtfseatcount.getText().isEmpty()) {
    		fcount=0;
    	}else {
    		fcount = Integer.parseInt(txtfseatcount.getText());
    	}
    	int bcount;
    	if(txtbseatcount.getText().isEmpty()) {
    		bcount=0;
    	}else {
    		bcount = Integer.parseInt(txtbseatcount.getText());
    	}
    	int ecount;
    	if(txteseatcount.getText().isEmpty()) {
    		ecount=0;
    	}else {
    		ecount = Integer.parseInt(txteseatcount.getText());
    	}
    	int cnum = AplaneDao.aplaneDao.getcnum(cname);
    	ObservableList<Aplane> aplanes = AplaneDao.aplaneDao.getairplane();
    	
    	
    	if(txtaname.getText().length()<1 || fcount==0 || bcount==0 || ecount==0) {
        		alert.setHeaderText("입력하지 않은 값이 있습니다.");
        		alert.showAndWait();
        		return;
        }
    	if(cnum==0) {
    		alert.setHeaderText("일치하는 항공사명이 없습니다.");
    		alert.showAndWait();
    		return;
    	}
    	for(Aplane temp : aplanes) {
    		if(temp.getAname().equals(aname)) {
    			alert.setHeaderText("동일한 비행기명이 있습니다.");
        		alert.showAndWait();
        		return;
    		}
    	}
    	String pattern2 = "^[0-9]*$";
    	if(!(Pattern.matches(pattern2, txtbseatcount.getText()) || Pattern.matches(pattern2, txteseatcount.getText()) || Pattern.matches(pattern2, txtfseatcount.getText()) )) {
    		alert.setHeaderText("좌석수는 숫자만 입력가능합니다.");
    		alert.showAndWait();
    		return;
    	}
    	if(Admin_main.instance.update) {
    		
    		Aplane aplane1 = new Aplane(aname, cnum, fcount, bcount, ecount, null);
    		boolean result = AplaneDao.aplaneDao.aupdate(aplane1, aplane.getaname());
    		if(result) {
    			alert.setHeaderText("수정이 완료되었습니다.");
    			alert.showAndWait();
    			Admin_main.instance.loadpage("/view/admin/admin_main.fxml");
    		}
    	}
    	else {
    		Aplane aplane = new Aplane(aname, cnum, fcount, bcount, ecount, null);
        	boolean result = AplaneDao.aplaneDao.addairplane(aplane);
        	if(result) {
        		alert.setHeaderText("비행기 추가가 완료되었습니다.");
        		alert.showAndWait();
        		Admin_main.instance.loadpage("/view/admin/airplaneadd.fxml");
        	}else {
        		alert.setHeaderText("비행기 추가에 실패했습니다. [DB 오류]");
        		alert.showAndWait();
        	}
    	}
    	
    }

    @FXML
    void delete(ActionEvent event) {

    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("정말 삭제하시겠습니까?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get()==ButtonType.OK) {
    		boolean result = AplaneDao.aplaneDao.adelete(aplane.getaname());
        	if(result) {
        		Alert alert2 = new Alert(AlertType.INFORMATION);
        		alert2.setHeaderText("삭제가 완료되었습니다.");
        		alert2.showAndWait();
        		Admin_main.instance.loadpage("/view/admin/airplaneadd.fxml");
        	}
    	}
    	
    }
	
}
