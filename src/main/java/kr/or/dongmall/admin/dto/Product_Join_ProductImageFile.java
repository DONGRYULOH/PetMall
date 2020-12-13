package kr.or.dongmall.admin.dto;

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

	Create table Product_imageFile(
		file_number INT AUTO_INCREMENT NOT NULL, -- 상품이미지파일번호 
		product_number INT NOT NULL, -- 상품번호
		origin_file_name VARCHAR(300) NOT NULL, -- 원본파일이름 
		stored_file_name VARCHAR(300) NOT NULL, -- 서버에저장될파일이름 
		stored_thumbNail VARCHAR(300) NOT NULL, -- 썸네일 이미지  
		delegate_thumbNail VARCHAR(1) DEFAULT 'N' NOT NULL, -- 대표썸네일 여부 ( Y 일경우 대표썸네일 뜻함) 
		file_size INT NOT NULL, -- 파일크기
		create_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,  -- 파일생성날짜
		delete_check VARCHAR(1) DEFAULT 'N' NOT NULL, -- 파일삭제여부
		PRIMARY KEY(file_number),
		FOREIGN KEY(product_number) REFERENCES Product(product_number)
		ON DELETE CASCADE
	);
*/
// 상품테이블 + 상품이미지테이블 조인 
public class Product_Join_ProductImageFile {
	
	//상품테이블
	private int product_number;
	private String category_code;
	private String product_name;
	private int product_price;
	private int product_stock;
	private String product_desc;
	private Date product_date;
	private int product_hits;
	
	//상품이미지 테이블
	private int file_number;
	private String origin_file_name;
	private String stored_file_name;
	private String stored_thumbNail; // 썸네일 이미지
	private String delegate_thumbNail; // 대표썸네일 여부 ( Y 일경우 대표썸네일 뜻함) 
	private int file_size;
	private Date create_date;
	private String delete_check;
	
	public String getDelegate_thumbNail() {
		return delegate_thumbNail;
	}
	public void setDelegate_thumbNail(String delegate_thumbNail) {
		this.delegate_thumbNail = delegate_thumbNail;
	}
	public int getFile_number() {
		return file_number;
	}
	public void setFile_number(int file_number) {
		this.file_number = file_number;
	}
	public String getOrigin_file_name() {
		return origin_file_name;
	}
	public void setOrigin_file_name(String origin_file_name) {
		this.origin_file_name = origin_file_name;
	}
	public String getStored_file_name() {
		return stored_file_name;
	}
	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getDelete_check() {
		return delete_check;
	}
	public void setDelete_check(String delete_check) {
		this.delete_check = delete_check;
	}
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
