package kr.or.dongmall.admin.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.dongmall.admin.dao.AdminDao;
import kr.or.dongmall.admin.dto.CategoryDto;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;

@Service
public class AdminService {
	
	@Inject
	SqlSession sqlSession;
	
	//카테고리 종류 
	public List<CategoryDto> category() throws Exception{
		
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		List<CategoryDto> list = admindao.category();
		
		for(int i=0;i<list.size();i++) {
			System.out.print("카테고리 이름"+list.get(i).getCategory_name());
			System.out.println("카테고리 레벨"+list.get(i).getLevel());
		}
		
		
		return admindao.category();
	}
	
	//상품등록
	public void product_insert(ProductDto product) {
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		try {
			int result = admindao.product_insert(product);
			System.out.println("결과값"+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//상품목록 서비스 
	public List<ProductDto> product_list() throws Exception{
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		
		return admindao.product_list(); 
	} 
	
	//해당상품보기 
	public ProductCateDto product_detail(int product_number) throws Exception {
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		
		return admindao.product_detail(product_number); 
	}
	
	//해당상품 수정 서비스 
	public String product_update(ProductCateDto product) throws Exception {
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		int result = admindao.product_update(product);
		if(result == 1) {
			System.out.println("상품 수정성공");
			return "redirect:/admin/product_detail";
		}else {
			System.out.println("상품 수정실패");
			return "redirect:/admin/product_modify";
		}
	}
	
	//상품삭제
	public void product_delete(int product_number) throws Exception{
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		
		int result = admindao.product_delete(product_number);
		if(result == 1) {
			System.out.println("상품 삭제성공");
		}else {
			System.out.println("상품 삭제실패");
		}
	}
	
}
























