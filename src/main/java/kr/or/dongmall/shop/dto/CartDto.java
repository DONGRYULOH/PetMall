package kr.or.dongmall.shop.dto;
/*
 	create table cart(
		cart_num int not null auto_increment, -- 장바구니 번호 
		product_num int not null,-- 상품번호 
		user_id varchar(50) not null, -- 유저ID 
		cart_stock int not null, -- 상품수량 
		primary key(cart_num)
	);
	
	select c.cart_num,c.product_number,c.user_id,c.cart_stock,p.product_name,p.product_price,p.product_ThumbImg
	from cart c join product p using(product_number)
	where c.user_id = 'admin'
	order by c.cart_num desc;
 */
public class CartDto {
	private int cart_num; //장바구니 번호 
	private int product_number; //상품 번호 
	private String user_id; //유저ID 
	private int cart_stock; //선택한 상품 수량 
	
	//장바구니 리스트에 보일정보(상품테이블에서 join해서 가져옴)
	private String product_name; // 상품 이름 
	private int product_price; //상품 가격 
	private String product_ThumbImg; //상품이미지 
	
	
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
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
	public int getCart_stock() {
		return cart_stock;
	}
	public void setCart_stock(int cart_stock) {
		this.cart_stock = cart_stock;
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
	public String getProduct_ThumbImg() {
		return product_ThumbImg;
	}
	public void setProduct_ThumbImg(String product_ThumbImg) {
		this.product_ThumbImg = product_ThumbImg;
	}
	
	
	
	
}
