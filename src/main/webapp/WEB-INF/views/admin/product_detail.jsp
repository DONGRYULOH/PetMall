<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>상품등록 페이지</title>
   

	
   <%@ include file="/WEB-INF/views/admin/include/admin_head_import.jsp"%> 
    
  </head>
  <body>
  	<!-- sidenav 영역 -->
	<%@ include file="/WEB-INF/views/admin/include/admin_sidenav.jsp"%>
  	
  	<!-- 메인 컨텐츠 영역 -->
  	<div class="main-content" id="panel">
  	  
	  	<!-- topnav 영역 -->
		<%@ include file="/WEB-INF/views/admin/include/admin_topnav.jsp"%>

			
  	  		<h1>상품 세부 목록</h1>
  	  		<form role="form" method="post" autocomplete="off">
  	  			<!--  수정과 삭제시 번호를 받아서 할꺼기 떄문에 hidden 으로 숨겨놈 -->
				<input type="hidden" name="n" value="${product.product_number}" />
				
				<div class="inputArea"> 
				 <label>1차 분류</label>
				 <span class="category1">${product.category_ref_code}</span> 
				</div>
				
				<div class="inputArea">        
				 <label>2차 분류</label>
				 <span class="category2">${product.category_name}</span>
				</div>
				
				<div class="inputArea">
				 <label for="gdsName">상품명</label>
				 <span>${product.product_name}</span>
				</div>
				
				<div class="inputArea">
				 <label for="gdsPrice">상품가격</label>
				 <span><fmt:formatNumber value="${product.product_price}" pattern="###,###,###"/></span>
				</div>
				
				<div class="inputArea">
				 <label for="gdsStock">상품수량</label>
				 <span>${product.product_stock}</span>
				</div>
				
				<div class="inputArea">
				 <label for="gdsDes">상품소개</label>
				 <span>${product.product_desc}</span>
				</div>
				
				<label for="gdsImg">상품 이미지</label>
				<div class="inputArea">
				   
				    <c:forEach items="${ThumbImg}" var="list" varStatus="status">
				    	<h3>${status.count}</h3>
				    	<img src="${pageContext.request.contextPath}/imgUpload/${list.stored_thumbNail}" class="thumbImg"/>
				    </c:forEach>     

				</div>
				
				<div class="inputArea">
				 <!-- 아래방식으로 해도됨 -->
				 <%-- <a href="${pageContext.request.contextPath}/admin/product_modify?n=${product.product_number}">수정</a> --%>
				 <button type="button" id="modify_Btn" class="btn btn-warning">수정</button>
				 <button type="button" id="delete_Btn" class="btn btn-danger">삭제</button>
				</div>
			
			</form>

 		
   		 
    		<%@ include file="/WEB-INF/include/footer.jsp"%>
	    	
    </div>
    	
    	<%@ include file="/WEB-INF/views/admin/include/admin_tail_import.jsp"%> 

		<script>
			  var formObj = $("form[role='form']");
			  
			  $("#modify_Btn").click(function(){
				   formObj.attr("action", "${pageContext.request.contextPath}/admin/product_modify");
				   formObj.attr("method", "get")
				   formObj.submit();
			  });
			  
			  $("#delete_Btn").click(function(){    
				  
				  var con = confirm("정말로 삭제하시겠습니까?");
				  
				  if(con) {      
				   formObj.attr("action","${pageContext.request.contextPath}/admin/product_delete");
				   formObj.submit();
				  }

			  });
		 </script>
  </body>
  	  
</html>





