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
  	  		
  	  			<form id="frm" method="post" name="frm" enctype="multipart/form-data"> 
  	  			<table class="board_view"> 
	  	  			<colgroup> 
		  	  			<col width="15%"> 
		  	  			<col width="*"/> 
	  	  			</colgroup> 
	  	  			 <tbody> 
	  	  			 <tr> 
		  	  			 <th scope="row">제목</th> 
		  	  			 <td>
		  	  			 	<input type="text" id="TITLE" name="title" class="wdp_90"></input>
		  	  			 </td> 
	  	  			 </tr> 
	  	  			 <tr> 
	  	  			 	 <th scope="row">내용</th> 	
		  	  			 <td> 
		  	  			 	<textarea rows="20" cols="100" title="내용" id="CONTENTS" name="contents"></textarea> 
	  	  			 	</td> 
	  	  			 </tr> 
	  	  			 </tbody> 
  	  			 </table> 
	  	  			 <div id="fileDiv"> 
		  	  			 <p> 
		  	  			 	<input type="file" id="file" name="file_0"> 
		  	  			 	<a href="#this" class="btn" id="delete" name="delete">삭제</a> 
		  	  			 </p> 
	  	  			 </div>
	  	  			 <br/><br/> 
	  	  			 
	  	  			 <a href="#this" class="btn" id="addFile">파일 추가</a>
	  	  			 <input type="submit">작성하기
	  	  			 
  	  			 </form>




   		 	
   		 	
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
  </body>
  	  <script type="text/javascript"> 
  		var gfv_count = 1;
  		
  		 //모든 HTML 태그가 렌더링 된후 아래 함수를 정의한다 
		  $(document).ready(function(){ 
			 
			  //파일 추가 버튼 
			  $("#addFile").on("click", function(e){ 
				  e.preventDefault(); 
				  fn_addFile(); 
				}); 
			
			  //삭제 버튼 
			  $("a[name='delete']").on("click", function(e){ 
				  e.preventDefault(); 
				  fn_deleteFile($(this)); 
				});

		  });
  	  		
  		 // 파일추가 버튼 클릭시 실행함수 
		  function fn_addFile(){ 
			  var str = "<p><input type='file' name='file_"+(gfv_count++)+"'><a href='#this' class='btn' name='delete'>삭제</a></p>"; 
			  $("#fileDiv").append(str); 
			  $("a[name='delete']").on("click", function(e){ //삭제 버튼
				    e.preventDefault(); 
			  		fn_deleteFile($(this)); 
			  }); 
		  }
		
  		 // 파일 삭제 버튼클릭시 실행함수
		  function fn_deleteFile(obj){ 
			  obj.parent().remove(); 
		  }



  	  </script>
  		  



</html>


