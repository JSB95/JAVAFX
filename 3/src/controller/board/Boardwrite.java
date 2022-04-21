package controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Boardwrite implements Initializable{
	
	Mapsearchfield mapsearchfield;
    private String pimage;	// 파일 경로 저장용 변수
    private File file;

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
    private Button btnimgupload;
    @FXML
    private ImageView imgshow;
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
    	if( ! (txtsearch.getText().equals("") ) ) {
	    	if(btnsearch.getText().equals("검색")) {
	    		btnsearch.setText("선택");
	    		txtsearch.setEditable(false);
	    		
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
				
	    	}else {
				mapsearchfield = tableresult.getSelectionModel().getSelectedItem();	// 사용자가 리스트에서 선택한 대상을 저장.
				btnsearch.setText("검색");
		    }
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("알림");
    		alert.setHeaderText("검색어를 입력 해 주세요.");
    		alert.showAndWait();
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
    		if(txttitle.getText().equals("") || txtcontent.getText().equals("")) {
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("경고");
    			alert.setHeaderText("내용이나 제목이 비어있어요!!");
    			alert.showAndWait();
    			return;
    		}else {
    			String blocation = null;
    			String bsnapshoturl = null;
    			if(mapsearchfield!=null) {	// 사용자가 지도 위치를 선택했으면 구글 url을 저장. 아니면 안해!
    				blocation = "https://www.google.com/maps/search/?api=1&query="+mapsearchfield.getLatitude()+
            				"%2C"+mapsearchfield.getLongitude()+"&query_place_id="+mapsearchfield.getPlace_id();
            		bsnapshoturl = "https://maps.googleapis.com/maps/api/staticmap?center="+mapsearchfield.getLatitude()+
            				"%2C"+mapsearchfield.getLongitude()+"&zoom=12&size=400x400&key="+Gmaps.gmaps.getAPIkey();
    			}
    			if(pimage!=null)
    				copyimgfile();	// 사용자가 이미지를 업로드 했을 경우에만 이미지 카피 메서드 실행.=
        		
        		Board board = new Board(0, 0, txttitle.getText(), txtcontent.getText(), 
        				blocation, bsnapshoturl, pimage, null, 0);		
        			// 코드 병합할 때 로그인 한 사용자의 회원번호 따와야함. 나머지는 바꿀 거 없음.
    ////////////////////////////////////////////////////////////////////////////////////////////////////
        		BoardDao.boardDao.wrtite(board);
        		alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("완료");
        		alert.setHeaderText("글 게시가 완료되었습니다.");
        		alert.showAndWait();
            	Mainpage.instance.loadmainmenu("/view/board/board.fxml");
    		}
    		    	}else {
    		return;	// 사용자가 글을 게시하시겠습니까?  ->> 취소 버튼 눌렀을 때
    	}
    }
    

    @FXML
    void imgclicked(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();	// 파일 선택기 클래스의 객체 선언
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("이미지파일 : image file" , "*.jpg", "*.jpeg", "*.png", "*.gif"));
		file =  fileChooser.showOpenDialog(new Stage());	
    	if(file!=null) {	// 사용자가 파일 아무것도 선택 안하고 취소 누르면 실행 안함.
	    	pimage = file.toURI().toString();	// 여기에선 사용자의 위치가 저장됨. ->> 절대경로
	    	Image image = new Image(pimage);	// 이미지 클래스 객체를 선언하면서 파일 경로를 넣어줌.
	    	imgshow.setImage(image);	// 이미지 컨트롤에 이미지 객체를 설정해줌.
//	    	txtimglocation.setText(pimage);
    	}else
    		return;
    }
    
    void copyimgfile() {
    	try {
    	// 1. 파일 입력 스트림 [ 이돈 단위 : byte ] 
    	FileInputStream fileInputStream = new FileInputStream(file);	// file : fileChooser에서 선택된 파일 객체.
    	// 2. 파일 출력 스트림
    	File copyfile = new File("C:\\JAVAlibrary\\img"+file.getName());
    		// 새로운 경로 설정의 용도.
    	FileOutputStream fileOutputStream = new FileOutputStream(copyfile);
    	// 3. 바이트 배열 선언
    	byte[] bytes = new byte[1024*1024*20];	// 최대 20메가바이트
    	// 4. 반복문을 이용한 inputStream의 파일 스트림을 모두 읽어오기
    	int size;
    	while( ( size = fileInputStream.read(bytes) ) > 0 ) {	// 읽어 온 바이트가 0보다 작으면 종료.
    		fileOutputStream.write(bytes, 0, size);	// 읽어온 바이트만큼만 내보내기.
    	}
    	// 5. 용량이 큰 경우에는 스트림 종료 필수.
    	fileInputStream.close();
    	fileOutputStream.close();
    	// 6. 서버의 이미지 위치 저장 변수
    	pimage = copyfile.toURI().toString();
    	
    	}catch(Exception e) {System.out.println("imgadd 메서드 예외 발생 : "+e);}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	pimage=null;	
    	file=null;
    	mapsearchfield=null;
    		// 글 쓰다 말고 뒤로가기 했을때 메모리에 쓰래기값 남아있을수 있으니 실행때마다 초기화
    }
}
