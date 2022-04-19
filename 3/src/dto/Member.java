package dto;

public class Member {

	private int mnum;			// ȸ�� ��ȣ pk
	private String mid;			// ȸ�� ���̵�
	private String mpassword;	// ȸ�� ��й�ȣ
	private String mname;		// ȸ�� �̸�
	private String mphone;		// ȸ�� ��ȭ��ȣ
	private String mpassport;	// ȸ�� ���ǹ�ȣ
	private String mcard;		// ȸ�� ī���ȣ
	private String mtoday;
	private String msince; // ������
	private int mpoint;
	
	public Member() {}
	public Member(int mnum, String mid, String mpassword, String mname, String mphone, String mpassport, String mcard,
		String msince) {
		super();
		this.mnum = mnum;
		this.mid = mid;
		this.mpassword = mpassword;
		this.mname = mname;
		this.mphone = mphone;
		this.mpassport = mpassport;
		this.msince = msince;
		this.mcard = mcard;
	}
	public Member(int mnum, String mid, String mpassword, String mname, String mphone, String mpassport, String mcard,
			String mtoday, String msince, int mpoint) {
		super();
		this.mnum = mnum;
		this.mid = mid;
		this.mpassword = mpassword;
		this.mname = mname;
		this.mphone = mphone;
		this.mpassport = mpassport;
		this.mcard = mcard;
		this.mtoday = mtoday;
		this.msince = msince;
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

	public String getMsince() {
		return msince;
	}

	public void setMsince(String msince) {
		this.msince = msince;
	}

	public String getMtoday() {
		return mtoday;
	}

	public void setMtoday(String mtoday) {
		this.mtoday = mtoday;
	}
	
}