<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>로그인 페이지</title>
	<%@ include file="/WEB-INF/include/head_import.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/include/header.jsp"%>
			<div>
			 <form  method="post">
			
			   <label >아이디</label>
			   <!--
			   		name은 서버로 보내질 아이디로 Dto 있는 변수명과 똑같이 매칭이 되야지 Dto에 담길수 있다.  
			    -->
			   <input type="text" id="user_id" name="user_id" required="required" />      
	
			   <label>패스워드</label>
			   <input type="password" id="user_pwd" name="user_pwd" required="required" />      
	  
			
			  
			  <button type="submit" >로그인</button>
			   <c:if test="${msg == false}">
			   	<script type="text/javascript">
			   		alert("로그인에 실패했습니다 아이디 또는 패스워드가 틀림");
			   	</script>
			   </c:if>
			 </form>   
			</div>
			
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
	
	<%@ include file="/WEB-INF/include/tail_import.jsp"%>
</body>
</html>