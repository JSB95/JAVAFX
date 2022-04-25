package dto;

/*
 * 
 */

public class Member {

	private int mnum;		// pk
	private String mid;
	private String mpassword;
	private String mname;
	private String mphone;
	private String mpassport;
	private String mcard;
	private int mpoint;
	
	public Member() {}

	

	public Member(int mnum, String mid, String mpassword, String mname, String mphone, String mpassport, String mcard,
			int mpoint) {
		this.mnum = mnum;
		this.mid = mid;
		this.mpassword = mpassword;
		this.mname = mname;
		this.mphone = mphone;
		this.mpassport = mpassport;
		this.mcard = mcard;
		this.mpoint = mpoint;
	}



	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getMpassport() {
		return mpassport;
	}

	public void setMpassport(String mpassport) {
		this.mpassport = mpassport;
	}

	public String getMcard() {
		return mcard;
	}

	public void setMcard(String mcard) {
		this.mcard = mcard;
	}

	public int getMpoint() {
		return mpoint;
	}

	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	
}
