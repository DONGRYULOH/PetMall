package kr.or.dongmall.admin.dto;

/*
	Create table Product_category(
	category_code VARCHAR(30) NOT NULL, -- 카테고리 코드(100:상위코드 , 100-1:하위코드) 
	category_name VARCHAR(50) NOT NULL, -- 카테고리 이름(100:상의 , 100-1:반팔티)
	category_ref_code VARCHAR(30), -- 참조코드(100:null , 100-1:100번을 참조) 
	PRIMARY KEY(category_code),
	FOREIGN KEY(category_ref_code) REFERENCES Product_category(category_code)
	);
*/

public class CategoryDto {
	private String category_code; //카테고리 이름 
	private String category_name; //카테고리 코드 
	private String category_ref_code; //카테고리 참조코드  EX) 가디건(101) -> 상의(101)를 참조 
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
	public String getCategory_ref_code() {
		return category_ref_code;
	}
	public void setCategory_ref_code(String category_ref_code) {
		this.category_ref_code = category_ref_code;
	}
	
	
}
