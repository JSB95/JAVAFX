package parsePack;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import dto.CashVO;
import dto.GraphVO;

public class Parser {
	
	public List<CashVO> getCashData(){
		
		String menu = null;
		String url = "https://finance.naver.com/marketindex/exchangeList.nhn";
		List<CashVO> mList = new ArrayList<>();
		
		try {
			
			Document doc = Jsoup.connect(url).get();
			Elements list = doc.select(".tbl_area tbody tr");
			list.forEach(value ->{
				
				String name = value.selectFirst("td:nth-child(1) > a").text();
				String bias = value.selectFirst("td:nth-child(2)").text();
				String salePrice = value.selectFirst("td:nth-child(3)").text();
	            String buyPrice = value.selectFirst("td:nth-child(4)").text();
	            String urlLink = value.selectFirst("td:nth-child(1) > a").attr("href");
	            CashVO temp = new CashVO(name, bias, salePrice, buyPrice, urlLink);
	            mList.add(temp);
			});
			
			
			
		} catch (Exception e) {
			System.err.println("PARSER LIST ERROR" + e);
		}
		
		return mList;
	}
	
	public List<GraphVO> getGraphData(String graphUrl){
		List<GraphVO> mList = new ArrayList<>();
		try {
			
			Document doc2 = Jsoup.connect(graphUrl).get();
			Elements list = doc2.select(".tbl_exchange > tbody > tr");
			System.out.println(list.size());
			list.forEach(value ->{
				
				String date = value.selectFirst(".date").text();
	            String bias = value.selectFirst("td:nth-child(2)").text();
	            String persent = value.selectFirst("td:nth-child(3)").text();
	            GraphVO temp = new GraphVO(date, bias, persent);
	            mList.add(temp);
				
			});
			
		} catch (Exception e) {
			System.err.println("GETGRAPHDATA ERROR" + e);
		}
		return mList;
	}

}
