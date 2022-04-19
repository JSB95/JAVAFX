package controller.select;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import controller.main.Mainpage;
import controller.main.Searchpage;
import dao.AplaneDao;
import dao.RouteDao;
import dto.Aplane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Pay implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Aplane aplane = AplaneDao.aplaneDao.getaplane(Searchpage.route.getAname());
		String seatclass =null;
		if(Class.seat.toString().contains("F")) {
			seatclass="first";
		}else if(Class.seat.toString().contains("B")) {
			seatclass="business";
		}else if(Class.seat.toString().contains("E")) {
			seatclass="economy";
		}
		double ratio = AplaneDao.aplaneDao.getratio(aplane.getCnum(), seatclass);
		double p = (Searchpage.route.getRbaseprice()*ratio);
		DecimalFormat decimalFormat = new DecimalFormat("#,###��");
		String price = decimalFormat.format(p);
		info.appendText("�װ���� : "+RouteDao.routeDao.getcname(Searchpage.route.getAname())+"\n");
		info.appendText("������ : "+Searchpage.route.getAname()+"\n");
		info.appendText("��߳�¥ : "+Searchpage.route.getRdeparturedate()+"\n");
		info.appendText("����� : " +Searchpage.route.getRdeparture()+"\n");
		info.appendText("������ : " +Searchpage.route.getRdestination()+"\n");
		info.appendText("���� : " +price+"\n");
		info.appendText("�¼� ��ȣ : ");
		for(String temp : Class.seat) {
			info.appendText(temp+" , ");
		}
		
		
	}
	
    @FXML
    private TextField txtname;

    @FXML
    private TextField txtcard;

    @FXML
    private Button btnpay;

    @FXML
    private Button btnback;

    @FXML
    private TextArea info;

    @FXML
    void back(ActionEvent event) {
    	Mainpage.instance.loadpage("/view/select/selectseatclass.fxml");
    }

    @FXML
    void pay(ActionEvent event) {

    }
	
}