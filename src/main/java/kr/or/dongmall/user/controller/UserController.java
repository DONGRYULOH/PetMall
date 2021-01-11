package kr.or.dongmall.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.dongmall.shop.dto.NonuserOrderDetailDto;
import kr.or.dongmall.shop.dto.NonuserOrderDto;
import kr.or.dongmall.shop.dto.NonuserRefundDto;
import kr.or.dongmall.shop.dto.OrderDetailDto;
import kr.or.dongmall.shop.dto.OrderDto;
import kr.or.dongmall.shop.dto.OrderRefundDto;
import kr.or.dongmall.user.dto.UserAddressDto;
import kr.or.dongmall.user.dto.UserDto;
import kr.or.dongmall.user.service.UserService;
import kr.or.dongmall.utils.CommonUtils;
import kr.or.dongmall.utils.RefundFileUtils;




@Controller
public class UserController {

	@Inject
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@Resource(name="RefundFileUtils")
	private RefundFileUtils refundFileUtils;

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
	
	// *********************************** <비회원> ********************************************
	
	//주문번호 조회 버튼 클릭시 
	@RequestMapping(value="/orderNumberCheck", method=RequestMethod.POST)
	public String orderNumberCheck(HttpServletRequest req,RedirectAttributes rttr,Model model)throws Exception {
		
		String orderNumber = req.getParameter("orderNumber");
	    System.out.println("입력한 주문번호: " + orderNumber);

	    //입력한 주문번호 검증 
		int result = userService.orderNumberCk(orderNumber);
		
		//입력한 주문번호가 DB에 있는경우 주문내역 조회 
		if(result == 1) {
			System.out.println("주문 내역 리스트 보여주기");
			return "redirect:/myPage/nonUserOrderList?orderNumber="+orderNumber;
		}else {
			System.out.println("입력한 주문번호는 존재하지 않음!!");
			rttr.addFlashAttribute("orderMsg",false);
			return "redirect:/login";
		}
		
	}
	
	//주문내역 리스트 보여주기 
	@RequestMapping(value = "/myPage/nonUserOrderList", method = RequestMethod.GET)
	public String nonUserOrderList(@RequestParam("orderNumber") String orderNumber,Model model) throws Exception {
		
		System.out.println("주문번호 확인:"+orderNumber);
		
		//2.비회원 주문테이블에서 주문번호에 해당하는 주문정보를 가져옴
		List<NonuserOrderDto> nonuserOrderInfo = userService.getNonUserOrderInfo(orderNumber);
		model.addAttribute("nonuserOrderInfo", nonuserOrderInfo);
		
		//3.비회원 주문상세테이블에서 주문번호에 해당되는 상품정보들을 가져옴(여러개일수도 있고 하나일수도 있음) 
		// 상품이미지 테이블(상품 썸네일) + 상품 테이블(상품이름) + 주문상세 테이블을 조인시켜야됨
		ArrayList<List<NonuserOrderDetailDto>> nonUserOrderDetailList = new ArrayList<List<NonuserOrderDetailDto>>();
		List<NonuserOrderDetailDto> nonUserOrderList = new ArrayList<NonuserOrderDetailDto>(); //상세주문정보를 다시 모아놓을 List 
		for(int i=0;i<nonuserOrderInfo.size();i++) {
			// OrderDetailList에 해당 주문번호에 해당되는 모든 상세주문번호가 List형태로 저장되서 add됨 
			nonUserOrderDetailList.add(userService.getNonUserOrderDetailInfo(nonuserOrderInfo.get(i).getOrder_number())); 
		}
		
		//4.nonUserOrderDetailList 안에 담긴 주문정보들을 클라이언트단에서 사용하기 힘들기 때문에 사용하기 쉽도록 데이터 가공을 해줌 
		NonuserOrderDetailDto nonuserOrderDetailDto = null;
		for(int i=0;i<nonUserOrderDetailList.size();i++) {			
			for(int j=0;j<nonUserOrderDetailList.get(i).size();j++) {
				nonuserOrderDetailDto = new NonuserOrderDetailDto();
				nonuserOrderDetailDto.setStored_thumbNail(nonUserOrderDetailList.get(i).get(j).getStored_thumbNail()); //상품 썸네일
				nonuserOrderDetailDto.setProduct_name(nonUserOrderDetailList.get(i).get(j).getProduct_name()); //상품명 
				nonuserOrderDetailDto.setProduct_price(nonUserOrderDetailList.get(i).get(j).getProduct_price()); //상품가격 
				nonuserOrderDetailDto.setProduct_number(nonUserOrderDetailList.get(i).get(j).getProduct_number()); //상품번호 
				nonuserOrderDetailDto.setOrder_detail_number(nonUserOrderDetailList.get(i).get(j).getOrder_detail_number()); // 주문상세번호
				nonuserOrderDetailDto.setOrder_number(nonUserOrderDetailList.get(i).get(j).getOrder_number()); // 고유주문번호
				nonuserOrderDetailDto.setProduct_count(nonUserOrderDetailList.get(i).get(j).getProduct_count()); //주문한 상품 개수 
				nonuserOrderDetailDto.setOrder_detail_status(nonUserOrderDetailList.get(i).get(j).getOrder_detail_status()); //주문처리상태 
				nonuserOrderDetailDto.setRefund_check(nonUserOrderDetailList.get(i).get(j).getRefund_check()); //환불여부 
				nonuserOrderDetailDto.setOrder_date(nonUserOrderDetailList.get(i).get(j).getOrder_date()); //주문일자
				nonUserOrderList.add(nonuserOrderDetailDto);
			}
		}
		
		model.addAttribute("nonUserOrderList", nonUserOrderList);
		model.addAttribute("orderNum", orderNumber);
		return "nonUserOrderPage/orderList";
	}
	
	
	//MyPage 환불요청 작성 페이지 이동 
	@RequestMapping(value = "/myPage/nonUserRefund", method = RequestMethod.GET)
	public String nonUserRefundPage(@RequestParam("n") String order_detail_number,Model model) throws Exception {
		
	    //해당 상세주문번호로 상품정보(썸네일,상품이름,수량,가격,썸네일) 가져오기
		NonuserOrderDetailDto nonuserOrderDetailDto = userService.getNonUserOrderDInfo(order_detail_number);
		model.addAttribute("nonuserOrderDetailDto", nonuserOrderDetailDto);
		
	 	return "nonUserOrderPage/refundPage";
	}
	
	//환불 요청페이지에서 작성한 환불정보 DB에 INSERT 하기 
	@PostMapping("/myPage/nonUserRefund")
	public String nonUserRefundOk(Model model,HttpServletRequest request,HttpSession session) throws Exception {
		
		//HttpServletRequest 이용해 전송된(업로드한) 파일을 가져온다 
		MultipartHttpServletRequest MultipartHttpServletRequest = (MultipartHttpServletRequest)request;
		String OriFile = MultipartHttpServletRequest.getFile("refund_img").getOriginalFilename();
		
	    //서버에 저장될 환불사유 이미지파일명을 중복되지 않는 이름으로 재설정 해줌 
		System.out.println("업로드한 오리진 파일명:"+OriFile); //클라이언트가 업로드한 파일이름 
		String originalFileExtension = OriFile.substring(OriFile.lastIndexOf(".")); // 해당원본파일의 확장명을 뽑아냄 (png)  
		String storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32자리의 랜덤파일이름 + 확장명(서버에 저장될 파일명) 
		System.out.println("서버에 저장될 파일명 : " +storedFileName);
		//서버에 이미지 파일 업로드하기 
		refundFileUtils.refundFileUpload(storedFileName,MultipartHttpServletRequest.getFile("refund_img"));
		
		 /*
		 	파일전송을 위해 form에서 encType="multipart/form-data" 로 전송하게 되면 DTO가 자동으로 생성/주입되지 않기 때문에
		 	request 이용해 클라이언트에 작성한 환불 정보(환불사유,주문상세번호)를 가져온다 
		 */
		String order_detail_number = request.getParameter("order_detail_number"); //주문상세번호 
		String refund_reason = request.getParameter("refund_reason"); //환불사유 
		String refund_email = request.getParameter("refund_email"); //환불정보를 받을 이메일 
		
		System.out.println("주문상세번호 : " +order_detail_number);
		System.out.println("환불사유 : " +refund_reason);
		System.out.println("환불정보를 받을 이메일  : " +refund_email);
		
		//1.환불내역 테이블에 INSERT 해주기 
		NonuserRefundDto refundDto = new NonuserRefundDto();
		refundDto.setOrder_detail_number(order_detail_number);
		refundDto.setRefund_email(refund_email);
		refundDto.setRefund_img(storedFileName);
		refundDto.setRefund_reason(refund_reason);
		userService.nonUserRefundInfoInsert(refundDto); 
		
		//2.환불내역 테이블에 INSERT가 되고난후 환불을 요청한 해당 주문의 처리상태가 "환불중"으로 변경되야됨
		userService.NonUserOrderDetailCkUpdate(order_detail_number);
		
		//3.1번과 2번의 작업이 정상적으로 수행되고 나면 주문내역 페이지로 이동하기 
		String orderNum = userService.getOrderNumber(order_detail_number);
		
	 	return "redirect:/myPage/nonUserOrderList?orderNumber="+orderNum;
	}
		
	// *********************************** <비회원> END********************************************
	
	
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
		ArrayList<List<OrderDetailDto>> OrderDetailList = new ArrayList<List<OrderDetailDto>>();
		List<OrderDetailDto> OrderList = new ArrayList<OrderDetailDto>(); //상세주문정보를 다시 모아놓을 List 
		for(int i=0;i<orderInfo.size();i++) {
			// OrderDetailList에 해당 주문번호에 해당되는 모든 상세주문번호가 List형태로 들어감 
			OrderDetailList.add(userService.getOrderDetailInfo(orderInfo.get(i).getOrder_number())); 
		}
		
		//4.OrderDetailList안에 담긴 주문정보들을 클라이언트단에서 사용하기 힘들기 때문에 사용하기 쉽도록 데이터 가공을 해줌 
		OrderDetailDto orderDetail = null;
		for(int i=0;i<OrderDetailList.size();i++) {		
			
			for(int j=0;j<OrderDetailList.get(i).size();j++) {
				System.out.println("주문번호:"+OrderDetailList.get(i).get(j).getOrder_number());
				System.out.println("상세주문 번호:"+OrderDetailList.get(i).get(j).getOrder_detail_number());
				System.out.println("주문일자 확인 : "+OrderDetailList.get(i).get(j).getOrder_date());
				
				orderDetail = new OrderDetailDto();
				orderDetail.setStored_thumbNail(OrderDetailList.get(i).get(j).getStored_thumbNail()); //상품 썸네일
				orderDetail.setProduct_name(OrderDetailList.get(i).get(j).getProduct_name()); //상품명 
				orderDetail.setProduct_price(OrderDetailList.get(i).get(j).getProduct_price()); //상품가격 
				orderDetail.setProduct_number(OrderDetailList.get(i).get(j).getProduct_number()); //상품번호 
				orderDetail.setOrder_detail_number(OrderDetailList.get(i).get(j).getOrder_detail_number()); // 주문상세번호
				orderDetail.setOrder_number(OrderDetailList.get(i).get(j).getOrder_number()); // 고유주문번호
				orderDetail.setProduct_count(OrderDetailList.get(i).get(j).getProduct_count()); //주문한 상품 개수 
				orderDetail.setOrder_detail_status(OrderDetailList.get(i).get(j).getOrder_detail_status()); //주문처리상태 
				orderDetail.setRefund_check(OrderDetailList.get(i).get(j).getRefund_check()); //환불여부 
				orderDetail.setOrder_date(OrderDetailList.get(i).get(j).getOrder_date()); //주문일자
				OrderList.add(orderDetail);
			}
			
		}
	
		model.addAttribute("OrderList", OrderList);
		
	 	return "myPage/myPage_OrderList";
	}
	
	//MyPage 환불요청 작성 페이지 이동 
	@RequestMapping(value = "/myPage/refundPage", method = RequestMethod.GET)
	public String refundPage(@RequestParam("n") String order_detail_number,Model model) throws Exception {
		
	    //해당 상세주문번호로 상품정보(썸네일,상품이름,수량,가격,썸네일) 가져오기
		OrderDetailDto orderDetailInfo = userService.getOrderDInfo(order_detail_number);
		model.addAttribute("orderDetailInfo", orderDetailInfo);
		
	 	return "myPage/refundPage";
	}
	
	//환불 요청페이지에서 작성한 환불정보 DB에 INSERT 하기 
	@PostMapping("/myPage/refundPage")
	public String refundPageOk(Model model,HttpServletRequest request,HttpSession session) throws Exception {
		
		//HttpServletRequest 이용해 전송된(업로드한) 파일을 가져온다 
		MultipartHttpServletRequest MultipartHttpServletRequest = (MultipartHttpServletRequest)request;
		String OriFile = MultipartHttpServletRequest.getFile("refund_img").getOriginalFilename();
		
	    //서버에 저장될 환불사유 이미지파일명을 중복되지 않는 이름으로 재설정 해줌 
		System.out.println("업로드한 오리진 파일명:"+OriFile); //클라이언트가 업로드한 파일이름 
		String originalFileExtension = OriFile.substring(OriFile.lastIndexOf(".")); // 해당원본파일의 확장명을 뽑아냄 (png)  
		String storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32자리의 랜덤파일이름 + 확장명(서버에 저장될 파일명) 
		System.out.println("서버에 저장될 파일명 : " +storedFileName);
		//서버에 이미지 파일 업로드하기 
		refundFileUtils.refundFileUpload(storedFileName,MultipartHttpServletRequest.getFile("refund_img"));
		
		 /*
		 	파일전송을 위해 form에서 encType="multipart/form-data" 로 전송하게 되면 DTO가 자동으로 생성/주입되지 않기 때문에
		 	request 이용해 클라이언트에 작성한 환불 정보(환불사유,주문상세번호)를 가져온다 
		 */
		String order_detail_number = request.getParameter("order_detail_number"); //주문상세번호 
		String refund_reason = request.getParameter("refund_reason"); //환불사유 
		UserDto user = (UserDto)session.getAttribute("User");
		
		System.out.println("주문상세번호 : " +order_detail_number);
		System.out.println("환불사유 : " +refund_reason);
		System.out.println("유저 이메일 : " +user.getUser_email());
		
		//1.환불내역 테이블에 INSERT 해주기 
		OrderRefundDto refundDto = new OrderRefundDto();
		refundDto.setOrder_detail_number(order_detail_number);
		refundDto.setRefund_img(storedFileName);
		refundDto.setRefund_reason(refund_reason);
		refundDto.setUser_email(user.getUser_email());	
		userService.refundInfoInsert(refundDto); 
		
		//2.환불내역 테이블에 INSERT가 되고난후 환불을 요청한 해당 주문의 처리상태가 "환불중"으로 변경되야됨
		userService.orderDetailCkUpdate(order_detail_number);
		
		//3.1번과 2번의 작업이 정상적으로 수행되고 나면 주문내역 페이지로 이동하기 
		
	 	return "redirect:/myPage/orderList";
	}
	
}



















