package dto;

public class Board {
<<<<<<< HEAD
	private int bnum;		// pk
	private int mnum;		// fk, 회원번호, not null 속성.
	private String btitle;	// 제목
	private String bcontent;	// 내용
	private String blocation;	// 사용자가 지정한 공항 이름 저장용 필드
	private String bsnapshoturl;
	private String bimgurl;
	private String bdate;	// 글 작성일자, SQL이 알아서 찍을거임.
	private int bview;		// 조회수
	
	public Board() {}

	public Board(int bnum, int mnum, String btitle, String bcontent, String blocation, String bsnapshoturl,
			String bimgurl, String bdate, int bview) {
		this.bnum = bnum;
		this.mnum = mnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.blocation = blocation;
		this.bsnapshoturl = bsnapshoturl;
		this.bimgurl = bimgurl;
		this.bdate = bdate;
		this.bview = bview;
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

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBlocation() {
		return blocation;
	}

	public void setBlocation(String blocation) {
		this.blocation = blocation;
	}

	public String getBsnapshoturl() {
		return bsnapshoturl;
	}

	public void setBsnapshoturl(String bsnapshoturl) {
		this.bsnapshoturl = bsnapshoturl;
	}

	public String getBimgurl() {
		return bimgurl;
	}

	public void setBimgurl(String bimgurl) {
		this.bimgurl = bimgurl;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getBview() {
		return bview;
	}

=======
	// 필드 
	private int bnum;
	private String btitle;
	private String bcontent;
	private String bwrite;
	private String bdate;
	private int bview;
	// 생성자
	public Board() {}
	public Board(int bnum, String btitle, String bcontent, String bwrite, String bdate, int bview) {
		this.bnum = bnum;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bwrite = bwrite;
		this.bdate = bdate;
		this.bview = bview;
	}
	// 메소드
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBwrite() {
		return bwrite;
	}
	public void setBwrite(String bwrite) {
		this.bwrite = bwrite;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public int getBview() {
		return bview;
	}
>>>>>>> parent of 0c620d7 (board.fxml)
	public void setBview(int bview) {
		this.bview = bview;
	}
	
<<<<<<< HEAD
}
=======
}
>>>>>>> parent of 0c620d7 (board.fxml)
