package dto;

public class Price {
	private int pnum;					// 가격 번호 pk
	private int pseatClass;				// 좌석 등급
	private int cnum;					// 항공사 번호 fk, Company cnum
	private double pfirstSeatRatio;		// 퍼스트클래스 배율
	private double pbusinessSeatRatio;	// 비즈니스클래스 배율
	
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