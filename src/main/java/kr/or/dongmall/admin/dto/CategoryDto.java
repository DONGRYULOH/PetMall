package kr.or.dongmall.admin.dto;

/*
category_name varchar(20), 
category_code varchar(30) primary key, 
category_code_ref varchar(30),
*/

public class CategoryDto {
	private String category_name; //카테고리 이름 
	private String category_code; //카테고리 코드 
	private String category_code_ref; //카테고리 참조코드  EX) 가디건(101) -> 상의(101)를 참조 
	private int level;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getCategory_code_ref() {
		return category_code_ref;
	}
	public void setCategory_code_ref(String category_code_ref) {
		this.category_code_ref = category_code_ref;
	}
	
	
}
