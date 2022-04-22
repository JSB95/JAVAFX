package dto;

public class GraphVO {
	  private String date;
	  
	  private String bias;
	  
	  private String persent;
	  
	  public GraphVO(String date, String bias, String persent) {
	    this.date = date;
	    this.bias = bias;
	    this.persent = persent;
	  }
	  
	  public String getDate() {
	    return this.date;
	  }
	  
	  public void setDate(String date) {
	    this.date = date;
	  }
	  
	  public String getBias() {
	    return this.bias;
	  }
	  
	  public void setBias(String bias) {
	    this.bias = bias;
	  }
	  
	  public String getPersent() {
	    return this.persent;
	  }
	  
	  public void setPersent(String persent) {
	    this.persent = persent;
	  }
	  
	  public String toString() {
	    return "graphVO [date=" + this.date + ", bias=" + this.bias + ", persent=" + this.persent + "]";
	  }
	}