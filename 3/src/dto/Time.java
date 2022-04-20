package dto;

public class Time {
	
	private String city;
	private int gmt;
	
	public Time() {}
	
	public Time(String city, int gmt) {
		super();
		this.city = city;
		this.gmt = gmt;
	
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getGmt() {
		return gmt;
	}
	public void setGmt(int gmt) {
		this.gmt = gmt;
	}

}
