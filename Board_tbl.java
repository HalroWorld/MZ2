package mz;

import java.sql.Blob;

public class Board_tbl {
	int BoardUid;
	String UserName;
	String BoardTitle;
	String BoardDate;
	int BoardHit;
	String BoardPost;
	
	
	
	public int getBoardUid() {
		return BoardUid;
	}
	public void setBoardUid(int boardUid) {
		this.BoardUid = boardUid;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		this.UserName = userName;
	}
	
	public String getBoardTitle() {
		return BoardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		BoardTitle = boardTitle;
	}
	
	public String getBoardDate() {
		return BoardDate;
	}
	public void setBoardDate(String boardDate) {
		BoardDate = boardDate;
	}
	public int getBoardHit() {
		return BoardHit;
	}
	public void setBoardHit(int boardHit) {
		BoardHit = boardHit;
	}

	public String getBoardPost() {
		return BoardPost;
	}
	public void setBoardPost(String boardPost) {
		BoardPost = boardPost;
	}

	
}
