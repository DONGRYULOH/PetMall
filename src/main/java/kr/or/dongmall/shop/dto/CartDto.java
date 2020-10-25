package kr.or.dongmall.shop.dto;
/*
 	create table cart(
		cart_num int not null auto_increment, -- 장바구니 번호 
		product_num int not null,-- 상품번호 
		user_id varchar(50) not null, -- 유저ID 
		cart_stock int not null, -- 상품수량 
		primary key(cart_num)
	);
 */
public class CartDto {
	private int cart_num;
	private int product_num;
	private String user_id;
	private int cart_stock;
	
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getCart_stock() {
		return cart_stock;
	}
	public void setCart_stock(int cart_stock) {
		this.cart_stock = cart_stock;
	}
	
}
