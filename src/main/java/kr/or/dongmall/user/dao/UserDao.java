package kr.or.dongmall.user.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.dongmall.shop.dto.NonuserOrderDetailDto;
import kr.or.dongmall.shop.dto.NonuserOrderDto;
import kr.or.dongmall.shop.dto.NonuserRefundDto;
import kr.or.dongmall.shop.dto.OrderDetailDto;
import kr.or.dongmall.shop.dto.OrderDto;
import kr.or.dongmall.shop.dto.OrderRefundDto;
import kr.or.dongmall.user.dto.UserAddressDto;
import kr.or.dongmall.user.dto.UserDto;


public interface UserDao {

	// ----------- 회원 가입 ------------
	public int signUp(UserDto user) throws Exception;
	
	// ----------- 로그인 확인 ----------
	public UserDto signIn(UserDto user) throws Exception;
	
	// ---------- ID중복체크 --------------
	public int userIdCheck(String user_id) throws Exception;
	
	public List<UserDto> userList() throws Exception;
	
	//해당 User의 배송지 주소값 입력 
	public void insertAddress(UserAddressDto userAddress);
	
	//유저에 해당하는 모든 주문정보를 가져옴 
	public List<OrderDto> getOrderInfo(String user_id);

	//주문번호에 해당되는 상품정보들을 가져옴
	public List<OrderDetailDto> getOrderDetailInfo(String order_number);

	//환불여부 체크 프로시저 호출 
	public void refundCheck();
	
	//상세주문번호에 해당되는 상품정보 가져오기 
	public OrderDetailDto getOrderDInfo(String order_detail_number);

	//환불내역 테이블에 INSERT 해주기 
	public void refundInfoInsert(OrderRefundDto refundDto);

	//환불을 요청한 해당 주문의 처리상태가 "환불중"으로 변경되야됨
	public void orderDetailCkUpdate(String order_detail_number);
	
	//입력한 주문번호가 DB테이블에 있는지 검증 
	public String orderNumberCk(String orderNumber);

	//주문번호에 해당하는 주문정보를 가져옴(비회원)
	public List<NonuserOrderDto> getNonUserOrderInfo(String orderNumber);

	//주문번호에 해당하는 주문세부 정보를 가져옴(비회원)
	public List<NonuserOrderDetailDto> getNonUserOrderDetailInfo(String order_number);

	//상세주문번호에 해당되는 상품정보 가져오기(비회원)
	public NonuserOrderDetailDto getNonUserOrderDInfo(String order_detail_number);

	//환불 내역테이블에 환불정보 INSERT(비회원)
	public void nonUserRefundInfoInsert(NonuserRefundDto refundDto);
	
	//환불 처리상태 변경(비회원)
	public void NonUserOrderDetailCkUpdate(String order_detail_number);
	
	//주문번호 가져오기(비회원)
	public String getOrderNumber(String order_detail_number);
	
	
}
