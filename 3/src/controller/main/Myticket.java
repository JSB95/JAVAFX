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
		ArrayList<Ticket> tlist = TicketDao.ticketDao.getticket(Login.member.getMnum());
		for(int i=0; i<tlist.size(); i++) {
			Route route = RouteDao.routeDao.numgetroute(tlist.get(i).getRnum());
			Aplane aplane = AplaneDao.aplaneDao.getaplane(route.getAname());
			String cname = RouteDao.routeDao.getcname(aplane.getaname());
			DecimalFormat decimalFormat = new DecimalFormat("#,###원");
			String price = decimalFormat.format(tlist.get(i).getTprice());
			info.appendText("항공사명 : "+cname+"\n");
			info.appendText("비행기명 : "+aplane.getaname()+"\n");
			info.appendText("출발날짜 : "+route.getRdeparturedate()+"\n");
			info.appendText("출발지 : " +route.getRdeparture()+"\n");
			info.appendText("도착지 : " +route.getRdestination()+"\n");
			info.appendText("가격 : " +price+"\n");
			info.appendText("좌석 번호 : "+tlist.get(i).getTseatnum()+"\n");
			info.appendText("-----------------------------------------------\n");
		}
		info.setEditable(false);
	}
	
    @FXML
    private TextArea info;

}
