package dto;

public class Plane {
	
	private String pname;	// pk, not null
	private int cnum;	// fk, Company cnum
	private int pfirstSeatCount;
	private int pbusinessSeatCount;
	private int peconomySeatCount;
	private int pstate;
	
	public Plane() {}

	public Plane(String pname, int cnum, int pfirstSeatCount, int pbusinessSeatCount, int peconomySeatCount,
			int pstate) {
		this.pname = pname;
		this.cnum = cnum;
		this.pfirstSeatCount = pfirstSeatCount;
		this.pbusinessSeatCount = pbusinessSeatCount;
		this.peconomySeatCount = peconomySeatCount;
		this.pstate = pstate;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getPfirstSeatCount() {
		return pfirstSeatCount;
	}

	public void setPfirstSeatCount(int pfirstSeatCount) {
		this.pfirstSeatCount = pfirstSeatCount;
	}

	public int getPbusinessSeatCount() {
		return pbusinessSeatCount;
	}

	public void setPbusinessSeatCount(int pbusinessSeatCount) {
		this.pbusinessSeatCount = pbusinessSeatCount;
	}

	public int getPeconomySeatCount() {
		return peconomySeatCount;
	}

	public void setPeconomySeatCount(int peconomySeatCount) {
		this.peconomySeatCount = peconomySeatCount;
	}

	public int getPstate() {
		return pstate;
	}

	public void setPstate(int pstate) {
		this.pstate = pstate;
	}

}
