package dto;

public class Aplane {
	
	private String aname;	// pk, not null
	private int cnum;	// fk, Company cnum
	private int afirstSeatCount;
	private int abusinessSeatCount;
	private int aeconomySeatCount;
	private int astate;

	public Aplane() {
		// TODO Auto-generated constructor stub
	}

	public Aplane(String aname, int cnum, int afirstSeatCount, int abusinessSeatCount, int aeconomySeatCount,
			int astate) {
		this.aname = aname;
		this.cnum = cnum;
		this.afirstSeatCount = afirstSeatCount;
		this.abusinessSeatCount = abusinessSeatCount;
		this.aeconomySeatCount = aeconomySeatCount;
		this.astate = astate;
	}

	public String getaname() {
		return aname;
	}

	public void setaname(String aname) {
		this.aname = aname;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public int getAfirstSeatCount() {
		return afirstSeatCount;
	}

	public void setAfirstSeatCount(int afirstSeatCount) {
		this.afirstSeatCount = afirstSeatCount;
	}

	public int getAbusinessSeatCount() {
		return abusinessSeatCount;
	}

	public void setAbusinessSeatCount(int abusinessSeatCount) {
		this.abusinessSeatCount = abusinessSeatCount;
	}

	public int getAeconomySeatCount() {
		return aeconomySeatCount;
	}

	public void setAeconomySeatCount(int aeconomySeatCount) {
		this.aeconomySeatCount = aeconomySeatCount;
	}

	public int getAstate() {
		return astate;
	}

	public void setAstate(int astate) {
		this.astate = astate;
	}
	
	
	
}