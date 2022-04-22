package dto;

public class Ticket {

	private int tnum;			// �װ��ǹ�ȣ PK
	private int tstate;			// ���� ����
	private int tprice;			// ����
	private int mnum;			// ȸ�� ��ȣ FK
	private int rnum;			// �װ����� ��ȣ FK
	private String tseatnum;	// �¼���ȣ
	private String tseatclass;	// �¼����
	
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
