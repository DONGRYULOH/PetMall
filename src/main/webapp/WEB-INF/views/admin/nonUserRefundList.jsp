<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>비회원 환불요청 내역 페이지</title>
   

	
   <%@ include file="/WEB-INF/views/admin/include/admin_head_import.jsp"%> 
    
  </head>
  <body>
  	<!-- sidenav 영역 -->
	<%@ include file="/WEB-INF/views/admin/include/admin_sidenav.jsp"%>
  	
  	<!-- 메인 컨텐츠 영역 -->
  	<div class="main-content" id="panel">
  	  
	  	<!-- topnav 영역 -->
		<%@ include file="/WEB-INF/views/admin/include/admin_topnav.jsp"%>

  	  		<div class="table-responsive">
             <table class="table align-items-center table-flush">
                <thead class="thead-light">
                  <tr>
                       <th>환불번호</th>
                       <th>주문상세번호</th>
					   <th>처리상태(환불중,환불완료)</th>
					   <th>환불가능여부(환불처리하기,환불처리완료됨)</th>
                  </tr>
                </thead>
                <tbody class="list">
                   <c:forEach items="${refundList}" var="list">
                   <tr>
	                    <th scope="row">
	    					${list.refund_number}
	                    </th>
					    <td>
					    	${list.order_detail_number}
					    </td>
					    <td>
					    	 <c:if test="${list.order_detail_status == 'PaymentRefunding' }">
					    	 	<h3>환불중</h3>
					    	 </c:if>
					    	 <c:if test="${list.order_detail_status == 'PaymentRefundOk' }">
					    	 	<h3>환불완료</h3>
					    	 </c:if>
					    </td>
					    <td>
					    	<c:if test="${list.order_detail_status == 'PaymentRefunding' }">
					    	 	<a href="${pageContext.request.contextPath}/admin/nonUserRefundProcess?n=${list.refund_number}">환불처리하기</a>
					    	 </c:if>
					    	 <c:if test="${list.order_detail_status == 'PaymentRefundOk' }">
					    	 	<h3>환불처리완료됨</h3>
					    	 </c:if>		
					    </td>                
	              </tr>    
                  </c:forEach>           
                </tbody>
              </table>
            </div>


		
   		 
   		 
   		
   		 
    		<%@ include file="/WEB-INF/include/footer.jsp"%>
	    	
    </div>
    	
    	<%@ include file="/WEB-INF/views/admin/include/admin_tail_import.jsp"%> 

  </body>
  	  
</html>






