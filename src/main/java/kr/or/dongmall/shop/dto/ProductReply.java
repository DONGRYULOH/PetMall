package kr.or.dongmall.shop.dto;

import java.util.Date;
/*
 * -- 상품댓글 테이블
 	Create table Product_reply(
		reply_number INT AUTO_INCREMENT NOT NULL, -- 댓글번호
		product_number INT NOT NULL, -- 상품번호 
		content TEXT NOT NULL, -- 댓글내용 
		writer_nickname VARCHAR(50) NOT NULL, -- 작성자 닉네임 
		reporting_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 작성날짜 
		reply_orgin_number INT NOT NULL, -- 부모댓글 번호 
		reply_order INT NOT NULL, -- 그룹의 댓글순서 
		reply_depth INT NOT NULL,
		PRIMARY KEY(reply_number),
		FOREIGN KEY(product_number) REFERENCES Product(product_number)
	);
	
	-- USER 테이블 
	Create table User(
		user_id VARCHAR(30) NOT NULL, -- 아이디 
		user_pwd VARCHAR(100) NOT NULL, -- 패스워드
		user_name VARCHAR(20) NOT NULL, -- 이름 
		user_phone VARCHAR(20) NOT NULL, -- 휴대번호 
		user_email VARCHAR(50) NOT NULL, -- 이메일(아이디나 비밀번호를 잊어버린경우 / 본인인증) 
		user_nickname VARCHAR(20) UNIQUE NOT NULL, -- 닉네임
		user_profile VARCHAR(100) DEFAULT 'Basic.png', -- 프로필사진(회원가입할때 받지말고 디폴트값을 넣기) 
		email_check VARCHAR(1) DEFAULT 'N' NOT NULL , -- 이메일 수신여부체크(Y OR N ) 
		user_role INT DEFAULT 1 NOT NULL, -- 유저권한(관리자인지 일반회원인지) 관리자 : 9  , 일반회원 : 1 
		user_grade VARCHAR(10), -- 등급(일단보류) 
		user_insertdate DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 가입날짜 
		PRIMARY KEY(user_id)
	);
 */
public class ProductReply {
	private int reply_number; //댓글번호 
	private int product_number; //상품번호 
	private String content;
	private String writer_nickname; //작성자 닉네임 
	private Date reporting_date;
	private int reply_orgin_number; //부모 댓글 번호
	private int reply_order; //답글순서 
	private int reply_depth; //답글 깊이 
	
	public int getReply_number() {
		return reply_number;
	}
	public void setReply_number(int reply_number) {
		this.reply_number = reply_number;
	}
	public int getProduct_number() {
		return product_number;
	}
	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter_nickname() {
		return writer_nickname;
	}
	public void setWriter_nickname(String writer_nickname) {
		this.writer_nickname = writer_nickname;
	}
	public Date getReporting_date() {
		return reporting_date;
	}
	public void setReporting_date(Date reporting_date) {
		this.reporting_date = reporting_date;
	}
	public int getReply_orgin_number() {
		return reply_orgin_number;
	}
	public void setReply_orgin_number(int reply_orgin_number) {
		this.reply_orgin_number = reply_orgin_number;
	}
	public int getReply_order() {
		return reply_order;
	}
	public void setReply_order(int reply_order) {
		this.reply_order = reply_order;
	}
	public int getReply_depth() {
		return reply_depth;
	}
	public void setReply_depth(int reply_depth) {
		this.reply_depth = reply_depth;
	}
	
	
		
	
	
}
