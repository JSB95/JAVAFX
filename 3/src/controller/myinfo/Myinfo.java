/*
 * �� ���� ��ȸ ������ ��Ʈ�ѷ�
 */

package controller.myinfo;

import java.net.URL;
import java.util.ResourceBundle;

import controller.login.Login;
import dao.MemberDao;
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
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
   	Member member =  MemberDao.memberDao.getMember(Login.member.getMnum());
   
    	lblmid.setText(member.getMid());			// ���̵�
    	lblmname.setText(member.getMname());		// �̸�
    	lblmphone.setText(member.getMphone());		// ��ȭ��ȣ
    	lblmpassport.setText(member.getMpassport());   // ���ǹ�ȣ
    	lblmpoint.setText(member.getMpoint()+" ��");
    }
}
