package kr.or.dongmall.shop.dto;

/*
 	-- 비회원 환불 내역 테이블(식별관계) 
	Create table nonuserOrder_refund(
		refund_number INT AUTO_INCREMENT NOT NULL, -- 환불번호 
		order_detail_number VARCHAR(20) NOT NULL, -- 주문상세번호
		refund_reason VARCHAR(300) NOT NULL, -- 환불사유(텍스트 형태) 
		refund_img VARCHAR(300) NOT NULL, -- 환불이미지(이미지) 
		refund_email VARCHAR(50) NULL, -- 환불을 요청한 유저의 이메일 
		PRIMARY KEY(refund_number),
		FOREIGN KEY(order_detail_number) REFERENCES NonUserOrder_detail(order_detail_number) -- 환불내역 테이블 <-> 주문 상세 테이블 
	);
 */
public class NonuserRefundDto {
	
	private int refund_number;
	private String order_detail_number;
	private String refund_reason;
	private String refund_img;
	private String refund_email;
	
	private String order_detail_status; //처리상태 
	
	private String order_number; //주문번호 
	
	private int product_count; // 주문한 상품개수
	private int product_price; // 주문한 상품 1개당 가격 
	private int total_price; // 주문한 상품개수 * 상품 가격(총가격)
	
	public String getOrder_detail_status() {
		return order_detail_status;
	}
	public void setOrder_detail_status(String order_detail_status) {
		this.order_detail_status = order_detail_status;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
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
	public String getRefund_email() {
		return refund_email;
	}
	public void setRefund_email(String refund_email) {
		this.refund_email = refund_email;
	}
	
	
}
