package dto;

public class CashVO {
	  private String name;
	  
	  private String bias;
	  
	  private String salePrice;
	  
	  private String buyPrice;
	  
	  private String urlLink;
	  
	  public CashVO(String name, String bias, String salePrice, String buyPrice, String urlLink) {
	    this.name = name;
	    this.bias = bias;
	    this.salePrice = salePrice;
	    this.buyPrice = buyPrice;
	    this.urlLink = urlLink;
	  }
	  
	  public String getName() {
	    return this.name;
	  }
	  
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  public String getBias() {
	    return this.bias;
	  }
	  
	  public void setBias(String bias) {
	    this.bias = bias;
	  }
	  
	  public String getSalePrice() {
	    return this.salePrice;
	  }
	  
	  public void setSalePrice(String salePrice) {
	    this.salePrice = salePrice;
	  }
	  
	  public String getBuyPrice() {
	    return this.buyPrice;
	  }
	  
	  public void setBuyPrice(String buyPrice) {
	    this.buyPrice = buyPrice;
	  }
	  
	  public String getUrlLink() {
	    return this.urlLink;
	  }
	  
	  public void setUrlLink(String urlLink) {
	    this.urlLink = urlLink;
	  }
	  
	  public String toString() {
	    return "CashVO [name=" + this.name + ", bias=" + this.bias + ", salePrice=" + this.salePrice + ", buyPrice=" + this.buyPrice + 
	      ", urlLink=" + this.urlLink + "]";
	  }
}
