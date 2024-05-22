package model;

import java.util.Date;

public class KicBoard {
	private int num;
	private String name;
	private String pw;
	private String subject;
	private String content;
	private String  file1 ;
	private Date  regdate;
	private int  readcnt;
	private String  boardid;
	@Override
	public String toString() {
		return "KicBoard [num=" + num + ", name=" + name + ", pw=" + pw + ", subject=" + subject + ", content="
				+ content + ", file1=" + file1 + ", regdate=" + regdate + ", readcnt=" + readcnt + ", boardid="
				+ boardid + "]";
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getBoardid() {
		return boardid;
	}
	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}
	
}
