package kr.or.dongmall.user.dto;

import java.util.Date;

/*
	-- USER 테이블 
	Create table User(
	user_id VARCHAR(30) NOT NULL, -- 아이디 
	user_pwd VARCHAR(50) NOT NULL, -- 패스워드
	user_name VARCHAR(20) NOT NULL, -- 이름 
	user_phone VARCHAR(20) NOT NULL, -- 휴대번호 
	user_email VARCHAR(50) NOT NULL, -- 이메일(아이디나 비밀번호를 잊어버린경우 / 본인인증) 
	user_nickname VARCHAR(20) UNIQUE NOT NULL, -- 닉네임
	user_profile VARCHAR(100) DEFAULT 'Basic.png', -- 프로필사진(회원가입할때 받지말고 디폴트값을 넣기) 
	email_check VARCHAR(1) NOT NULL, -- 이메일 수신여부체크(Y OR N ) 
	user_role INT DEFAULT 1 NOT NULL, -- 유저권한(관리자인지 일반회원인지) 관리자 : 9  , 일반회원 : 1 
	user_grade VARCHAR(10), -- 등급(일단보류) 
	user_insertdate DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 가입날짜 
	PRIMARY KEY(user_id)
	);
*/

public class UserDto {
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_phone;
	private String user_email;
	private String user_nickname;
	private String user_profile;
	private char email_check; // Y 또는 N으로 구성된 문자하나라서 Char 형태로 선언을 해줌 
	private int user_role;
	private String user_grade;
	private Date user_insertdate;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_profile() {
		return user_profile;
	}
	public void setUser_profile(String user_profile) {
		this.user_profile = user_profile;
	}
	public char getEmail_check() {
		return email_check;
	}
	public void setEmail_check(char email_check) {
		this.email_check = email_check;
	}
	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}
	public String getUser_grade() {
		return user_grade;
	}
	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}
	public Date getUser_insertdate() {
		return user_insertdate;
	}
	public void setUser_insertdate(Date user_insertdate) {
		this.user_insertdate = user_insertdate;
	}

}
