package dto;

public class MemberView {
	private String id;
	private int bnum;
	private String date;
	
	public MemberView() {}

	public MemberView(String id, int bnum, String date) {
		this.id = id;
		this.bnum = bnum;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
