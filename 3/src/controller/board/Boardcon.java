package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.main.Mainpage;
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
    		alert.setTitle("�˸�");
    		alert.setHeaderText("��� �Խñ��� ǥ���մϴ�.");
    		alert.showAndWait();
    		initialize(null,null);
    	}else {
        	ObservableList<Board> result =  BoardDao.boardDao.list(txtsearch.getText());
        	if(result==null) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("���");
        		alert.setHeaderText("�˻� ����� �����ϴ�.");
        		alert.showAndWait();
        	}else {
        		setboardlist(result);
        	}
    	}
    }

    @FXML
    void accwrite(ActionEvent event) {
    	Mainpage.instance.loadpage("/view/board/board_write.fxml");
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
    	tc = boardtable.getColumns().get(1);	// ���̺��� �ι�° �� ��������
    	tc.setCellValueFactory( new PropertyValueFactory<>("btitle"));		

    	tc = boardtable.getColumns().get(2);	// ���̺��� ����° �� ��������
    	tc.setCellValueFactory( new PropertyValueFactory<>("mid"));
			// fk�� ���̺� ���� id ���;���. �ϴ��� mnum ����ϴ� �ɷ�. ->> ���߿� �����ؾ� ��.
//////////////////////////////////////////////////////////////////////////////////////////
    	tc = boardtable.getColumns().get(3);	// ���̺��� �׹�° �� ��������
    	tc.setCellValueFactory( new PropertyValueFactory<>("bdate"));
    	
    	tc = boardtable.getColumns().get(4);	// ���̺��� �ټ���° �� ��������
    	tc.setCellValueFactory( new PropertyValueFactory<>("bview"));
    	if(list!=null)
    		boardtable.setItems(list);
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	Boardcon.boardinstance = null;
    	System.out.println("Boardcon_initialized");
    	ObservableList<Board> list = BoardDao.boardDao.list(null);
    	
    	// �ְԽñ� ���ÿ� �ݺ���. ���� ���� : ��ȸ�� ���� ��
    	Board[] boards = new Board[2];
    	for(int i = 0; i<2; i++) {
    		boards[i] = new Board(0, 0, null, null, null, null, null, null, null, 0);
    	}
    	
    	for(int i=0; i<list.size(); i++) {
    		for(int j=i+1; j<list.size(); j++) {
    			if(list.get(i).getBview()<list.get(j).getBview()) {
    				if(boards[0].getBview()<list.get(j).getBview()) 
    					boards[0] = list.get(j);
    				if(boards[1].getBview()<list.get(i).getBview()) 
    					boards[1] = list.get(i);
    			}
    		}
    	}
    	
    	lblhottitle1.setText(boards[0].getBtitle());
    	lblhotviewcount1.setText("��ȸ�� : "+boards[0].getBview());
    	if(boards[0].getBimgurl()!=null) imghotphoto1.setImage(new Image(boards[0].getBimgurl()));
    	panehot1.setOnMouseClicked( e -> {
    		boardinstance = boards[0];
    		Mainpage.instance.loadpage("/view/board/board_read.fxml");
    	});
    	
    	lblhottitle2.setText(boards[1].getBtitle());
    	lblhotviewcount2.setText("��ȸ�� : "+boards[1].getBview());
    	if(boards[1].getBimgurl()!=null) imghotphoto2.setImage(new Image(boards[1].getBimgurl()));
    	panehot2.setOnMouseClicked( e -> {
    		boardinstance = boards[1];
    		Mainpage.instance.loadpage("/view/board/board_read.fxml");
    	});
    	
    	setboardlist(list);
    	
    	boardtable.setOnMouseClicked( e -> {
    		// 1. ���̺��� Ŭ���� ��ü�� �ӽ� ��ü�� ����
    		boardinstance = boardtable.getSelectionModel().getSelectedItem();	// ����ڰ� Ŭ���� ��ü�� �޸� �ּҸ� Board(dto) board ��ü�� ����
    		// 2. ��ȸ�� ����
    			// 1) �����߿� ����� �̸�, ������ ��¥�� �ִ��� Ȯ��.
    			// 2-1) ������ ��ȸ�� 1 �߰� �� ���ÿ� ������ null, �۾��̰� ������� ���� �߰�
    			// 2-2) ������ 3������ �ٷ� �Ѿ.
    			
    		// 3. Ŭ���ϸ� ȭ�� ��ȯ
    		Mainpage.instance.loadpage("/view/board/board_read.fxml");
    	});
    	
    }
    
}
