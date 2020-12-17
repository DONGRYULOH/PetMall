package kr.or.dongmall.user.dao;

import java.sql.SQLException;
import java.util.List;

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
	
	
}
