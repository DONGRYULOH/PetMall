package kr.or.dongmall.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;

import kr.or.dongmall.admin.dto.CategoryDto;
import kr.or.dongmall.admin.dto.Product_ImageFile;
import kr.or.dongmall.admin.service.AdminService;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.user.dto.UserDto;
import kr.or.dongmall.utils.UploadFileUtils;
import net.sf.json.JSONArray;


@Controller
@RequestMapping("/admin/*") 
public class AdminController {
	
	//AdminController에 한해서 로그설정하기 
	protected Log log = LogFactory.getLog(AdminController.class);
	
	@ExceptionHandler(Exception.class)
	public void Ex(Exception e) {
		System.out.println("에러발생"+e.getClass());
		System.out.println("에러발생"+e.getMessage());
	}	
	
	@Inject
	AdminService adminService;
	
	//servlet-context.xml에서 설정했던 uploadPath를 가져옴 
	//@Resource 어노테이션은 빈의 이름을 이용해서 주입할 객체를 검색한다.
	@Resource(name="uploadPath")
	private String uploadPath;
	
	//관리자화면이동
	@RequestMapping(value="/index",method=RequestMethod.GET) 
	public String adminIndex(Model model,HttpServletRequest req) throws Exception{
		System.out.println("관리자 화면");

				
		return "admin/admin_index";
	}
	
	//상품등록 페이지 이동 
	@RequestMapping(value="/product_register",method=RequestMethod.GET) 
	public String productRegister(Model model,HttpServletRequest req) throws Exception{
		System.out.println("상품등록 페이지 이동");
		
		//카테고리 호출 
		List<CategoryDto> category = adminService.category();
		
		//JSON 라이브러리를 사용해 JSON 형태로 변경시킴 
		model.addAttribute("category",JSONArray.fromObject(category));
		
		
		return "admin/product_register";
	}
	
	//상품등록(상품등록후 상품이 등록된 페이지로 이동할것인가? 아니면 계속해서 상품을 등록할수 있게 상품등록페이지로 이동할것인가?)
	@RequestMapping(value="/product_register",method=RequestMethod.POST) 
	public String productRegisterApply(ProductDto product,MultipartFile file,HttpServletRequest request) throws Exception{
		System.out.println("상품등록중...");
		
		//대표썸네일 체크여부 검사 로직  (체크박스에 체크하지 않으면 NULL로 해당값을 가져오지 못함)
		String[] checkList =  request.getParameterValues("checkList");
		for(int i=0;i<checkList.length;i++) {
			System.out.println(i+"번째 이미지 대표 썸네일 체크여부:"+checkList[i]);
		}
		
		//HttpServletRequest 이용해 전송된(업로드한) 파일을 가져온다 (업로드한 파일 로그로 확인하기) 
		MultipartHttpServletRequest MultipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = MultipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		while(iterator.hasNext()) {
			multipartFile = MultipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				
				 log.debug("-----------file start--------------");
				 log.debug("name :"+multipartFile.getName());
				 log.debug("filename"+multipartFile.getOriginalFilename());
				 log.debug("size:"+multipartFile.getSize());
				 log.debug("-----------file start--------------");
				
			}
		}
		
		//상품등록하기 
		adminService.product_insert(product,MultipartHttpServletRequest,checkList);

		
		return "redirect:/admin/product_register";
	}

	//상품목록 
	@RequestMapping(value="/product_list",method=RequestMethod.GET) 
	public String goods_list(Model model,HttpServletRequest req) throws Exception{
		System.out.println("상품목록 페이지");
		
		//상품목록 서비스 호출 
		List<ProductDto> ProductList = adminService.product_list();
		
		for(int i=0;i<ProductList.size();i++) {
			//System.out.println("썸네일이미지 -> "+ProductList.get(i).getProduct_ThumbImg());
		}
		
		model.addAttribute("ProductList",ProductList);
	
		return "admin/product_list";
	}
	
	//해당 상품(디테일)보기 
	@RequestMapping("/product_detail")
	public String product_detail(@RequestParam("n") int product_number, Model model) throws Exception {
		
		 //해당 상품목록 가져오기(이미지 제외) 
		 ProductCateDto product = adminService.product_detail(product_number);
		 model.addAttribute("product", product);
		 
		 //해당 상품에 해당되는 이미지파일(썸네일) 가져오기 
		 List<Product_ImageFile> thumbImg = adminService.product_img(product_number);
		 for(int i=0;i<thumbImg.size();i++) {
			 System.out.println(i+"번쨰 이미지 파일"+thumbImg.get(i));
		 }
		 model.addAttribute("ThumbImg", thumbImg);
		 
		 return "admin/product_detail";
	}
	
	//해당 상품수정 페이지 이동 
	@RequestMapping(value="/product_modify",method=RequestMethod.GET) 
	public String product_modify(@RequestParam("n") int product_number,Model model,HttpServletRequest req) throws Exception{
		System.out.println("상품수정 페이지 이동");
		System.out.println("상품 번호"+product_number);
		
		//해당 상품의 세부목록가져오기 
		ProductCateDto product = adminService.product_detail(product_number);
		model.addAttribute("product",product);
		
		//카테고리 호출 
		List<CategoryDto> category = adminService.category();
		model.addAttribute("category",JSONArray.fromObject(category));
		
		//해당 상품에 해당되는 이미지파일(썸네일) 가져오기 
		 List<Product_ImageFile> thumbImg = adminService.product_img(product_number);
		 for(int i=0;i<thumbImg.size();i++) {
			 System.out.println(i+"번쨰 이미지 파일"+thumbImg.get(i));
		 }
		 model.addAttribute("ThumbImg", thumbImg);
		 
		return "admin/product_modify";
	}
	
	//해당 상품수정 완료버튼 클릭시 
	@RequestMapping(value="/product_modify",method=RequestMethod.POST) 
	public String product_modify_apply(@ModelAttribute ProductCateDto product,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes){
		System.out.println("해당 상품수정중... ");
		//뷰단의 form의 name 값이랑 Dto의 필드명이랑 똑같아야지 자동으로 setter를 통해 삽입될수있음 

		String url="redirect:/admin/product_modify";
		
		//대표썸네일 체크여부 검사 로직  (체크박스에 체크하지 않으면 NULL로 해당값을 가져오지 못함)
		String[] checkList =  request.getParameterValues("checkList");
		for(int i=0;i<checkList.length;i++) {
			System.out.println(i+"번째 이미지 대표 썸네일 체크여부:"+checkList[i]);
		}
				
		try {
			url = adminService.product_update(product,request,checkList);
			System.out.println("이동URL 주소"+url);
			System.out.println("상품수정 완료 버튼 클릭시 상품번호"+product.getProduct_number());
			redirectAttributes.addAttribute("n",product.getProduct_number());
		} catch (Exception e) {
			System.out.println("해당상품 Update 문제발생..."+e.getMessage());
		}
							
		model.addAttribute("product",product);
		
		return url;
	}
	
	//해당 상품삭제 
	@RequestMapping(value="/product_delete",method=RequestMethod.POST) 
	public String product_delete(@RequestParam("n") int product_number) throws Exception{
		System.out.println("상품삭제중...");
	
		adminService.product_delete(product_number);
		
		return "redirect:/admin/product_list";
	}
	
	//CK 에디터에서 업로드한 파일 
	@RequestMapping(value="/ckUpload",method=RequestMethod.POST)
	@ResponseBody
	public void ckEditorUpload(HttpServletRequest req,HttpServletResponse res,@RequestParam MultipartFile upload)throws Exception{
		// 랜덤 문자 생성
		 UUID uid = UUID.randomUUID();
		 
		 OutputStream out = null;
		 PrintWriter printWriter = null;
		 JsonObject json = new JsonObject();
		 
		 // 인코딩
		 res.setCharacterEncoding("utf-8");
		 res.setContentType("text/html;charset=utf-8");
		 
		 try {
			  
			  String fileName = upload.getOriginalFilename();  // 파일 이름 가져오기
			  byte[] bytes = upload.getBytes();
			  
			  // 업로드 경로
			  String ckUploadPath = uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
			  
			  out = new FileOutputStream(new File(ckUploadPath));
			  out.write(bytes);
			  out.flush();  // out에 저장된 데이터를 전송하고 초기화
			  
			  String callback = req.getParameter("CKEditorFuncNum");
			  //responese 서버측에서 클라이언트로 정보를 보내고자 할때 그역활을 담당하는 객체...
			  printWriter = res.getWriter();
			  // CK에디터 파일 업로드후 서버로 전송시 보여질 URL주소 
			  String fileUrl = req.getContextPath() + "/ckUpload/" + uid + "_" + fileName; 
			  
			  System.out.println("callback"+callback);
			  System.out.println("fileUrl"+fileUrl);
			  
			  // json 데이터로 등록
              // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
              // 이런 형태로 리턴이 나가야함.
              json.addProperty("uploaded", 1);
              json.addProperty("fileName", fileName);
              json.addProperty("url", fileUrl);
			  
			  // 업로드시 메시지 출력
			  printWriter.println(json);
			  
			  printWriter.flush();
			  
			 } catch (IOException e) { e.printStackTrace();
			 } finally {
			  try {
			   if(out != null) { out.close(); }
			   if(printWriter != null) { printWriter.close(); }
			  } catch(IOException e) { e.printStackTrace(); }
			 }
			 
	}
	
}





























































