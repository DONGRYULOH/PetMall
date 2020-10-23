package kr.or.dongmall.shop.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.shop.dao.ShopDao;
import kr.or.dongmall.shop.dto.ProductReply;

@Service
public class ShopService {

	@Inject
	SqlSession sqlSession;
	
	//카테고리 별로 해당 상품목록 가져오는 서비스 
	public List<ProductCateDto> cateProductList(String categoryNumber) throws Exception{
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		
		List<ProductCateDto> list = shopdao.cateProductList(categoryNumber);
		for(int i=0;i<list.size();i++) {
			System.out.println("상품번호"+list.get(i).getProduct_number());
		}
		
		return list;
	}
	
	//전체 상품목록 가져오는 서비스 
	public List<ProductCateDto> AllcateProductList() throws Exception{
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);		
		List<ProductCateDto> list = shopdao.AllcateProductList();
		return list;
	}
	
	//해당 상품 조회 서비스 
	public ProductCateDto shopDetail(int product_number) throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.shopDetail(product_number);
	}
	
	//상품 댓글 작성 
	public void replyInsert(ProductReply reply){
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		try {
			shopdao.replyInsert(reply);
			System.out.println("댓글 작성 성공!!");
			// 댓글번호 == ref 만들어주기 
			shopdao.replyInsert_ref(reply);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러발생!!"+e.getMessage());
		}
	} 
	
	//상품 댓글 리스트 
	public List<ProductReply> replyList(int product_number) throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		List<ProductReply> list = shopdao.replyList(product_number);
		return list;
	}
	
	//상품 댓글 삭제
	public void replyDelete(ProductReply reply) throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		shopdao.replyDelete(reply);
	}
	
	//상품 댓글 수정
	public void replyModify(ProductReply reply) throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		shopdao.replyModify(reply);
	}
	
	//상품 답글 작성
	public void reCmtInsert(ProductReply reply) throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		shopdao.reCmtInsert(reply);
	}
	
	
}























