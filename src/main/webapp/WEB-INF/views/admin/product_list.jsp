<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>상품목록 페이지</title>
   

	
   <%@ include file="/WEB-INF/views/admin/include/admin_head_import.jsp"%> 
    
  </head>
  <body>
  	<!-- sidenav 영역 -->
	<%@ include file="/WEB-INF/views/admin/include/admin_sidenav.jsp"%>
  	
  	<!-- 메인 컨텐츠 영역 -->
  	<div class="main-content" id="panel">
  	  
	  	<!-- topnav 영역 -->
		<%@ include file="/WEB-INF/views/admin/include/admin_topnav.jsp"%>

			
  	  		<h1>상품목록 페이지</h1>
  	  		<div class="table-responsive">
             <table class="table align-items-center table-flush">
                <thead class="thead-light">
                  <tr>
                       <th>번호</th>
                       <th>썸네일</th>
					   <th>이름</th>
					   <th>카테고리</th>
					   <th>가격</th>
					   <th>수량(재고)</th>
					   <th>등록날짜</th>
                  </tr>
                </thead>
                <tbody class="list">
                   <c:forEach items="${ProductList}" var="list">
                   <tr>
	                    <th scope="row">
	    					${list.product_number}
	                    </th>
	                    <!-- 썸네일 출력 -->
	                    <td>
		                    <%-- <c:set var="ThumbImg" value="${ProductList.product_ThumbImg}" /> --%>
		                    <c:choose>
						        <c:when test="${list.stored_thumbNail == null }">
			                    		<img src="${pageContext.request.contextPath}/images/none.PNG" class="thumbImg"/>
						        </c:when>         
						        <c:otherwise>
				                    	<img src="${pageContext.request.contextPath}/imgUpload/${list.stored_thumbNail}" class="thumbImg"/>
						         </c:otherwise>
						    </c:choose>
						 </td>
					    <td>
					    	<a href="${pageContext.request.contextPath}/admin/product_detail?n=${list.product_number}">${list.product_name}</a>
					    </td>
					    <td>${list.category_code}</td>
					    <td>
					    	<fmt:formatNumber value="${list.product_price}" pattern="###,###,###"/>
					    </td>
					    <td>${list.product_stock}</td>
					    <td>
					    	<fmt:formatDate value="${list.product_date}" pattern="yyyy-MM-dd"/>		
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






