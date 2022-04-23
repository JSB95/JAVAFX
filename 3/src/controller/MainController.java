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
    parser = new Parser();		// parser È£Ãâ
    
    nationList = FXCollections.observableArrayList(); // ±¹°¡ ¸®½ºÆ® ÀúÀå¿ë
    
    codeList = FXCollections.observableArrayList();	// ÅëÈ­ ´ÜÀ§ ÀúÀå¿ë

    try {
    	
    	String line;
    	
    	// ÆÄÀÏ ºÒ·¯¿À±â
    	
    	File dataFile = new File(getClass().getResource("/resources/data.txt").getFile());

    	FileInputStream fis = new FileInputStream(dataFile);

    	InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

    	BufferedReader br = new BufferedReader(isr);

//    	List<CashVO> list = this.parser.getCashData();
    	while ((line = br.readLine()) != null) {	// ºÒ·¯¿Â ÆÄÀÏÀÌ null ÀÌ µÉ¶§±îÁö
    		
    		String[] lines = line.split("#");		// #À» ±âÁØÀ¸·Î ³ª´® (±¹°¡#ÄÚµå)

    		NationVO temp = new NationVO(lines[0], lines[1]);	// NationVO¿¡ ÀúÀå
    		

    		nationList.add(temp);					// ¸®½ºÆ®¿¡ Ãß°¡

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
    	

    	

    	

    	
    	box1.setItems(nationList);					// ÄŞº¸¹Ú½º1 °ª ÁöÁ¤
    	box2.setItems(nationList);					// ÄŞº¸¹Ú½º2 °ª ÁöÁ¤
    	
    	
    	
    	box1.setValue(nationList.get(0));			// ÄŞº¸¹Ú½º1 ±âº»°ª ÁöÁ¤
    	box2.setValue(nationList.get(1));			// ÄŞº¸¹Ú½º2 ±âº»°ª ÁöÁ¤
    	
    	
 

  	   	
//    try 
    @SuppressWarnings("unchecked")
	ChangeListener<NationVO> funcBox1Changed = (ob, o, v) -> {		// Ã¼ÀÎÁö ¸®½º³Ê
		
    	List<CashVO> list = parser.getCashData();					// È¯À² ¸®½ºÆ® ³×ÀÌ¹ö¿¡¼­ ºÒ·¯¿À±â
        String nationName = v.getName();							// »õ·Î¿î °ªÀÇ ÀÌ¸§À» ÃßÃâÇØ nationName¿¡ ÀúÀå
        List<GraphVO> graphList = new ArrayList<GraphVO>();			// ±×·¡ÇÁ¸®½ºÆ® È£Ãâ
        System.out.println(list);
        boolean sw = false;
        for (CashVO data : list) {									// È¯À² ¸®½ºÆ® ³¡³¯¶§±îÁö ¹İº¹¹®

            if (nationName.length() > data.getName().length()) {	// ±¹°¡ÀÌ¸§±æÀÌ > CashVO¿¡¼­ ÆÄ½ÌÇÑ (ÀÎÅÍ³İ¿¡¼­ ±Ü¾î¿Â) ÀÌ¸§ ±æÀÌ¸¦ ºñ±³ÇÏ¿©
                continue;											// ±æ´Ù¸é ¹İº¹¹® ¸Ç À§·Î(CashVO¿¡¼­ ÆÄ½ÌÇÑ ÀÌ¸§ ±æÀÌ°¡ º¸Åë ´õ ±æ´Ù)
            } 
            
            String dataName = data.getName().substring(0, nationName.length()); // ÆÄ½ÌÇÑ ÀÌ¸§À» Ã³À½ºÎÅÍ ±¹°¡ÀÌ¸§ ±æÀÌ¸¸Å­ ÀÚ¸¥´Ù. "¹Ì±¹ USD" => "¹Ì±¹"

            
            if (dataName.equals(nationName)) {				// ÀÚ¸¥ °á°ú°ªÀÌ °°´Ù¸é
            	sw=true;

            	String url = data.getUrlLink();				// Url ¸µÅ© È£Ãâ (ÆÄ½ÌÇÑ ÈÄ, ¹Ì±¹ÀÇ °æ¿ì urlLink=/marketindex/exchangeDetail.naver?marketindexCd=FX_USDKRW)
                int idx = url.lastIndexOf("?");				// ?ÀÇ ÀÎµ¦½º ÁöÁ¤
                url = url.substring(idx + 1);				// ?ÀÇ ÀÎµ¦½º + 1¸¸Å­ ÀÚ¸£±â (marketindexCd=FX_USDKRW)
                for (int i = 0; i < 6; ++i) {

                    String graphUrl = "https://finance.naver.com/marketindex/exchangeDailyQuote.nhn?" + url; // ÀÚ¸¥ url °ú »ó¼¼ ÆäÀÌÁö¸¦ ÇÕÄ§
                    graphUrl = String.valueOf(graphUrl) + String.format("&page=%d", i + 1);			// 6¹øÂ° »ó¼¼ ÆäÀÌÁö ¸¸Å­ graphUrl¿¡ ÀúÀå
                    parser.getGraphData(graphUrl).forEach(graphItem -> graphList.add(graphItem));	// ÆÄ½ÌÇÏ¿© ³¯Â¥, bias ÆÛ¼¾Æ®¸¦ GraphVO¿¡ ÀúÀå
                }
                
            	for (int i = 0; i < nationList.size(); i++) {		// Label1¿¡ ÄŞº¸¹Ú½º¿¡¼­ ¼±ÅÃÇÑ ³ª¶óÀÇ ÅëÈ­±âÈ£ ³ëÃâ
            		if (box1.getValue().toString().equals(nationList.get(i).toString())) {
            			Label1.setText(codeList.get(i).toString());
            			Label2.setText("");
            		} 
            	}
                

                


                break;	
            }
            
        }
        if( sw==false ) { // Label1¿¡ ÄŞº¸¹Ú½º¿¡¼­ ¼±ÅÃÇÑ ³ª¶óÀÇ ÅëÈ­±âÈ£ ³ëÃâ / ÀÌ°÷¿¡µµ ³Ö¾îÁÖ´Â ÀÌÀ¯´Â ÄŞº¸¹Ú½º¿¡¼­ ÇÑ±¹À» ¼±ÅÃÇßÀ» ¶§µµ ÅëÈ­ ±âÈ£°¡ ³ë­‹µÇ¾î¾ß ÇÏ±â ¶§¹®
        	
        	for (int i = 0; i < nationList.size(); i++) {
        		if (box1.getValue().toString().equals(nationList.get(i).toString())) {
        			Label1.setText(codeList.get(i).toString());
        			Label2.setText("");
        		} 
        	}
        	
        	return;
        }
		
        
        lineChart.getData().clear();						// Â÷Æ® ÃÊ±âÈ­
        XYChart.Series series = new XYChart.Series();
        series.setName(nationName);											// Â÷Æ® ÀÌ¸§ ÁöÁ¤
        
        graphList.sort((a, b) -> a.getDate().compareTo(b.getDate()));		// ³¯Â¥ ³»¸²Â÷¼ø Á¤·Ä
  
        double[] dblMinMax = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};	// ÃÖ´ë ÃÖ¼Ò ÁöÁ¤ÇÔ¼ö È£Ãâ
        
        graphList.forEach(graphVo -> {		// ¹İº¹¹® ½ÇÇà
        	int nIdx = graphList.indexOf(graphVo);		// graphVoÀÇ ÀÎµ¦½º ÀúÀå
        	double dblValue = Double.parseDouble(graphVo.getBias().replaceAll(",", ""));	// biasÁ¤º¸¸¸ »Ì¾Æ¼­ ÄŞ¸¶¸¦ Áö¿ì°í, µû·Î dblValue¿¡ ÀúÀå

        	
        	// bias Á¤·Ä (³»¸²Â÷¼ø)
        	if (dblValue < dblMinMax[0]) {
        		dblMinMax[0] = dblValue;
        	}
        	if (dblValue > dblMinMax[1]) {
        		dblMinMax[1] = dblValue;
        	}
        	
        	series.getData().add((Object)new XYChart.Data((Object)nIdx, (Object)dblValue)); // Â÷Æ®¿¡ µ¥ÀÌÅÍ°ª ÁöÁ¤
        	
        }); // ¹İº¹¹® Á¾·á
        
        lineChart.getData().add(series);		// Â÷Æ® ÃÊ±âÈ­
        double yTickSpace = Math.floor(dblMinMax[0]) / 100.0; // yÃà ÃÖ´ë ÃÖ¼ÒÈ­ ¼³Á¤
        NumberAxis yAxis = (NumberAxis)lineChart.getYAxis();	// Â÷Æ®¿¡ yÃà ÁöÁ¤
        yAxis.setForceZeroInRange(false);			
        yAxis.setAutoRanging(false);		// yÃà ÀÚµ¿Á¶Á¤ ÇØÁ¦
        yAxis.setTickUnit(yTickSpace);		// yÃà µ¥ÀÌÅÍ ÁöÁ¤
        yAxis.setTickLength(yTickSpace);	// yÃà ±æÀÌ ÁöÁ¤
        yAxis.setLowerBound(dblMinMax[0] - yTickSpace);	// yÃà lowerbound ¼³Á¤
        yAxis.setUpperBound(dblMinMax[1] + yTickSpace);	// yÃà upperbound ¼³Á¤
        NumberAxis xAxis = (NumberAxis)lineChart.getXAxis();	// xÃà ÁöÁ¤
        xAxis.setTickUnit(1.0);		// xÃà °ª ÁöÁ¤
        xAxis.setTickLength(1.0);	// xÃà ±æÀÌ ÁöÁ¤
        xAxis.setUpperBound((double)(graphList.size() - 1));	// upper bound¸¦ ±×·¡ÇÁ ¸®½ºÆ® -1 ±îÁö ÁöÁ¤
        xAxis.setTickLabelFormatter((StringConverter<Number>)new NumberAxis.DefaultFormatter(xAxis) { // xÃà °ªÀÎ ³¯Â¥¸¦ °¡Á®¿À±â À§ÇØ Æ÷¸ËÅÍ ¼±¾ğ
        	
        	public String toString(Number object) {
        		String tickDate;
        		@SuppressWarnings("unused")
				String string = tickDate = graphList.size() > 0 ? (graphList.get(object.intValue())).getDate() : "";	// ³¯Â¥¸¦ ±×·¡ÇÁ ¸®½ºÆ®ÀÇ ³¯Â¥·Î ÁöÁ¤
        		if (tickDate.length() == 10) {	// ³¯Â¥ ±æÀÌ°¡ 10ÀÌ µÇ¸é
        			return tickDate.substring(5).replace('.', '/');	// ³¯Â¥ÀÇ "."À» "/"·Î
        		}
        		return tickDate;	// ³¯Â¥°ª ¹İÈ¯
        	}
        });


    };
    
    input1.textProperty().addListener((observable, oldValue, newValue) -> {	// Listener Ãß°¡
    	if (!newValue.matches("\\d*") && newValue.charAt(newValue.length() - 1) != '.'){
    		input1.setText(newValue.replaceAll("[^\\d]",""));	// input1ÀÇ °ª ¼³Á¤ (È¯À² °è»êÇÒ °ª)
    	}
    });
    box1.valueProperty().addListener(funcBox1Changed);	// box1(±¹°¡¸®½ºÆ®)ÀÇ °ª ¼³Á¤
    funcBox1Changed.changed(null, nationList.get(0), nationList.get(1));	// ÃÊ±â°ªÀ» ÇÑ±¹, ¹Ì±¹À¸·Î ¼³Á¤ÇÏ¿© ´Ş·¯ È¯À² Â÷Æ®¸¦ È£Ãâ
    

    

    
    } catch (Exception e) {
    	e.printStackTrace();
    }

  }
    
	
  
  public void getData() {		// È¯À² °è»ê ¸Ş¼Òµå
	  
	  try {
	  
		  List<CashVO> list = parser.getCashData();	// CashVOÀÇ ¸®½ºÆ®¸¦ parser¸¦ ÀÌ¿ëÇÏ¿© ÀÚ¸¥´Ù
		  NationVO value = box1.getValue();		// box1ÀÇ °ª ¼±¾ğ
		  NationVO value2 = box2.getValue();	// box2ÀÇ °ª ¼±¾ğ
		  
		  if (value == null || value2 == null || input1.getCharacters().length() == 0) {	// ºñ¾îÀÖ´Â Ä­ÀÌ Á¸ÀçÇßÀ»¶§
			  Alert alert = new Alert(AlertType.WARNING);
			  alert.setTitle("WARNING");			// °æ°íÃ¢ Ãâ·Â
			  alert.setHeaderText("ºñ¾îÀÖ´Â ÀÔ·ÂÄ­ÀÌ ÀÖ½À´Ï´Ù.");
			  alert.setContentText("´Ù½Ã ÇÑ¹ø È®ÀÎÇØÁÖ¼¼¿ä.");
			  alert.showAndWait();
		  } else {
			  List<String> nationNames = Arrays.asList(new String[] {value.getName(), value2.getName()});	// ÀÔ·Â¹ŞÀº °ªÀ» ÀúÀåÇÏ´Â ¸®½ºÆ® ¼±¾ğ
			  double[] dblsBias = new double[] {1.0,1.0};		// ¸Å¸Å±âÁØÀ²À» ±âÁØÀ¸·Î °è»êÇÒ bias ¼±¾ğ
			  for (CashVO data : list) {						// CashVO¸¦ ¹İº¹ 
				  nationNames.forEach(nationName ->{			// ÀÔ·Â¹ŞÀº °ªÀ» ÀúÀåÇÑ ¸®½ºÆ®¸¦ ¹İº¹
					  String dataName = data.getName().substring(0, nationName.length());	// ±¹°¡ ÀÌ¸§À» ¼±¾ğÇÏ¿© ÄŞº¸¹Ú½º¿¡¼­ ÁöÁ¤ÇÑ °ªÀ» ÃßÃâ
					  if (dataName.equals(nationName)) {		// ¹İº¹¹®À» µ¹·Á ÀÏÄ¡ÇÏ´Â ±¹°¡°¡ Á¸ÀçÇÑ´Ù¸é
						  int idx = nationNames.indexOf(nationName);	// ÀÎµ¦½º ÁöÁ¤
						  dblsBias[idx] = Double.parseDouble(data.getBias().replaceAll(",", ""));	// bias°ªÀ» ÆÄ½ÌÈÄ ÀúÀå
						  if (nationName.equals("ÀÏº»")) {	// ÀÏº»Àº 1/10ÀÌ´Ï µû·Î ÁöÁ¤ÇÔ (bias°¡ Á¸ÀçÇÏÁö ¾ÊÀ½)
							  int n = idx;
							  dblsBias[n] = dblsBias[n] / 100.0;
						  }
						  
					  }
				  });
			  }
			  int inputValue1= Integer.parseInt(input1.getText());	// ÀÔ·Â¹ŞÀº °ªÀ» ÀúÀåÇÏ´Â int º¯¼ö ÀúÀå
			  double krMoney = inputValue1 * dblsBias[0];	// bias¸¦ ÀúÀåÇÑ ¹è¿­ÀÇ Ã¹¹øÂ° °ª°ú ÀÔ·Â¹ŞÀº °ªÀ» °öÇÏ¿© ÀúÀå
			  input2.setText(String.format("%.2f", new Object[] { Double.valueOf(krMoney / dblsBias[1]) })); // µÎ¹øÂ° ¹Ú½º¿¡ È¯À²À» °è»êÇÑ °ªÀ» ³ëÃâ½ÃÅ´
			  
			  for (int i = 0; i < nationList.size(); i++) {		// È¯À²À» °è»êÇÒ ³ª¶óÀÇ ÅëÈ­±âÈ£¸¦ Label2¿¡ ³ëÃâ
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
