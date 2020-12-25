package kr.or.dongmall.shop.dto;
/*
 	Create table Order_refund(
		refund_number INT AUTO_INCREMENT NOT NULL, -- 환불번호 
		order_detail_number VARCHAR(20) NOT NULL, -- 주문상세번호
		refund_reason VARCHAR(300) NOT NULL, -- 환불사유(텍스트 형태) 
		refund_img VARCHAR(300) NOT NULL, -- 환불이미지(이미지) 
		user_email VARCHAR(50) NOT NULL, -- 환불을 요청한 유저의 이메일 
		PRIMARY KEY(refund_number),
		FOREIGN KEY(order_detail_number) REFERENCES Order_detail(order_detail_number) -- 환불내역 테이블 <-> 주문 상세 테이블 
	);
 */
public class OrderRefundDto {
	
	private int refund_number;
	private String order_detail_number;
	private String refund_reason;
	private String refund_img;
	private String user_email;
	
	public int getRefund_number() {
		return refund_number;
	}
	public void setRefund_number(int refund_number) {
		this.refund_number = refund_number;
	}
	public String getOrder_detail_number() {
		return order_detail_number;
	}
	public void setOrder_detail_number(String order_detail_number) {
		this.order_detail_number = order_detail_number;
	}
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	public String getRefund_img() {
		return refund_img;
	}
	public void setRefund_img(String refund_img) {
		this.refund_img = refund_img;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
}
