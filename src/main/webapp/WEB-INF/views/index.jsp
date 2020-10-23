<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  	  		
  	  		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
			  <ol class="carousel-indicators">
			    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
			    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			  </ol>
			  <div class="carousel-inner">
			    <div class="carousel-item active">
			     <img src="${pageContext.request.contextPath}/resources/images/a.jpg" title="Funky roots" style="width: 100%; height:500px;">
			    </div>
			    <div class="carousel-item">
			      <img src="${pageContext.request.contextPath}/resources/images/b.jpg" title="Funky roots" style="width: 100%; height:500px;">
			    </div>
			    <div class="carousel-item">
			      <img src="${pageContext.request.contextPath}/resources/images/c.jpg" title="Funky roots" style="width: 100%; height:500px;">
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
			
  	  		
			
  	  		
		      
		<%-- <div class="row">
				  <c:forEach var="productList" items="${productList}" begin="0" end="3"> 
		  		   <div>	
					
					 
					    <div class="col-md-3">
					      <div class="thumbnail">
					        <a href="/w3images/lights.jpg" target="_blank">
					          <img src="${pageContext.request.contextPath}/resources/images/${productList.product_image}" style="width:100%; height:300px;" >
					          <div class="caption">
					            <p>${productList.product_name} </p>
					          </div>
					        </a>
					      </div>
					    </div>
					       
					
					  
		           </div>
		   		 </c:forEach>
   		 </div>
   		 
   		 <div class="row">
				  <c:forEach var="productList" items="${productList}" begin="4" end="7"> 
		  		   <div>	
					
					 
					    <div class="col-md-3">
					      <div class="thumbnail">
					        <a href="/w3images/lights.jpg" target="_blank">
					          <img src="${pageContext.request.contextPath}/resources/images/${productList.product_image}" style="width: 100%; height:300px;" >
					          <div class="caption">
					            <p>${productList.product_name} </p>
					          </div>
					        </a>
					      </div>
					    </div>
					       
					
					  
		           </div>
		   		 </c:forEach>
   		 </div>
   		 
   		 <div class="row">
				  <c:forEach var="productList" items="${productList}" begin="8" end="11"> 
		  		   <div>	
					
					 
					    <div class="col-md-3">
					      <div class="thumbnail">
					        <a href="/w3images/lights.jpg" target="_blank">
					          <img src="${pageContext.request.contextPath}/resources/images/${productList.product_image}" style="width:100%; height:300px;" >
					          <div class="caption">
					            <p>${productList.product_name} </p>
					          </div>
					        </a>
					      </div>
					    </div>
					       
					
					  
		           </div>
		   		 </c:forEach>
   		 </div> --%>
   		 
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
  </body>
  	  

</html>


