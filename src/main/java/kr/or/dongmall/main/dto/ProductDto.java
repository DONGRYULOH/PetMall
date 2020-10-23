package kr.or.dongmall.main.dto;

import java.util.Date;

/*
-- 상품 테이블 
create table product(
	product_number int auto_increment, -- 상품번호 
	product_name varchar(50) not null, -- 상품이름 
    product_category varchar(30) not null, -- 상품 카테고리 
	product_price int not null, -- 상품가격 
	product_stock int null, -- 상품 수량 
	product_desc varchar(500), -- 상품설명 
	product_image varchar(500), -- 상품이미지 
    product_date datetime default current_timestamp, -- 상품 등록날짜 
	primary key(product_number)
);
*/
public class ProductDto {

	private int product_number;
	private String product_name;
	private String product_category;
	private int product_price;
	private int product_stock;
	private String product_desc;
	private String product_image;
	private Date product_date;
	
	//썸네일 이미지 
	private String product_ThumbImg;
	
	public String getProduct_ThumbImg() {
		return product_ThumbImg;
	}
	public void setProduct_ThumbImg(String product_ThumbImg) {
		this.product_ThumbImg = product_ThumbImg;
	}
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
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
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
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public Date getProduct_date() {
		return product_date;
	}
	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}
	
	
	
	
	
}
