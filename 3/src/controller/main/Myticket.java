package controller.main;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.login.Login;
import dao.AplaneDao;
import dao.RouteDao;
import dao.TicketDao;
import dto.Aplane;
import dto.Route;
import dto.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class Myticket implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("1");
		ArrayList<Ticket> tlist = TicketDao.ticketDao.getticket(Login.member.getMnum());
		System.out.println("1");

		for(int i=0; i<tlist.size(); i++) {
			System.out.println("1");

			Route route = RouteDao.routeDao.numgetroute(tlist.get(i).getRnum());
			System.out.println("1");

			Aplane aplane = AplaneDao.aplaneDao.getaplane(route.getAname());
			System.out.println("1");

			String cname = RouteDao.routeDao.getcname(aplane.getaname());
			System.out.println("1");

			DecimalFormat decimalFormat = new DecimalFormat("#,###��");
			System.out.println("1");

			String price = decimalFormat.format(tlist.get(i).getTprice());
			info.appendText("�װ���� : "+cname+"\n");
			info.appendText("������ : "+aplane.getaname()+"\n");
			info.appendText("��߳�¥ : "+route.getRdeparturedate()+"\n");
			info.appendText("����� : " +route.getRdeparture()+"\n");
			info.appendText("������ : " +route.getRdestination()+"\n");
			info.appendText("���� : " +price+"\n");
			info.appendText("�¼� ��ȣ : "+tlist.get(i).getTseatnum()+"\n");
			info.appendText("-----------------------------------------------\n");
		}
		info.setEditable(false);
	}
	
    @FXML
    private TextArea info;

}
