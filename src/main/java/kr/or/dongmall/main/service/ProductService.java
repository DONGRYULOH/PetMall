package kr.or.dongmall.main.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.dongmall.main.dao.ProductDao;
import kr.or.dongmall.main.dto.ProductDto;


@Service
public class ProductService {
	
	@Inject
	SqlSession sqlsession;
	
	//전체 상품목록 리스트 가져오기 
	public List<ProductDto> getProductList() throws Exception{
		
		ProductDao productDao = sqlsession.getMapper(ProductDao.class);
		
		List<ProductDto> list = productDao.getProductList();
		
		for(int i=0;i<list.size();i++) {
			System.out.println("상품명"+list.get(i).getProduct_name());
		}
		
		return productDao.getProductList();
		
	}
	
}
