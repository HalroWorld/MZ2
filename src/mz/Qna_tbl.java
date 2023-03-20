package mz;

import java.sql.Blob;

public class Qna_tbl {
	int QnaUid;
	String QnaName;
	String QnaTitle;
	String QnaDate;
	int QnaHit;
	String QnaPost;
	
	
	
	public int getQnaUid() {
		return QnaUid;
	}
	public void setQnaUid(int qnaUid) {
		this.QnaUid = qnaUid;
	}
	public String getQnaName() {
		return QnaName;
	}
	public void setQnaName(String qnaName) {
		this.QnaName = qnaName;
	}
	
	public String getQnaTitle() {
		return QnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		QnaTitle = qnaTitle;
	}
	
	public String getQnaDate() {
		return QnaDate;
	}
	public void setQnaDate(String qnaDate) {
		QnaDate = qnaDate;
	}
	public int getQnaHit() {
		return QnaHit;
	}
	public void setQnaHit(int qnaHit) {
		QnaHit = qnaHit;
	}

	public String getQnaPost() {
		return QnaPost;
	}
	public void setQnaPost(String qnaPost) {
		QnaPost = qnaPost;
	}

	
}
