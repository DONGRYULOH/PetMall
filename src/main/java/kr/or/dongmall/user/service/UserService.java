package kr.or.dongmall.user.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.dongmall.shop.dto.NonuserOrderDetailDto;
import kr.or.dongmall.shop.dto.NonuserOrderDto;
import kr.or.dongmall.shop.dto.NonuserRefundDto;
import kr.or.dongmall.shop.dto.OrderDetailDto;
import kr.or.dongmall.shop.dto.OrderDto;
import kr.or.dongmall.shop.dto.OrderRefundDto;
import kr.or.dongmall.user.dao.UserDao;
import kr.or.dongmall.user.dto.UserAddressDto;
import kr.or.dongmall.user.dto.UserDto;

@Service
public class UserService {

	@Inject
	SqlSession sqlSession;
	
	//패스워드를 암호화 할수 있음 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// -----------회원가입 서비스-----------
	public String register(UserDto user,UserAddressDto userAddress) {
		System.out.println(user.toString());
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		
		try {
			System.out.println("register try문");
			// 이메일을 체크여부가 N일때는 왜?? Null값이 들어오지?? 이메일 체크가 Y이면 Y값이 잘넘어옴 
			System.out.println("이메일 체크여부 "+user.getEmail_check());
			if(user.getEmail_check() == 0 || user.getEmail_check() == ' ') {
				user.setEmail_check('N');
			}

			// 패스워드를 암호화 시킨후 User 테이블에 Insert 시킴 
			user.setUser_pwd(bCryptPasswordEncoder.encode(user.getUser_pwd()));
			System.out.println("암호화된 패스워드 "+user.getUser_pwd()); 
			userdao.signUp(user);  
				
			// 해당 User의 배송지 주소값 입력 
			userAddress.setUser_id(user.getUser_id());
			userdao.insertAddress(userAddress);
			
			
//			List<UserDto> list = userdao.userList();
//			
//			for(int i=0;i<list.size();i++) {
//				System.out.println("유저 아이디"+list.get(i).getUser_id());
//			}
			
		} catch (Exception e) {
			System.out.println("에러발생"+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/login";
	}
	
	//-----------로그인 확인 서비스 -----------
	public UserDto signIn(UserDto user) throws Exception {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		
		return userdao.signIn(user);
		
	}
	
	//ID중복체크 
	public int userIdCheck(String user_id) throws Exception {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		return userdao.userIdCheck(user_id);
	}
	
	//유저에 해당하는 모든 주문정보를 가져옴 
	public List<OrderDto> getOrderInfo(String user_id) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		return userdao.getOrderInfo(user_id);
	}

	//주문번호에 해당되는 상품정보들을 가져옴
	public List<OrderDetailDto> getOrderDetailInfo(String order_number) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		return userdao.getOrderDetailInfo(order_number);
	}

	//환불여부 체크 프로시저 호출
	public void refundCheck() {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		userdao.refundCheck();
	}
	
	//상세주문번호에 해당되는 상품정보 가져오기 
	public OrderDetailDto getOrderDInfo(String order_detail_number) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		return userdao.getOrderDInfo(order_detail_number);
	}

	//환불내역 테이블에 INSERT 해주기 
	public void refundInfoInsert(OrderRefundDto refundDto) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		try {
			userdao.refundInfoInsert(refundDto);
		}catch (Exception e) {
			System.out.println("환불 내역 테이블에 INSERT 하는 중 에러발생"+e.getMessage());
		}	
	}

	//환불을 요청한 해당 주문의 처리상태가 "환불중"으로 변경되야됨
	public void orderDetailCkUpdate(String order_detail_number) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		try {
			userdao.orderDetailCkUpdate(order_detail_number);
		}catch (Exception e) {
			System.out.println("환불을 요청한 해당 주문의 처리상태가 UPDATE 도중 에러발생"+e.getMessage());
		}		
	}
	
	//입력한 주문번호가 DB테이블에 있는지 검증 
	public int orderNumberCk(String orderNumber) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		int result = 0;
		try {
			String orderNum = userdao.orderNumberCk(orderNumber);
			if(orderNumber.equals(orderNum)) { // DB테이블에 있는 주문번호와 입력한 주문번호가 동일한 경우 
				result = 1;
			}
		}catch (Exception e) {
			System.out.println("입력한 주문번호가 DB테이블에 있는지 검증 도중 에러발생"+e.getMessage());
		}			
		return result;
	}

	//주문번호에 해당하는 주문정보를 가져옴(비회원)
	public List<NonuserOrderDto> getNonUserOrderInfo(String orderNumber) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		return userdao.getNonUserOrderInfo(orderNumber);
	}
	
	//주문번호에 해당하는 주문세부 정보를 가져옴(비회원)
	public List<NonuserOrderDetailDto> getNonUserOrderDetailInfo(String order_number) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		return userdao.getNonUserOrderDetailInfo(order_number);
	}
	
	//상세주문번호에 해당되는 상품정보 가져오기(비회원)
	public NonuserOrderDetailDto getNonUserOrderDInfo(String order_detail_number) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		return userdao.getNonUserOrderDInfo(order_detail_number);
	}

	//환불 내역테이블에 환불정보 INSERT(비회원)
	public void nonUserRefundInfoInsert(NonuserRefundDto refundDto) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		userdao.nonUserRefundInfoInsert(refundDto);
	}
	
	//환불 처리상태 변경(비회원)
	public void NonUserOrderDetailCkUpdate(String order_detail_number) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		userdao.NonUserOrderDetailCkUpdate(order_detail_number);
	}

	//주문번호 가져오기(비회원)
	public String getOrderNumber(String order_detail_number) {
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		return userdao.getOrderNumber(order_detail_number);
	}
	
	
}

































