/*
 * �� ���� ��ȸ ������ ��Ʈ�ѷ�
 */

package controller.myinfo;

import java.net.URL;
import java.util.ResourceBundle;

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
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
//    	Member member =  MyinfoDao.myinfoDao.myinfopage(Login.member.getMnum);	// ���ս� ����Ʈ �ʿ�, LoginŬ�������� ������ member��ü���� mnum ������
    	Member member = new Member(1, "godoklife", "password", "��ƹ���", "010-1234-5678", "M12345678", "5409-2605-6666-6666");
    	lblmid.setText(member.getMid());			// ���̵�
    	lblmname.setText(member.getMname());		// �̸�
    	lblmphone.setText(member.getMphone());		// ��ȭ��ȣ
    	lblmpassport.setText(member.getMpassport());   // ���ǹ�ȣ
    }
}
