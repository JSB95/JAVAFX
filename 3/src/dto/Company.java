package dto;

public class Company {
	private int cnum;
	private String cname;
	private String cphone;
	
	public Company() {}

	public Company(int cnum, String cname, String cphone) {
		this.cnum = cnum;
		this.cname = cname;
		this.cphone = cphone;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

}
