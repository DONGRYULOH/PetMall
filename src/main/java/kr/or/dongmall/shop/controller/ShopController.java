package kr.or.dongmall.shop.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.shop.dto.CartDto;
import kr.or.dongmall.shop.dto.ProductReply;
import kr.or.dongmall.shop.service.ShopService;
import kr.or.dongmall.user.dto.UserDto;

@Controller
@RequestMapping("/shop/*") 
public class ShopController {
	
	//세션값을 부여하는 함수(임시로 만듬)
	public UserDto UserSession(Model model,HttpServletRequest req) {
		//session.setAttribute("User",login); 했던게 사라지므로 index페이지에 세션값을 부여할려면 다시 지정해줘야함 
		HttpSession session = req.getSession();
		UserDto name = (UserDto)session.getAttribute("User");	
		System.out.println("세션에 저장되어있는 값"+name);
		return name;
	}
		
	@ExceptionHandler(Exception.class)
	public void Ex(Exception e) {
		System.out.println("에러발생"+e.getClass());
		System.out.println("에러발생"+e.getMessage());
	}

	@Inject
	ShopService shopService;
	
	//카테고리별 상품리스트 보여주기
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String shopList(@RequestParam("cateNum") String categoryNumber,Model model) throws Exception{
		System.out.println("카테고리 코드번호" + categoryNumber);
		//1.전체목록을 볼경우(100) 2.해당카테고리 목록만 볼경우(101,102,103 ...등등) 
		
		List<ProductCateDto> list = null;
		if(categoryNumber.equals("100")) { //문자열을 == 으로 하면 해당 주소번지를 비교하기때문에 equals메소드를 사용해야지 내용자체를 비교할수있음  
			System.out.println("전체상품목록 가져오는중..");
			list = shopService.AllcateProductList();
		}else {
			System.out.println("해당 카테고리의 상품목록 가져오는중..");
			list = shopService.cateProductList(categoryNumber);
			/*
			if(categoryNumber.equals("101")) model.addAttribute("cateNum",categoryNumber); //상의
			if(categoryNumber.equals("102")) model.addAttribute("cateNum",categoryNumber); //하의
			if(categoryNumber.equals("103")) model.addAttribute("cateNum",categoryNumber); //신발
			if(categoryNumber.equals("104")) model.addAttribute("cateNum",categoryNumber); //시계
			*/
		}
		
		model.addAttribute("cateNum",categoryNumber); // 카테고리 번호 (전체,상의,하의,신발,시계 중하나) 
		model.addAttribute("cateSize",list.size());	// 카테고리 목록 사이즈(해당카테고리 물품이 총몇개있는지)
		model.addAttribute("cateProductList",list);
		return "shop/list";
	}
	
	//해당상품 조회 
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String shopDetail(@RequestParam("n") int product_number,Model model,HttpServletRequest req)throws Exception{
		System.out.println("상품번호"+product_number);
		ProductCateDto product = shopService.shopDetail(product_number);
		
		model.addAttribute("product", product);
		
		//해당 상품에 대한 댓글리스트 가져오기 
		/*
		List<ProductReply> reply = shopService.replyList(product_number);
		model.addAttribute("replyList",reply);
		*/
		model.addAttribute("User",UserSession(model,req));
		
		return "shop/detail";
	}
	
	//상품조회시 소감(댓글)작성  AJAX
	@ResponseBody
	@RequestMapping(value="/view/replyInsert", method=RequestMethod.POST)
	public void replyInsert(ProductReply reply,HttpSession session)throws Exception{
		
		//현재세션에 저장되어있는 유저ID 가져오기 
		UserDto user = (UserDto)session.getAttribute("User");
		reply.setReply_writer(user.getUser_id());
		
		shopService.replyInsert(reply);
		
	}
	
	//상품 소감(댓글) 목록(AJAX) 
	@ResponseBody
	@RequestMapping(value = "/view/replyList",method=RequestMethod.GET)
	public List<ProductReply> getReplyList(@RequestParam("n") int product_number) throws Exception {
	   
		List<ProductReply> reply = shopService.replyList(product_number);
	 
		return reply;
	} 
	
	//상품 소감(댓글) 삭제(AJAX) 
	@ResponseBody
	@RequestMapping(value="/view/replyDelete", method=RequestMethod.POST)
	public void replyDelete(ProductReply reply,HttpSession session)throws Exception{
		System.out.println("지울 댓글 번호:"+reply.getReply_num());
		System.out.println("지울 참조 부모댓글:"+reply.getOrigin_ref());
		System.out.println("댓글 깊이:"+reply.getGroup_layer());
		shopService.replyDelete(reply);
		
	}
	
	//상품 소감(댓글) 수정(AJAX) 
	@ResponseBody
	@RequestMapping(value="/view/replyModify", method=RequestMethod.POST)
	public void replyModify(ProductReply reply)throws Exception{
		System.out.println("수정할 댓글 번호:"+reply.getReply_num());
		System.out.println("수정할 내용:"+reply.getReply_content());
		shopService.replyModify(reply);
		
	}
	
	
	//상품 소감 답글 작성(AJAX) 
	@ResponseBody
	@RequestMapping(value="/view/reCmtInsert", method=RequestMethod.POST)
	public void reCmtInsert(ProductReply reply)throws Exception{
		System.out.println("부모댓글 번호:"+reply.getOrigin_ref());
		System.out.println("상품번호 내용:"+reply.getProduct_number());
		System.out.println("작성자 이름:"+reply.getReply_writer());
		System.out.println("답글내용:"+reply.getReply_content());
		
		shopService.reCmtInsert(reply);
		
	}
	
	// 카트 담기
	@ResponseBody
	@RequestMapping(value="/addCart", method=RequestMethod.POST)
	public int addCart(CartDto cart,HttpSession session,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		int result = 0;
		//현재세션에 저장되어있는 유저ID 가져오기(로그인된 사용자의 아이디 가져오는것)
		UserDto user = (UserDto)session.getAttribute("User");
		String user_id = null;
		
		//서버에 저장되어있는 모든 세션을 가져오기 
		Enumeration session_values = session.getAttributeNames();
		int i = 0;
		String session_name = "";
		String session_value = "";
		boolean check = false;
		
		//서버의 세션에 저장되어 있는 값들 (키,값 -> map으로 저장시킬까??)
		ArrayList<String> s_list = new ArrayList<String>();
		//nextElement 하면 서버로부터 뽑아올 세션데이터 1씩 줄어들기 떄문에 ... 
		while(session_values.hasMoreElements()) {
			System.out.println("세션값이 존재함..."+session_values.hasMoreElements());
			i++; 
			session_name = session_values.nextElement().toString();
			session_value = session.getAttribute(session_name).toString();
			System.out.println(i+"번째 세션이름->"+session_name);
			System.out.println(i+"번째 세션값->"+session_value);
			s_list.add(session_value);
		}
		
		if(user != null) { //세션이 남아있는경우(로그인시) - 회원인 경우  	
			user_id = user.getUser_id();
			result = 1;
		}else { // 비회원일경우 장바구니에 물품을 담는 경우 
			// 1.사용자 브라우저에 저장되있는 쿠키값을 가져와서 서버의 세션값과 비교 
			Cookie[] getCookie = request.getCookies();
			root:for(int k=0;k<getCookie.length;k++) {
				String value = getCookie[k].getValue(); //쿠키값 
				
				//1-1.사용자 브라우저의 쿠키값과 서버의 세션값이 일치하는 경우(사용자 브라우저의 쿠키값으로 장바구니에 물품을 등록함)
				for(int j=0;j<s_list.size();j++) {
					//브라우저의 쿠키값 과 서버에 저장되어있는 세션값들(1부터...N까지 돌면서 비교)
					System.out.println("서버의 값->"+s_list.get(j));
					if(value.equals(s_list.get(j))){
						user_id = session_value;
						check = true;
						break root;
					}
				}
		
			}//root:END 
			
			//2.사용자브라우저의 쿠키값과 서버의 세션값이 일치하는게 없는경우(비회원식별값을 랜덤으로 만들어서 세션에 저장시킨다음 사용자 브라우저의 쿠키값으로 전달)
			if(check == false) {
				//128비트의 랜덤 UUID 코드생성한 값을 서버의 세션과 브라우저의 쿠키에 저장시킴
				//페이지 이동시 쿠키값이 사라짐 
				 UUID uid = UUID.randomUUID();
				 String guest = uid + "guest";
				 user_id = guest;
				 //쿠키이름은 guest라는 이름으로 부여 
				 System.out.println("생성된 쿠키값->"+guest);				 
				 //비회원 식별값을 세션에 저장시킴
				 session.setAttribute("guest",guest);
				 Cookie setCookie = new Cookie("guest",guest);
				 setCookie.setPath("/"); //쿠키가 유효한 경로 설정(전역 페이지) 
				 setCookie.setMaxAge(60*60*24); //쿠키 유효시간 
				 response.addCookie(setCookie); //쿠키 저장하기 
			}
		}
		
		//장바구니에 물품담기 
		cart.setUser_id(user_id);
		shopService.addCart(cart);
			
		return result;
	}
	
	//장바구니 목록 
	@RequestMapping(value="/cart/cartList", method=RequestMethod.GET)
	public String cartList(HttpSession session,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//1.현재세션에 저장되어있는 유저ID 가져오기 
		UserDto user = null;
		String user_id = null;
		
		//1-1.회원일경우 OR 비회원일 경우로 나눠봐야됨 
		if(session.getAttribute("User") != null) {
			// 회원인 경우 
			user = (UserDto)session.getAttribute("User");
			user_id = user.getUser_id();
			System.out.println("현재 유저ID"+user.getUser_id());			
		}else {
			// 비회원인 경우 
			// 1.사용자 브라우저에 저장되있는 쿠키값을 가져온다 
			Cookie[] getCookie = request.getCookies();
			boolean check = false; //사용자브라우저에 쿠키값이 서버의 세션에 존재하는지 체크함(쿠키값이 서버에 있다면 3번을 실행안시킴)
			
			//2.가져온 쿠키값을 서버(장바구니)에 있는 세션값(유저ID)이랑 비교를 한다 (만약 일치하는게 있다면 그 세션값으로 장바구니 리스트를 출력한다)
			List<CartDto> all_id = shopService.all_id();
			
			//중첩문을 탈출하는 방법(함수로 만들어서 return 해주기 , 라벨 붙이기 , flag 세우기) 
			root:for(int i=0;i<getCookie.length;i++) {
				String name = getCookie[i].getName(); //쿠키이름 
				String value = getCookie[i].getValue(); //쿠키값 
				System.out.println("쿠키이름->"+name);
				System.out.println("쿠키값->"+value);
				for(int j=0;j<all_id.size();j++) {
					System.out.println("유저ID->"+all_id.get(j).getUser_id());
					// 쿠키값  == 장바구니의 세션값 일치하는 경우 
					if(value.equals(all_id.get(j).getUser_id())) {
						System.out.println("쿠키값  == 장바구니의 세션값 일치하는 경우");
						user_id =  all_id.get(j).getUser_id();
						check = true;
						break root;
					}else {
						System.out.println("일치하는 값없음");
					}
				}
			}
			
			//3.사용자 브라우저에 쿠키값이 없을 경우(서버와 일치하는) 
			if(check == false) {
				//128비트의 랜덤 UUID 코드생성한 값을 서버의 세션과 브라우저의 쿠키에 저장시킴
				//페이지 이동시 쿠키값이 사라짐 
				 UUID uid = UUID.randomUUID();
				 String guest = uid + "guest";
				 user_id = guest;
				 //쿠키이름은 guest라는 이름으로 부여 
				 System.out.println("생성된 쿠키값->"+guest);
				 //비회원 식별값을 세션에 저장시킴
				 session.setAttribute("guest",guest);
				 Cookie setCookie = new Cookie("guest",guest);
				 setCookie.setPath("/"); //쿠키가 유효한 경로 설정(전역 페이지) 
				 setCookie.setMaxAge(60*60*24); //쿠키 유효시간 
				 response.addCookie(setCookie); //쿠키 저장하기 
			}
									 
		}

		//2.해당 유저가 장바구니에 담은 리스트 가져오기 
		List<CartDto> cartList = shopService.cartList(user_id);
		
		//3.장바구니의 전체금액 가져오기(회원일 경우에도 장바구니에 물품이 없는경우가 있는데???
		int total = 0;
		try {
			total = shopService.cartTotal(user_id);
		} catch (Exception e) {
			System.out.println("총액 에러 발생!!");
			e.printStackTrace();
		}
		
		//4.장바구니 전체금액에 따른 배송비 구분 ( 5만원 이상이면 무료 미만이면 배송비 2500 추가  )
		int fee = total >= 50000 ? 0 : 2500;
		
		//5.배송비를 포함한 전체금액 
		int total_fee = total + fee;				
			
		//6.key값으로 해당유저가 장바구니에 담은 리스트 넣어주기 
		model.addAttribute("cartList",cartList);	
		//7.장바구니에 담긴 상품의 유무(하나도 없으면 0을 반환 )
		model.addAttribute("cartListCount",cartList.size());
		model.addAttribute("total",total);
		model.addAttribute("fee",fee);
		model.addAttribute("total_fee",total_fee);
		
		return "cart/cartList_1";
	}
	
	//장바구니 물품 삭제 
	@RequestMapping(value="/cart/cartDelete", method=RequestMethod.GET)
	public String cartDelete(@RequestParam int cart_num) {
		System.out.println("삭제할 카트번호 -> " + cart_num);
		shopService.cartDelete(cart_num);
		
		return "redirect:/shop/cart/cartList";
	}
	
	//장바구니 물품 수량 수정 
	@RequestMapping(value="/cart/cartUpdate", method=RequestMethod.GET)
	public String cartUpdate(@RequestParam int cart_num,int cart_stock) {
		System.out.println("수정할 카트번호 -> " + cart_num);
		System.out.println("수정될 물품수량 -> " + cart_stock);
		shopService.cartUpdate(cart_num,cart_stock);
		
		return "redirect:/shop/cart/cartList";
	}
}






























































