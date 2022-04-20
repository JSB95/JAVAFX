package controller.board;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.mainpage.Mainpage;
import controller.webview.Gmaps;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Boardwrite implements Initializable{

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
    private ListView<Mapsearchfield> listlocation;

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
    	String search = txtsearch.getText();
    	String encoded = "";
		try {	encoded = URLEncoder.encode(search, "UTF-8");
		} catch (UnsupportedEncodingException e) {System.out.println("Boardwrite_accsearch_UnsupportedEncodingException : "+e);}
		String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="+encoded+"&key="+key;

		ArrayList<Mapsearchfield> resultarray = controller.webview.Gmaps.gmaps.gmapsquery(url);
		
		ObservableList<Mapsearchfield> oresultarray = FXCollections.observableArrayList(resultarray);
		listlocation.setItems(oresultarray);
		
    }
    
    String selectedlocation;	// 사용자가 선택한 지역 저장할 변수. 아직 설계 안됨.
    String key = controller.webview.Gmaps.gmaps.getAPIkey();	// 내 노트북 파일로 들어있는 구글맵스 api 키
    @FXML
    void accwrite(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("게시하기");
    	alert.setHeaderText("글을 게시하시겠습니까?");
    	Optional<ButtonType> result = alert.showAndWait();
////////////////////////////////////////////////////////////////////////////////////////////////////
    	if(result.get() == ButtonType.OK) {
    		Board board = new Board(0, 0, txttitle.getText(), txtcontent.getText(), 
    				selectedlocation, null, 0);		
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
