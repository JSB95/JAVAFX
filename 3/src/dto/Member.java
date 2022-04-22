package dto;

public class Member {

	private int mnum;			// ȸ�� ��ȣ pk
	private String mid;			// ȸ�� ���̵�
	private String mpassword;	// ȸ�� ��й�ȣ
	private String mname;		// ȸ�� �̸�
	private String mphone;		// ȸ�� ��ȭ��ȣ
	private String mpassport;	// ȸ�� ���ǹ�ȣ
	private String mcard;		// ȸ�� ī���ȣ
	
	public Member() {}

	public Member(int mnum, String mid, String mpassword, String mname, String mphone, String mpassport, String mcard) {
		this.mnum = mnum;
		this.mid = mid;
		this.mpassword = mpassword;
		this.mname = mname;
		this.mphone = mphone;
		this.mpassport = mpassport;
		this.mcard = mcard;
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
	
}