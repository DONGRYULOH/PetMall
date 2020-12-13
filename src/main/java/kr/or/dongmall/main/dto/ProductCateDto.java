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

-- 상품 카테고리 테이블 
-- 카테고리 참조코드는 카테고리코드를 참조함(존재하는 카테고리 코드에 한해서만 참조가가능) 
Create table Product_category(
	category_code VARCHAR(30) NOT NULL, -- 카테고리 코드(100:상위코드 , 100-1:하위코드) 
	category_name VARCHAR(50) NOT NULL, -- 카테고리 이름(100:상의 , 100-1:반팔티)
	category_ref_code VARCHAR(30), -- 참조코드(100:null , 100-1:100번을 참조) 
	PRIMARY KEY(category_code),
	FOREIGN KEY(category_ref_code) REFERENCES Product_category(category_code)
);

*/
//상품테이블 + 카테고리 테이블 조인 
public class ProductCateDto {

	//상품테이블 
	private int product_number;
	private String product_name;
	private String category_code;
	private int product_price;
	private int product_stock;
	private String product_desc;
	private Date product_date;
	private int product_hits;
	
	//카테고리 테이블 
	private String category_name;
	//private String category_code;
	private String category_ref_code;
		
	public int getProduct_number() {
		return product_number;
	}
	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
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
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_ref_code() {
		return category_ref_code;
	}
	public void setCategory_ref_code(String category_ref_code) {
		this.category_ref_code = category_ref_code;
	}
	
	
	
	
	
	
	
}
