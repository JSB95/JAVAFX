package controller;

import javafx.fxml.*;
import parsePack.*;
import javafx.collections.*;
import java.io.*;
import javafx.scene.control.*;
import dto.*;
import javafx.scene.chart.*;
import javafx.util.*;
import javafx.beans.value.*;
import java.util.*;

public class MainController
{
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
        final int MIN = 0;
        final int MAX = 1;
        this.parser = new Parser();
        this.nationList = FXCollections.observableArrayList();
        try {
            final File dataFile = new File(this.getClass().getResource("/resources/data.txt").getFile());
            final FileInputStream fis = new FileInputStream(dataFile);
            final InputStreamReader isr = new InputStreamReader(fis, "utf-8");
            final BufferedReader br = new BufferedReader(isr);
            final List<CashVO> list = this.parser.getCashData();
            while (true) {
                final String line = br.readLine();
                if (line == null) {
                    break;
                }
                final String[] lines = line.split("#");
                final NationVO temp = new NationVO(lines[0], lines[1]);
                this.nationList.add(temp);
            }
            this.box1.setItems(this.nationList);
            this.box2.setItems(this.nationList);
            this.box1.setValue(this.nationList.get(0));
            this.box2.setValue(this.nationList.get(1));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("\ub370\uc774\ud130 \ud30c\uc77c \ubd88\ub7ec\uc624\ub294 \uc911 \uc624\ub958 \ubc1c\uc0dd");
            final Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("\ub370\uc774\ud130 \ud30c\uc77c \ubd88\ub7ec\uc624\ub294 \uc911 \uc624\ub958 \ubc1c\uc0dd");
            alert.showAndWait();
        }
        final List<CashVO> list2;
        final String nationName;
        final List<GraphVO> graphList;
        final Iterator<CashVO> iterator;
        CashVO data;
        String dataName;
        String url;
        int idx;
        String url2;
        int i;
        String graphUrl;
        String graphUrl2;
        final XYChart.Series<Number, Number> series;
        final double[] dblMinMax;
        final List list3;
        final int nIdx;
        final double dblValue;
        final XYChart.Series<Integer, Double> series2;
        final double yTickSpace;
        final NumberAxis yAxis;
        final NumberAxis xAxis;
        final ChangeListener<NationVO> funcBox1Changed = (ob, o, v) -> {
            list2 = this.parser.getCashData();
            nationName = v.getName();
            graphList = new ArrayList<GraphVO>();
            list2.iterator();
            while (iterator.hasNext()) {
                data = iterator.next();
                if (nationName.length() > data.getName().length()) {
                    continue;
                }
                else {
                    dataName = data.getName().substring(0, nationName.length());
                    if (dataName.equals(nationName)) {
                        url = data.getUrlLink();
                        idx = url.lastIndexOf("?");
                        url2 = url.substring(idx + 1);
                        for (i = 0; i < 6; ++i) {
                            graphUrl = "https://finance.naver.com/marketindex/exchangeDailyQuote.nhn?" + url2;
                            graphUrl2 = String.valueOf(graphUrl) + String.format("&page=%d", i + 1);
                            this.parser.getGraphData(graphUrl2).forEach(graphItem -> graphList.add(graphItem));
                        }
                        break;
                    }
                    else {
                        continue;
                    }
                }
            }
            this.lineChart.getData().clear();
            series = new XYChart.Series<Number, Number>();
            series.setName(nationName);
            graphList.sort((a, b) -> a.getDate().compareTo(b.getDate()));
            dblMinMax = new double[] { Double.MAX_VALUE, Double.MIN_VALUE };
            graphList.forEach(graphVo -> {
                nIdx = list3.indexOf(graphVo);
                dblValue = Double.parseDouble(graphVo.getBias().replaceAll(",", ""));
                if (dblValue < o[0]) {
                    o[0] = dblValue;
                }
                if (dblValue > o[1]) {
                    o[1] = dblValue;
                }
                series2.getData().add(new XYChart.Data<Integer, Double>(nIdx, dblValue));
                return;
            });
            this.lineChart.getData().add((XYChart.Series<Object, Object>)series);
            yTickSpace = Math.floor(dblMinMax[0]) / 100.0;
            yAxis = (NumberAxis)this.lineChart.getYAxis();
            yAxis.setForceZeroInRange(false);
            yAxis.setAutoRanging(false);
            yAxis.setTickUnit(yTickSpace);
            yAxis.setTickLength(yTickSpace);
            yAxis.setLowerBound(dblMinMax[0] - yTickSpace);
            yAxis.setUpperBound(dblMinMax[1] + yTickSpace);
            xAxis = (NumberAxis)this.lineChart.getXAxis();
            xAxis.setTickUnit(1.0);
            xAxis.setTickLength(1.0);
            xAxis.setUpperBound(graphList.size() - 1);
            xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis) {
                private final /* synthetic */ List val$graphList;
                
                @Override
                public String toString(final Number object) {
                    final String tickDate = (this.val$graphList.size() > 0) ? this.val$graphList.get(object.intValue()).getDate() : "";
                    if (tickDate.length() == 10) {
                        return tickDate.substring(5).replace('.', '/');
                    }
                    return tickDate;
                }
            });
            return;
        };
        this.input1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") && newValue.charAt(newValue.length() - 1) != '.') {
                this.input1.setText(newValue.replaceAll("[^\\d]", ""));
            }
            return;
        });
        this.box1.valueProperty().addListener((ChangeListener<? super Object>)funcBox1Changed);
        funcBox1Changed.changed(null, this.nationList.get(0), this.nationList.get(1));
    }
    
    public void getData() {
        final List<CashVO> list = this.parser.getCashData();
        final NationVO value = this.box1.getValue();
        final NationVO value2 = this.box2.getValue();
        if (value == null || value2 == null || this.input1.getCharacters().length() == 0) {
            final Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("\ube44\uc5b4\uc788\ub294 \uc785\ub825 \uce78\uc774 \uc788\uc2b5\ub2c8\ub2e4!");
            alert.setContentText("\ub2e4\uc2dc \ud55c \ubc88 \ud655\uc778\ud574 \uc8fc\uc138\uc694.");
            alert.showAndWait();
        }
        else {
            final List<String> nationNames = Arrays.asList(value.getName(), value2.getName());
            final double[] dblBias = { 1.0, 1.0 };
            for (final CashVO data : list) {
                final CashVO cashVO;
                final String dataName;
                final List list2;
                int idx;
                final Object o;
                final int n;
                nationNames.forEach(nationName -> {
                    dataName = cashVO.getName().substring(0, nationName.length());
                    if (dataName.equals(nationName)) {
                        idx = list2.indexOf(nationName);
                        o[idx] = Double.parseDouble(cashVO.getBias().replaceAll(",", ""));
                        if (nationName.equals("\uc77c\ubcf8")) {
                            o[n] /= 100.0;
                        }
                    }
                    return;
                });
            }
            final int inputValue1 = Integer.parseInt(this.input1.getText());
            final double krMoney = inputValue1 * dblBias[0];
            this.input2.setText(String.format("%.2f", krMoney / dblBias[1]));
        }
    }
}