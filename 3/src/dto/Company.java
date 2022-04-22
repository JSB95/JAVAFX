package dto;

public class Company {
	private int cnum;		// �װ��� ��ȣ pk
	private String cname;	// �װ��� �̸�
	private String cphone;	// �װ��� ��ȭ��ȣ
	private double cfirstSeatRatio;		// �۽�ƮŬ���� ����
	private double cbusinessSeatRatio;	// ����Ͻ�Ŭ���� ����
	
	public Company() {}

	public Company(int cnum, String cname, String cphone, double cfirstSeatRatio, double cbusinessSeatRatio) {
		this.cnum = cnum;
		this.cname = cname;
		this.cphone = cphone;
		this.cfirstSeatRatio = cfirstSeatRatio;
		this.cbusinessSeatRatio = cbusinessSeatRatio;
	}


	public double getCfirstSeatRatio() {
		return cfirstSeatRatio;
	}

	public void setCfirstSeatRatio(double cfirstSeatRatio) {
		this.cfirstSeatRatio = cfirstSeatRatio;
	}

	public double getCbusinessSeatRatio() {
		return cbusinessSeatRatio;
	}

	public void setCbusinessSeatRatio(double cbusinessSeatRatio) {
		this.cbusinessSeatRatio = cbusinessSeatRatio;
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