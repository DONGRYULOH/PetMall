package kr.or.dongmall.admin.dao;

import java.util.List;

import kr.or.dongmall.admin.dto.CategoryDto;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;



public interface AdminDao {

	//카테고리
	public List<CategoryDto> category() throws Exception;
	//상품등록
	public int product_insert(ProductDto product) throws Exception; 
	//상품목록
	public List<ProductDto> product_list() throws Exception;
	//해당 상품(디테일)
	public ProductCateDto product_detail(int product_number) throws Exception;
	//해당 상품수정 
	public int product_update(ProductCateDto product) throws Exception;
	//상품삭제
	public int product_delete(int product_number) throws Exception;
}
