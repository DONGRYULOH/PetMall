package kr.or.dongmall.user.dto;

import java.util.Date;

/*
-- 주소(우편번호,주소,상세주소) 
-- 회원테이블 
create table user(
	user_id varchar(50) auto_increment, -- 회원 아이디
    user_pwd varchar(50) not null, -- 회원 패스워드
    user_name varchar(30) not null, -- 회원 이름
    user_phone varchar(20) not null, -- 회원 휴대번호
    verify int default 0, -- 인증여부컬럼 
    register_date datetime default current_timestamp, -- 가입날짜  
    user_addr1 varchar(20), -- 우편번호 
    user_addr2 varchar(50), -- 주소 
    user_addr3 varchar(50), -- 상세주소 
    primary key(user_id)
    );
*/

public class UserDto {
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_phone;
	private int verify;
	
	
	public int getVerify() {
		return verify;
	}
	public void setVerify(int verify) {
		this.verify = verify;
	}
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

}
