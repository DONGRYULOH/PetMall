package kr.or.dongmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.dongmall.user.dto.UserDto;

//가로 챈다라는 의미로 허가되지 않은 페이지에 어떠한 사용자가 들어올 경우 확인 여부에 따라서 알맞는 화면으로 보내줌 
public class AdminInterceptor extends HandlerInterceptorAdapter{

	//컨트롤러가 실행되기전에 관리자 여부를 확인	
	 @Override
	 public boolean preHandle(HttpServletRequest req,HttpServletResponse res, Object obj) throws Exception {
		  System.out.println("관리자 인터셉터 실행");
		  
		  HttpSession session = req.getSession();
		  UserDto user = (UserDto)session.getAttribute("User");		 
		  System.out.println("세션"+user);
		  
		  //세션이 없을때(로그인이 되어있지 않은상태라면) 
		  if(user == null ) {
			  System.out.println("로그인 화면으로 강제이동");
			  res.sendRedirect("/dongmall/login");
			  return false;
		  }
		  
		  //관리자 권한이 아닐때(일반 회원인경우) 
		  if(user.getUser_role() != 9) {
			  System.out.println("메인 화면으로 강제이동");
			  res.sendRedirect("/dongmall/");
			  return false;
		  }
		  
		  return true;
	 }
	 
}
