package dto;

public class Aplane {
	
	private String aname;			// ������ pk, not null
	private int cnum;				// �װ����ȣ fk, Company cnum
	private int afirstSeatCount; 	// �۽�ƮŬ���� �¼���
	private int abusinessSeatCount; // ����Ͻ� Ŭ���� �¼���
	private int aeconomySeatCount; 	// ���ڳ�� Ŭ���� �¼���
	private String astate; 			// ����� ����

	public Aplane() {}

	

	public Aplane(String aname, int cnum, int afirstSeatCount, int abusinessSeatCount, int aeconomySeatCount,
			String astate) {
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



	public String getAname() {
		return aname;
	}



	public void setAname(String aname) {
		this.aname = aname;
	}



	public String getAstate() {
		return astate;
	}



	public void setAstate(String astate) {
		this.astate = astate;
	}

	
	
	
	
}
