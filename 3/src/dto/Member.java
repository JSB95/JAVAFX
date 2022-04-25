package dto;

<<<<<<< HEAD
/*
 * 
 */

public class Member {

	private int mnum;		// pk
	private String mid;
	private String mpassword;
	private String mname;
	private String mphone;
	private String mpassport;
	private String mcard;
	private int mpoint;
	
	public Member() {}

	

	public Member(int mnum, String mid, String mpassword, String mname, String mphone, String mpassport, String mcard,
			int mpoint) {
=======
public class Member {

	private int mnum;			// 회원 번호 pk
	private String mid;			// 회원 아이디
	private String mpassword;	// 회원 비밀번호
	private String mname;		// 회원 이름
	private String mphone;		// 회원 전화번호
	private String mpassport;	// 회원 여권번호
	private String mcard;		// 회원 카드번호
	private String msince; 		// 가입일
	private int mpoint;			// 포인트
	
	public Member() {}

	public Member(int mnum, String mid, String mpassword, String mname, String mphone, String mpassport, String mcard,
			String msince, int mpoint) {
>>>>>>> parent of 0c620d7 (board.fxml)
		this.mnum = mnum;
		this.mid = mid;
		this.mpassword = mpassword;
		this.mname = mname;
		this.mphone = mphone;
		this.mpassport = mpassport;
		this.mcard = mcard;
<<<<<<< HEAD
		this.mpoint = mpoint;
	}


=======
		this.msince = msince;
		this.mpoint = mpoint;
	}

	

	public String getMsince() {
		return msince;
	}

	public void setMsince(String msince) {
		this.msince = msince;
	}

	public int getMpoint() {
		return mpoint;
	}

	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
>>>>>>> parent of 0c620d7 (board.fxml)

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
<<<<<<< HEAD

	public int getMpoint() {
		return mpoint;
	}

	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	
}
=======
	
}
>>>>>>> parent of 0c620d7 (board.fxml)
