package controller;

import dto.CashVO;
import dto.CodeVO;
import dto.GraphVO;
import dto.NationVO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ChangeListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import parsePack.Parser;

public class MainController {
  @FXML
  private ComboBox<NationVO> box1;
  
  @FXML
  private ComboBox<NationVO> box2;
  
  @FXML
  private TextField input1;
  
  @FXML
  private TextField input2;
  
  @FXML
  private Label Label1;

  @FXML
  private Label Label2;
  
  private ObservableList<NationVO> nationList;
  
  private ObservableList<CodeVO> codeList;
  
  private Parser parser;
  
  @FXML
  StackedAreaChart<Number, Number> lineChart;
  
  @SuppressWarnings({ "resource", "rawtypes" })
  @FXML
  private void initialize() {
//    boolean MIN = false;
//    boolean MAX = true;
    parser = new Parser();		// parser 호출
    
    nationList = FXCollections.observableArrayList(); // 국가 리스트 저장용
    
    codeList = FXCollections.observableArrayList();

    try {
    	
    	String line;
    	
    	// 파일 불러오기
    	
    	File dataFile = new File(getClass().getResource("/resources/data.txt").getFile());

    	FileInputStream fis = new FileInputStream(dataFile);

    	InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

    	BufferedReader br = new BufferedReader(isr);

//    	List<CashVO> list = this.parser.getCashData();
    	while ((line = br.readLine()) != null) {	// 불러온 파일이 null 이 될때까지
    		
    		String[] lines = line.split("#");		// #을 기준으로 나눔 (국가#코드)

    		NationVO temp = new NationVO(lines[0], lines[1]);	// NationVO에 저장
    		

    		nationList.add(temp);					// 리스트에 추가

    	}
    	
    	String line1;
    	
    	File dataFile2 = new File(getClass().getResource("/resources/data2.txt").getFile());

    	FileInputStream fis2 = new FileInputStream(dataFile2);

    	InputStreamReader isr2 = new InputStreamReader(fis2, "UTF-8");

    	BufferedReader br2 = new BufferedReader(isr2);
    	
    	while ((line1 = br2.readLine()) != null) {
    		String[] lines = line1.split("#");
    		
    		CodeVO temp = new CodeVO(lines[0], lines[1]);
    		
    		codeList.add(temp);
    	}
    	

    	

    	

    	
    	box1.setItems(nationList);					// 콤보박스1 값 지정
    	box2.setItems(nationList);					// 콤보박스2 값 지정
    	
    	
    	
    	box1.setValue(nationList.get(0));			// 콤보박스1 기본값 지정
    	box2.setValue(nationList.get(1));			// 콤보박스2 기본값 지정
    	
    	
 

  	   	
//    try 
    @SuppressWarnings("unchecked")
	ChangeListener<NationVO> funcBox1Changed = (ob, o, v) -> {		// 체인지 리스너
		
    	List<CashVO> list = parser.getCashData();					// 환율 리스트 네이버에서 불러오기
        String nationName = v.getName();							// 새로운 값의 이름을 추출해 nationName에 저장
        List<GraphVO> graphList = new ArrayList<GraphVO>();			// 그래프리스트 호출
        System.out.println(list);
        boolean sw = false;
        for (CashVO data : list) {									// 환율 리스트 끝날때까지 반복문

            if (nationName.length() > data.getName().length()) {	// 국가이름길이 > CashVO에서 파싱한 (인터넷에서 긁어온) 이름 길이를 비교하여
                continue;											// 길다면 반복문 맨 위로(CashVO에서 파싱한 이름 길이가 보통 더 길다)
            } 
            
            String dataName = data.getName().substring(0, nationName.length()); // 파싱한 이름을 처음부터 국가이름 길이만큼 자른다. "미국 USD" => "미국"

            
            if (dataName.equals(nationName)) {				// 자른 결과값이 같다면
            	sw=true;

            	String url = data.getUrlLink();				// Url 링크 호출 (파싱한 후, 미국의 경우 urlLink=/marketindex/exchangeDetail.naver?marketindexCd=FX_USDKRW)
                int idx = url.lastIndexOf("?");				// ?의 인덱스 지정
                url = url.substring(idx + 1);				// ?의 인덱스 + 1만큼 자르기 (marketindexCd=FX_USDKRW)
                for (int i = 0; i < 6; ++i) {

                    String graphUrl = "https://finance.naver.com/marketindex/exchangeDailyQuote.nhn?" + url; // 자른 url 과 상세 페이지를 합침
                    graphUrl = String.valueOf(graphUrl) + String.format("&page=%d", i + 1);			// 6번째 상세 페이지 만큼 graphUrl에 저장
                    parser.getGraphData(graphUrl).forEach(graphItem -> graphList.add(graphItem));	// 파싱하여 날짜, bias 퍼센트를 GraphVO에 저장
                }
                
            	for (int i = 0; i < nationList.size(); i++) {
            		if (box1.getValue().toString().equals(nationList.get(i).toString())) {
            			Label1.setText(codeList.get(i).toString());
            			Label2.setText("");
            		} 
            	}
                

                


                break;	
            }
            
        }
        if( sw==false ) {
        	
        	for (int i = 0; i < nationList.size(); i++) {
        		if (box1.getValue().toString().equals(nationList.get(i).toString())) {
        			Label1.setText(codeList.get(i).toString());
        			Label2.setText("");
        		} 
        	}
        	
        	return;
        }
		
        
        lineChart.getData().clear();						// 차트 초기화
        XYChart.Series series = new XYChart.Series();
        series.setName(nationName);											// 차트 이름 지정
        
        graphList.sort((a, b) -> a.getDate().compareTo(b.getDate()));		// 날짜 내림차순 정렬
  
        double[] dblMinMax = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};	// 최대 최소 지정함수 호출
        
        graphList.forEach(graphVo -> {		// 반복문 실행
        	int nIdx = graphList.indexOf(graphVo);		// graphVo의 인덱스 저장
        	double dblValue = Double.parseDouble(graphVo.getBias().replaceAll(",", ""));	// bias정보만 뽑아서 콤마를 지우고, 따로 dblValue에 저장

        	
        	// bias 정렬 (내림차순)
        	if (dblValue < dblMinMax[0]) {
        		dblMinMax[0] = dblValue;
        	}
        	if (dblValue > dblMinMax[1]) {
        		dblMinMax[1] = dblValue;
        	}
        	
        	series.getData().add((Object)new XYChart.Data((Object)nIdx, (Object)dblValue)); // 차트에 데이터값 지정
        	
        }); // 반복문 종료
        
        lineChart.getData().add(series);
        double yTickSpace = Math.floor(dblMinMax[0]) / 100.0;
        NumberAxis yAxis = (NumberAxis)lineChart.getYAxis();
        yAxis.setForceZeroInRange(false);
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(yTickSpace);
        yAxis.setTickLength(yTickSpace);
        yAxis.setLowerBound(dblMinMax[0] - yTickSpace);
        yAxis.setUpperBound(dblMinMax[1] + yTickSpace);
        NumberAxis xAxis = (NumberAxis)lineChart.getXAxis();
        xAxis.setTickUnit(1.0);
        xAxis.setTickLength(1.0);
        xAxis.setUpperBound((double)(graphList.size() - 1));
        xAxis.setTickLabelFormatter((StringConverter<Number>)new NumberAxis.DefaultFormatter(xAxis) {
        	
        	public String toString(Number object) {
        		String tickDate;
        		@SuppressWarnings("unused")
				String string = tickDate = graphList.size() > 0 ? (graphList.get(object.intValue())).getDate() : "";
        		if (tickDate.length() == 10) {
        			return tickDate.substring(5).replace('.', '/');
        		}
        		return tickDate;
        	}
        });


    };
    
    input1.textProperty().addListener((observable, oldValue, newValue) -> {
    	if (!newValue.matches("\\d*") && newValue.charAt(newValue.length() - 1) != '.'){
    		input1.setText(newValue.replaceAll("[^\\d]",""));
    	}
    });
    box1.valueProperty().addListener(funcBox1Changed);
    funcBox1Changed.changed(null, nationList.get(0), nationList.get(1));
    

    

    
    } catch (Exception e) {
    	e.printStackTrace();
    }

  }
    
	
  
  public void getData() {
	  
	  try {
	  
		  List<CashVO> list = parser.getCashData();
		  NationVO value = box1.getValue();
		  NationVO value2 = box2.getValue();
		  
		  if (value == null || value2 == null || input1.getCharacters().length() == 0) {
			  Alert alert = new Alert(AlertType.WARNING);
			  alert.setTitle("WARNING");
			  alert.setHeaderText("비어있는 입력칸이 있습니다.");
			  alert.setContentText("다시 한번 확인해주세요.");
			  alert.showAndWait();
		  } else {
			  List<String> nationNames = Arrays.asList(new String[] {value.getName(), value2.getName()});
			  double[] dblsBias = new double[] {1.0,1.0};
			  for (CashVO data : list) {
				  nationNames.forEach(nationName ->{
					  String dataName = data.getName().substring(0, nationName.length());
					  if (dataName.equals(nationName)) {
						  int idx = nationNames.indexOf(nationName);
						  dblsBias[idx] = Double.parseDouble(data.getBias().replaceAll(",", ""));
						  if (nationName.equals("일본")) {
							  int n = idx;
							  dblsBias[n] = dblsBias[n] / 100.0;
						  }
						  
					  }
				  });
			  }
			  int inputValue1= Integer.parseInt(input1.getText());
			  double krMoney = inputValue1 * dblsBias[0];
			  input2.setText(String.format("%.2f", new Object[] { Double.valueOf(krMoney / dblsBias[1]) }));
			  
			  for (int i = 0; i < nationList.size(); i++) {
          		if (box2.getValue().toString().equals(nationList.get(i).toString())) {
          			Label2.setText(codeList.get(i).toString());
          		} 
          	}

		  }
	  } catch (Exception e) {
		  System.err.println("GETDATA ERROR");
	  }
  }
}
