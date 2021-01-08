<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>도르가 무역 메인 페이지</title>
   
	 
    <%@ include file="/WEB-INF/include/head_import.jsp"%>
	
	<!-- 반응형 모바일 구축 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mobile_css/main.css">
	
  </head>
  <body>
  	 
  	 <div class="container"> 
  	  
  	    	<%@ include file="/WEB-INF/include/header.jsp"%>
  	  		
  	  		<!-- 카테고리 구분  -->
  	  		<nav aria-label="breadcrumb" role="navigation">
			  <ol class="breadcrumb">
			     <c:set var="cate" value="${cateNum}" />
			     <!-- 상단 네브바 -->
			     <c:choose>
			     	<c:when test="${cate == '100'}">
			     		
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=100"><strong>ALL</strong></a>
			     		</li>
			     		
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101">봄&여름</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102">가을&겨울</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103">라이프</a>
			     		</li>
					</c:when>
					<%--상의 품목들 --%>
					<c:when test="${cate == '101' || cate == '101-1' || cate == '101-2' || cate == '101-3' || cate == '101-4'}">
						<%--상위항목 --%>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101"><strong>봄&여름</strong></a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=100">ALL</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102">가을&겨울</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103">라이프</a>
			     		</li>
			     		<%--하위 항목 --%>
			     		<%-- <c:choose>
			     			<c:when test="${cate == '101'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101">봄/여름</a>
					     		</li>
				     		</c:when>
				     		<c:when test="${cate == '101-1'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101-1">반팔티셔츠</a>
					     		</li>
				     		</c:when>
				     		<c:when test="${cate == '101-2'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101-2">긴팔티셔츠</a>
					     		</li>
				     		</c:when>
				     		<c:when test="${cate == '101-3'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101-3">기타상의</a>
					     		</li>
				     		</c:when>
			     		 </c:choose> --%>
					</c:when>
					<%--하의 품목들 --%>
					<c:when test="${cate == '102' || cate == '102-1' || cate == '102-2' || cate == '102-3' || cate == '102-4'}">
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102"><strong>가을&겨울</strong></a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=100">ALL</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101">봄&여름</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103">라이프</a>
			     		</li>
			     		<%-- <c:choose>
			     			<c:when test="${cate == '102'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102">가을/겨울</a>
					     		</li>
				     		</c:when>
			     			<c:when test="${cate == '102-1'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102-1">군복바지</a>
					     		</li>
				     		</c:when>
				     		<c:when test="${cate == '102-2'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102-2">레깅스</a>
					     		</li>
				     		</c:when>
				     		<c:when test="${cate == '102-3'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102-3">숏팬츠</a>
					     		</li>
				     		</c:when>
				     		<c:when test="${cate == '102-4'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102-4">기타하의</a>
					     		</li>
				     		</c:when>
			     		 </c:choose> --%>
					</c:when>
					<%--신발 품목들 --%>
					<c:when test="${cate == '103' || cate == '103-1' || cate == '103-2' }">
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103"><strong>라이프</strong></a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=100">ALL</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101">봄&여름</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102">가을&겨울</a>
			     		</li>
			     		<%-- <c:choose>
			     			<c:when test="${cate == '103'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103">라이프</a>
					     		</li>
				     		</c:when>
			     			<c:when test="${cate == '103-1'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103-1">구두</a>
					     		</li>
				     		</c:when>
				     		<c:when test="${cate == '103-2'}">
					     		<li class="breadcrumb-item active" aria-current="page">
					     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103-2">기타신발</a>
					     		</li>
				     		</c:when>
			     		 </c:choose> --%>
					</c:when>
			     </c:choose>
			  </ol>
			</nav>
			
			<nav aria-label="breadcrumb" role="navigation">
			  <ol class="breadcrumb">
			    <!-- 하단 카테고리 네브바 -->
			  	<c:choose>
	     			<c:when test="${cate == '101' || cate == '101-1' || cate == '101-2' || cate == '101-3' || cate == '101-4'}">
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101"><strong>봄&여름</strong></a>
			     		</li>
			     		<%-- <li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101-1">반팔티셔츠</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101-2">긴팔티셔츠</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=101-3">기타상의</a>
			     		</li> --%>
		     		</c:when>
		     		<c:when test="${cate == '102' || cate == '102-1' || cate == '102-2' || cate == '102-3' || cate == '102-4'}">
		     			<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102"><strong>가을&겨울</strong></a>
			     		</li>
			     		<%-- <li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102-1">군복바지</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102-2">레깅스</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102-3">숏팬츠</a>
			     		</li>
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=102-4">기타하의</a>
			     		</li>	 --%>				     		
		     		</c:when>
		     		<c:when test="${cate == '103' || cate == '103-1' || cate == '103-2' }">
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103"><strong>라이프</strong></a>
			     		</li>		     		
			     		<%-- <li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103-1">구두</a>
			     		</li>				     
			     		<li class="breadcrumb-item active" aria-current="page">
			     			<a href="${pageContext.request.contextPath}/shop/list?cateNum=103-2">기타신발</a>
			     		</li>	 --%>
					</c:when>
	     		 </c:choose>
			   
			  </ol>
			</nav>
   		 	<!-- 카테고리 구분  END-->
   		 	
   		 	<!-- 상품별 카테고리 목록  -->	
   		 	<!-- 시작엔덱스와 끝인덱스 변수선언 -->
   		 	<c:set var="index" value="0"/>
			<c:set var="index_2" value="2"/>
			
   		 	<c:forEach begin="0" end="${cateSize}" step="1" varStatus="status">				
	   		 	<div class="row" id="Main_row">
	   		 		<%-- <h1>끝번호 : ${status.current}</h1> --%>
	   		 		<c:forEach items="${cateProductList}" var="list" begin="${index}" end="${index_2}" step="1" varStatus="status_2">
				        <div class="col-4" id="Main_col">
				          <a href="${pageContext.request.contextPath}/shop/detail?n=${list.product_number}">
				          	<img src="${pageContext.request.contextPath}/imgUpload/${list.stored_thumbNail}" class="thumbImg"/>
				          </a>			          
				          <strong  id="Prod_name" >${list.product_name}</strong><br>
				          <span>(<fmt:formatNumber value="${list.product_price}" pattern="###,###,###"/>)</span>
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
		    
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
  </body>
  	  

</html>


