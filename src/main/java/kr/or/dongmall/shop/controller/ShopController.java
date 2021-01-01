package kr.or.dongmall.shop.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import kr.or.dongmall.admin.dto.Product_ImageFile;
import kr.or.dongmall.admin.dto.Product_Join_ProductImageFile;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.shop.dto.CartDto;
import kr.or.dongmall.shop.dto.OrderDetailDto;
import kr.or.dongmall.shop.dto.OrderDto;
import kr.or.dongmall.shop.dto.ProductReply;
import kr.or.dongmall.shop.service.ShopService;
import kr.or.dongmall.user.dto.UserAddressDto;
import kr.or.dongmall.user.dto.UserDto;
import kr.or.dongmall.utils.CartUtils;
import kr.or.dongmall.utils.ImportUtils;
import kr.or.dongmall.utils.OrderUtils;

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
		
		List<Product_Join_ProductImageFile> list = null;
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
		
		//전체 리스트 사이즈가 0,1,2,3일때는 사이즈가 마이너스가 되버림 이떄는 사이즈가 0이되야됨
		int size = 0;
		if(list.size()>3) {
			size = list.size()/2-1; // 전체사이즈(8)/2 -> 반으로 나눔(몫:4) -> 몫(4)-1 (인덱스가 0부터 시작하므로 -1를뺴주자)
		}
		System.out.println("사이즈는?"+size);
		model.addAttribute("cateNum",categoryNumber); // 카테고리 번호 (전체,상의,하의,신발,시계 중하나) 
		model.addAttribute("cateSize",size);	// 카테고리 목록 사이즈(해당카테고리 물품이 총몇개있는지)
		model.addAttribute("cateProductList",list);
		return "shop/list";
	}
	
	//해당상품 조회 
	/*
	 	<상품를 계속해서 무한새로고침 또는 조회시 계속해서 조회수가 올라가는걸 막기위해 쿠키를 사용한 조회수 방지방법을 사용해본다>
	 	해당 게시물(상품)클릭시 조회수 올리기 방지 방법 => 쿠키사용 (세션 쿠키를 사용)
	 	@CookieValue => 사용자브라우저에 저장되어있는 쿠키를 읽기만 가능
	 	
	 	쿠키 이외의 다른 방식) 
	 	1.세션은 쿠키보다 보안이 좋지만 세션을 사용시 서버의 자원을 낭비하므로 보안이 중요하지 않은데이터는 쿠키를 사용하는것이 좋다고 판단함 
	 	2.상품조회시 해당 IP를 테이블에 저장하는 방식으로 DB에 저장(IP,조회날짜)시키는 방식은 사용자수가 많아지면 DB의 용량이 커지고 많은 자원을 사용하게 된다고 판단함 
	 	
	 	<쿠키 라이프 타임>
	 	-세션 쿠키 : 브라우저를 닫을때 쿠키가 삭제됨(세션쿠키가 없어지는 시점)
	 	-지속적 쿠키 : 쿠키의 정해진 시간에 따라 쿠키가 삭제됨 
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String shopDetail(HttpServletResponse response,HttpServletRequest request,@CookieValue(value = "viewProductCookie",defaultValue="null") String viewProduct,@RequestParam("n") int product_number,Model model)throws Exception{
		
		Cookie[] cookies = request.getCookies(); //사용자 브라우저에 저장되있는 모든 쿠키를 가져와서 배열에 넣음 
		Cookie viewProductCookie = null; //해당게시물의 다중접근 여부 
		
		for(int i=0;i<cookies.length;i++) {
			//사용자 클라이언트 브라우저에 해당이름으로된 쿠키가 존재하는경우 
			if(cookies[i].getName().equals("viewProductCookie")) {
				viewProductCookie = cookies[i];
				System.out.println("cookies 는??"+cookies[i]);
			}
		}
		
		String getViewCookie = null; //조회수를 방지하는 쿠키의 값 
		int check = 0;
		
		//상품조회시 조회수를 방지하는 쿠키가 없을경우
		if(viewProductCookie == null) {
			System.out.println("조회수 1증가 가능!");
			//조회수 1증가시키기 
			shopService.productViewCount(product_number);
			//세션 쿠키 생성 (key - 쿠키이름 , value - 쿠키값(상품번호) ) 
			Cookie newCookie = new Cookie("viewProductCookie","|"+product_number);
			response.addCookie(newCookie);
		}else { //조회수를 방지하는 쿠키가 있을경우 
			System.out.println("viewProductCookie 존재!");
			getViewCookie = viewProductCookie.getValue(); //조회수를 방지하는 쿠키의 값을 가져옴 
			System.out.println("가져온 쿠키값 -->"+getViewCookie); // ex) |6|7|5|9|10|11|12
			
			//가져온 쿠키값이 해당게시글번호와 일치여부 판단 
			//ex) 쿠키값 (|6|7|5|9|10|11|12) <-> 현재 보고있는 상품번호(6번) => 쿠키값에 6번이 존재하므로 이미 조회수가 1증가되었음  더이상 조회수 증가X
			//ex) 쿠키값 (|7|5|9|10|11|12) <-> 현재 보고있는 상품번호(6번) => 쿠키값에 6번이 존재하지 않으므로 조회수 증가시킴  
			if(getViewCookie.indexOf("|"+product_number) == -1) { // 값.indexOf("찾을특정문자",시작할위치(생략가능 생략시 처음부터찾음))
				System.out.println("조회수 1증가 가능!");
				//해당 상품의 조회수를 1증가 시키고 쿠키값을 재설정 해줌 
				shopService.productViewCount(product_number);
				viewProductCookie.setValue(getViewCookie + "|" + product_number);
				response.addCookie(viewProductCookie);
				check = 1;
			}
			
			if(check != 1)
				System.out.println("해당 상품에 대한 조회수가 이미 증가되었음!! 조회수 증가X");
		}
		
		//상품이미지 제외한 상품세부사항만 가져옴 
		ProductDto product = shopService.shopDetail(product_number);
		model.addAttribute("product", product);
		
		//해당 상품번호의 모든 이미지를 가져옴 
		List<Product_ImageFile> product_image = shopService.shopDetailImg(product_number);
		model.addAttribute("product_image",product_image);
		
		//해당 상품의 대표이미지만 가져옴 
		Product_ImageFile delegate_image = shopService.shop_delegate_image(product_number);
		model.addAttribute("delegate_image",delegate_image);
		
		return "shop/detail";
	}
	
	//상품조회시 소감(댓글)작성  AJAX
	@ResponseBody
	@RequestMapping(value="/view/replyInsert", method=RequestMethod.POST)
	public void replyInsert(ProductReply reply,HttpSession session)throws Exception{
		
		//현재세션에 저장되어있는 유저닉네임 가져오기 
		UserDto user = (UserDto)session.getAttribute("User");
		reply.setWriter_nickname(user.getUser_nickname());
		
		shopService.replyInsert(reply);
		
	}
	
	//상품 소감(댓글) 목록(AJAX) 
	@ResponseBody // 자바객체를 JSON 형태로 변환을 해서 클라이언트단에 응답하는 역할 
	@RequestMapping(value = "/view/replyList",method=RequestMethod.GET)
	public List<ProductReply> getReplyList(@RequestParam("n") int product_number) throws Exception {
	   
		List<ProductReply> reply = shopService.replyList(product_number);
	 
		return reply;
	} 
	
	//상품 소감(댓글) 삭제(AJAX) 
	@ResponseBody
	@RequestMapping(value="/view/replyDelete", method=RequestMethod.POST)
	public void replyDelete(ProductReply reply,HttpSession session)throws Exception{
//		System.out.println("지울 댓글 번호:"+reply.getReply_num());
//		System.out.println("지울 참조 부모댓글:"+reply.getOrigin_ref());
//		System.out.println("댓글 깊이:"+reply.getGroup_layer());
		shopService.replyDelete(reply);
		
	}
	
	//상품 소감(댓글) 수정(AJAX) 
	@ResponseBody
	@RequestMapping(value="/view/replyModify", method=RequestMethod.POST)
	public void replyModify(ProductReply reply)throws Exception{
		shopService.replyModify(reply);	
	}
	
	
	//상품 소감 답글 작성(AJAX) 
	@ResponseBody
	@RequestMapping(value="/view/reCmtInsert", method=RequestMethod.POST)
	public void reCmtInsert(ProductReply reply)throws Exception{
		shopService.reCmtInsert(reply);
	}
	
	
	
	// 카트 담기
	@ResponseBody
	@RequestMapping(value="/addCart", method=RequestMethod.POST)
	public int addCart(CartDto cart,HttpSession session,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
		int result = 0; // 카트담기 성공/실패 여부 (1이면 성공 0이면 실패) 
		
		//현재세션에 저장되어있는 유저ID 가져오기(로그인된 사용자의 아이디 가져오는것) 
		UserDto user = (UserDto)session.getAttribute("User");
		
		//<카트(장바구니)를 담을때 회원 OR 비회원 여부 파악하기> 
		CartUtils cartUtils = new CartUtils();
		String user_id = cartUtils.nowCartDivide(user, session, request, response);
		
		//장바구니에 물품담기 
		cart.setUser_id(user_id); //회원일 경우 회원ID를 비회원일 경우 비회원고유식별값(쿠키)를 셋팅시킴 
		result = shopService.addCart(cart); //result 값이 1일경우 카트담기 성공 0일경우 실패 
		
		return result;
	}
	
	//장바구니 목록 
	@RequestMapping(value="/cart/cartList", method=RequestMethod.GET)
	public String cartList(HttpSession session,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//1.현재세션에 저장되어있는 유저ID 가져오기 
		UserDto user = (UserDto)session.getAttribute("User");
		
		//<장바구니 목록을 출력할때 회원 OR 비회원 여부 파악>
		CartUtils cartUtils = new CartUtils();
		String user_id = cartUtils.nowCartDivide(user, session, request, response);

		//2.해당 유저가 장바구니에 담은 리스트 가져오기 
		List<CartDto> cartList = shopService.cartList(user_id);
		
		//3.장바구니의 전체금액 가져오기
		int total = 0;
		for(int i=0;i<cartList.size();i++) {
			total += cartList.get(i).getProduct_count() * cartList.get(i).getProduct_price(); // 수량 * 상품가격 
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
	public String cartDelete(@RequestParam int cart_number) {
		System.out.println("삭제할 카트번호 -> " + cart_number);
		shopService.cartDelete(cart_number);
		
		return "redirect:/shop/cart/cartList";
	}
	
	//장바구니 체크박스로 선택한 물품들 삭제 
	@ResponseBody
	@RequestMapping(value="/cart/selectDelete", method=RequestMethod.POST)
	public int selectDelete(@RequestParam(value="DeleteCartList[]") Integer[] DeleteCartList) {
		
		for(Integer list : DeleteCartList) {
			System.out.println("삭제할 상품번호"+list);
		}
		int result = shopService.cartselectDelete(DeleteCartList);
		
		return result;
	}
	
	//장바구니 물품 수량 수정 
	@RequestMapping(value="/cart/cartUpdate", method=RequestMethod.GET)
	public String cartUpdate(@RequestParam int cart_number,@RequestParam int product_count) {
		System.out.println("수정할 카트번호 -> " + cart_number);
		System.out.println("수정될 물품수량 -> " + product_count);
		shopService.cartUpdate(cart_number,product_count);
		
		return "redirect:/shop/cart/cartList";
	}
	
	// *********************** 주문 관련 컨트롤러 ****************************
	
	//주문서(주문/결제) 작성 페이지 이동(장바구니에서 주문하기를 클릭했을 경우) 
	@RequestMapping(value="/order_page_a", method=RequestMethod.GET)
	public String orderPage_A(HttpSession session,Model model) {
		
		UserDto user = (UserDto)session.getAttribute("User");
		
		//1.회원의 배송지 정보 가져오기 
		UserAddressDto user_address = shopService.getUserAddress(user.getUser_id());
		model.addAttribute("user_address",user_address);
		
		//2.회원의 정보(이름,전화번호,이메일) 가져오기 
		UserDto userInfo = shopService.getUserInfo(user.getUser_id());
		model.addAttribute("userInfo",userInfo);
		
		//3.장바구니에 넣은 상품정보 가져오기 
		List<CartDto> cartList = shopService.cartList(user.getUser_id());
		model.addAttribute("cartList",cartList);
		
		//4.장바구니의 전체금액 가져오기
		int total = 0;
		for(int i=0;i<cartList.size();i++) {
			total += cartList.get(i).getProduct_count() * cartList.get(i).getProduct_price(); // 수량 * 상품가격 
		}
		//5.장바구니 전체금액에 따른 배송비 구분 ( 5만원 이상이면 무료 미만이면 배송비 2500 추가  )
		int fee = total >= 50000 ? 0 : 2500;
		//6.배송비를 포함한 전체금액 
		int total_fee = total + fee;
		
		model.addAttribute("total",total);
		model.addAttribute("fee",fee);
		model.addAttribute("total_fee",total_fee);
		
		return "shop/order_page";
	}
	
	//주문서(주문/결제) 작성 페이지 이동(해당 상품을 주문하기를 클릭했을 경우) 
	@RequestMapping(value="/order_page_b", method=RequestMethod.POST)
	public String orderPage_B(ProductDto product,HttpSession session,Model model,HttpServletRequest request) {
		
		UserDto user = (UserDto)session.getAttribute("User");
		
		//1.회원의 배송지 정보 가져오기 
		UserAddressDto user_address = shopService.getUserAddress(user.getUser_id());
		model.addAttribute("user_address",user_address);
		
		//2.회원의 정보(이름,전화번호,이메일) 가져오기 
		UserDto userInfo = shopService.getUserInfo(user.getUser_id());
		model.addAttribute("userInfo",userInfo);
		
		//3.해당상품 정보가져오기
		int product_count = Integer.parseInt(product.getProduct_count()); //문자열 -> 정수형으로 변환 
		model.addAttribute("product_count",product_count); //선택한 상품개수
		ProductDto productInfo = shopService.getProductInfo(product.getProduct_number());
		model.addAttribute("productInfo",productInfo);

		//4.전체금액/배송비/배송비를 포함한 전체금액 
		int total = productInfo.getProduct_price() * product_count; //상품의 총금액 
		int fee = total >= 50000 ? 0 : 2500; //배송비 
		int total_fee = total + fee; // 배송비 + 총금액 (전체금액)
		model.addAttribute("total",total);
		model.addAttribute("fee",fee);
		model.addAttribute("total_fee",total_fee);
		
		return "shop/order_page";
	}
	
	//결제완료시 주문정보 테이블에 INSERT 
	@ResponseBody
	@RequestMapping(value="/orderInfoInsert", method=RequestMethod.POST)
	public int orderInfoInsert(OrderDto orderInfo,ArrayList<OrderDetailDto> orderDetailInfo,@RequestBody String json,HttpSession session) throws Exception {

		UserDto user = (UserDto)session.getAttribute("User");
		System.out.println("프론트 단에서 얻어온 json 데이터 -> "+json);		
		
		// JSON 형식으로 된 결제,주문정보 값 추출해서 해당 변수에 설정하기  
		OrderUtils orderUtils = new OrderUtils();
		orderUtils.orderInfoExtract(orderInfo, orderDetailInfo, json, user);
		
		//주문한 상품들과 주문정보(배송지,회원정보)를 서비스단에서 처리함 
		int result = shopService.insertOrderInfo(orderInfo,orderDetailInfo);
		
		return result;
	}
	
	//결제완료 페이지로 이동 
	@RequestMapping(value="/paymentOk", method=RequestMethod.GET)
	public String paymentOk(@RequestParam String order_number,Model model) {
		
		System.out.println("주문번호 ->"+order_number);
		//결제한 회원의 정보 가져오기(배송지,주문번호,회원정보)
		OrderDto orderInfo =  shopService.getOrderInfo(order_number);
		model.addAttribute("orderInfo", orderInfo);
		
		//결제한 상품정보 가져오기(상품명,상품이미지,주문금액)
		
		return "shop/paymentOkPage";
	}
	
}






























































