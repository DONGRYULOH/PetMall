package kr.or.dongmall.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.dongmall.shop.dto.OrderDetailDto;
import kr.or.dongmall.shop.dto.OrderDto;
import kr.or.dongmall.user.dto.UserAddressDto;
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
	public String register(UserDto user,UserAddressDto userAddress,HttpServletRequest req) {
		return userService.register(user,userAddress);	
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
		System.out.println("login값은??"+login); // DB에 존재하는 회원이 없을경우 null값임  
		HttpSession session = req.getSession();
		
		//입력한 패스워드와 db에 암호화해서 저장된 패스워드 일치여부(틀리면 False , 맞으면 true) 
		boolean pwdMatch = false;
		if(login != null) { //입력한 회원아이디가 DB에 존재할경우에만 입력한 패스워드를 비교함 
			pwdMatch = passEncoder.matches(user.getUser_pwd(),login.getUser_pwd());
			System.out.println("입력한 패스워드 == 기존에 가입한 패스워드의 암호화"+pwdMatch);
		}
		
		//DB에 유저 정보가 존재하고 입력한 패스워드가 일치할경우 로그인성공
		//DB에 저장된 유저가 아닐경우에는  login.getUser_pwd() 해봤자 null 값이 나옴
		if(login != null && pwdMatch){			
			session.setAttribute("User",login);
			System.out.println("로그인 성공시 메인페이지로 이동");
			return "redirect:/";
		}
		else{
			 session.setAttribute("User",null);
			 rttr.addFlashAttribute("msg",false);
			 System.out.println("아이디 또는 패스워드가 틀림 로그인 실패 다시 로그인 페이지 리다이렉트");
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
	
	// **************** MY PAGE 관련 *******************************************
	
	//MyPage 이동 
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(HttpSession session) throws Exception {

	   
	 	return "myPage/myPage_index";
	}
	
	//MyPage 주문내역 조회시  
	@RequestMapping(value = "/myPage/orderList", method = RequestMethod.GET)
	public String myPageOrderList(HttpSession session,Model model) throws Exception {
		
		//1.환불여부 체크 프로시저 호출하기
		userService.refundCheck();
		
		//2.주문테이블에서 접속한 유저의 세션에 해당하는 주문정보를 가져옴(주문정보가 여러개일수도 있음)  
		UserDto user = (UserDto)session.getAttribute("User");
		List<OrderDto> orderInfo = userService.getOrderInfo(user.getUser_id());
		model.addAttribute("orderInfo", orderInfo);
		
		//3.주문상세테이블에서 주문번호에 해당되는 상품정보들을 가져옴(여러개일수도 있고 하나일수도 있음) 
		// 상품이미지 테이블(상품 썸네일) + 상품 테이블(상품이름) + 주문상세 테이블을 조인시켜야됨   
		ArrayList<OrderDetailDto> OrderList = new ArrayList<OrderDetailDto>();
		for(int i=0;i<orderInfo.size();i++) {
			List<OrderDetailDto> OrderDetailList = userService.getOrderDetailInfo(orderInfo.get(i).getOrder_number()); //첫번째 주문번호, 두번쨰 주문번호 이런식으로 
			OrderList.addAll(i,OrderDetailList); // 0 번째 ArrayList 에 List 담기 (ArrayList 안에 ArrayList 추가하기) 
		}
		
		for(int i=0;i<OrderList.size();i++) {
			
		}

		
		model.addAttribute("OrderDetailList", OrderDetailList);
		
	 	return "myPage/myPage_OrderList";
	}
	
	
}



















