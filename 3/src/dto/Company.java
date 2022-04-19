package dto;

public class Company {
	private int cnum;		// 항공사 번호 pk
	private String cname;	// 항공사 이름
	private String cphone;	// 항공사 전화번호
	
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