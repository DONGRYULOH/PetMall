package kr.or.dongmall.user.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.dongmall.user.dao.UserDao;
import kr.or.dongmall.user.dto.UserDto;

@Service
public class UserService {

	@Inject
	SqlSession sqlSession;
	
	//패스워드를 암호화 할수 있음 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// -----------회원가입 서비스-----------
	public String register(UserDto user) {
		System.out.println(user.toString());
		UserDao userdao = sqlSession.getMapper(UserDao.class);
		
		try {
			System.out.println("register try문");
			
			//이것도 잘얻어옴 
//			System.out.println("ID"+user.getUser_id());
//			System.out.println("name"+user.getUser_name());
//			System.out.println("pwd"+user.getUser_pwd());
//			System.out.println("phone"+user.getUser_phone());
			
			user.setUser_pwd(bCryptPasswordEncoder.encode(user.getUser_pwd()));
			System.out.println("암호화된 패스워드 "+user.getUser_pwd()); //여기까지 성공 
			userdao.signUp(user); //여기서 에러발생 

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
	
	
}

































