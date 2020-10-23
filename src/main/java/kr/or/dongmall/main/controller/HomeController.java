package kr.or.dongmall.main.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.main.service.ProductService;
import kr.or.dongmall.user.dto.UserDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	ProductService productService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model,HttpServletRequest req) throws Exception {
		System.out.println("인덱스 페이지 ");
		
		//List<ProductDto> productList = productService.getProductList();
		//model.addAttribute("productList",productList);
		
		//session.setAttribute("User",login); 했던게 사라지므로 index페이지에 세션값을 부여할려면 다시 지정해줘야함 
		HttpSession session = req.getSession();
		UserDto name = (UserDto)session.getAttribute("User");	
		
		model.addAttribute("User",name);
		
		return "index";
	}
	
}
