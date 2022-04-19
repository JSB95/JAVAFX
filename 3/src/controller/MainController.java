package controller;

import dto.CashVO;
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
import javafx.beans.value.ObservableValue;
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
  
  private ObservableList<NationVO> nationList;
  
  private Parser parser;
  
  @FXML
  StackedAreaChart<Number, Number> lineChart;
  
  @FXML
  private void initialize() {
    boolean MIN = false;
    boolean MAX = true;
    this.parser = new Parser();
    this.nationList = FXCollections.observableArrayList();
    try {
    	
    	String line;
    	File dataFile = new File(this.getClass().getResource("/resources/data.txt").getFile());
    	FileInputStream fis = new FileInputStream(dataFile);
    	InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
    	BufferedReader br = new BufferedReader(isr);
    	List<CashVO> list = this.parser.getCashData();
    	while ((line = br.readLine()) != null) {
    		String[] lines = line.split("#");
    		NationVO temp = new NationVO(lines[0], lines[1]);
    		this.nationList.add(temp);
    	}
    	this.box1.setItems(this.nationList);
    	this.box2.setItems(this.nationList);
    	this.box1.setValue((NationVO)this.nationList.get(0));
    	this.box2.setValue((NationVO)this.nationList.get(1));
    	
    } catch (Exception e) {
    	e.printStackTrace();
    }
    
    ChangeListener<NationVO> funcBox1Changed = (ob, o, v) -> {
    	List<CashVO> list = this.parser.getCashData();
        String nationName = v.getName();
        List<GraphVO> graphList = new ArrayList<GraphVO>();
        for (CashVO data : list) {
            if (nationName.length() > data.getName().length()) {
                continue;
            }
            String dataName = data.getName().substring(0, nationName.length());
            if (dataName.equals(nationName)) {
                String url = data.getUrlLink();
                int idx = url.lastIndexOf("?");
                url = url.substring(idx + 1);
                for (int i = 0; i < 6; ++i) {
                    String graphUrl = "https://finance.naver.com/marketindex/exchangeDailyQuote.nhn?" + url;
                    graphUrl = String.valueOf(graphUrl) + String.format("&page=%d", i + 1);
                    this.parser.getGraphData(graphUrl).forEach(graphItem -> graphList.add(graphItem));
                }
                break;
            }
        }
        this.lineChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName(nationName);
        graphList.sort((a, b) -> a.getDate().compareTo(b.getDate()));
        double[] dblMinMax = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
        
        graphList.forEach(graphVo -> {
        	int nIdx = graphList.indexOf(graphVo);
        	double dblValue = Double.parseDouble(graphVo.getBias().replaceAll(",", ""));
        	if (dblValue < dblMinMax[0]) {
        		dblMinMax[0] = dblValue;
        	}
        	if (dblValue > dblMinMax[1]) {
        		dblMinMax[1] = dblValue;
        	}
        	series.getData().add((Object)new XYChart.Data((Object)nIdx, (Object)dblValue));
        });
        this.lineChart.getData().add(series);
        double yTickSpace = Math.floor(dblMinMax[0]) / 100.0;
        NumberAxis yAxis = (NumberAxis)this.lineChart.getYAxis();
        yAxis.setForceZeroInRange(false);
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(yTickSpace);
        yAxis.setTickLength(yTickSpace);
        yAxis.setLowerBound(dblMinMax[0] - yTickSpace);
        yAxis.setUpperBound(dblMinMax[1] + yTickSpace);
        NumberAxis xAxis = (NumberAxis)this.lineChart.getXAxis();
        xAxis.setTickUnit(1.0);
        xAxis.setTickLength(1.0);
        xAxis.setUpperBound((double)(graphList.size() - 1));
        xAxis.setTickLabelFormatter((StringConverter<Number>)new NumberAxis.DefaultFormatter(xAxis) {
        	
        	public String toString(Number object) {
        		String tickDate;
        		String string = tickDate = graphList.size() > 0 ? (graphList.get(object.intValue())).getDate() : "";
        		if (tickDate.length() == 10) {
        			return tickDate.substring(5).replace('.', '/');
        		}
        		return tickDate;
        	}
        });
    };
    
    this.input1.textProperty().addListener((observable, oldValue, newValue) -> {
    	if (!newValue.matches("\\d*") && newValue.charAt(newValue.length() - 1) != '.'){
    		this.input1.setText(newValue.replaceAll("[^\\d]",""));
    	}
    });
    this.box1.valueProperty().addListener(funcBox1Changed);
    funcBox1Changed.changed(null, (NationVO)this.nationList.get(0), (NationVO)this.nationList.get(1));
    
    

  }
  
  public void getData() {
	  List<CashVO> list = this.parser.getCashData();
	  NationVO value = this.box1.getValue();
	  NationVO value2 = this.box2.getValue();
	  
	  if (value == null || value2 == null || this.input1.getCharacters().length() == 0) {
		  Alert alert = new Alert(AlertType.WARNING);
		  alert.setTitle("WARNING");
		  alert.setHeaderText("����ִ� �Է�ĭ�� �ֽ��ϴ�.");
		  alert.setContentText("�ٽ� �ѹ� Ȯ�����ּ���.");
		  alert.showAndWait();
	  } else {
		  List<String> nationNames = Arrays.asList(new String[] {value.getName(), value2.getName()});
		  double[] dblsBias = new double[] {1.0,1.0};
		  for (CashVO data : list) {
			  nationNames.forEach(nationName ->{
				  String dataName = data.getName().substring(0, nationName.length());
				  if (dataName.equals(nationNames)) {
					  int idx = nationNames.indexOf(nationName);
					  dblsBias[idx] = Double.parseDouble(data.getBias().replaceAll(",", ""));
					  if (nationName.equals("�Ϻ�")) {
						  int n = idx;
						  dblsBias[n] = dblsBias[n] / 100.0;
					  }
					  
				  }
			  });
		  }
		  int inputValue1= Integer.parseInt(this.input1.getText());
		  double krMoney = inputValue1 * dblsBias[0];
		  this.input2.setText(String.format("%.2f",krMoney / dblsBias[1]));
	  }
  }
}
