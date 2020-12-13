package kr.or.dongmall.main.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.dongmall.admin.dto.Product_Join_ProductImageFile;
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
		
		//조회수가 가장 높은 상품순으로 상위9개 가져오기
		List<Product_Join_ProductImageFile> productRankNine = productService.getRankNine();
		model.addAttribute("productRankNine",productRankNine);
		/*
		Cookie[] getCookie = req.getCookies();
		for(int i=0;i<getCookie.length;i++) {
			String value = getCookie[i].getValue(); //쿠키값 
			System.out.println("홈에서의 쿠키값->"+value);
		}
		*/
		
		return "index";
	}
	
}
