package kr.or.dongmall.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import kr.or.dongmall.admin.dao.AdminDao;
import kr.or.dongmall.admin.dto.CategoryDto;
import kr.or.dongmall.admin.dto.Product_ImageFile;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.shop.dto.OrderRefundDto;
import kr.or.dongmall.utils.FileUtils;

@Service
public class AdminService {
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Inject
	SqlSession sqlSession;
	
	//카테고리 종류 
	public List<CategoryDto> category() throws Exception{
		
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		List<CategoryDto> list = admindao.category();
		
		for(int i=0;i<list.size();i++) {
			System.out.print("카테고리 이름"+list.get(i).getCategory_name());
			System.out.println("카테고리 레벨"+list.get(i).getLevel());
		}
		
		
		return admindao.category();
	}
	
	//상품등록
	public void product_insert(ProductDto product,HttpServletRequest request,String[] checkList) {
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		
		Map<String,Object> productMap = new HashMap<String,Object>();
		productMap.put("product_number", product.getProduct_number()); //상품번호 
		productMap.put("category_code", product.getCategory_code()); //상품코드 
		productMap.put("product_name", product.getProduct_name()); //상품명 
		productMap.put("product_price", product.getProduct_price()); //상품가격 
		productMap.put("product_stock", product.getProduct_stock()); //상품재고량 
		productMap.put("product_desc", product.getProduct_desc()); //상품설명 
		
		//상품등록(상품이미지 파일 제외) 
		try {
			admindao.product_insert(productMap); //상품등록이 되고난후 상품번호를 product_number(key)의 Value에 넣어줌 
			System.out.println("등록된 상품번호는??"+productMap.get("product_number"));
		} catch (Exception e) {
			System.out.println("상품등록 도중 예외발생..."+e.getMessage());
			e.printStackTrace();
		}
		
		// 업로드한 상품이미지 파일을 서버와 DB에 저장 	
		try {
			Map<String,Object> tempMap = null;
			
			List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(productMap, request,checkList);
			//업로드한 이미지 첨부파일을 구분하는 작업을 함( 신규일경우(IS_NEW==Y) DB에 등록을함 , 기존에 저장될 파일인 경우(IS_NEW==N)  
			for(int i=0;i<list.size();i++) {
				tempMap = list.get(i);
				if(tempMap.get("IS_NEW").equals("Y")) { // 신규로 업로드한 파일이거나 기존의 파일을 새로운 파일로 대체해서 업로드한 경우
					admindao.insertFile(tempMap);
				}
			}
		} catch (Exception e) {
			System.out.println("상품이미지 등록 도중 예외발생..."+e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	//상품목록 서비스 
	public List<ProductDto> product_list() throws Exception{
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		
		return admindao.product_list(); 
	} 
	
	//해당상품보기 
	public ProductCateDto product_detail(int product_number) throws Exception {
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		
		return admindao.product_detail(product_number); 
	}
	
	//해당상품 수정 서비스 (상품 목록 + 상품이미지) 
	public String product_update(ProductCateDto product,HttpServletRequest request,String[] checkList){
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		
		Map<String,Object> productMap = new HashMap<String,Object>();
		String product_number = Integer.toString(product.getProduct_number());
		productMap.put("product_number", product_number); //상품번호 
		
		//상품이미지 파일을 제외한 상품수정 
		int result = 0;
		try {
			result = admindao.product_update(product);
		} catch (Exception e1) {
			System.out.println("상품이미지 파일을 제외한 상품수정 도중 예외발생..."+e1.getMessage());
			e1.printStackTrace();
		}
		
		//기존의 첨부파일을 수정하거나 신규로 등록한 첨부파일만 서버의 파일저장소에 저장함 (상품이미지 파일의 수정) 
		try {
			Map<String,Object> tempMap = null;
			admindao.deleteFileList(productMap); // 해당 상품번호에 해당되는 이미지 파일을 모두 삭제함(delete_check -> Y 바꿈)
			
			List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(productMap, request,checkList);
			
			//업로드한 이미지 첨부파일을 구분하는 작업을 함( 신규일경우(IS_NEW==Y) DB에 등록을함 , 기존에 저장될 파일인 경우(IS_NEW==N)  
			for(int i=0;i<list.size();i++) {
				result = 0;
				tempMap = list.get(i);
				if(tempMap.get("IS_NEW").equals("Y")) { // 신규로 업로드한 파일이거나 기존의 파일을 새로운 파일로 대체해서 업로드한 경우
					result = admindao.insertFile(tempMap);
				}else{  // 기존에 저장된 파일인 경우 삭제했던 delete_check(Y) -> delete_check(N) 바꿈 
					result = admindao.updateFile(tempMap);
				}
			}
		} catch (Exception e) {
			System.out.println("상품이미지 등록 도중 예외발생..."+e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("결과값 ?? "+result);
		if(result == 1) {
			System.out.println("상품 수정성공");
			return "redirect:/admin/product_detail";
		}else {
			System.out.println("상품 수정실패");
			return "redirect:/admin/product_modify";
		}
	}
	
	//상품삭제(해당 상품을 삭제하면 해당 상품에 속한 이미지파일도 모두삭제처리를함 서버에 있는 이미지파일도 지울라면 시간이 오래걸리므로 DB에 있는 파일만 지우자) 
	public void product_delete(int product_number) throws Exception{
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		
		int result = admindao.product_delete(product_number);
		if(result == 1) {
			System.out.println("상품 삭제성공");
		}else {
			System.out.println("상품 삭제실패");
		}
	}
	
	//해당 상품에 해당되는 이미지 파일 가져오기
	public List<Product_ImageFile> product_img(int product_number) {
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		return admindao.product_img(product_number);
	}

	//환불 요청 내역리스트를 가져옴 
	public List<OrderRefundDto> RefundList() {
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		return admindao.RefundList();
	}

	//환불 번호에 해당되는 환불정보 가져오기 
	public OrderRefundDto getRefundInfo(String refund_number) {
		AdminDao admindao = sqlSession.getMapper(AdminDao.class);
		return admindao.getRefundInfo(refund_number);
	}
	
}
























