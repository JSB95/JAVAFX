package dto;

public class Route {
	private int rnum;				// 항공일지 번호 pk
	private String pname;			// 비행기명 fk, Plane
	private String rdeparture;		// 출발지
	private String rdestination;	// 도착지
	private String rflightTime;		// 비행시간
	private String rdepartureDate;	// 비행 출발 날짜
	private int rbasePrice;			// 이코노미 가격
	
	public Route() {}

	public Route(int rnum, String pname, String rdeparture, String rdestination, String rflightTime,
			String rdepartureDate, int rbasePrice) {
		this.rnum = rnum;
		this.pname = pname;
		this.rdeparture = rdeparture;
		this.rdestination = rdestination;
		this.rflightTime = rflightTime;
		this.rdepartureDate = rdepartureDate;
		this.rbasePrice = rbasePrice;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

	public String getRdepartureDate() {
		return rdepartureDate;
	}

	public void setRdepartureDate(String rdepartureDate) {
		this.rdepartureDate = rdepartureDate;
	}

	public int getRbasePrice() {
		return rbasePrice;
	}

	public void setRbasePrice(int rbasePrice) {
		this.rbasePrice = rbasePrice;
	}
	
	
	
	
}