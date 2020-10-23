package kr.or.dongmall.shop.dto;

import java.util.Date;

public class ProductReply {
	private int reply_num; //댓글번호 
	private int product_number; //상품번호 
	private String reply_content;
	private String reply_writer; //작성자 ID 
	private Date reply_date;
	private int origin_ref; //부모 댓글 번호
	private int group_ord; //답글순서 
	private int group_layer; //답글 깊이 
	
	private String user_name; //유저닉네임 
		
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public int getProduct_number() {
		return product_number;
	}
	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	public int getOrigin_ref() {
		return origin_ref;
	}
	public void setOrigin_ref(int origin_ref) {
		this.origin_ref = origin_ref;
	}
	public int getGroup_ord() {
		return group_ord;
	}
	public void setGroup_ord(int group_ord) {
		this.group_ord = group_ord;
	}
	public int getGroup_layer() {
		return group_layer;
	}
	public void setGroup_layer(int group_layer) {
		this.group_layer = group_layer;
	}
	
}
