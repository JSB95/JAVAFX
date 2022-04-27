package controller.select;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

import controller.login.Login;
import controller.main.Mainpage;
import controller.main.Searchpage;
import dao.AplaneDao;

import dao.RouteDao;
import dao.TicketDao;
import dto.Aplane;

import dto.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Pay implements Initializable {

	String seatclass =null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Aplane aplane = AplaneDao.aplaneDao.getaplane(Searchpage.route.getAname());
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
		int total = (int)p*Searchpage.person;
		info.appendText("�װ���� : "+RouteDao.routeDao.getcname(Searchpage.route.getAname())+"\n");
		info.appendText("������ : "+Searchpage.route.getAname()+"\n");
		info.appendText("��߳�¥ : "+Searchpage.route.getRdeparturedate()+"\n");
		info.appendText("����� : " +Searchpage.route.getRdeparture()+"\n");
		info.appendText("������ : " +Searchpage.route.getRdestination()+"\n");
		info.appendText("���� : " +price+"\n");
		info.appendText("�Ѱ��� : "+total+"\n");
		info.appendText("�¼� ��ȣ : ");
		for(String temp : Class.seat) {
			info.appendText(temp+"  ");
		}
		info.setEditable(false);
		
	}
	


    @FXML
    private TextField txtcard;
    
    @FXML
    private TextField txtpassport;

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
    	Aplane aplane = AplaneDao.aplaneDao.getaplane(Searchpage.route.getAname());
		if(Class.seat.toString().contains("F")) {
			seatclass="first";
		}else if(Class.seat.toString().contains("B")) {
			seatclass="business";
		}else if(Class.seat.toString().contains("E")) {
			seatclass="economy";
		}
		
		if (txtpassport.getText().equals(Login.member.getMpassport()) && txtcard.getText().equals(Login.member.getMcard())){
			double ratio = AplaneDao.aplaneDao.getratio(aplane.getCnum(), seatclass);
			double p = (Searchpage.route.getRbaseprice()*ratio);
			int price = (int)p;
			int mnum = Login.member.getMnum();
			int rnum = Searchpage.route.getRnum();
			Iterator<String> iterator = Class.seat.iterator();
			while(iterator.hasNext()) {
				String seatnum = iterator.next();
				Ticket ticket = new Ticket(0, 1, price, mnum, rnum, seatnum, seatclass);
				TicketDao.ticketDao.ticketsave(ticket);
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����");
			alert.setHeaderText("���� ����");
			alert.setContentText("������ �Ϸ�Ǿ����ϴ�.");
			alert.showAndWait();
			Searchpage.person=0;
			Searchpage.route=null;
			Mainpage.instance.loadpage("/view/main/myticket.fxml");
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText("ȸ������ ����ġ");
			alert.setContentText("ȸ�� ������ �Է��Ͻ� ������ ��ġ���� �ʽ��ϴ�.");
			alert.showAndWait();
			return;
		}
		
		
		
    	
    	
    }
	
}
