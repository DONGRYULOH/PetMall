package kr.or.dongmall.shop.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.shop.dao.ShopDao;
import kr.or.dongmall.shop.dto.CartDto;
import kr.or.dongmall.shop.dto.ProductReply;
import kr.or.dongmall.user.dto.UserDto;

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
	
	
	// 카트관련 서비스  ----------------------------------------------------------
	
	//장바구니 추가 
	public void addCart(CartDto cart) throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		shopdao.addCart(cart);
	}
	
	//장바구니 리스트 호출 
	public List<CartDto> cartList(String user_id){
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		List<CartDto> cartList = null;
		try {
			cartList = shopdao.cartList(user_id);
			for(int i=0;i<cartList.size();i++) {
				System.out.println("장바구니 번호 -> "+cartList.get(i).getCart_num());
				
//				int cart_num = (int)cartList.get(i).getCart_num();				
//				cartList.get(i).setCart_num(cart_num);
					
				System.out.println("상품 번호 -> " +cartList.get(i).getProduct_number());
				System.out.println("유저ID -> "+cartList.get(i).getUser_id());
				System.out.println("선택한 상품 수량 -> "+cartList.get(i).getCart_stock());
				System.out.println("상품이름 -> "+cartList.get(i).getProduct_name());
				System.out.println("상품가격 -> "+cartList.get(i).getProduct_price());
				System.out.println("상품이미지 -> "+cartList.get(i).getProduct_ThumbImg());
				System.out.println("=========================================");
			}		
		} catch (Exception e) {
			System.out.println("장바구니 리스트 불러오는 중 에러발생......");
			e.printStackTrace();
		}
		
		return cartList;
	}
	
	//장바구니에 담긴 물품의 총액수 
	public int cartTotal(String user_id){
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		int result =0;
		try {
			result = shopdao.cartTotal(user_id);
		} catch (Exception e) {
			System.out.println("상품 총액수 가져오는 중 에러발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//장바구니 물품 삭제 
	public void cartDelete(int cart_num) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		try {
			shopdao.cartDelete(cart_num);
		} catch (Exception e) {
			System.out.println("장바구니에서 물품 삭제 중 에러발생....");
			e.printStackTrace();
		}
		
	}
	
	//장바구니 물풀 수량 수정 
	public void cartUpdate(int cart_num,int cart_stock) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		HashMap<String,Integer> cart = new HashMap<String,Integer>();
		cart.put("cart_num",cart_num);
		cart.put("cart_stock",cart_stock);
		try {
			shopdao.cartUpdate(cart);
		} catch (Exception e) {
			System.out.println("장바구니에서 물품 수량 수정 중 에러발생....");
			e.printStackTrace();
		}
	}
	
	//장바구니 모든 아이디 가져오기
	public List<CartDto> all_id() throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.all_id();
	}
	
}






















