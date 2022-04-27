package controller.select;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.main.Mainpage;
import controller.main.Searchpage;
import dao.AplaneDao;
import dao.TicketDao;
import dto.Aplane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class First implements Initializable {
	
	int j=0; // ������ �¼��� üũ
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// vbox�� ��ü�� ��������� vbox�� ���� ��ü ����
		if(vbox.getChildren().isEmpty()==false) {
			vbox.getChildren().remove(0);
		}
		// 1. ���������� ����� ���� ��������
		Aplane aplane = AplaneDao.aplaneDao.getaplane(Searchpage.route.getAname());
		
		// 2. �׸��� Ŭ����
		GridPane gridPane = new GridPane();
			// �׸��� ����
			gridPane.setPadding(new Insets(10));
			// ��ư ���� ����
			gridPane.setHgap(20);
			gridPane.setVgap(10);
		// 3. �ݺ���
		int i=0; // �ε����� ����
		for(int row=0; row<aplane.getAfirstSeatCount()/8; row++) {
			for(int col=0; col<8; col++) {
				// 1. �̹��� ���
				ImageView imageView = new ImageView("/img/first.png");
					// �̹��� ���� ����
					imageView.setFitHeight(50);
					imageView.setFitWidth(50);
				// 2. ��� ��ư ����
				ToggleButton button = new ToggleButton(null,imageView);
				button.setText((i+1)+""); // ��ư �̹��� ���� �¼� ��ȣ �ֱ�
				button.setId(i+"");	// ��ư id�� �ֱ�
				// 3. ���ÿϷ�� �¼� �ҷ�����
				ArrayList<String> seatlist = TicketDao.ticketDao.getseat(Searchpage.route.getRnum());
				if(seatlist!=null) {
					for(String temp : seatlist) {
						if(("F-"+button.getText()).equals(temp) ) { // ������ ��ư�� ���õ� �¼��� ��ġ�ϸ�
							button.setDisable(true);	// ���ÿϷ�� �¼� Ŭ�� �Ұ������� �ٲٱ�
						}
					}
				}
				// ��ư Ŭ�� �̺�Ʈ
				button.setOnAction(e ->{
					try {
						// ���õ� �¼� ��ȣ
						int num = Integer.parseInt(e.toString().split("'")[1]);
						String seat = "F-"+num; // �¼� ��� + �¼� ��ȣ
						boolean duplicate = true; // �ߺ�üũ�� ����ġ
						// �װ��� �˻����� ������ �ο����� ������ �¼����� ��ġ�ϸ�
						if(Searchpage.person==j) { 
							for(String temp : Class.seat) {
								if(temp.equals(seat)) {	// ������ �¼��� �̹� ������ �¼��� ���
									Class.seat.remove(seat); // ����Ʈ���� ���õ� �¼� ����
									duplicate = false;
									j--;	// �¼� ���ü� 1 ����
								}
							}
							if(duplicate) {	// ���õ� �¼� �� �ٸ� �¼� Ŭ����
								Alert alert = new Alert(AlertType.INFORMATION);
								button.setSelected(false); // ������ �¼� Ŭ�� �ȵǰ� ����
								alert.setHeaderText("�� �̻� ������ �� �����ϴ�."); // �޼��� ���
								alert.showAndWait();
								return;
							}
						}
						// �װ��� �˻����� ������ �ο����� ������ �¼������� Ŭ ���
						else if(Searchpage.person>j) {	
							for(String temp : Class.seat) {
								if(temp.equals(seat)) { // ������ �¼��� �̹� ������ �¼��� ���
									Class.seat.remove(seat); // ����Ʈ���� ���õ� �¼� ����
									duplicate = false;
									j--; // �¼� ���ü� 1 ����
								}
							}
							if(duplicate) {
								Class.seat.add(seat); // ����Ʈ�� ������ �¼� �߰�
								j++;	// �¼� ���ü� 1 ����
							}
						}
					} catch(Exception a) {System.out.println(a);}
				});
				gridPane.add(button, col, row); // �׸��峻 ��ư �߰�
				i++; // �ε��� ����
			}
		}
		int row = aplane.getAfirstSeatCount()/8;	// �۽�ƮŬ���� �¼��� ������ 8�� ��
		int remain = aplane.getAfirstSeatCount()%8;	// �۽�ƮŬ���� �¼��� ������ 8�� ������
		if(remain !=0 ) {
			for(int col=0; col<remain; col++) {
				ImageView imageView = new ImageView("/img/first.png");
				imageView.setFitHeight(50);
				imageView.setFitWidth(50);
				ToggleButton button = new ToggleButton(null,imageView);
				button.setText((i+1)+"");
				button.setId(i+"");
				ArrayList<String> seatlist = TicketDao.ticketDao.getseat(Searchpage.route.getRnum());
				if(seatlist!=null) {
					for(String temp : seatlist) {
						if(("F-"+button.getText()).equals(temp) ) {button.setDisable(true);}
					}
				}
				button.setOnAction(e ->{
					try {
						int num = Integer.parseInt(e.toString().split("'")[1]);
						String seat = "F-"+num;
						boolean duplicate = true;
						if(Searchpage.person==j) {
							for(String temp : Class.seat) {
								if(temp.equals(seat)) {Class.seat.remove(seat);	duplicate = false; j--;}
							}
							if(duplicate) {
								Alert alert = new Alert(AlertType.INFORMATION);
								button.setSelected(false);
								alert.setHeaderText("�� �̻� ������ �� �����ϴ�.");
								alert.showAndWait();
								return;
							}
						}
						else if(Searchpage.person>j) {
							for(String temp : Class.seat) {
								if(temp.equals(seat)) {Class.seat.remove(seat);	duplicate = false; j--;}
							}
							if(duplicate) {Class.seat.add(seat); j++;}
						}
					} catch(Exception a) {System.out.println(a);}
				});
				gridPane.add(button, col, row);
				i++;
			}
		}
		// 4. vbox�� �׸��� �ֱ�
		vbox.getChildren().add(gridPane);
	}

	
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;

    @FXML
    private Button btnselect;
    
    @FXML
    private Button btnback;

    
    @FXML
    void back(ActionEvent event) {
    	Selectseatclass.instance.loadpage("/view/select/class.fxml");
    }

    @FXML
    void select(ActionEvent event) {
    	if(Searchpage.person>j) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText((Searchpage.person-j)+"���� �¼��� �� �����ؾ� �մϴ�.");
    		alert.showAndWait();
    		return;
    	}else {
    		Mainpage.instance.loadpage("/view/select/pay.fxml");
    	}
    	
    }
	
}
