<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
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
  	  		
  	  		<!-- 대표 상품 슬라이드  -->
  	  		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="margin-bottom: 80px;">
			  <ol class="carousel-indicators">
			    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
			    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			  </ol>
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			     <img src="${pageContext.request.contextPath}/resources/images/b.jpg" title="Funky roots" style="width: 100%; height:500px;">
			    </div>
			    <div class="carousel-item">
			      <img src="${pageContext.request.contextPath}/resources/images/b.jpg" title="Funky roots" style="width: 100%; height:500px;">
			    </div>
			    <div class="carousel-item">
			      <img src="${pageContext.request.contextPath}/resources/images/b.jpg" title="Funky roots" style="width: 100%; height:500px;">
			    </div>
			  </div>
			  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div> 

   		 	<!-- 조회수가 가장 높은 상품순으로 상위9개 가져오기 -->
   		 	<c:set var="index" value="0"/>
			<c:set var="index_2" value="2"/>
			
   		 	<c:forEach begin="0" end="2" step="1" varStatus="status">
				
	   		 	<div class="row" style="margin-bottom: 50px;">
	   		 		<c:forEach items="${productRankNine}" var="list" begin="${index}" end="${index_2}" step="1" varStatus="status_2">
				        <div class="col-4">
				          <a href="${pageContext.request.contextPath}/shop/detail?n=${list.product_number}">
				          	<img src="${pageContext.request.contextPath}/imgUpload/${list.stored_thumbNail}" class="thumbImg"/>
				          </a>
				          <small class="d-block text-uppercase font-weight-bold mb-4" style="height: 5px;">${list.product_name}</small>
				          <!-- 가격 -->
						  <span style="font-size:16px;color:#000000;font-weight:bold;">
						  	<fmt:formatNumber pattern="###,###,###" value="${list.product_price}" />원
						  </span>
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
   		 	
   		 	
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
  </body>
  	  

</html>


