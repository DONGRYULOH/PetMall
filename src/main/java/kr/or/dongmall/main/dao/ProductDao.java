package kr.or.dongmall.main.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.dongmall.main.dto.ProductDto;



public interface ProductDao {
	
	//전체 상품 리스트 가져오기
	public List<ProductDto> getProductList() throws Exception;
}
