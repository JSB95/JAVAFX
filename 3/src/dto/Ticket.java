package dto;

public class Ticket {

	private int tnum;			// 항공권번호 PK
	private int tstate;			// 예매 상태
	private int tprice;			// 가격
	private int mnum;			// 회원 번호 FK
	private int rnum;			// 항공일지 번호 FK
	private String tseatnum;	// 좌석번호
	private String tseatclass;	// 좌석등급
	
	public Ticket() {}

	public Ticket(int tnum, int tstate, int tprice, int mnum, int rnum, String tseatnum, String tseatclass) {
		this.tnum = tnum;
		this.tstate = tstate;
		this.tprice = tprice;
		this.mnum = mnum;
		this.rnum = rnum;
		this.tseatnum = tseatnum;
		this.tseatclass = tseatclass;
	}

	public int getTnum() {
		return tnum;
	}

	public void setTnum(int tnum) {
		this.tnum = tnum;
	}

	public int getTstate() {
		return tstate;
	}

	public void setTstate(int tstate) {
		this.tstate = tstate;
	}

	public int getTprice() {
		return tprice;
	}

	public void setTprice(int tprice) {
		this.tprice = tprice;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getTseatnum() {
		return tseatnum;
	}

	public void setTseatnum(String tseatnum) {
		this.tseatnum = tseatnum;
	}

	public String getTseatclass() {
		return tseatclass;
	}

	public void setTseatclass(String tseatclass) {
		this.tseatclass = tseatclass;
	}
	
	
	
	
}