package kr.or.dongmall.user.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.dongmall.user.dto.UserDto;
import kr.or.dongmall.user.service.UserService;



@Controller
public class UserController {

	@Inject
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	

	//-----------회원가입--------------- 
	//회원가입 페이지
	@RequestMapping(value="/register" , method=RequestMethod.GET)
	public String register() {
		return "user/register";
	}
	//회원가입 처리
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public String register(UserDto user) {
		//System.out.println("회원아이디"+user.getUser_id());
		return userService.register(user);	
	}
	
	//----------로그인----------------
	//로그인 페이지 
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	//로그인 버튼 클릭시 
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String signIn(UserDto user,HttpServletRequest req,RedirectAttributes rttr)throws Exception {
		System.out.println("회원아이디"+user.getUser_id());
		System.out.println("회원패스워드"+user.getUser_pwd());
		
		UserDto login = userService.signIn(user);
		//System.out.println("암호화된 패스워드"+login.getUser_pwd()); DB에 저장된 유저가 있을경우에만 암호화된 패스워드가 있음 
		HttpSession session = req.getSession();
		
		//DB에 유저 정보가 있을경우 
		if(login != null){
			//DB에 저장된 유저가 아닐경우에는  login.getUser_pwd() 해봤자 null 값이 나옴 
			boolean pwdMatch = passEncoder.matches(user.getUser_pwd(),login.getUser_pwd());
			System.out.println("입력한 패스워드 == 기존에 가입한 패스워드의 암호화"+pwdMatch);
			
			if(pwdMatch == true) { //입력한PWD와 DB저장된 암호화된 PWD가 맞을경우 실행 
				session.setAttribute("User",login);
				System.out.println("로그인 성공시 메인페이지로 이동");
				return "redirect:/";
			}
			System.out.println("로그인 실패 패스워드가 틀립니다...");
			return "redirect:/login";
		}
		else{
			session.setAttribute("User",null);
			 rttr.addFlashAttribute("msg",false);
			 System.out.println("로그인 실패 다시 로그인 페이지 리다이렉트");
			 return "redirect:/login";
		}

	}
	
	// 로그아웃
	@RequestMapping(value = "/signOut", method = RequestMethod.GET)
	public String signOut(HttpSession session) throws Exception {

		session.invalidate();
	   
	 	return "redirect:/";
	}
	
	//ID 중복체크 
	@RequestMapping(value="/idCheck", method = RequestMethod.GET)
	@ResponseBody
	public int idCheck(@RequestParam("user_id")String user_id) throws Exception {
		return userService.userIdCheck(user_id);
	}
	
	
	
	
	
}



















