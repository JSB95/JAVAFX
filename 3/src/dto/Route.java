package dto;

public class Route {
	private int rnum;	// pk
	private String pname;	// fk, Plane
	private String rdeparture;
	private String rdestination;
	private String rflightTime;
	private String rdepartureDate;
	private int rbasePrice;
	
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
