package kr.or.dongmall.main.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.dongmall.admin.dto.Product_Join_ProductImageFile;
import kr.or.dongmall.main.dto.ProductDto;



public interface ProductDao {
	
	//전체 상품 리스트 가져오기
	public List<ProductDto> getProductList() throws Exception;
	
	//조회수가 가장 높은 상품순으로 상위9개 가져오기
	public List<Product_Join_ProductImageFile> getRankNine() throws Exception;
	
}
