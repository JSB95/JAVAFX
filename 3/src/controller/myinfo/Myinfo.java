/*
 * �� ���� ��ȸ ������ ��Ʈ�ѷ�
 */

package controller.myinfo;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import dao.MyinfoDao;
import dto.Member;
//import controller.Main;				// ���ս� ����Ʈ �ʿ�
//import controller.login.Login;		// ���ս� ����Ʈ �ʿ�

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Myinfo implements Initializable{
	
    @FXML
    private Label lblmid;

    @FXML
    private Label lblmname;

    @FXML
    private Label lblmphone;

    @FXML
    private Label lblmpassport;
    
    @FXML
    private Label lblmpoint;
    
    @FXML
    private Label lblmsince;
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	Member member =  MyinfoDao.myinfodao.myinfopage(Login.member.getMid());	// ���ս� ����Ʈ �ʿ�, LoginŬ�������� ������ member��ü���� mnum ������
    	lblmid.setText(member.getMid());			// ���̵�
    	lblmname.setText(member.getMname());		// �̸�
    	lblmphone.setText(member.getMphone());		// ��ȭ��ȣ
    	lblmpassport.setText(member.getMpassport());   // ���ǹ�ȣ
    	lblmpoint.setText(member.getMpoint()+" ��");
    	lblmsince.setText(member.getMsince());
    }
}
