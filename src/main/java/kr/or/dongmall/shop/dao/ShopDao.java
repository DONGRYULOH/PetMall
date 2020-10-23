package kr.or.dongmall.shop.dao;

import java.util.List;

import kr.or.dongmall.admin.dto.CategoryDto;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.shop.dto.ProductReply;



public interface ShopDao {

	//카테고리별 상품목록 
	public List<ProductCateDto> cateProductList(String categoryNumber)throws Exception;
	
	//전체 상품목록 
	public List<ProductCateDto> AllcateProductList()throws Exception;
	
	//해당상품조회 
	public ProductCateDto shopDetail(int product_number) throws Exception;
	
	//상품댓글작성
	public void replyInsert(ProductReply reply) throws Exception;
	
	//상품댓글작성후 (댓글번호 == ref 만들어주기)
	public void replyInsert_ref(ProductReply reply) throws Exception;
	
	//상품댓글리스트 
	public List<ProductReply> replyList(int product_number) throws Exception;
	
	//상품댓글삭제
	public void replyDelete(ProductReply reply) throws Exception;
	
	//상품댓글수정
	public void replyModify(ProductReply reply) throws Exception;
	
	//상품답글작성
	public void reCmtInsert(ProductReply reply) throws Exception;
}
