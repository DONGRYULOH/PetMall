package kr.or.dongmall.shop.dto;
/*
 	Create table Cart(
		cart_number INT AUTO_INCREMENT NOT NULL, -- 장바구니번호
		product_number INT NOT NULL, -- 상품번호 
		user_id VARCHAR(30) NOT NULL, -- 회원아이디(비회원일경우 회원아이디 대신 비회원고유식별값이 들어감) 
		product_count INT NOT NULL, -- 상품수량(개수) 
		PRIMARY KEY(cart_number),
		FOREIGN KEY(product_number) REFERENCES Product(product_number),
		FOREIGN KEY(user_id) REFERENCES User(user_id)
	);
	
	select c.cart_num,c.product_number,c.user_id,c.cart_stock,p.product_name,p.product_price,p.product_ThumbImg
	from cart c join product p using(product_number)
	where c.user_id = 'admin'
	order by c.cart_num desc;
 */
public class CartDto {
	
	//장바구니 테이블 
	private int cart_number; //장바구니 번호 
	private int product_number; //상품 번호 
	private String user_id; //유저ID 또는 비회원 식별번호
	private int product_count; //선택한 상품 수량
	
	//상품 테이블 
	private String product_name; //상품이름
	private int product_price; //상품가격
	
	//상품 이미지 테이블 
	private int file_number; //상품이미지 파일번호 
	private String stored_thumbNail; //썸네일 이미지 
	
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
	public int getFile_number() {
		return file_number;
	}
	public void setFile_number(int file_number) {
		this.file_number = file_number;
	}
	public String getStored_thumbNail() {
		return stored_thumbNail;
	}
	public void setStored_thumbNail(String stored_thumbNail) {
		this.stored_thumbNail = stored_thumbNail;
	}
	public int getCart_number() {
		return cart_number;
	}
	public void setCart_number(int cart_number) {
		this.cart_number = cart_number;
	}
	public int getProduct_number() {
		return product_number;
	}
	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	
	
	
	
	
	
}
