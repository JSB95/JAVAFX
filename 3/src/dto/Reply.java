package dto;

public class Reply {
	private int replynum;	// pk
	private int bnum;		// fk references test.board(bnum),
	private int mnum;		// fk references test.member(mnum)
	private String replycontent;
	private String replyid;
	private String replydate;
	
	public Reply( int bnum, int replynum, int mnum, String replycontent, String replyid, String replydate) {
		this.replynum = replynum;
		this.bnum = bnum;
		this.mnum = mnum;
		this.replycontent = replycontent;
		this.replyid = replyid;
		this.replydate = replydate;
	}
	
	public Reply(  int replynum, int mnum, String replycontent, String replyid, String replydate) {
		this.replynum = replynum;
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
