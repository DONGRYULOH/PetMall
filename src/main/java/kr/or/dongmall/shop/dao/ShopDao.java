package kr.or.dongmall.shop.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.dongmall.admin.dto.CategoryDto;
import kr.or.dongmall.admin.dto.Product_ImageFile;
import kr.or.dongmall.admin.dto.Product_Join_ProductImageFile;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.shop.dto.CartDto;
import kr.or.dongmall.shop.dto.NonuserOrderDetailDto;
import kr.or.dongmall.shop.dto.NonuserOrderDto;
import kr.or.dongmall.shop.dto.OrderDetailDto;
import kr.or.dongmall.shop.dto.OrderDto;
import kr.or.dongmall.shop.dto.ProductReply;
import kr.or.dongmall.user.dto.UserAddressDto;
import kr.or.dongmall.user.dto.UserDto;



public interface ShopDao {

	//카테고리별 상품목록 
	public List<Product_Join_ProductImageFile> cateProductList(String categoryNumber)throws Exception;
	
	//전체 상품목록 
	public List<Product_Join_ProductImageFile> AllcateProductList()throws Exception;
	
	//해당상품조회 
	public ProductDto shopDetail(int product_number) throws Exception;
	
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
	
	//상품조회시 조회수 증가
	public void productViewCount(int product_number) throws Exception;
	
	//카트 담기 
	public int addCart(CartDto cart) throws Exception;
	
	//장바구니 리스트 출력 
	public List<CartDto> cartList(String user_id) throws Exception;
	
	//장바구니 물품 총액수 
	public int cartTotal(String user_id) throws Exception;
	
	//장바구니 물품 삭제 
	public int cartDelete(int cart_number) throws Exception;
	
	//장바구니 물품 수정 
	public void cartUpdate(HashMap<String,Integer> cart) throws Exception;
	
	//장바구니 모든 아이디 가져오기 
	public List<CartDto> all_id() throws Exception;
	
	//해당 상품번호에 대한 모든 이미지를 가져옴 
	public List<Product_ImageFile> shopDetailImg(int product_number);
	
	//해당 상품의 대표이미지 
	public Product_ImageFile shop_delegate_image(int product_number);
	
	//회원의 배송지 정보 가져오기 
	public UserAddressDto getUserAddress(String user_id);
	
	//회원의 정보(이름,전화번호,이메일) 가져오기
	public UserDto getUserInfo(String user_id);
	
	//해당 상품 주문시 상품 정보가져오기 
	public ProductDto getProductInfo(int product_number);
	
	//주문한 기본(배송지주소,회원)정보
	public int insertOrderInfo(OrderDto orderInfo);

	//주문한 상품 정보들
	public int insertProductOrderInfo(OrderDetailDto orderDetailDto);

	//결제한 회원의 정보 가져오기(배송지,주문번호,회원정보)
	public OrderDto getOrderInfo(String order_number);
	
	//비회원 주문정보 INSERT
	public int insertNonUserOrderInfo(NonuserOrderDto nonuserOrderDto);
	
	//비회원 상품정보 INSERT
	public int insertNonUserProductOrderInfo(NonuserOrderDetailDto nonuserOrderDetailDto);
	
	//결제후 비회원 주문정보 
	public NonuserOrderDto getNonuserOrderInfo(String order_number);
}




























