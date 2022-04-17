package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MyinfoDao;
import dto.Member;
import controller.Main;				// 병합시 임포트 필요
import controller.login.Login;		// 병합시 임포트 필요

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Mypage implements Initializable{
	
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
    	Member member =  MyinfoDao.myinfoDao.myinfopage(Login.member.getMnum);	// 병합시 임포트 필요, Login클래스에서 저정한 member객체에서 mnum 빼오기
    	lblmid.setText(member.getMid());			// 아이디
    	lblmname.setText(member.getMname());		// 이름
    	lblmphone.setText(member.getMphone());		// 전화번호
    	lblmphone.setText(member.getMpassport());   // 여권번호
    }
}
