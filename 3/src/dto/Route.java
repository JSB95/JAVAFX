package dto;

public class Route {
	private int rnum;				// 항공일지 번호 pk
	private String aname;			// 비행기명 fk, Plane
	private String rdeparture;		// 출발지
	private String rdestination;	// 도착지
	private String rflightTime;		// 비행시간
	private String rdeparturedate;	// 비행 출발 날짜
	private int rbaseprice;			// 이코노미 가격
	private String price;
	
	public Route() {}
	
	public Route(int rnum, String aname, String rdeparture, String rdestination, String rflightTime,
			String rdeparturedate, String price) {
		this.rnum = rnum;
		this.aname = aname;
		this.rdeparture = rdeparture;
		this.rdestination = rdestination;
		this.rflightTime = rflightTime;
		this.rdeparturedate = rdeparturedate;
		this.price = price;
	}

	public Route(int rnum, String aname, String rdeparture, String rdestination, String rflightTime,
			String rdeparturedate, int rbaseprice) {
		this.rnum = rnum;
		this.aname = aname;
		this.rdeparture = rdeparture;
		this.rdestination = rdestination;
		this.rflightTime = rflightTime;
		this.rdeparturedate = rdeparturedate;
		this.rbaseprice = rbaseprice;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getRdeparture() {
		return rdeparture;
	}

	public void setRdeparture(String rdeparture) {
		this.rdeparture = rdeparture;
	}

	public String getRdestination() {
		return rdestination;
	}

	public void setRdestination(String rdestination) {
		this.rdestination = rdestination;
	}

	public String getRflightTime() {
		return rflightTime;
	}

	public void setRflightTime(String rflightTime) {
		this.rflightTime = rflightTime;
	}

	public String getRdeparturedate() {
		return rdeparturedate;
	}

	public void setRdeparturedate(String rdeparturedate) {
		this.rdeparturedate = rdeparturedate;
	}

	public int getRbaseprice() {
		return rbaseprice;
	}

	public void setRbaseprice(int rbaseprice) {
		this.rbaseprice = rbaseprice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	

}