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
    	tc = boardtable.getColumns().get(1);	// ���̺��� �ι�° �� ��������
    	tc.setCellValueFactory( new PropertyValueFactory<>("btitle"));		

    	tc = boardtable.getColumns().get(2);	// ���̺��� ����° �� ��������
    	tc.setCellValueFactory( new PropertyValueFactory<>("mnum"));
			// fk�� ���̺� ���� id ���;���. �ϴ��� mnum ����ϴ� �ɷ�. ->> ���߿� �����ؾ� ��.
//////////////////////////////////////////////////////////////////////////////////////////
    	tc = boardtable.getColumns().get(3);	// ���̺��� �׹�° �� ��������
    	tc.setCellValueFactory( new PropertyValueFactory<>("bdate"));
    	
    	tc = boardtable.getColumns().get(4);	// ���̺��� �ټ���° �� ��������
    	tc.setCellValueFactory( new PropertyValueFactory<>("bview"));
    	
    	boardtable.setItems(list);
    	
    	boardtable.setOnMouseClicked( e -> {
    		// 1. ���̺��� Ŭ���� ��ü�� �ӽ� ��ü�� ����
    		boardinstance = boardtable.getSelectionModel().getSelectedItem();	// ����ڰ� Ŭ���� ��ü�� �޸� �ּҸ� Board(dto) board ��ü�� ����
    		// 2. ��ȸ�� ����
    			// 1) �����߿� ����� �̸�, ������ ��¥�� �ִ��� Ȯ��.
    			// 2-1) ������ ��ȸ�� 1 �߰� �� ���ÿ� ������ null, �۾��̰� ������� ���� �߰�
    			// 2-2) ������ 3������ �ٷ� �Ѿ.
    			
    		// 3. Ŭ���ϸ� ȭ�� ��ȯ
    		Mainpage.instance.loadmainmenu("/view/board/board_read.fxml");
    	});
    	
    	
    }

    
    
   
    
}
