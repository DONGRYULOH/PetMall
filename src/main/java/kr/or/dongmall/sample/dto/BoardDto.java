package kr.or.dongmall.sample.dto;

//게시판 테이블 DTO 
public class BoardDto {
	
	private int idx; // 글번호 
	private String title; //글제목
	private String contents; //글내용
	private int hit_cnt; //조회수
	private String user_id; //유저아이디
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHit_cnt() {
		return hit_cnt;
	}
	public void setHit_cnt(int hit_cnt) {
		this.hit_cnt = hit_cnt;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
