package kr.or.dongmall.shop.dto;

import java.util.List;

/*
 	-- 주문 상세테이블 
	Create table Order_detail(
		order_detail_number VARCHAR(20) NOT NULL, -- 주문상세번호
		order_number VARCHAR(20) NOT NULL, -- 주문번호
		product_number INT NOT NULL, -- 상품번호
		product_count INT NOT NULL, -- 수량(개수)
		product_price INT NOT NULL, -- 가격
		order_detail_status VARCHAR(10) NOT NULL, -- 처리상태(배송완료,환불진행중... 등등) 
		PRIMARY KEY(order_detail_number),
		FOREIGN KEY(order_number) REFERENCES orders(order_number), -- 주문상세 <-> 주문 
		FOREIGN KEY(product_number) REFERENCES product(product_number) -- 주문상세 <-> 상품 
	);
 */
public class OrderDetailDto {

	private String order_detail_number;
	private String order_number;
	private int product_number;
	private int product_count;
	private int product_price;
	private String order_detail_status;
	
	public String getOrder_detail_number() {
		return order_detail_number;
	}
	public void setOrder_detail_number(String order_detail_number) {
		this.order_detail_number = order_detail_number;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public int getProduct_number() {
		return product_number;
	}
	public void setProduct_number(int product_number) {
		this.product_number = product_number;
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
	public String getOrder_detail_status() {
		return order_detail_status;
	}
	public void setOrder_detail_status(String order_detail_status) {
		this.order_detail_status = order_detail_status;
	}
	
	
}
