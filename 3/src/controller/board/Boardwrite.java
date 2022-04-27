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

import controller.login.Login;
import controller.main.Mainpage;
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
    private String pimage;	// ���� ��� ����� ����
    private File file;
    boolean calledforupdate = false;

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
    	alert.setTitle("�ڷΰ���");
    	alert.setHeaderText("�ۼ��� ����ϰ� �ڷ� ���ðڽ��ϱ�?");
    	Optional<ButtonType> result = alert.showAndWait();
    	if(result.get() == ButtonType.OK) {
    		System.out.println(calledforupdate);
    		if(calledforupdate) {	// 
    			Mainpage.instance.loadpage("/view/board/board_read.fxml");
    		}else {
    			Mainpage.instance.loadpage("/view/board/board.fxml");
    		}
    	}else {
    		return;		// ����ڰ� �ڷ� ���ðڽ��ϱ�?? ->> ��� ��ư ������ ��
    	}
    }
    
    @FXML
    void accsearch(ActionEvent event) {
    	// ���࿡ ����ڰ� ���� ��� ������ �˻���ư -> ���� ��ư���� ��ư�� �ؽ�Ʈ�� ������� �ٲٱ�.
    	if( ! (txtsearch.getText().equals("") ) ) {
	    	if(btnsearch.getText().equals("�˻�")) {
	    		btnsearch.setText("����");
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
					
				// ����, �浵�� ����� �ʿ� �����Ƿ� ����.
					
				tableresult.setItems(oresultarray);
				
	    	}else {
	    		txtsearch.setEditable(false);

				mapsearchfield = tableresult.getSelectionModel().getSelectedItem();	// ����ڰ� ����Ʈ���� ������ ����� ����.
				btnsearch.setText("�˻�");
	    		txtsearch.setEditable(true);

		    }
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("�˸�");
    		alert.setHeaderText("�˻�� �Է� �� �ּ���.");
    		alert.showAndWait();
    	}
    }
    
    String key = controller.webview.Gmaps.gmaps.getAPIkey();	// �� ��Ʈ�� ���Ϸ� ����ִ� ���۸ʽ� api Ű
    @FXML
    void accwrite(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("�Խ��ϱ�");
    	alert.setHeaderText("���� �Խ��Ͻðڽ��ϱ�?");
    	Optional<ButtonType> result = alert.showAndWait();
////////////////////////////////////////////////////////////////////////////////////////////////////
    	if(result.get() == ButtonType.OK) {
    		if(txttitle.getText().equals("") || txtcontent.getText().equals("")) {
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("���");
    			alert.setHeaderText("�����̳� ������ ����־��!!");
    			alert.showAndWait();
    			return;
    		}else {
    			String blocation = null;
    			String bsnapshoturl = null;
    			Board board;
    			
    			if(pimage!=null) copyimgfile();	// ����ڰ� �̹����� ���ε� ���� ��쿡�� �̹��� ī�� �޼��� ����.=
    			
    			if(mapsearchfield!=null) {	// ����ڰ� ���� ��ġ�� ���������� ���� url�� ����. �ƴϸ� ����!
    				blocation = "https://www.google.com/maps/search/?api=1&query="+mapsearchfield.getLatitude()+
            				"%2C"+mapsearchfield.getLongitude()+"&query_place_id="+mapsearchfield.getPlace_id();
            		bsnapshoturl = "https://maps.googleapis.com/maps/api/staticmap?center="+mapsearchfield.getLatitude()+
            				"%2C"+mapsearchfield.getLongitude()+"&zoom=12&size=400x400&key="+Gmaps.gmaps.getAPIkey();
    			}
        		
        		if(calledforupdate) {
        			if(mapsearchfield==null) {
        				blocation = Boardcon.boardinstance.getBlocation();
        				bsnapshoturl = Boardcon.boardinstance.getBsnapshoturl();
        			}
        			if(file==null) pimage = Boardcon.boardinstance.getBimgurl();	// ����ڰ� �� ������ �̹����� �������� �ʾҴٸ�
        			
        			board = new Board(Boardcon.boardinstance.getBnum(), Login.member.getMnum(), txttitle.getText(), txtcontent.getText(), 
                			blocation, bsnapshoturl, pimage, null, Login.member.getMid(), 0);
    				BoardDao.boardDao.update(board);
    				alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("�Ϸ�");
            		alert.setHeaderText("�� ������ �Ϸ�Ǿ����ϴ�.");
            		
        		}else {
	        		board = new Board(0, Login.member.getMnum() , txttitle.getText(), txtcontent.getText(), 
	            			blocation, bsnapshoturl, pimage, null, Login.member.getMid(), 0);		
	        		BoardDao.boardDao.wrtite(board);
	        		alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("�Ϸ�");
	        		alert.setHeaderText("�� �Խð� �Ϸ�Ǿ����ϴ�.");
        		}
        		alert.showAndWait();
        		Mainpage.instance.loadpage("/view/board/board.fxml");
        		
    		}
    		    	}else {
    		return;	// ����ڰ� ���� �Խ��Ͻðڽ��ϱ�?  ->> ��� ��ư ������ ��
    	}
    }
    

    @FXML
    void imgclicked(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();	// ���� ���ñ� Ŭ������ ��ü ����
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("�̹������� : image file" , "*.jpg", "*.jpeg", "*.png", "*.gif"));
		file =  fileChooser.showOpenDialog(new Stage());	
    	if(file!=null) {	// ����ڰ� ���� �ƹ��͵� ���� ���ϰ� ��� ������ ���� ����.
	    	pimage = file.toURI().toString();	// ���⿡�� ������� ��ġ�� �����. ->> ������
	    	Image image = new Image(pimage);	// �̹��� Ŭ���� ��ü�� �����ϸ鼭 ���� ��θ� �־���.
	    	imgshow.setImage(image);	// �̹��� ��Ʈ�ѿ� �̹��� ��ü�� ��������.
    	}else
    		return;
    }
    
    void copyimgfile() {
    	try {
    	// 1. ���� �Է� ��Ʈ�� [ �̵� ���� : byte ] 
    	FileInputStream fileInputStream = new FileInputStream(file);	// file : fileChooser���� ���õ� ���� ��ü.
    	// 2. ���� ��� ��Ʈ��
    	File copyfile = new File("C:\\JAVAlibrary\\img\\"+file.getName());
    		// ���ο� ��� ������ �뵵.
    	FileOutputStream fileOutputStream = new FileOutputStream(copyfile);
    	// 3. ����Ʈ �迭 ����
    	byte[] bytes = new byte[1024*1024*20];	// �ִ� 20�ް�����Ʈ
    	// 4. �ݺ����� �̿��� inputStream�� ���� ��Ʈ���� ��� �о����
    	int size;
    	while( ( size = fileInputStream.read(bytes) ) > 0 ) {	// �о� �� ����Ʈ�� 0���� ������ ����.
    		fileOutputStream.write(bytes, 0, size);	// �о�� ����Ʈ��ŭ�� ��������.
    	}
    	// 5. �뷮�� ū ��쿡�� ��Ʈ�� ���� �ʼ�.
    	fileInputStream.close();
    	fileOutputStream.close();
    	// 6. ������ �̹��� ��ġ ���� ����
    	pimage = copyfile.toURI().toString();
    	
    	}catch(Exception e) {System.out.println("imgadd �޼��� ���� �߻� : "+e);}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		if(Boardcon.boardinstance!=null) {	// ���� Boardwrite�� �� ���������� ȣ��ȰŶ��
			calledforupdate=true;
			txttitle.setText(Boardcon.boardinstance.getBtitle());
    		txtcontent.setText(Boardcon.boardinstance.getBcontent());
    		if(Boardcon.boardinstance.getBimgurl()!=null) {
    			imgshow.setImage(new Image(Boardcon.boardinstance.getBimgurl()));
    		}
    		// ������ ǥ���Ұ��� ����. ���� �� �����Ҷ� �������� �ٸ��ٸ� �����ϰ�, ���ٸ� ���δ°ɷ�.
    	}
    	pimage=null;	
    	file=null;
    	mapsearchfield=null;
    		// �� ���� ���� �ڷΰ��� ������ �޸𸮿� �����Ⱚ ���������� ������ ���ට���� �ʱ�ȭ
    }
}
