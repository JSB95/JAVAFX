<<<<<<< HEAD
package dto;

public class Reply {
	private int replynum;	// pk
	private int bnum;		// fk references test.board(bnum),
	private int mnum;		// fk references test.member(mnum)
	private String replycontent;
	private String replyid;
	private String replydate;
	
	public Reply(int replynum, int bnum, int mnum, String replycontent, String replyid, String replydate) {
		this.replynum = replynum;
		this.bnum = bnum;
		this.mnum = mnum;
		this.replycontent = replycontent;
		this.replyid = replyid;
		this.replydate = replydate;
	}

	public int getReplynum() {
		return replynum;
	}

	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public String getReplyid() {
		return replyid;
	}

	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}

	public String getReplydate() {
		return replydate;
	}

	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}

	
}
=======
package dto;

public class Reply {

	private int rnum;
	private String rcontent;
	private String rwrite ;
	private String rdate ;
	private int bnum ;
	
	public Reply() {}
	public Reply(int rnum, String rcontent, String rwrite, String rdate, int bnum) {
		super();
		this.rnum = rnum;
		this.rcontent = rcontent;
		this.rwrite = rwrite;
		this.rdate = rdate;
		this.bnum = bnum;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getRwrite() {
		return rwrite;
	}
	public void setRwrite(String rwrite) {
		this.rwrite = rwrite;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	
	
	
	
}
>>>>>>> parent of 0c620d7 (board.fxml)
