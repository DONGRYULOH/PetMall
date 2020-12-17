package kr.or.dongmall.shop.dto;

import java.util.List;

/*
 	-- 주문 상세테이블 
	Create table Order_detail(
		order_detail_number INT AUTO_INCREMENT NOT NULL, -- 주문상세번호
		order_number INT NOT NULL, -- 주문번호
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
	private List<OrderDetailDto> list;

	public List<OrderDetailDto> getList() {
		return list;
	}

	public void setList(List<OrderDetailDto> list) {
		this.list = list;
	}
	
	
}
