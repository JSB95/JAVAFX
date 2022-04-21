package controller.admin;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.AplaneDao;
import dto.Company;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Cadd implements Initializable {

	public Company company;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btndelete.setVisible(false);
		ctable.setVisible(false);
		if(Admin_main.instance.update) {
			btndelete.setVisible(true);
			ctable.setVisible(true);
			lblmenuname.setText("�װ��� ����/����");
			btnadd.setText("����");
			ObservableList<Company> clist = AplaneDao.aplaneDao.getcompany();
			
			TableColumn tc = ctable.getColumns().get(0);
			tc.setCellValueFactory(new PropertyValueFactory<>("cname"));
			tc = ctable.getColumns().get(1);
			tc.setCellValueFactory(new PropertyValueFactory<>("cphone"));
			tc = ctable.getColumns().get(2);
			tc.setCellValueFactory(new PropertyValueFactory<>("cfirstSeatRatio"));
			tc = ctable.getColumns().get(3);
			tc.setCellValueFactory(new PropertyValueFactory<>("cbusinessSeatRatio"));
			ctable.setItems(clist);
			
			ctable.setOnMouseClicked(e->{
				company = ctable.getSelectionModel().getSelectedItem();
				txtcname.setText(company.getCname());
				txtcphone.setText(company.getCphone());
				txtfratio.setText(Double.toString(company.getCfirstSeatRatio()) );
				txtbratio.setText(Double.toString(company.getCbusinessSeatRatio()));
			});
			
		}
	}
	
    @FXML
    private Label lblmenuname;

    @FXML
    private TextField txtcname;

    @FXML
    private TextField txtcphone;

    @FXML
    private TextField txtfratio;

    @FXML
    private Button btnadd;

    @FXML
    private TextField txtbratio;

    @FXML
    private Button btndelete;
    
    @FXML
    private TableView<Company> ctable;

    @FXML
    void add(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String cname = txtcname.getText();
    	String cphone = txtcphone.getText();
    	double fratio = Double.parseDouble(txtfratio.getText());
    	double bratio = Double.parseDouble(txtbratio.getText());
    	int cnum = company.getCnum();
    	Company company = new Company(cnum, cname, cphone, fratio, bratio);
    	if(Admin_main.instance.update) {
    		System.out.println(cname+"\t"+cphone+"\t"+fratio+"\t"+bratio);
    		boolean result = AplaneDao.aplaneDao.cupdate(company);
    		if(result) {
        		alert.setHeaderText("�װ��� ������ �Ϸ�Ǿ����ϴ�.");
        		alert.showAndWait();
        		Admin_main.instance.loadpage("/view/admin/cadd.fxml");

        	}else {
        		alert.setHeaderText("�װ��� ���� ���� - DB ����");
        		alert.showAndWait();
        	}
    	}else {
        	boolean result = AplaneDao.aplaneDao.addcompany(cname, cphone, fratio, bratio);
        	if(result) {
        		alert.setHeaderText("�װ��� �߰��� �Ϸ�Ǿ����ϴ�.");
        		alert.showAndWait();
        		Admin_main.instance.loadpage("/view/admin/cadd.fxml");
        		
        	}else {
        		alert.setHeaderText("�װ��� �߰� ���� - DB ����");
        		alert.showAndWait();
        	}
    	}

    }

    @FXML
    void delete(ActionEvent event) {
    	String table = "company";
    	int cnum = company.getCnum();
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("���� �����Ͻðڽ��ϱ�?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get()==ButtonType.OK) {
    		boolean result = AplaneDao.aplaneDao.delete(table, cnum);
        	if(result) {
        		Alert alert2 = new Alert(AlertType.INFORMATION);
        		alert2.setHeaderText("������ �Ϸ�Ǿ����ϴ�.");
        		alert2.showAndWait();
        		Admin_main.instance.loadpage("/view/admin/cadd.fxml");
        	}
    	}
    	
    }
	
}
