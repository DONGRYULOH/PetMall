package kr.or.dongmall.shop.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
	public int addCart(CartDto cart,HttpSession session)throws Exception{
		
		int result = 0;
		//현재세션에 저장되어있는 유저ID 가져오기 
		UserDto user = (UserDto)session.getAttribute("User");
		
		if(user != null) { //세션이 남아있는경우(로그인시) 
			cart.setUser_id(user.getUser_id());
			shopService.addCart(cart);
			result = 1;
		}
			
		return result;
	}
}





























































