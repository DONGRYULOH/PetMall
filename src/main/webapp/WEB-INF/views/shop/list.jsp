<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>도르가 무역 메인 페이지</title>
   
	 
    <%@ include file="/WEB-INF/include/head_import.jsp"%>

  </head>
  <body>
  	 
  	 <div class="container"> 
  	  
  	    	<%@ include file="/WEB-INF/include/header.jsp"%>
  	  		
  	  		<!-- 카테고리 구분 -->
  	  		<nav aria-label="breadcrumb" role="navigation">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="#">Shop</a></li>
			     <c:set var="cate" value="${cateNum}" />
			     <c:choose>
			     	<c:when test="${cate == '100'}">
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=100">ALL</a>
			     		</li>
					</c:when>
					<c:when test="${cate == '101'}">
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101">상의</a>
			     		</li>
					</c:when>
					<c:when test="${cate == '102'}">
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102">하의</a>
			     		</li>
					</c:when>
					<c:when test="${cate == '103'}">
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103">신발</a>
			     		</li>
					</c:when>
					<c:otherwise>
						<li class="breadcrumb-item active" aria-current="page">
							<a href="${pageContext.request.contextPath}/shop/list?cateNum=104">시계</a>
						</li>
					</c:otherwise>
			     </c:choose>
			  </ol>
			</nav>
			
			<nav aria-label="breadcrumb" role="navigation">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath}/shop/list?cateNum=100">ALL</a></li>
			    <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath}/shop/list?cateNum=101">상의</a></li>
			    <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath}/shop/list?cateNum=102">하의</a></li>
			    <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath}/shop/list?cateNum=103">신발</a></li>
			    <li class="breadcrumb-item" aria-current="page"><a href="${pageContext.request.contextPath}/shop/list?cateNum=104">시계</a></li>
			  </ol>
			</nav>
   		 	<!-- 카테고리 구분  END-->
   		 	
   		 	<!-- 상품별 카테고리 목록  -->	
   		 	<!-- 시작엔덱스와 끝인덱스 변수선언 -->
   		 	<c:set var="index" value="0"/>
			<c:set var="index_2" value="2"/>
			
   		 	<c:forEach begin="0" end="${cateSize}" step="1" varStatus="status">
				
	   		 	<div class="row">
	   		 		<%-- <h1>끝번호 : ${status.current}</h1> --%>
	   		 		<c:forEach items="${cateProductList}" var="list" begin="${index}" end="${index_2}" step="1" varStatus="status_2">
				        <div class="col-4">
				          <a href="${pageContext.request.contextPath}/shop/detail?n=${list.product_number}">
				          	<img src="${pageContext.request.contextPath}/imgUpload${list.product_ThumbImg}" class="thumbImg"/>
				          </a>
				          <small class="d-block text-uppercase font-weight-bold mb-4">${list.product_name}</small>
				          <span><fmt:formatNumber value="${list.product_price}" pattern="###,###,###"/></span>
				        </div>
				        <!-- 끝번호라면 다음if구문실행(시작인덱스와 끝인덱스값을 바꿔줌) -->
				        <c:if test="${status_2.last}">
				        	<!--  
				        		시작인덱스 0  -> 3(2+1) -> 6
								끝인덱스 2  -> 5(3+2) -> 8 
								하나의 row에 3개씩 나타낼꺼기 떄문에 각3번씩증가함 
				        	-->
				        	<c:set var="index" value="${status_2.end+1}"/>
				        	<c:set var="index_2" value="${index+2}"/>
				    	</c:if>
			         </c:forEach>
			         
			    </div>
		    	
			</c:forEach>
		    <!-- 상품별 카테고리 목록  END-->
		    
		    <%-- <div class="row">
   		 		<c:forEach items="${cateProductList}" var="list" begin="3" end="5">
			        <div class="col-4">
			          <a href="${pageContext.request.contextPath}/shop/detail?n=${list.product_number}">
			          	<img src="${pageContext.request.contextPath}/imgUpload${list.product_ThumbImg}" class="thumbImg"/>
			          </a>
			          <small class="d-block text-uppercase font-weight-bold mb-4">${list.product_name}</small>
					  <span><fmt:formatNumber value="${list.product_price}" pattern="###,###,###"/></span>
			        </div>
		         </c:forEach>
		    </div> --%>
   		 	
   		 	
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
  </body>
  	  

</html>


