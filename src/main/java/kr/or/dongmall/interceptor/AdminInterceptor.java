package kr.or.dongmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.dongmall.user.dto.UserDto;

public class AdminInterceptor extends HandlerInterceptorAdapter{

	//컨트롤러가 실행되기전에 관리자 여부를 확인 	
	 @Override
	 public boolean preHandle(HttpServletRequest req,
	    HttpServletResponse res, Object obj) throws Exception {
		  System.out.println("관리자 인터셉터");
		  HttpSession session = req.getSession();
		  UserDto user = (UserDto)session.getAttribute("User");
		  
		  System.out.println("세션"+user);
		  	  
		  if(user == null ) {
			  System.out.println("로그인 화면으로 강제이동");
			  res.sendRedirect("/dongmall/login");
			  return false;
		  }
		  
		  if(user.getVerify() != 9) {
			  System.out.println("메인 화면으로 강제이동");
			  res.sendRedirect("/dongmall/");
			  return false;
		  }
		  
		  return true;
	 }
	 
}
