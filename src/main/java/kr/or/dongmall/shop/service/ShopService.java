package kr.or.dongmall.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.dongmall.admin.dto.Product_ImageFile;
import kr.or.dongmall.admin.dto.Product_Join_ProductImageFile;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.shop.dao.ShopDao;
import kr.or.dongmall.shop.dto.CartDto;
import kr.or.dongmall.shop.dto.NonuserOrderDetailDto;
import kr.or.dongmall.shop.dto.NonuserOrderDto;
import kr.or.dongmall.shop.dto.OrderDetailDto;
import kr.or.dongmall.shop.dto.OrderDto;
import kr.or.dongmall.shop.dto.ProductReply;
import kr.or.dongmall.user.dto.UserAddressDto;
import kr.or.dongmall.user.dto.UserDto;

@Service
public class ShopService {

	@Inject
	SqlSession sqlSession;
	
	//카테고리 별로 해당 상품목록 가져오는 서비스 
	public List<Product_Join_ProductImageFile> cateProductList(String categoryNumber) throws Exception{
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		
		List<Product_Join_ProductImageFile> list = shopdao.cateProductList(categoryNumber);
		for(int i=0;i<list.size();i++) {
			System.out.println("상품번호"+list.get(i).getProduct_number());
		}
		
		return list;
	}
	
	//전체 상품목록 가져오는 서비스 
	public List<Product_Join_ProductImageFile> AllcateProductList() throws Exception{
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);		
		List<Product_Join_ProductImageFile> list = shopdao.AllcateProductList();
		return list;
	}
	
	//해당 상품 조회 서비스 
	public ProductDto shopDetail(int product_number) throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.shopDetail(product_number);
	}
	
	//상품 조회시 조회수 1증가 서비스 
	public void productViewCount(int product_number)throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		shopdao.productViewCount(product_number);
	}
	
	
	// ------ 상품 댓글 관련 -------------------------------------------------------
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
			System.out.println("원댓글 작성중 에러발생!!"+e.getMessage());
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
	// ------ 상품 댓글 관련 END-------------------------------------------------------
	
	
	
	// 카트(장바구니)관련 서비스  ----------------------------------------------------------
	
	//장바구니 추가 
	public int addCart(CartDto cart) throws Exception {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.addCart(cart);
	}
	
	//장바구니 리스트 호출 
	public List<CartDto> cartList(String user_id){
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		List<CartDto> cartList = null;
		try {
			cartList = shopdao.cartList(user_id);
			for(int i=0;i<cartList.size();i++) {				
				System.out.println("상품 번호 -> " +cartList.get(i).getProduct_number());
				System.out.println("유저ID -> "+cartList.get(i).getUser_id());
				System.out.println("상품이름 -> "+cartList.get(i).getProduct_name());
				System.out.println("상품가격 -> "+cartList.get(i).getProduct_price());			
				System.out.println("=========================================");
			}		
		} catch (Exception e) {
			System.out.println("장바구니 리스트 불러오는 중 에러발생......");
			e.printStackTrace();
		}
		
		return cartList;
	}
	
	//장바구니에 담긴 물품의 총액수 계산 
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
	public void cartDelete(int cart_number) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		try {
			shopdao.cartDelete(cart_number);
		} catch (Exception e) {
			System.out.println("장바구니에서 물품 삭제 중 에러발생....");
			e.printStackTrace();
		}
		
	}
	
	//장바구니에서 선택한 물품만 삭제 
	public int cartselectDelete(Integer[] deleteCartList) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		int result = 0;
		try {
			for(int i=0;i<deleteCartList.length;i++) {
				result = shopdao.cartDelete(deleteCartList[i]); //삭제 성공시 1 반환 실패시 0 반환 
			}
		} catch (Exception e) {
			System.out.println("장바구니에서 물품 삭제 중 에러발생....");
			e.printStackTrace();
			result = 0; //삭제도중 예외가 발생함 실패라는 경우임(0) 
		}
		return result;
	}
	
	//장바구니 물풀 수량 수정 
	public void cartUpdate(int cart_number,int product_count) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		HashMap<String,Integer> cart = new HashMap<String,Integer>();
		cart.put("cart_number",cart_number); //카트번호
		cart.put("product_count",product_count); //주문할 상품수량 
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
	
	// 카트(장바구니)관련 서비스  END ----------------------------------------------------------
	
	
	//해당 상품번호의 이미지를 모두가져옴
	public List<Product_ImageFile> shopDetailImg(int product_number) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.shopDetailImg(product_number);
	}
	
	//해당 상품의 대표이미지 가져오기 
	public Product_ImageFile shop_delegate_image(int product_number) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.shop_delegate_image(product_number);
	}
	
	
	// <주문 관련 서비스> ---------------------------------------------------------------------------
	
	//1.회원의 배송지 정보 가져오기 
	public UserAddressDto getUserAddress(String user_id) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.getUserAddress(user_id);
	}
	
	//2.회원의 정보(이름,전화번호,이메일) 가져오기 
	public UserDto getUserInfo(String user_id) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.getUserInfo(user_id);
	}
	
	// 해당 상품 주문시 상품 정보가져오기 
	public ProductDto getProductInfo(int product_number) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.getProductInfo(product_number);
	}
	
	//주문한 상품정보들과 주문정보(배송지,회원정보) DB에 INSERT 하기 (회원인 경우)
	public int insertOrderInfo(OrderDto orderInfo, ArrayList<OrderDetailDto> orderDetailInfo) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		
		int result = 0;
		
		//회원 주문정보 INSERT
		result = shopdao.insertOrderInfo(orderInfo);
		
		//회원 상품정보 INSERT 
		for(int i=0;i<orderDetailInfo.size();i++) {
			result = shopdao.insertProductOrderInfo(orderDetailInfo.get(i));
		}

		return result;
	}

	//결제한 회원의 정보 가져오기(배송지,주문번호,회원정보)
	public OrderDto getOrderInfo(String order_number) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.getOrderInfo(order_number);
	}
	
	//주문한 상품정보들과 주문정보(배송지,회원정보) DB에 INSERT 하기 (비회원인 경우)
	public int nonUserOrderInfoInsert(NonuserOrderDto nonuserOrderDto,ArrayList<NonuserOrderDetailDto> nonuserOrderDetailInfo) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		
		int result = 0;
		
		//비회원 주문정보 INSERT
		result = shopdao.insertNonUserOrderInfo(nonuserOrderDto);
		
		//비회원 상품정보 INSERT 
		for(int i=0;i<nonuserOrderDetailInfo.size();i++) {
			result = shopdao.insertNonUserProductOrderInfo(nonuserOrderDetailInfo.get(i));
		}

		return result;
	}
	
	//결제후 비회원의 주문정보 가져오기 
	public NonuserOrderDto getNonuserOrderInfo(String order_number) {
		ShopDao shopdao = sqlSession.getMapper(ShopDao.class);
		return shopdao.getNonuserOrderInfo(order_number);
	}

	
	
}























