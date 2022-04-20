package controller.board;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.mainpage.Mainpage;
import controller.webview.Gmaps.Mapsearchfield;
import dao.BoardDao;
import dto.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Boardwrite implements Initializable{
	
	Mapsearchfield mapsearchfield;

	@FXML
    private Button btnwrite;

    @FXML
    private TextField txttitle;

    @FXML
    private TextArea txtcontent;

    @FXML
    private Button btnback;

    @FXML
    private TextField txtsearch;

    @FXML
    private Button btnsearch;

    @FXML
    private TextField txtimglocation;

    @FXML
    private Button btnsearch1;
    
    @FXML
    private TableView<Mapsearchfield> tableresult;

    @FXML
    void accback(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("뒤로가기");
    	alert.setHeaderText("작성을 취소하고 뒤로 가시겠습니까?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
        	Mainpage.instance.loadmainmenu("/view/board/board.fxml");
    	}else {
    		return;		// 사용자가 뒤로 가시겠습니까?? ->> 취소 버튼 눌렀을 때
    	}
    }
    
    @FXML
    void accsearch(ActionEvent event) {
    	// 만약에 사용자가 지도 목록 누르면 검색버튼 -> 선택 버튼으로 버튼의 텍스트와 기능을을 바꾸기.
    	if(btnsearch.getText().equals("검색")) {
	    	String search = txtsearch.getText();
	    	String encoded = "";
			try {	encoded = URLEncoder.encode(search, "UTF-8");
			} catch (UnsupportedEncodingException e) {System.out.println("Boardwrite_accsearch_UnsupportedEncodingException : "+e);}
			String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="+encoded+"&key="+key;
	
			ArrayList<Mapsearchfield> resultarray = controller.webview.Gmaps.gmaps.gmapsquery(url);
			ObservableList<Mapsearchfield> oresultarray = FXCollections.observableArrayList(resultarray);
			
			TableColumn tc = tableresult.getColumns().get(0);
			tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
			tc = tableresult.getColumns().get(1);
			tc.setCellValueFactory( new PropertyValueFactory<>("formatted_address")); 
				
			// 위도, 경도는 출력할 필요 없으므로 생략.
				
			tableresult.setItems(oresultarray);
			btnsearch.setText("선택");
    	}else {
			mapsearchfield = tableresult.getSelectionModel().getSelectedItem();	// 사용자가 리스트에서 선택한 대상을 저장.
			btnsearch.setText("검색");
	    }
    }
    
    String key = controller.webview.Gmaps.gmaps.getAPIkey();	// 내 노트북 파일로 들어있는 구글맵스 api 키
    @FXML
    void accwrite(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("게시하기");
    	alert.setHeaderText("글을 게시하시겠습니까?");
    	Optional<ButtonType> result = alert.showAndWait();
////////////////////////////////////////////////////////////////////////////////////////////////////
    	if(result.get() == ButtonType.OK) {
    		String blocation = "https://www.google.com/maps/search/?api=1&query="+mapsearchfield.getLatitude()+
    				"%2C"+mapsearchfield.getLongitude()+"&query_place_id="+mapsearchfield.getPlace_id();
    		
    		Board board = new Board(0, 0, txttitle.getText(), txtcontent.getText(), 
    				blocation, null, 0);		
    			// 코드 병합할 때 로그인 한 사용자의 회원번호 따와야함. 나머지는 바꿀 거 없음.
////////////////////////////////////////////////////////////////////////////////////////////////////
    		BoardDao.boardDao.wrtite(board);
    		alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("완료");
    		alert.setHeaderText("글 게시가 완료되었습니다.");
    		alert.showAndWait();
        	Mainpage.instance.loadmainmenu("/view/board/board.fxml");
    	}else {
    		return;	// 사용자가 글을 게시하시겠습니까?  ->> 취소 버튼 눌렀을 때
    	}
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }
}
