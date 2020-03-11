package com.kh.board.model.vo;

import java.sql.Date;

// DELETE_YN
// 실제로 삭제하는것이 아니라 숨기는 형태로 구현
// 약관에 명시되어있는 부분.
// delete 하는게 아니라 update로 delete_yn status 를 바꿔준다.

public class Board {
	// - bNo : int
	// - title : String
	// - content : String
	// - createDate : Date
	// - writer : String
	
	// + 기본 생성자
	// + Board(bNo : int, title:String, content:String, createDate:Date, writer:String)
	// + Board(bNo : int, title:String, content:String)
	// + Board(title:String, content:String, writer:String)
	// + Board(title:String, content:String)
	
	// + getter/setter
	// + toString():String
	//		반환 값 : 글번호, 제목, 내용, 날짜, 작성자
	
	private int bNo;
	private String title;
	private String content;
	private Date createDate;
	private String writer;
	
	public Board() {
	}
	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}
	public Board(String title, String content, String writer) {
		this(title,content);
		this.writer = writer;
	}
	public Board(int bNo, String title, String content) {
		this(title,content);
		this.bNo = bNo;
	}
	public Board(int bNo, String title, String content, Date createDate, String writer) {
		this(bNo,title,content);
		this.createDate = createDate;
		this.writer = writer;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Override
	public String toString() {
		// 		반환 값 : 글번호, 제목, 내용, 날짜, 작성자
		String str = this.bNo +", " + this.title + ", " + this.content + ", " + this.createDate;
		return str;
	}
}
