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
	
	public static dto.Board boardinstance;
	
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
    	Boardcon.boardinstance = null;
    	System.out.println("initialized");
    	ObservableList<Board> list = BoardDao.boardDao.list();
    	
    	TableColumn tc = boardtable.getColumns().get(0);
    	tc.setCellValueFactory(new PropertyValueFactory<>("bnum"));
//////////////////////////////////////////////////////////////////////////////////////////    	
    	tc = boardtable.getColumns().get(1);	// 테이블에서 두번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("btitle"));		

    	tc = boardtable.getColumns().get(2);	// 테이블에서 세번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("mnum"));
			// fk로 테이블 들어가서 id 따와야함. 일단은 mnum 출력하는 걸로. ->> 나중에 수정해야 함.
//////////////////////////////////////////////////////////////////////////////////////////
    	tc = boardtable.getColumns().get(3);	// 테이블에서 네번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("bdate"));
    	
    	tc = boardtable.getColumns().get(4);	// 테이블에서 다섯번째 열 가져오기
    	tc.setCellValueFactory( new PropertyValueFactory<>("bview"));
    	
    	boardtable.setItems(list);
    	
    	boardtable.setOnMouseClicked( e -> {
    		// 1. 테이블에서 클릭한 객체를 임시 객체에 저장
    		boardinstance = boardtable.getSelectionModel().getSelectedItem();	// 사용자가 클릭한 객체의 메모리 주소를 Board(dto) board 객체에 저장
    		// 2. 조회수 증가
    			// 1) 리플중에 사용자 이름, 동일한 날짜가 있는지 확인.
    			// 2-1) 없으면 조회수 1 추가 후 리플에 내용이 null, 글쓴이가 사용자인 리플 추가
    			// 2-2) 있으면 3번으로 바로 넘어감.
    			
    		// 3. 클릭하면 화면 전환
    		Mainpage.instance.loadmainmenu("/view/board/board_read.fxml");
    	});
    	
    	
    }

    
    
   
    
}
