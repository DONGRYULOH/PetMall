<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>도르가 무역 주문서 페이지</title>
   
	 
    <%@ include file="/WEB-INF/include/head_import.jsp"%>
     <!-- 아임포트 라이브러리  -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

  <body>
  
  	 <div class="container"> 
  	  
  	    	<%@ include file="/WEB-INF/include/header.jsp"%>
   		 	
	  	  	<div class="card" style="margin-top: 30px;">
	            <div class="card-header">
	              <h3 class="mb-0">주문/결제</h3>
	            </div>

	            <!-- 배송지 정보  -->
	            <div class="card-body">
	              <h3 class="ct-title">배송지 정보</h3>
	              <div class="row">
	              	 <div class="col-lg-3" style="margin-top: 20px;">배송지 선택</div>
	              	 <div class="col-lg-9">
	              	 	<div class="nav-wrapper">
	              	 		 <!-- 기존 또는 신규 배송지인지 확인여부 (처음에 주문페이지로 들어오면 "UserD"로 되있음 )  
					            	value 값이 UserD -> 기존 배송지 , NewD -> 신규 배송지						            	
					            -->
					        <input type="hidden" id="D_Check" value="UserD" />
					        
						    <ul class="nav nav-pills nav-fill flex-column flex-md-row" id="tabs-icons-text" role="tablist">
						    	<!-- 기존(회원) 배송지 -->
						        <li class="nav-item">
						            <a class="nav-link mb-sm-3 mb-md-0 active" id="tabs-icons-text-1-tab" data-toggle="tab" href="#tabs-icons-text-1" role="tab" aria-controls="tabs-icons-text-1" aria-selected="true">고정(회원) 배송지</a>						      
						        </li>
						        <!-- 신규 배송지 -->
						        <li class="nav-item">
						            <a class="nav-link mb-sm-3 mb-md-0" id="tabs-icons-text-2-tab" data-toggle="tab" href="#tabs-icons-text-2" role="tab" aria-controls="tabs-icons-text-2" aria-selected="false">신규 배송지</a>
						        </li>
						    </ul>
						</div>
	              	 </div>
	              </div>
	              <hr class="my-4">
	              
	              <div class="card shadow">
				    <div class="card-body">
				        <div class="tab-content" id="myTabContent">
				         	<!-- 회원(기존) 배송지  -->
				            <div class="tab-pane fade show active" id="tabs-icons-text-1" role="tabpanel" aria-labelledby="tabs-icons-text-1-tab">
				               	  <div class="row">
					              	 <div class="col-lg-3">수령인</div>
					              	 <div class="col-lg-9">
					              	 	<h3>${userInfo.user_name}</h3>
					              	 	<input type="hidden" id="D_userName" value="${userInfo.user_name}" />
					              	 </div>
					              </div>
					              <hr class="my-4">
					              <div class="row">
					              	 <div class="col-lg-3">전화번호</div>
					              	 <div class="col-lg-9">
					              	 	<h3>${userInfo.user_phone}</h3>
					              	 	<input type="hidden" id="D_userPhone" value="${userInfo.user_phone}" />
					              	 </div>
					              </div>
					              <hr class="my-4">
					              <div class="row">
					              	 <div class="col-lg-3">배송지 주소</div>
					              	 <div class="col-lg-9">
					              	 	<h3>${user_address.user_address1}</h3>
					              	 	<h3>${user_address.user_address2}</h3>
					              	 	<h3>${user_address.user_address3}</h3>
					              	 	<input type="hidden" id="D_userAddress1" value="${user_address.user_address1}" />
					              	 	<input type="hidden" id="D_userAddress2" value="${user_address.user_address2}" />
					              	 	<input type="hidden" id="D_userAddress3" value="${user_address.user_address3}" />
					              	 </div>	              	 
					              </div>
				            </div>
				            <!-- 신규 배송지  -->
				            <div class="tab-pane fade" id="tabs-icons-text-2" role="tabpanel" aria-labelledby="tabs-icons-text-2-tab">
				                <div class="row">
					              	 <div class="col-lg-3">수령인</div>
					              	 <div class="col-lg-9">
					              	 	<input type="text" id="new_receiver">
					              	 </div>
					              </div>
					              <hr class="my-4">
					              <div class="row">
					              	 <div class="col-lg-3">전화번호</div>
					              	 <div class="col-lg-9">
					              	 	<div id="cellPhone">
					              	 		<input type="text" maxlength="3" id="new_phone_first">-
					              	 		<input type="text" maxlength="4" id="new_phone_mid">-
					              	 		<input type="text" maxlength="4" id="new_phone_last">
					              	 	</div>
					              	 </div>
					              </div>
					              <hr class="my-4">
					              <div class="row">
					              	 <div class="col-lg-3">배송지 주소</div>
					              	 <div class="col-lg-9">
					              	 	<input type="text"  id="new_sample6_postcode" style="width:100px;" maxlength="5" readonly="readonly" placeholder="우편번호">
										<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
										<input type="text" id="new_sample6_address" placeholder="주소" style="width: 47%;" readonly="readonly"><br>
										<input type="text" id="new_sample6_detailAddress" style="width: 47%;" placeholder="상세주소">
					              	 </div>	              	 
					              </div>
				            </div>
				            
				        </div>
				    </div>
				</div>
	              
	               
	              
	              
	            </div>
	            <!-- 배송지 정보 Card-body END -->
	             
	             
	            <!-- 주문자 정보  -->
	            <div class="card-body">
	              <h3 class="ct-title">주문자 정보</h3>
	              <div class="row">
	              	 <div class="col-lg-3">이름</div>
	              	 <div class="col-lg-9">
	              	 	<h3>${userInfo.user_name}</h3>
	              	 	<input type="hidden" id="user_name" value="${userInfo.user_name}" />
	              	 </div>
	              </div>
	              <hr class="my-4">
	               <div class="row">
	              	 <div class="col-lg-3">전화번호</div>
	              	 <div class="col-lg-9">
	              	 	<h3>${userInfo.user_phone}</h3>
	              	 	<input type="hidden" id="user_phone" value="${userInfo.user_phone}" />
	              	 </div>
	              </div>
	              <hr class="my-4">
	              <div class="row">
	              	 <div class="col-lg-3">이메일</div>
	              	 <div class="col-lg-9">
	              	 	<h3>${userInfo.user_email}</h3>
	              	 	<input type="hidden" id="user_email" value="${userInfo.user_email}" />
	              	 </div>	              	 
	              </div>
	            </div>
	            
	        </div>
	        
	        <!-- 상품 정보  -->
	        <div class="card" style=" margin-top: 30px;">
	            <div class="card-header">
	              <h3 class="ct-title">상품 정보</h3>
	            </div>
	            
	            <div class="table-responsive">
		             <table class="table align-items-center table-flush">
		                <thead class="thead-light">
		                  <tr>
		                       <th>상품이미지</th>
		                       <th>상품명</th>
							   <th>수량</th>
							   <th>가격</th>
							   <th>총합금액</th>
		                  </tr>
		                </thead>
		                
		                <!-- 장바구니에서 주문하기 버튼 클릭시  -->
		                <c:if test="${cartList != null}">
			                <c:forEach var="list" items="${cartList}" varStatus="status">
				                <tbody class="list">		                   
				                   <tr>
					                    <td>
						                   	<img src="${pageContext.request.contextPath}/imgUpload/${list.stored_thumbNail}" /> 
						                   	<!-- 상품번호 -->
						                   	<input type="hidden" name="product_number[]" value="${list.product_number}" />
									    <td>
									    	${list.product_name}
									    </td>
									    <td> 
									    	${list.product_count}
									    	<!-- 주문한 상품개수 -->
						                   	<input type="hidden" name="product_count[]" value="${list.product_count}" />
									     </td>
									    <td>
									    	<fmt:formatNumber pattern="###,###,###" value="${list.product_price}" /> 원
									    	<!-- 상품가격 -->
						                   	<input type="hidden" name="product_price[]" value="${list.product_price}" />
									    </td>				
									    <td>
									    	<fmt:formatNumber pattern="###,###,###" value="${list.product_price * list.product_count}" /> 원		
									    </td>                
					              </tr>          
				                </tbody>
			                </c:forEach>
			             </c:if>
		                
		                <!-- 해당 상품을 주문하기 버튼 클릭시  -->
		                 <c:if test="${productInfo != null}">
		                 	<tbody class="list">		                   
			                   <tr>
				                    <td>
					                   	<img src="${pageContext.request.contextPath}/imgUpload/${productInfo.stored_thumbNail}" /> 
					                   	<!-- 상품번호 -->
						                <input type="hidden" id="product_number" value="${productInfo.product_number}" />
								    <td>
								    	${productInfo.product_name}
								    </td>
								    <td> 
								    	${product_count}
								    	<!-- 주문한 상품개수 -->
						                <input type="hidden" id="product_count" value="${product_count}" />
								     </td>
								    <td>
								    	<fmt:formatNumber pattern="###,###,###" value="${productInfo.product_price}" /> 원
								    	<!-- 상품가격 -->
						                <input type="hidden" id="product_price" value="${productInfo.product_price}" />
								    </td>				
								    <td>
								    	<fmt:formatNumber pattern="###,###,###" value="${productInfo.product_price * product_count}" /> 원		
								    </td>                
				              </tr>          
			                </tbody>
		                 </c:if>
		                 
		              </table>
           		 </div>
           		 
           		 <!-- 상품의 총가격  -->
           		 <div class="card-body">
			   		장바구니 금액 합계 : <fmt:formatNumber pattern="###,###,###" value="${total}"/><br>
		                        배송료 : ${fee}<br>
		                        전체 주문금액  :<fmt:formatNumber pattern="###,###,###" value="${total_fee}" />
		            <input type="hidden" id="total_fee" value="${total_fee}" />
			    </div> 
	        </div>
				
   		 	 <!-- 결제 정보   -->
	        <div class="card" style="margin-top: 30px;">
	            <div class="card-header">
	              <h3 class="ct-title">결제 정보</h3>
	            </div>
	            
	            <div class="card-body">
	                  <div class="row">
		              	 <div class="col-lg-3">결제수단</div>
		              	 <div class="col-lg-9">
		              	 	<input type="radio" >
	  					    <label>카카오페이</label>
		              	 </div>
		              </div>
	            </div>
	        </div>
	        
	        <button id="Payment" class="btn btn-default" style="width: 228px;" type="button">결제하기</button>
	        
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
    	 
    	
		
  </body>
			
  
  	  <!-- 아임포트 결제 API 사용 -->
  	  <script type="text/javascript">
  	  		$(document).ready(function(){
  	  			console.log("결제 API 서비스 불러오기");
	  	  		var IMP = window.IMP; // 생략가능
	  	  		IMP.init('imp16146364'); // 가맹점 식별코드
  	  		});
  	  		
  	  		$('#Payment').click(function(){
  	  			
  	  		//<결제 완료후 주문테이블에 주문정보가 들어가야됨>
		  	      
	  	          // ***************** <기본 주문 정보 > START********************************
	  	          
		  	          // 회원(기존)배송지인지 신규배송지인지 여부를 파악해야됨(value 값이 UserD -> 기존 배송지 , NewD -> 신규 배송지) 
		  	          var Ck = document.getElementById("D_Check").value;
		  	          var orderDate = new Object(); //기본주문정보가 들어갈 객체선언 
		  	          
		  	          if(Ck == 'UserD'){
		  	        	console.log("기존(회원) 배송지임!!");
		  	        	orderDate.address1 = document.getElementById("D_userAddress1").value; //우편번호
		  	        	orderDate.address2 = document.getElementById("D_userAddress2").value; //주소
		  	        	orderDate.address3 = document.getElementById("D_userAddress3").value; //상세주소
		  	        	orderDate.receiver_name = document.getElementById("D_userName").value; //수령인
		  	        	orderDate.receiver_phone = document.getElementById("D_userPhone").value; //전화번호
		  	          }else{
			  	        	console.log("신규 배송지임!!");
			  	        	var newP = document.getElementById("new_phone_first").value + '-' + document.getElementById("new_phone_mid").value + '-' + document.getElementById("new_phone_last").value;
			  	        	console.log("신규 배송지 전화번호 : "+newP);
			  	        	orderDate.address1 = document.getElementById("new_sample6_postcode").value; //우편번호
			  	        	orderDate.address2 = document.getElementById("new_sample6_address").value; //주소
			  	        	orderDate.address3 = document.getElementById("new_sample6_detailAddress").value; //상세주소
			  	        	orderDate.receiver_name = document.getElementById("new_receiver").value; //수령인
			  	        	orderDate.receiver_phone = newP; //전화번호
		  	          }
		  	          	console.log("기본 주문 정보 :"+ Object.values(orderDate) );
		  	          	
	  	          // ***************** <기본 주문 정보 > END ******************************** 	
	  	          	
	  	          
	  	       // ************** <주문 세부 사항 관련 데이터들 > START ************************************
	  	       
	  	  			  var cartListSize = '${fn:length(cartList)}'; //장바구니에서 주문하기 버튼 클릭시 주문리스트
	  	  			  console.log("cartListSize 는??"+cartListSize); //해당 상품을 주문을 할경우에는 값이 0이 나옴 
	  	  			  var order_detail_list = []; //주문한 상품들에 대한 정보가 들어갈 배열을 만듬 
	  	  			  
	  	  			 // ************** 장바구니에서 주문을 했을때 *************************
	  	  			  if(cartListSize != 0){
			  	          //각각의 상품들에 대한 정보 (주문한 상품이 여러개일 경우는 for문으로 여러개의 객체가 생성됨)
			  	          for(var i=0;i<cartListSize;i++){
			  	        	 	  var order_detail = {
					  	        		product_number :   document.getElementsByName("product_number[]")[i].value, //상품번호
					  	        		product_count :   document.getElementsByName("product_count[]")[i].value, //주문한 개수
					  	        		product_price :   document.getElementsByName("product_price[]")[i].value,  //상품 가격 
					  	        		order_detail_status : 'PaymentComplete' // 결제완료 상태 
					  	          };
			  	        	 	  console.log(i+"번째 상품 정보 : "+Object.values(order_detail));
			  	        	 	  order_detail_list.push(order_detail);
			  	          }
	  	  			  }
	  	  			 // ************** 해당 상품을 주문을 했을때 *************************
		  	         else{ 
		  	  			var order_detail = {
		  	        		product_number :   document.getElementById("product_number").value, //상품번호
		  	        		product_count :   document.getElementById("product_count").value, //주문한 개수
		  	        		product_price :   document.getElementById("product_price").value,  //상품 가격 
		  	        		order_detail_status : 'PaymentComplete' // 결제완료 상태 
		  	            };
		  	  		    order_detail_list.push(order_detail)
		  	  		    console.log("주문한 상품 정보 : "+Object.values(order_detail));
		  	         }
	  			  
	  	  			  console.log("상품정보가 들어간 배열 :"+ order_detail_list[0] );
	  		    // ************** <주문 세부 사항 관련 데이터들 > END************************************
				
	  		    // 컨트롤러로 주문이랑 주문세부내역을 넘기는 방법을 찾아야됨 .... (12/18)
	  			$.ajax({
				    url : "${pageContext.request.contextPath}/shop/orderInfoInsert",
				    dataType    : "json",
				    contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				    type : "post",
				    data : {list : order_detail_list },
				    success : function(result){
				    	if(result == 1){
					     	alert("카트 담기 성공");
					     	$("#numBox").val("1");
				    	}else{
				    		alert("카트 담기 실패");
					     	$("#numBox").val("1");
				    	}
				    },
				    error : function(){
				     	alert("카트 담기 실패");
				    }
			    });  
	  	          	    
  	  		    
			  	 IMP.request_pay({
			  	      pg : 'kakaopay', // version 1.1.0부터 지원.
			  	      pay_method : 'card',
			  	      merchant_uid : 'merchant_' + new Date().getTime(),
			  	      name : '주문명:결제테스트',
			  	      amount : 10, //document.getElementById("total_fee").value, // 총 주문금액 
			  	      buyer_email : document.getElementById("user_email").value,  //구매자 이메일 
			  	      buyer_name : document.getElementById("user_name").value, //구매자 이름
			  	      buyer_tel : document.getElementById("user_phone").value, //구매자 전화번호 
			  	      buyer_addr : '서울특별시 강남구 삼성동',
			  	      buyer_postcode : '123-456'
			  	  }, function(rsp) {
			  	      if ( rsp.success ) {
			  	          var msg = '결제가 완료되었습니다.';
			  	          msg += '고유ID : ' + rsp.imp_uid;
			  	          msg += '고유 주문번호 : ' + rsp.merchant_uid;
			  	          msg += 'PG사 거래고유번호 : ' + rsp.apply_num; 
			  	          msg += '결제 금액 : ' + rsp.paid_amount;
			  	          msg += '카드 승인번호 : ' + rsp.apply_num;
			  	          	  	          
			  	      //<결제 완료후 주문테이블에 주문정보가 들어가야됨>
			  	      
			  	          // ***************** <기본 주문 정보 > START********************************
			  	          
				  	          // 회원(기존)배송지인지 신규배송지인지 여부를 파악해야됨(value 값이 UserD -> 기존 배송지 , NewD -> 신규 배송지) 
				  	          var Ck = document.getElementById("D_Check").value;
				  	          var orderDate = new Object(); //기본주문정보가 들어갈 객체선언 
				  	          
				  	          if(Ck == 'UserD'){
				  	        	console.log("기존(회원) 배송지임!!");
				  	        	orderDate.address1 = document.getElementById("D_userAddress1").value; //우편번호
				  	        	orderDate.address2 = document.getElementById("D_userAddress2").value; //주소
				  	        	orderDate.address3 = document.getElementById("D_userAddress3").value; //상세주소
				  	        	orderDate.receiver_name = document.getElementById("D_userName").value; //수령인
				  	        	orderDate.receiver_phone = document.getElementById("D_userPhone").value; //전화번호
				  	          }else{
					  	        	console.log("신규 배송지임!!");
					  	        	var newP = document.getElementById("new_phone_first").value + '-' + document.getElementById("new_phone_mid").value + '-' + document.getElementById("new_phone_last").value;
					  	        	console.log("신규 배송지 전화번호 : "+newP);
					  	        	orderDate.address1 = document.getElementById("new_sample6_postcode").value; //우편번호
					  	        	orderDate.address2 = document.getElementById("new_sample6_address").value; //주소
					  	        	orderDate.address3 = document.getElementById("new_sample6_detailAddress").value; //상세주소
					  	        	orderDate.receiver_name = document.getElementById("new_receiver").value; //수령인
					  	        	orderDate.receiver_phone = newP; //전화번호
				  	          }
				  	          	console.log("기본 주문 정보 :"+ Object.values(orderDate) );
				  	          	
			  	          // ***************** <기본 주문 정보 > END ******************************** 	
			  	          	
			  	          
			  	       // ************** <주문 세부 사항 관련 데이터들 > START ************************************
			  	       
			  	  			  var cartListSize = '${fn:length(cartList)}'; //장바구니에서 주문하기 버튼 클릭시 주문리스트
			  	  			  console.log("cartListSize 는??"+cartListSize); //해당 상품을 주문을 할경우에는 값이 0이 나옴 
			  	  			  var order_detail_list = []; //주문한 상품들에 대한 정보가 들어갈 배열을 만듬 
			  	  			  
			  	  			 // ************** 장바구니에서 주문을 했을때 *************************
			  	  			  if(cartListSize != 0){
					  	          //각각의 상품들에 대한 정보 (주문한 상품이 여러개일 경우는 for문으로 여러개의 객체가 생성됨)
					  	          for(var i=0;i<cartListSize;i++){
					  	        	 	  var order_detail = {
							  	        		product_number :   document.getElementsByName("product_number[]")[i].value, //상품번호
							  	        		product_count :   document.getElementsByName("product_count[]")[i].value, //주문한 개수
							  	        		product_price :   document.getElementsByName("product_price[]")[i].value,  //상품 가격 
							  	        		order_detail_status : 'PaymentComplete' // 결제완료 상태 
							  	          };
					  	        	 	  console.log(i+"번째 상품 정보 : "+Object.values(order_detail));
					  	        	 	  order_detail_list.push(order_detail);
					  	          }
			  	  			  }
			  	  			 // ************** 해당 상품을 주문을 했을때 *************************
				  	         else{ 
				  	  			var order_detail = {
				  	        		product_number :   document.getElementById("product_number").value, //상품번호
				  	        		product_count :   document.getElementById("product_count").value, //주문한 개수
				  	        		product_price :   document.getElementById("product_price").value,  //상품 가격 
				  	        		order_detail_status : 'PaymentComplete' // 결제완료 상태 
				  	            };
				  	  		    order_detail_list.push(order_detail)
				  	  		    console.log("주문한 상품 정보 : "+Object.values(order_detail));
				  	         }
		  	  			  
		  	  		    // ************** <주문 세부 사항 관련 데이터들 > END************************************
			  	          	
		  	  			$.ajax({
						    url : "${pageContext.request.contextPath}/shop/orderInfoInsert",
						    type : "post",
						    data : {order_detail_list : order_detail_list},
						    success : function(result){
						    	if(result == 1){
							     	alert("카트 담기 성공");
							     	$("#numBox").val("1");
						    	}else{
						    		alert("카트 담기 실패");
							     	$("#numBox").val("1");
						    	}
						    },
						    error : function(){
						     	alert("카트 담기 실패");
						    }
					    });  
		  	  		    
			  	      } else {
			  	          var msg = '결제에 실패하였습니다.';
			  	          msg += '에러내용 : ' + rsp.error_msg;
			  	      }
			  	      alert(msg);
			  	  });
	  	   		
  	  	   });
  	  </script>
  	  
  	  
  	 <script type="text/javascript">
  	 
  	 	/* 고정 배송지 클릭시 실행 함수 */
	  	 document.getElementById('tabs-icons-text-1-tab').onclick = function(){
	  		 console.log("고정 배송지 클릭함!");
	  		 console.log("배송지 값"+$(this).parent().parent().parent().children("#D_Check").val());
	  		 $(this).parent().parent().parent().children("#D_Check").val("UserD"); //고정배송지를 뜻하는 UserD로 바꿔줌 
	  	 };
  	 
	  	/* 신규 배송지 클릭시 실행 함수 */
	  	document.getElementById('tabs-icons-text-2-tab').onclick = function(){
	 		 console.log("신규 배송지 클릭함!");
	 		 console.log("배송지 값"+$(this).parent().parent().parent().children("#D_Check").val());
	 		 $(this).parent().parent().parent().children("#D_Check").val("NewD"); //신규배송지를 뜻하는 NewD로 바꿔줌 
	 	 };

  	 </script>
  	 
  	  <!-- 다음 우편변호API 사용 -->
  	<script type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    function sample6_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	                	//경기 화성시 동탄순환대로20길 45(주소정보 추가)
	                	document.getElementById("new_sample6_address").value = addr;
	                	
	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다. 
	                    // 경기 화성시 동탄순환대로20길 45 + 참고항목 (목동, 동탄 금강펜테리움 센트럴파크Ⅳ) 
	                    // 즉.. 아파트명이나 무슨무슨동을 추가 
	                    document.getElementById("new_sample6_address").value += extraAddr;
	                
	                } else {
	                    document.getElementById("new_sample6_address").value = addr;
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('new_sample6_postcode').value = data.zonecode;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("new_sample6_detailAddress").focus();
	            }
	        }).open();
	    }
	</script>	
	
	
		
</html>










































