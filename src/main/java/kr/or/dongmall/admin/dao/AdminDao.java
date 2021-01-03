package kr.or.dongmall.admin.dao;

import java.util.List;
import java.util.Map;

import kr.or.dongmall.admin.dto.CategoryDto;
import kr.or.dongmall.admin.dto.Product_ImageFile;
import kr.or.dongmall.main.dto.ProductCateDto;
import kr.or.dongmall.main.dto.ProductDto;
import kr.or.dongmall.shop.dto.NonuserRefundDto;
import kr.or.dongmall.shop.dto.OrderRefundDto;



public interface AdminDao {

	//카테고리
	public List<CategoryDto> category() throws Exception;
	//상품등록
	public void product_insert(Map<String,Object> productMap) throws Exception; 
	//상품목록
	public List<ProductDto> product_list() throws Exception;
	//해당 상품(디테일)
	public ProductCateDto product_detail(int product_number) throws Exception;
	//해당 상품수정 
	public int product_update(ProductCateDto product) throws Exception;
	//상품삭제
	public int product_delete(int product_number) throws Exception;
	//해당 상품번호에 해당되는 이미지 파일을 모두 삭제함(delete_check -> Y 바꿈)
	public void deleteFileList(Map<String, Object> productMap)throws Exception;
	//상품이미지 파일등록 
	public int insertFile(Map<String, Object> tempMap);
	//해당 상품에 해당되는 이미지파일 가져오기 
	public List<Product_ImageFile> product_img(int product_number);
	//상품이미지 파일의 변경 
	public int updateFile(Map<String, Object> tempMap);
	//환불 요청 내역리스트를 가져옴 
	public List<OrderRefundDto> RefundList();
	//환불 번호에 해당되는 환불정보 가져오기 
	public OrderRefundDto getRefundInfo(String refund_number);
	//해당 환불 주문정보 업데이트 처리 
	public void RefundInfoUpdate(String merchant_uid);
	//비회원 환불 요청 내역 가져오기 
	public List<NonuserRefundDto> nonUserRefundList();
	//비회원 환불번호에 해당하는 환불정보 가져오기 
	public NonuserRefundDto nonUserRefundProcess(String refund_number);
	//비회원 환불처리
	public void nonUserRefundProcessOk(String merchant_uid);
}
