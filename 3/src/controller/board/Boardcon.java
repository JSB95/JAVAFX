package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.mainpage.Mainpage;
import dao.BoardDao;
import dto.Board;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Boardcon implements Initializable{
	
	public static dto.Board boardinstance;
	
    @FXML
    private AnchorPane panehot1;
    
    @FXML
    private AnchorPane panehot2;
	
    @FXML
    private TableView<Board> boardtable;

    @FXML
    private ImageView imghotphoto1;

    @FXML
    private Label lblhottitle1;

    @FXML
    private Label lblhotviewcount1;

    @FXML
    private ImageView imghotphoto2;

    @FXML
    private Label lblhottitle2;

    @FXML
    private Label lblhotviewcount2;

    @FXML
    private Button btnwrite;

    @FXML
    private TextField txtsearch;

    @FXML
    private Button btnsearch;

    @FXML
    void accsearch(ActionEvent event) {
    	if(txtsearch.getText().equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("알림");
    		alert.setHeaderText("모든 게시글을 표시합니다.");
    		alert.showAndWait();
    		initialize(null,null);
    	}else {
        	ObservableList<Board> result =  BoardDao.boardDao.list(txtsearch.getText());
        	if(result==null) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("결과");
        		alert.setHeaderText("검색 결과가 없습니다.");
        		alert.showAndWait();
        	}else {
        		setboardlist(result);
        	}
    	}
    }

    @FXML
    void accwrite(ActionEvent event) {
    	Mainpage.instance.loadmainmenu("/view/board/board_write.fxml");
    }

    @FXML
    void hotcontent1(MouseEvent event) {

    }

    @FXML
    void hotcontent2(MouseEvent event) {

    }
    public void setboardlist(ObservableList<Board> list) {
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
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Boardcon.boardinstance = null;
    	System.out.println("Boardcon_initialized");
    	ObservableList<Board> list = BoardDao.boardDao.list(null);
    	
    	
    	// 핫게시글 셋팅용 반복문. 정렬 기준 : 조회수 높은 순
    	Board[] boards = new Board[2];
    	for(int i = 0; i<2; i++) {
    		boards[i] = new Board(0, 0, null, null, null, null, null, null, 0);
    	}
    	
    	for(int i=0; i<list.size(); i++) {
    		for(int j=i+1; j<list.size(); j++) {
    			System.out.println("i값 : "+i);
    			System.out.println("j값 : "+j);
    			System.out.println("--1--");
    			if(list.get(i).getBview()<list.get(j).getBview()) {
        			System.out.println("--2--");

    				if(boards[0].getBview()<list.get(j).getBview()) boards[0] = list.get(j);
        			System.out.println("--3--");

    				if(boards[1].getBview()<list.get(i).getBview()) boards[1] = list.get(i);
        			System.out.println("--4--");

    			}
    		}
    	}
    	
    	
    	lblhottitle1.setText(boards[0].getBtitle());
    	lblhotviewcount1.setText("조회수 : "+boards[0].getBview());
    	if(boards[0].getBimgurl()!=null) imghotphoto1.setImage(new Image(boards[0].getBimgurl()));
    	panehot1.setOnMouseClicked( e -> {
    		boardinstance = boards[0];
    		Mainpage.instance.loadmainmenu("/view/board/board_read.fxml");
    	});
    	
    	lblhottitle2.setText(boards[1].getBtitle());
    	lblhotviewcount2.setText("조회수 : "+boards[1].getBview());
    	if(boards[1].getBimgurl()!=null) imghotphoto2.setImage(new Image(boards[1].getBimgurl()));
    	panehot2.setOnMouseClicked( e -> {
    		boardinstance = boards[1];
    		Mainpage.instance.loadmainmenu("/view/board/board_read.fxml");
    	});
    	
    	setboardlist(list);
    	
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
