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
  	  				<col width="15%"/> 
  	  				<col width="35%"/> 
  	  				<col width="15%"/> 
  	  				<col width="35%"/>  
  	  			</colgroup> 
  	  			<tbody> 
	  	  			<tr> 
	  	  				<th scope="row">글번호</th> 
	  	  				<td>${detailBoard.idx}</td> 
	  	  				<th scope="row">조회수</th> 
	  	  				<td>${detailBoard.hit_cnt}</td> 
	  	  			</tr>	
	  	  			<tr>	
	  	  				<th scope="row">제목</th> 
	  	  				<td colspan="3">${detailBoard.title}</td>
  	  				</tr> 
  	  				<tr>
  	  					<th scope="row">내용</th> 
  	  					<td colspan="3">${detailBoard.contents}</td>
  	  				</tr>
  	  				<tr>
  	  					<th scope="row">첨부파일</th>
  	  					<td colspan="3">
  	  						<c:forEach var="row" items="${fileList}">
	  	  						<input type="hidden" id="IDX" value="${row.idx}"> 
	  	  						<a href="${pageContext.request.contextPath}/board/fileDownload?n=${row.IDX}" name="file">${row.ORIGINAL_FILE_NAME }</a>
	  	  						(${row.FILE_SIZE }kb) 
  	  						</c:forEach>
  	  					</td>
  	  				</tr>
  	  			</tbody> 
  	  		</table>
			<a href="${pageContext.request.contextPath}/board/Update?n=${detailBoard.idx}">글수정하기</a>
			<a href="${pageContext.request.contextPath}/board/Write">글목록으로</a>

   		 	
   		 	
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
  </body>
  	  

</html>


