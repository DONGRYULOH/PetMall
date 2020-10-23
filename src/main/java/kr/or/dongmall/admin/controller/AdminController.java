package kr.or.dongmall.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;

import kr.or.dongmall.admin.dto.CategoryDto;
import kr.or.dongmall.admin.service.AdminService;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.user.dto.UserDto;
import kr.or.dongmall.utils.UploadFileUtils;
import net.sf.json.JSONArray;


@Controller
@RequestMapping("/admin/*") 
public class AdminController {
	
	@ExceptionHandler(Exception.class)
	public void Ex(Exception e) {
		System.out.println("에러발생"+e.getClass());
		System.out.println("에러발생"+e.getMessage());
	}
	
	
	//세션값을 부여하는 함수(임시로 만듬)
	public UserDto UserSession(Model model,HttpServletRequest req) {
		//session.setAttribute("User",login); 했던게 사라지므로 index페이지에 세션값을 부여할려면 다시 지정해줘야함 
		HttpSession session = req.getSession();
		UserDto name = (UserDto)session.getAttribute("User");	
		System.out.println("세션에 저장되어있는 값"+name);
		return name;
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

		model.addAttribute("User",UserSession(model,req));
				
		return "admin/admin_index";
	}
	
	//상품등록 페이지 이동 
	@RequestMapping(value="/product_register",method=RequestMethod.GET) 
	public String productRegister(Model model,HttpServletRequest req) throws Exception{
		System.out.println("상품등록 페이지 이동");
		
		//카테고리 호출 
		List<CategoryDto> category = adminService.category();
		
		
		model.addAttribute("category",JSONArray.fromObject(category));
		
		model.addAttribute("User",UserSession(model,req));
		
		return "admin/product_register";
	}
	
	//상품등록
	@RequestMapping(value="/product_register",method=RequestMethod.POST) 
	public String productRegisterApply(ProductDto product,MultipartFile file) throws Exception{
		System.out.println("상품등록 하기");
		
		String imgUploadPath = uploadPath + File.separator + "imgUpload";
		String ymdPath = UploadFileUtils.calcPath(imgUploadPath); //업로드될 파일을 년도/월/일 별로 관리할수 있게 지정 
		String fileName = null;
		
		//업로드한 파일이 존재하는 경우 
		if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
			fileName = UploadFileUtils.fileUpload(imgUploadPath,file.getOriginalFilename(),file.getBytes(), ymdPath);
			product.setProduct_image(ymdPath + File.separator + fileName);
			product.setProduct_ThumbImg(ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		}else { //파일을 업로드하지 않은경우
			fileName = null;
			product.setProduct_image(fileName);
			product.setProduct_ThumbImg(fileName);
		}
		
		System.out.println("imgUploadPath" + imgUploadPath);
		System.out.println("ymdPath" + ymdPath);
		System.out.println("fileName" + fileName);

		//상품등록하기 
		adminService.product_insert(product);
		
		return "admin/product_register";
	}

	//상품목록 
	@RequestMapping(value="/product_list",method=RequestMethod.GET) 
	public String goods_list(Model model,HttpServletRequest req) throws Exception{
		System.out.println("상품목록 페이지");
		
		//상품목록 서비스 호출 
		List<ProductDto> ProductList = adminService.product_list();
		
		for(int i=0;i<ProductList.size();i++) {
			System.out.println("썸네일이미지 -> "+ProductList.get(i).getProduct_ThumbImg());
		}
		
		model.addAttribute("ProductList",ProductList);
		model.addAttribute("User",UserSession(model,req));
		
		return "admin/product_list";
	}
	
	//해당 상품(디테일)보기 
	@RequestMapping("/product_detail")
	public String product_detail(@RequestParam("n") int product_number, Model model) throws Exception {
	 
		 ProductCateDto product = adminService.product_detail(product_number);
		 
		 model.addAttribute("product", product);
		 
		 return "admin/product_detail";
	}
	
	//해당 상품수정 페이지 
	@RequestMapping(value="/product_modify",method=RequestMethod.GET) 
	public String product_modify(@RequestParam("n") int product_number,Model model,HttpServletRequest req) throws Exception{
		System.out.println("상품수정 페이지 이동");
		System.out.println("상품 번호"+product_number);
		ProductCateDto product = adminService.product_detail(product_number);
		//카테고리 호출 
		List<CategoryDto> category = adminService.category();
							
		model.addAttribute("category",JSONArray.fromObject(category));
		model.addAttribute("User",UserSession(model,req));
		model.addAttribute("product",product);
		
		return "admin/product_modify";
	}
	
	//해당 상품수정 완료버튼 클릭시 
	@RequestMapping(value="/product_modify",method=RequestMethod.POST) 
	public String product_modify_apply(@ModelAttribute ProductCateDto product,MultipartFile file,Model model,HttpServletRequest req,RedirectAttributes redirectAttributes){
		System.out.println("해당 상품수정중... ");
		//뷰단의 form의 name 값이랑 Dto의 필드명이랑 똑같아야지 자동으로 setter를 통해 삽입될수있음 
		//System.out.println("상품수정 완료 버튼 클릭시 상품번호"+product.getProduct_number());
		//System.out.println("상품카테고리"+product.getProduct_category());
		//System.out.println("상품참조카테고리"+product.getCategory_code_ref());
		
		String url="redirect:/admin/product_modify";
		
		try {
			//새로운 파일을 등록헀다면 다음 구문실행 
		
			if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				
				System.out.println("새로운 파일등록완료 !");
				//기존파일삭제
				new File(uploadPath + req.getParameter("product_image")).delete();
				new File(uploadPath + req.getParameter("product_ThumbImg")).delete();
				
				//새로첨부한 파일등록
				String imgUploadPath = uploadPath + File.separator + "imgUpload";
				String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
				String fileName = UploadFileUtils.fileUpload(imgUploadPath,file.getOriginalFilename(),file.getBytes(), ymdPath);
				
				product.setProduct_image(ymdPath + File.separator + fileName);
				product.setProduct_ThumbImg(ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
			}else { //새로운 파일이 등록되지 않았을시
				//기존이미지를 그대로 사용함 
				product.setProduct_image(req.getParameter("product_image"));
				product.setProduct_ThumbImg(req.getParameter("product_ThumbImg"));
			}

			
			url = adminService.product_update(product);
			System.out.println("이동URL 주소"+url);
			System.out.println("상품수정 완료 버튼 클릭시 상품번호"+product.getProduct_number());
			redirectAttributes.addAttribute("n",product.getProduct_number());
		} catch (Exception e) {
			System.out.println("해당상품 Update 문제발생..."+e.getMessage());
		}
							
		model.addAttribute("User",UserSession(model,req));
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





























































