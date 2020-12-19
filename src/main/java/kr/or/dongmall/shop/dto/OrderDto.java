package kr.or.dongmall.shop.dto;

import java.util.Date;

/*
 	-- 주문 테이블 
	Create table orders(
		order_number VARCHAR(20) NOT NULL, -- 주문번호 
		user_id VARCHAR(30) NOT NULL, -- 회원아이디
		order_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,  -- 주문날짜  
		address1 VARCHAR(20) NOT NULL, -- 배송지주소1(우편번호)
		address2 VARCHAR(50) NOT NULL, -- 배송지주소2(주소)
		address3 VARCHAR(50) NOT NULL, -- 배송지주소3(상세주소)
		receiver_name VARCHAR(20) NOT NULL, -- 수령자이름 
		receiver_phone VARCHAR(20) NOT NULL, -- 수령자 전화번호 
		PRIMARY KEY(order_number),
		FOREIGN KEY(user_id) REFERENCES User(user_id)
	);
 */
public class OrderDto {
	private String order_number;
	private String user_id;
	private Date order_date;
	private String address1;
	private String address2;
	private String address3;
	private String receiver_name;
	private String receiver_phone;
	
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}
	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	
	
}
