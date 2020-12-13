package kr.or.dongmall.main.dto;

import java.util.Date;

/*
-- 상품 테이블 
Create table Product(
	product_number INT AUTO_INCREMENT NOT NULL, -- 상품번호 
	category_code VARCHAR(30) NOT NULL, -- 상품코드
	product_name VARCHAR(50) NOT NULL, -- 상품명
	product_price INT NOT NULL, -- 상품가격
	product_stock INT NOT NULL, -- 상품재고
	product_desc TEXT NOT NULL, -- 상품설명
	product_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, -- 상품등록일
	product_hits INT NOT NULL, -- 조회수 
	PRIMARY KEY(product_number),
	FOREIGN KEY(category_code) REFERENCES Product_category(category_code)
);
*/
public class ProductDto {

	private int product_number;
	private String category_code;
	private String product_name;
	private int product_price;
	private int product_stock;
	private String product_desc;
	private Date product_date;
	private int product_hits;
	
	private String stored_thumbNail; // 썸네일 이미지 
	
	
	public String getStored_thumbNail() {
		return stored_thumbNail;
	}
	public void setStored_thumbNail(String stored_thumbNail) {
		this.stored_thumbNail = stored_thumbNail;
	}
	public int getProduct_number() {
		return product_number;
	}
	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public Date getProduct_date() {
		return product_date;
	}
	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}
	public int getProduct_hits() {
		return product_hits;
	}
	public void setProduct_hits(int product_hits) {
		this.product_hits = product_hits;
	}
	
	
	
	
	
}
