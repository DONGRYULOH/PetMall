<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>해당 환불요청 처리 페이지</title>
   

	
   <%@ include file="/WEB-INF/views/admin/include/admin_head_import.jsp"%> 
    
  </head>
  <body>
  	<!-- sidenav 영역 -->
	<%@ include file="/WEB-INF/views/admin/include/admin_sidenav.jsp"%>
  	
  	<!-- 메인 컨텐츠 영역 -->
  	<div class="main-content" id="panel">
  	  
	  	<!-- topnav 영역 -->
		<%@ include file="/WEB-INF/views/admin/include/admin_topnav.jsp"%>
			
			<!-- 액세스 토큰값  -->
			<%-- <input type="hidden" id="access_token" value="${access_token}" /> --%>
			<!-- 주문번호 -->
			<input type="hidden" id="order_number" value="${refundInfo.order_number}" />
			<!-- 총가격 -->
			<input type="hidden" id="total_price" value="${refundInfo.total_price}" />
  	  		
				
				<div class="inputArea"> 
				 <label>환불번호:</label>
				 <span class="category1">${refundInfo.refund_number}</span> 
				</div>
				
				<div class="inputArea">        
				 <label>주문상세번호 : </label>
				 <span class="category2">${refundInfo.order_detail_number}</span>
				</div>
				
				<div class="inputArea">
				 <label for="gdsName">환불 사유 : </label>
				 <span>${refundInfo.refund_reason}</span>
				 <input type="hidden" id="refund_reason" value="${refundInfo.refund_reason}" />
				</div>

				<label for="gdsImg">상품 이미지</label>
				<div class="inputArea">
						<!-- Servlet-context.xml 에서 이미지 매핑주소를 설정해줘야해야지 이미지 파일을 찾을수 있음   -->
				    	<img src="${pageContext.request.contextPath}/refund_img/${refundInfo.refund_img}" class="thumbImg"/>
				</div>
				
				<div class="inputArea">
				 <button type="button" id="refund_Btn" class="btn btn-warning">환불처리</button>
				 <button type="button" id="back_Btn" class="btn btn-danger">뒤로가기</button>
				</div>
			
		

 		
   		 
    		<%@ include file="/WEB-INF/include/footer.jsp"%>
	    	
    </div>
    	
    	<%@ include file="/WEB-INF/views/admin/include/admin_tail_import.jsp"%> 

		<script>			  
			 
			 var reason = document.getElementById("refund_reason").value; //환불사유 
			 var merchant_uid = document.getElementById("order_number").value;  //환불 고유번호(주문번호)
			 var total_price = document.getElementById("total_price").value; //환불금액 
			 
			 console.log("refund_reason 값은??"+reason);
			 console.log("order_number 값은??"+merchant_uid);
			 console.log("total_price 값은??"+total_price);
			 
			 var obj = new Object();
			 obj.reason = reason;
			 obj.merchant_uid = merchant_uid;
			 obj.amount = total_price;
			 
			 var refundData = JSON.stringify(obj);
			 console.log("환불 JSON 데이터 :"+ refundData)
			 
			 $("#refund_Btn").click(function(){
				  $.ajax({
					    url : "${pageContext.request.contextPath}/admin/RefundProcess",
					    type : "POST",
					    contentType: "application/json; charset=utf-8",
					    data: refundData,
					    success : function(result){
					    	if(result == 1){
						     	alert("환불 성공!!");
						     	//결제 완료페이지로 이동 
						     	/* var url = "${pageContext.request.contextPath}/shop/paymentOk";	
						     	url = url + "?order_number=" + orderNum; //주문번호
				    			location.href = url; */
					    	}else{
					    		alert("환불중 에러 발생!!");
					    	}
					    },
					    error:function(request,status,error){
						    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
				    }); 
			  });

			 $("#back_Btn").click(function(){
				  //  history.back(); 이걸사용하면 DB를 안거치고 빠르게 갈수있음 
				  history.back(); //이전페이지로 이동이랑 똑같음 
			 });   

		 </script>
  </body>
  	  
</html>





