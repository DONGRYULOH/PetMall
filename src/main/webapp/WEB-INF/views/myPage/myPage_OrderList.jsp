<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>MyPage 주문내역 조회 페이지</title>
   

	
    <%@ include file="/WEB-INF/views/myPage/include/myPage_head_import.jsp"%>  
    
  </head>
  <body>
  	<!-- sidenav 영역 -->
	<%@ include file="/WEB-INF/views/myPage/include/myPage_sidenav.jsp"%>
  	
  	<!-- 메인 컨텐츠 영역 -->
  	<div class="main-content" id="panel">
  	  
	  	<!-- topnav 영역 -->
		<%@ include file="/WEB-INF/views/myPage/include/myPage_topnav.jsp"%>

			
  	  		<h2>결제처리(주문)된 날짜로부터 7일이 지나면 환불이 불가능!! </h2>
  	  		<h3>ex) 자정을 기준으로 주문일자(2020.8.4) , 환불가능한 일자 마지노선(2020.8.10) , 현재시각(2020.8.11 00:10) -> 환불 X</h3>
  	  		
  	  		<div class="table-responsive">
             <table class="table align-items-center table-flush">
                <thead class="thead-light">
                  <tr>
                       <th>썸네일</th>
					   <th>상품정보</th>
					   <th>처리상태(결제완료,환불중,환불완료)</th>
					   <th>환불 가능여부(환불요청,환불X)</th>
                  </tr>
                </thead>
                <tbody class="list">
                   <c:forEach items="${OrderDetailList}" var="list">
                   <tr>
	                    <!-- 썸네일 출력 -->
	                    <td>
				            <img src="${pageContext.request.contextPath}/imgUpload/${list.stored_thumbNail}" class="thumbImg"/>
						 </td>
						 
						<!-- 상품정보 출력(상품이름,상품가격,주문일자) -->
					    <td>
					    	<!-- 상품이름 클릭시 상세내역으로 이동(주문번호를 파라미터로 가지고 들어감) -->
					    	<h3>
					    		상품명 : <a href="${pageContext.request.contextPath}/admin/product_detail?n=${list.order_number}">${list.product_name}</a>
					    	</h3>
					    	<!-- 상품가격 -->
					    	<fmt:formatNumber value="${list.product_price}" pattern="###,###,###"/> 원 <br>
					    	<!-- 주문일자 -->
					    	<%-- 주문일자 : <fmt:formatDate value="${orderInfo.order_date}" pattern="yyyy-MM-dd"/> --%>	
					    </td>
					    
					    <!-- 처리상태(PaymentComplete -> 결제완료 , PaymentRefunding -> 환불진행중 , PaymentRefundOk -> 환불완료 -->
					    <td>
					    	 <c:if test="${list.order_detail_status == 'PaymentComplete' }">
					    	 	<h3>결제완료</h3>
					    	 </c:if>
					    	 <c:if test="${list.order_detail_status == 'PaymentRefunding' }">
					    	 	<h3>환불진행중</h3>
					    	 </c:if>
					    	 <c:if test="${list.order_detail_status == 'PaymentRefundOk' }">
					    	 	<h3>환불완료</h3>
					    	 </c:if>
					    </td>  
					    
					    <!-- 환불 가능여부(Y -> 환불하기 , N -> 환불X) -->
					    <td>
					    	 <c:if test="${list.refund_check == 'Y' }">
					    	 	<button>환불하기</button>
					    	 </c:if>
					    	 <c:if test="${list.refund_check == 'N' }">
					    	 	<button>환불X</button>
					    	 </c:if>
					    </td>    
					             
	              </tr>    
                  </c:forEach>           
                </tbody>
              </table>
            </div>


		
   		 
   		 
   		
   		 
    		<%@ include file="/WEB-INF/include/footer.jsp"%>
	    	
    </div>
    	
    <%@ include file="/WEB-INF/views/myPage/include/myPage_tail_import.jsp"%> 

  </body>
  	  
</html>






