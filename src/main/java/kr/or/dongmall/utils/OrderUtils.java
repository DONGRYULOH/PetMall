package kr.or.dongmall.utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kr.or.dongmall.shop.dto.NonuserOrderDetailDto;
import kr.or.dongmall.shop.dto.NonuserOrderDto;
import kr.or.dongmall.shop.dto.OrderDetailDto;
import kr.or.dongmall.shop.dto.OrderDto;
import kr.or.dongmall.user.dto.UserDto;

public class OrderUtils {

	
	//JSON 형식으로 된 결제,주문정보 값 추출해서 orderInfo,orderDetailInfo 에 각각 값들을 넣어주는 함수(회원인경우) 
	public ArrayList<OrderDetailDto> orderInfoExtract(OrderDto orderInfo,ArrayList<OrderDetailDto> orderDetailInfo,String json,UserDto user) throws Exception{
		
		
			try {
			// JSONParser 라이브러리를 사용해 프론트단에서 Ajax로 JSON.stringify()를 사용해서 얻은 JSON 데이터에서 원하는 것을 추출할수 있음  
			JSONParser jsonParser = new JSONParser();
			
			//json 데이터를 JSON 객체 형태로 변환 
			JSONObject jsonObj = (JSONObject)jsonParser.parse(json);
			
			//orderDate라는 키안에 담긴 값(기본 주문 정보들이 객체 형태로 저장이된것)을 얻어옴  
			JSONObject order = (JSONObject)jsonObj.get("orderDate");
			String order_number = (String)order.get("order_number"); //주문번호
			System.out.println(" 기본주문 정보 -> "+order); // {"address3":"","address2":"","address1":"","order_number":"ID1609242585383","receiver_name":"admin","receiver_phone":"010-2397-1523"} 
			System.out.println("고유 주문번호 :"+ order_number);
			
			String imp_uid = (String)jsonObj.get("imp_uid"); //결제 정보 조회시 필요 
			ImportUtils import_util = new ImportUtils();
			String access_token = import_util.getAccessToken(); //액세스 토큰을 발급받아서 가져오는 메서드 호출 
			import_util.paymentSelect(access_token, imp_uid); //결제정보 조회 					
			
			//기본주문 정보값 DTO에 넣기 
			orderInfo.setOrder_number(order_number); //주문번호 
			orderInfo.setUser_id(user.getUser_id()); //회원ID 
			orderInfo.setAddress1((String)order.get("address1")); //우편번호
			orderInfo.setAddress2((String)order.get("address2")); //주소
			orderInfo.setAddress3((String)order.get("address3")); //상세주소 
			orderInfo.setReceiver_name((String)order.get("receiver_name")); //수령인
			orderInfo.setReceiver_phone((String)order.get("receiver_phone")); //전화번호 
			
			//주문한 상품정보들은 배열로 담음  
			JSONArray order_detail_list = (JSONArray)jsonObj.get("order_detail_list"); 
			OrderDetailDto orderDetail = null;
			for(int i=0;i<order_detail_list.size();i++) {
				JSONObject orderDetailObj = (JSONObject)order_detail_list.get(i);
				System.out.println(" ====== " + i +"번째 주문 세부사항");
				 				
				String detailOrderNum = order_number + i; //상세 주문번호  (주문번호 + 인덱스값) 
				//각각의 상품정보들을 List 형태의 배열에 넣기 
				orderDetail = new OrderDetailDto();
				orderDetail.setOrder_detail_number(detailOrderNum); //상세 주문번호 
				orderDetail.setOrder_number(order_number); //주문번호 
				orderDetail.setProduct_number(Integer.parseInt((String)orderDetailObj.get("product_number"))); //상품번호
				orderDetail.setProduct_count(Integer.parseInt((String)orderDetailObj.get("product_count"))); // 주문한 상품 개수 
				orderDetail.setProduct_price(Integer.parseInt((String)orderDetailObj.get("product_price"))); // 상품 가격 
				orderDetail.setOrder_detail_status((String)orderDetailObj.get("order_detail_status")); // 결제 상태
				orderDetailInfo.add(i,orderDetail);
			}
			
			//List에 들어간 상품정보들 출력하기 
			for(int i=0;i<orderDetailInfo.size();i++) {
				System.out.println(i+"번째 상품번호:"+orderDetailInfo.get(i).getProduct_number());
				System.out.println(i+"번째 주문한 수량:"+orderDetailInfo.get(i).getProduct_count());
			}
			
		} catch (ParseException e) {
			System.out.println("JSON 데이터 파싱중 에러발생!!"+e.getMessage());
			e.printStackTrace();
		}
			
		return orderDetailInfo;
	}
	
	
	
	//JSON 형식으로 된 결제,주문정보 값 추출해서 orderInfo,orderDetailInfo 에 각각 값들을 넣어주는 함수(비회원인 경우) 
	public ArrayList<NonuserOrderDetailDto> nonUserOrderInfoExtract(NonuserOrderDto nonuserOrderDto,ArrayList<NonuserOrderDetailDto> nonuserOrderDetailInfo,String json) throws Exception{
		
		
			try {
			// JSONParser 라이브러리를 사용해 프론트단에서 Ajax로 JSON.stringify()를 사용해서 얻은 JSON 데이터에서 원하는 것을 추출할수 있음  
			JSONParser jsonParser = new JSONParser();
			
			//json 데이터를 JSON 객체 형태로 변환 
			JSONObject jsonObj = (JSONObject)jsonParser.parse(json);
			
			//orderDate라는 키안에 담긴 값(기본 주문 정보들이 객체 형태로 저장이된것)을 얻어옴  
			JSONObject order = (JSONObject)jsonObj.get("orderDate");
			String order_number = (String)order.get("order_number"); //주문번호
			System.out.println(" 기본주문 정보 -> "+order); // {"address3":"","address2":"","address1":"","order_number":"ID1609242585383","receiver_name":"admin","receiver_phone":"010-2397-1523"} 
			System.out.println("고유 주문번호 :"+ order_number);
			
			String imp_uid = (String)jsonObj.get("imp_uid"); //결제 정보 조회시 필요 
			ImportUtils import_util = new ImportUtils();
			String access_token = import_util.getAccessToken(); //액세스 토큰을 발급받아서 가져오는 메서드 호출 
			import_util.paymentSelect(access_token, imp_uid); //결제정보 조회(필요시 상황에 따라서 사용)  					
			
			//기본주문 정보값 DTO에 넣기 
			nonuserOrderDto.setOrder_number(order_number); //주문번호 
			nonuserOrderDto.setAddress1((String)order.get("address1")); //우편번호
			nonuserOrderDto.setAddress2((String)order.get("address2")); //주소
			nonuserOrderDto.setAddress3((String)order.get("address3")); //상세주소 
			nonuserOrderDto.setReceiver_name((String)order.get("receiver_name")); //수령인
			nonuserOrderDto.setReceiver_phone((String)order.get("receiver_phone")); //전화번호 
			
			//주문한 상품정보들은 배열로 담음  
			JSONArray order_detail_list = (JSONArray)jsonObj.get("order_detail_list"); 
			NonuserOrderDetailDto orderDetail = null;
			for(int i=0;i<order_detail_list.size();i++) {
				JSONObject orderDetailObj = (JSONObject)order_detail_list.get(i);
				System.out.println(" ====== " + i +"번째 주문 세부사항");
				 				
				String detailOrderNum = order_number + i; //상세 주문번호  (주문번호 + 인덱스값) 
				//각각의 상품정보들을 List 형태의 배열에 넣기 
				orderDetail = new NonuserOrderDetailDto(); //주문세부정보를 넣을 객체 생성 
				orderDetail.setOrder_detail_number(detailOrderNum); //상세 주문번호 
				orderDetail.setOrder_number(order_number); //주문번호 
				orderDetail.setProduct_number(Integer.parseInt((String)orderDetailObj.get("product_number"))); //상품번호
				orderDetail.setProduct_count(Integer.parseInt((String)orderDetailObj.get("product_count"))); // 주문한 상품 개수 
				orderDetail.setProduct_price(Integer.parseInt((String)orderDetailObj.get("product_price"))); // 상품 가격 
				orderDetail.setOrder_detail_status((String)orderDetailObj.get("order_detail_status")); // 결제 상태
				nonuserOrderDetailInfo.add(i,orderDetail);
			}
			
		} catch (ParseException e) {
			System.out.println("JSON 데이터 파싱중 에러발생!!"+e.getMessage());
			e.printStackTrace();
		}
			
		return nonuserOrderDetailInfo;
	}
	
	
}
