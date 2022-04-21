package dto;

public class Reply {
	private int replynum;
	private int bnum;
	private int mnum;
	private String replycontent;
	private String replydate;
	
	public Reply(int replynum, int bnum, int mnum, String replycontent, String replydate) {
		this.replynum = replynum;
		this.bnum = bnum;
		this.mnum = mnum;
		this.replycontent = replycontent;
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

	public String getReplydate() {
		return replydate;
	}

	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}
	
	
}
