package dto;

public class Price {
	private int pnum;					// ���� ��ȣ pk
	private int pseatClass;				// �¼� ���
	private int cnum;					// �װ��� ��ȣ fk, Company cnum
	private double pfirstSeatRatio;		// �۽�ƮŬ���� ����
	private double pbusinessSeatRatio;	// ����Ͻ�Ŭ���� ����
	
	public Price() {}

	public Price(int pnum, int pseatClass, int cnum, double pfirstSeatRatio, double pbusinessSeatRatio) {
		this.pnum = pnum;
		this.pseatClass = pseatClass;
		this.cnum = cnum;
		this.pfirstSeatRatio = pfirstSeatRatio;
		this.pbusinessSeatRatio = pbusinessSeatRatio;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getPseatClass() {
		return pseatClass;
	}

	public void setPseatClass(int pseatClass) {
		this.pseatClass = pseatClass;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public double getPfirstSeatRatio() {
		return pfirstSeatRatio;
	}

	public void setPfirstSeatRatio(double pfirstSeatRatio) {
		this.pfirstSeatRatio = pfirstSeatRatio;
	}

	public double getPbusinessSeatRatio() {
		return pbusinessSeatRatio;
	}

	public void setPbusinessSeatRatio(double pbusinessSeatRatio) {
		this.pbusinessSeatRatio = pbusinessSeatRatio;
	}
	
}