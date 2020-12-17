package kr.or.dongmall.user.dto;

/*
 	-- 회원 배송지 테이블 
	Create table Deliver_address(
		user_id VARCHAR(30) NOT NULL,
		user_address1 VARCHAR(20) NOT NULL, -- 우편번호
		user_address2 VARCHAR(50) NOT NULL, -- 주소 
		user_address3 VARCHAR(50) NOT NULL, -- 상세주소 
		FOREIGN KEY(user_id) REFERENCES user(user_id)
	);
 */
public class UserAddressDto {
	
	private String user_id;
	private String user_address1;
	private String user_address2;
	private String user_address3;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_address1() {
		return user_address1;
	}
	public void setUser_address1(String user_address1) {
		this.user_address1 = user_address1;
	}
	public String getUser_address2() {
		return user_address2;
	}
	public void setUser_address2(String user_address2) {
		this.user_address2 = user_address2;
	}
	public String getUser_address3() {
		return user_address3;
	}
	public void setUser_address3(String user_address3) {
		this.user_address3 = user_address3;
	}
	
	
}
