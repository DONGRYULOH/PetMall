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


  <body>
  
  	 <div class="container"> 
  	  
  	    	<%@ include file="/WEB-INF/include/header.jsp"%>
   		 	
	  	  	<div class="card" style="margin-top: 30px;">
	            <div class="card-header">
	              <h3 class="mb-0">주문이 정상적으로 완료되었습니다!!</h3>
	            </div>

		         	<!-- 주문자 정보  -->
		        	<div class="card-body">
			              <div class="row">
			              <div class="col-lg-3">주문번호</div>
			              	 <div class="col-lg-9">
			              	 	<h3>${orderInfo.order_number}</h3>
			              	 </div>
			              </div>
			              <hr class="my-4">
			               <div class="row">
			              	 <div class="col-lg-3">배송지 정보</div>
			              	 <div class="col-lg-9">
			              	 	<h3><strong>수령인: </strong>${orderInfo.receiver_name}</h3>
			              	 	<h3><strong>전화번호: </strong>${orderInfo.receiver_phone}</h3>
			              	 	<h4><strong>우편번호: </strong>${orderInfo.address1}</h4>
			              	 	<h4><strong>주소: </strong>${orderInfo.address2}</h4>
			              	 	<h4><strong>상세주소: </strong>${orderInfo.address3}</h4>
			              	 </div>
			              </div>
		            </div>
	        </div>
	             
	        
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
    	 
    	
		
  </body>
			
  
  	 
	
		
</html>










































