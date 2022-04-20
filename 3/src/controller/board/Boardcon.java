package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.mainpage.Mainpage;
import dao.BoardDao;
import dto.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Boardcon implements Initializable{
	
    @FXML
    private TableView<Board> boardtable;

    @FXML
    private ImageView imghotphoto1;

    @FXML
    private Label lblhottitle1;

    @FXML
    private Label lblhotwriter1;

    @FXML
    private ImageView imghotphoto2;

    @FXML
    private Label lblhottitle2;

    @FXML
    private Label lblhotwriter2;

    @FXML
    private Button btnwrite;

    @FXML
    private TextField txtsearch;

    @FXML
    private Button btnsearch;

    @FXML
    void accsearch(ActionEvent event) {
    	System.out.println("accsearch");
    }

    @FXML
    void accwrite(ActionEvent event) {
    	Mainpage.instance.loadmainmenu("/view/board/board_write.fxml");
    }

    @FXML
    void hotcontent1(MouseEvent event) {
    	System.out.println("hotcontent1");

    }

    @FXML
    void hotcontent2(MouseEvent event) {
    	System.out.println("hotcontent2");

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	System.out.println("initialized");
    	ObservableList<Board> list = BoardDao.boardDao.list();
    	
    	TableColumn tc = boardtable.getColumns().get(0);
    	tc.setCellValueFactory(new PropertyValueFactory<>("bnum"));
    	
    	tc = boardtable.getColumns().get(1);	// 테이블에서 두번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("mnum"));		// fk로 테이블 들어가서 id 따와야함. 내일하자 졸리다
    	
    	tc = boardtable.getColumns().get(2);	// 테이블에서 세번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("bwrite"));
    	
    	tc = boardtable.getColumns().get(3);	// 테이블에서 네번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("bdate"));
    	
    	tc = boardtable.getColumns().get(4);	// 테이블에서 다섯번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("bview"));
    	
    	boardtable.setItems(list);
    }

}
