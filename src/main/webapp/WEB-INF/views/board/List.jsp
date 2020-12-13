<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  	  		
  	  			<h2>게시판 목록</h2> 
  	  		<table style="border:1px solid #ccc"> 
  	  			<colgroup> 
  	  				<col width="10%"/> 
  	  				<col width="*"/> 
  	  				<col width="15%"/> 
  	  				<col width="20%"/> 
  	  			</colgroup> 
  	  			<thead> 
  	  				<tr> 
	  	  				<th scope="col">글번호</th> 
	  	  				<th scope="col">제목</th> 
	  	  				<th scope="col">내용</th> 
	  	  				<th scope="col">조회수</th> 
  	  				</tr> 
  	  			</thead> 
  	  			<tbody> 
	  	  			<c:choose> 
	  	  				<c:when test="${fn:length(list) > 0}"> 
		  	  				<c:forEach items="${list }" var="row"> 
			  	  				<tr> 
			  	  					<td>${row.idx }</td>
			  	  					<!-- 해당 게시글 상세 페이지 이동  --> 			  	  					
			  	  					<td>
			  	  						<a href="${pageContext.request.contextPath}/board/Detail?n=${row.idx}">
			  	  						${row.title }
			  	  						</a>
			  	  					</td> 			  	  							  	  					
			  	  					<td>${row.contents }</td>
			  	  					<td>${row.hit_cnt }</td>
			  	  				</tr> 
		  	  				</c:forEach> 
	  	  				</c:when>
	  	  				<c:otherwise>
	  	  				    <tr> 
	  	  				    	<td colspan="4">조회된 결과가 없습니다.</td> 
	  	  				    </tr> 
	  	  				</c:otherwise> 
	  	  			</c:choose> 
  	  			</tbody> 
  	  		</table>
			<a href="${pageContext.request.contextPath}/board/Write">글작성하기</a>

   		 	
   		 	
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
  </body>
  	  

</html>


