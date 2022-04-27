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
	
	int j=0; // 선택한 좌석수 체크
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// vbox내 객체가 비어있으면 vbox내 기존 객체 삭제
		if(vbox.getChildren().isEmpty()==false) {
			vbox.getChildren().remove(0);
		}
		// 1. 비행기명으로 비행기 정보 가져오기
		Aplane aplane = AplaneDao.aplaneDao.getaplane(Searchpage.route.getAname());
		
		// 2. 그리드 클래스
		GridPane gridPane = new GridPane();
			// 그리드 여백
			gridPane.setPadding(new Insets(10));
			// 버튼 사이 여백
			gridPane.setHgap(20);
			gridPane.setVgap(10);
		// 3. 반복문
		int i=0; // 인덱스용 변수
		for(int row=0; row<aplane.getAfirstSeatCount()/8; row++) {
			for(int col=0; col<8; col++) {
				// 1. 이미지 등록
				ImageView imageView = new ImageView("/img/first.png");
					// 이미지 길이 설정
					imageView.setFitHeight(50);
					imageView.setFitWidth(50);
				// 2. 토글 버튼 생성
				ToggleButton button = new ToggleButton(null,imageView);
				button.setText((i+1)+""); // 버튼 이미지 옆에 좌석 번호 넣기
				button.setId(i+"");	// 버튼 id값 넣기
				// 3. 선택완료된 좌석 불러오기
				ArrayList<String> seatlist = TicketDao.ticketDao.getseat(Searchpage.route.getRnum());
				if(seatlist!=null) {
					for(String temp : seatlist) {
						if(("F-"+button.getText()).equals(temp) ) { // 생성된 버튼과 선택된 좌석이 일치하면
							button.setDisable(true);	// 선택완료된 좌석 클릭 불가능으로 바꾸기
						}
					}
				}
				// 버튼 클릭 이벤트
				button.setOnAction(e ->{
					try {
						// 선택된 좌석 번호
						int num = Integer.parseInt(e.toString().split("'")[1]);
						String seat = "F-"+num; // 좌석 등급 + 좌석 번호
						boolean duplicate = true; // 중복체크용 스위치
						// 항공권 검색에서 선택한 인원수와 선택한 좌석수가 일치하면
						if(Searchpage.person==j) { 
							for(String temp : Class.seat) {
								if(temp.equals(seat)) {	// 선택한 좌석이 이미 선택한 좌석일 경우
									Class.seat.remove(seat); // 리스트에서 선택된 좌석 제거
									duplicate = false;
									j--;	// 좌석 선택수 1 감소
								}
							}
							if(duplicate) {	// 선택된 좌석 외 다른 좌석 클릭시
								Alert alert = new Alert(AlertType.INFORMATION);
								button.setSelected(false); // 선택한 좌석 클릭 안되게 설정
								alert.setHeaderText("더 이상 선택할 수 없습니다."); // 메세지 출력
								alert.showAndWait();
								return;
							}
						}
						// 항공권 검색에서 선택한 인원수가 선택한 좌석수보다 클 경우
						else if(Searchpage.person>j) {	
							for(String temp : Class.seat) {
								if(temp.equals(seat)) { // 선택한 좌석이 이미 선택한 좌석일 경우
									Class.seat.remove(seat); // 리스트에서 선택된 좌석 제거
									duplicate = false;
									j--; // 좌석 선택수 1 감소
								}
							}
							if(duplicate) {
								Class.seat.add(seat); // 리스트에 선택한 좌석 추가
								j++;	// 좌석 선택수 1 증가
							}
						}
					} catch(Exception a) {System.out.println(a);}
				});
				gridPane.add(button, col, row); // 그리드내 버튼 추가
				i++; // 인덱스 증가
			}
		}
		int row = aplane.getAfirstSeatCount()/8;	// 퍼스트클래스 좌석수 나누기 8한 값
		int remain = aplane.getAfirstSeatCount()%8;	// 퍼스트클래스 좌석수 나누기 8의 나머지
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
								alert.setHeaderText("더 이상 선택할 수 없습니다.");
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
		// 4. vbox에 그리드 넣기
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
    		alert.setHeaderText((Searchpage.person-j)+"개의 좌석을 더 선택해야 합니다.");
    		alert.showAndWait();
    		return;
    	}else {
    		Mainpage.instance.loadpage("/view/select/pay.fxml");
    	}
    	
    }
	
}
