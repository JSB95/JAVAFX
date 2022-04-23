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
    parser = new Parser();		// parser ȣ��
    
    nationList = FXCollections.observableArrayList(); // ���� ����Ʈ �����
    
    codeList = FXCollections.observableArrayList();	// ��ȭ ���� �����

    try {
    	
    	String line;
    	
    	// ���� �ҷ�����
    	
    	File dataFile = new File(getClass().getResource("/resources/data.txt").getFile());

    	FileInputStream fis = new FileInputStream(dataFile);

    	InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

    	BufferedReader br = new BufferedReader(isr);

//    	List<CashVO> list = this.parser.getCashData();
    	while ((line = br.readLine()) != null) {	// �ҷ��� ������ null �� �ɶ�����
    		
    		String[] lines = line.split("#");		// #�� �������� ���� (����#�ڵ�)

    		NationVO temp = new NationVO(lines[0], lines[1]);	// NationVO�� ����
    		

    		nationList.add(temp);					// ����Ʈ�� �߰�

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
    	

    	

    	

    	
    	box1.setItems(nationList);					// �޺��ڽ�1 �� ����
    	box2.setItems(nationList);					// �޺��ڽ�2 �� ����
    	
    	
    	
    	box1.setValue(nationList.get(0));			// �޺��ڽ�1 �⺻�� ����
    	box2.setValue(nationList.get(1));			// �޺��ڽ�2 �⺻�� ����
    	
    	
 

  	   	
//    try 
    @SuppressWarnings("unchecked")
	ChangeListener<NationVO> funcBox1Changed = (ob, o, v) -> {		// ü���� ������
		
    	List<CashVO> list = parser.getCashData();					// ȯ�� ����Ʈ ���̹����� �ҷ�����
        String nationName = v.getName();							// ���ο� ���� �̸��� ������ nationName�� ����
        List<GraphVO> graphList = new ArrayList<GraphVO>();			// �׷�������Ʈ ȣ��
        System.out.println(list);
        boolean sw = false;
        for (CashVO data : list) {									// ȯ�� ����Ʈ ���������� �ݺ���

            if (nationName.length() > data.getName().length()) {	// �����̸����� > CashVO���� �Ľ��� (���ͳݿ��� �ܾ��) �̸� ���̸� ���Ͽ�
                continue;											// ��ٸ� �ݺ��� �� ����(CashVO���� �Ľ��� �̸� ���̰� ���� �� ���)
            } 
            
            String dataName = data.getName().substring(0, nationName.length()); // �Ľ��� �̸��� ó������ �����̸� ���̸�ŭ �ڸ���. "�̱� USD" => "�̱�"

            
            if (dataName.equals(nationName)) {				// �ڸ� ������� ���ٸ�
            	sw=true;

            	String url = data.getUrlLink();				// Url ��ũ ȣ�� (�Ľ��� ��, �̱��� ��� urlLink=/marketindex/exchangeDetail.naver?marketindexCd=FX_USDKRW)
                int idx = url.lastIndexOf("?");				// ?�� �ε��� ����
                url = url.substring(idx + 1);				// ?�� �ε��� + 1��ŭ �ڸ��� (marketindexCd=FX_USDKRW)
                for (int i = 0; i < 6; ++i) {

                    String graphUrl = "https://finance.naver.com/marketindex/exchangeDailyQuote.nhn?" + url; // �ڸ� url �� �� �������� ��ħ
                    graphUrl = String.valueOf(graphUrl) + String.format("&page=%d", i + 1);			// 6��° �� ������ ��ŭ graphUrl�� ����
                    parser.getGraphData(graphUrl).forEach(graphItem -> graphList.add(graphItem));	// �Ľ��Ͽ� ��¥, bias �ۼ�Ʈ�� GraphVO�� ����
                }
                
            	for (int i = 0; i < nationList.size(); i++) {		// Label1�� �޺��ڽ����� ������ ������ ��ȭ��ȣ ����
            		if (box1.getValue().toString().equals(nationList.get(i).toString())) {
            			Label1.setText(codeList.get(i).toString());
            			Label2.setText("");
            		} 
            	}
                

                


                break;	
            }
            
        }
        if( sw==false ) { // Label1�� �޺��ڽ����� ������ ������ ��ȭ��ȣ ���� / �̰����� �־��ִ� ������ �޺��ڽ����� �ѱ��� �������� ���� ��ȭ ��ȣ�� �뭋�Ǿ�� �ϱ� ����
        	
        	for (int i = 0; i < nationList.size(); i++) {
        		if (box1.getValue().toString().equals(nationList.get(i).toString())) {
        			Label1.setText(codeList.get(i).toString());
        			Label2.setText("");
        		} 
        	}
        	
        	return;
        }
		
        
        lineChart.getData().clear();						// ��Ʈ �ʱ�ȭ
        XYChart.Series series = new XYChart.Series();
        series.setName(nationName);											// ��Ʈ �̸� ����
        
        graphList.sort((a, b) -> a.getDate().compareTo(b.getDate()));		// ��¥ �������� ����
  
        double[] dblMinMax = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};	// �ִ� �ּ� �����Լ� ȣ��
        
        graphList.forEach(graphVo -> {		// �ݺ��� ����
        	int nIdx = graphList.indexOf(graphVo);		// graphVo�� �ε��� ����
        	double dblValue = Double.parseDouble(graphVo.getBias().replaceAll(",", ""));	// bias������ �̾Ƽ� �޸��� �����, ���� dblValue�� ����

        	
        	// bias ���� (��������)
        	if (dblValue < dblMinMax[0]) {
        		dblMinMax[0] = dblValue;
        	}
        	if (dblValue > dblMinMax[1]) {
        		dblMinMax[1] = dblValue;
        	}
        	
        	series.getData().add((Object)new XYChart.Data((Object)nIdx, (Object)dblValue)); // ��Ʈ�� �����Ͱ� ����
        	
        }); // �ݺ��� ����
        
        lineChart.getData().add(series);		// ��Ʈ �ʱ�ȭ
        double yTickSpace = Math.floor(dblMinMax[0]) / 100.0; // y�� �ִ� �ּ�ȭ ����
        NumberAxis yAxis = (NumberAxis)lineChart.getYAxis();	// ��Ʈ�� y�� ����
        yAxis.setForceZeroInRange(false);			
        yAxis.setAutoRanging(false);		// y�� �ڵ����� ����
        yAxis.setTickUnit(yTickSpace);		// y�� ������ ����
        yAxis.setTickLength(yTickSpace);	// y�� ���� ����
        yAxis.setLowerBound(dblMinMax[0] - yTickSpace);	// y�� lowerbound ����
        yAxis.setUpperBound(dblMinMax[1] + yTickSpace);	// y�� upperbound ����
        NumberAxis xAxis = (NumberAxis)lineChart.getXAxis();	// x�� ����
        xAxis.setTickUnit(1.0);		// x�� �� ����
        xAxis.setTickLength(1.0);	// x�� ���� ����
        xAxis.setUpperBound((double)(graphList.size() - 1));	// upper bound�� �׷��� ����Ʈ -1 ���� ����
        xAxis.setTickLabelFormatter((StringConverter<Number>)new NumberAxis.DefaultFormatter(xAxis) { // x�� ���� ��¥�� �������� ���� ������ ����
        	
        	public String toString(Number object) {
        		String tickDate;
        		@SuppressWarnings("unused")
				String string = tickDate = graphList.size() > 0 ? (graphList.get(object.intValue())).getDate() : "";	// ��¥�� �׷��� ����Ʈ�� ��¥�� ����
        		if (tickDate.length() == 10) {	// ��¥ ���̰� 10�� �Ǹ�
        			return tickDate.substring(5).replace('.', '/');	// ��¥�� "."�� "/"��
        		}
        		return tickDate;	// ��¥�� ��ȯ
        	}
        });


    };
    
    input1.textProperty().addListener((observable, oldValue, newValue) -> {	// Listener �߰�
    	if (!newValue.matches("\\d*") && newValue.charAt(newValue.length() - 1) != '.'){
    		input1.setText(newValue.replaceAll("[^\\d]",""));	// input1�� �� ���� (ȯ�� ����� ��)
    	}
    });
    box1.valueProperty().addListener(funcBox1Changed);	// box1(��������Ʈ)�� �� ����
    funcBox1Changed.changed(null, nationList.get(0), nationList.get(1));	// �ʱⰪ�� �ѱ�, �̱����� �����Ͽ� �޷� ȯ�� ��Ʈ�� ȣ��
    

    

    
    } catch (Exception e) {
    	e.printStackTrace();
    }

  }
    
	
  
  public void getData() {		// ȯ�� ��� �޼ҵ�
	  
	  try {
	  
		  List<CashVO> list = parser.getCashData();	// CashVO�� ����Ʈ�� parser�� �̿��Ͽ� �ڸ���
		  NationVO value = box1.getValue();		// box1�� �� ����
		  NationVO value2 = box2.getValue();	// box2�� �� ����
		  
		  if (value == null || value2 == null || input1.getCharacters().length() == 0) {	// ����ִ� ĭ�� ����������
			  Alert alert = new Alert(AlertType.WARNING);
			  alert.setTitle("WARNING");			// ���â ���
			  alert.setHeaderText("����ִ� �Է�ĭ�� �ֽ��ϴ�.");
			  alert.setContentText("�ٽ� �ѹ� Ȯ�����ּ���.");
			  alert.showAndWait();
		  } else {
			  List<String> nationNames = Arrays.asList(new String[] {value.getName(), value2.getName()});	// �Է¹��� ���� �����ϴ� ����Ʈ ����
			  double[] dblsBias = new double[] {1.0,1.0};		// �Ÿű������� �������� ����� bias ����
			  for (CashVO data : list) {						// CashVO�� �ݺ� 
				  nationNames.forEach(nationName ->{			// �Է¹��� ���� ������ ����Ʈ�� �ݺ�
					  String dataName = data.getName().substring(0, nationName.length());	// ���� �̸��� �����Ͽ� �޺��ڽ����� ������ ���� ����
					  if (dataName.equals(nationName)) {		// �ݺ����� ���� ��ġ�ϴ� ������ �����Ѵٸ�
						  int idx = nationNames.indexOf(nationName);	// �ε��� ����
						  dblsBias[idx] = Double.parseDouble(data.getBias().replaceAll(",", ""));	// bias���� �Ľ��� ����
						  if (nationName.equals("�Ϻ�")) {	// �Ϻ��� 1/10�̴� ���� ������ (bias�� �������� ����)
							  int n = idx;
							  dblsBias[n] = dblsBias[n] / 100.0;
						  }
						  
					  }
				  });
			  }
			  int inputValue1= Integer.parseInt(input1.getText());	// �Է¹��� ���� �����ϴ� int ���� ����
			  double krMoney = inputValue1 * dblsBias[0];	// bias�� ������ �迭�� ù��° ���� �Է¹��� ���� ���Ͽ� ����
			  input2.setText(String.format("%.2f", new Object[] { Double.valueOf(krMoney / dblsBias[1]) })); // �ι�° �ڽ��� ȯ���� ����� ���� �����Ŵ
			  
			  for (int i = 0; i < nationList.size(); i++) {		// ȯ���� ����� ������ ��ȭ��ȣ�� Label2�� ����
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
