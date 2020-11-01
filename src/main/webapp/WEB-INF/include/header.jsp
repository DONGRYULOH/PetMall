<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%--  <div class="masthead">
	 	<div id="leftbar">
	 		<c:if test="${User == null}">
	  			<h5>
	  				<a href="login">로그인</a>
	  			</h5>
	     		<h5>
	     			<a href="register">회원가입</a>
	     		</h5>
     		</c:if>
     		<c:if test="${User != null}">
     			<h5>
     				<a href="/member/signout">로그아웃</a>
     			</h5>
     		</c:if>
     		<h5>장바구니</h5>
		</div>
		
		<input type="text" value="${User.user_id}"/>
		
	 	<div id="logo">	 
	 		<img src="${pageContext.request.contextPath}/resources/images/logo.jpg" class="img-responsive" width="360" height="200">
    	 </div>
	    <nav>
	      <ul class="nav nav-justified">
	        <li><a href="#">Home</a></li>
	        <li><a href="#">제품페이지</a></li>
	        <li><a href="#">회사소개</a></li>
	        <li><a href="#">관리자 페이지</a></li>
	      </ul>
	    </nav>
   </div>	 --%>
 	<nav class="navbar navbar-expand-lg navbar-dark bg-default" style="height: 62px;">
          <div class="container">
            
            	<img src="${pageContext.request.contextPath}/resources/images/logo.jpg" style="width:20%; height:100px;">
            
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-info" aria-controls="navbar-info" aria-expanded="false" aria-label="탐색 전환">
              <span class="navbar-toggler-icon"></span>
            </button>
            
         
              
            <div class="collapse navbar-collapse" id="navbar-info">
              <div class="navbar-collapse-header">
                <div class="row">
                  <div class="col-6 collapse-brand">
                    <a href="javascript:void(0)">
                      <img src="../../assets/img/brand/blue.png">
                    </a>
                  </div>
                  <div class="col-6 collapse-close">
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-info" aria-controls="navbar-info" aria-expanded="false" aria-label="탐색 전환">
                      <span></span>
                      <span></span>
                    </button>
                  </div>
                </div>
              </div>
              <ul class="navbar-nav ml-auto">      
                <c:if test="${User == null}">
	                <li class="nav-item">
	                  <a class="nav-link nav-link-icon" href="${pageContext.request.contextPath}/login">
	                    <span class="nav-link-inner--text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">로그인</font></font></span>
	                  </a>
	                </li>
	                <li class="nav-item">
	                  <a class="nav-link nav-link-icon" href="${pageContext.request.contextPath}/register">
	                    <span class="nav-link-inner--text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">회원가입</font></font></span>
	                  </a>
	                </li>
	             </c:if>
	             <c:if test="${User != null}">
	             	<li class="nav-item">
	             		 <a class="nav-link nav-link-icon" href="#">
		             		 <span class="nav-link-inner--text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
		             		  ${User.user_name}님 
		             		 </font></font></span>
						</a>		             		 
					</li>
					<c:if test="${User.verify == 9}">
						<a class="nav-link nav-link-icon" href="${pageContext.request.contextPath}/admin/index">
							관리자 화면
						</a>	
					</c:if>
	             	<a class="nav-link nav-link-icon" href="${pageContext.request.contextPath}/signOut">
                   		 <span class="nav-link-inner--text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">로그아웃</font></font></span>
                  	</a>
     			</c:if>
     			 <li class="nav-item">
                  <a class="nav-link nav-link-icon" href="${pageContext.request.contextPath}/shop/cart/cartList">
                    <span class="nav-link-inner--text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">장바구니</font></font></span>
                  </a>
                </li>
              </ul>
            </div>
            
          </div>
        </nav>
        
          <nav class="navbar navbar-expand-lg navbar-dark bg-primary rounded">
               <div class="row">
             		<div class="col-lg-2">
		                 <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
		                 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#nav-inner-primary" aria-controls="nav-inner-primary" aria-expanded="false" aria-label="Toggle navigation">
		                   <span class="navbar-toggler-icon"></span>
		                 </button>
                   </div>
                     
                  <div class="col-lg-10">  
	                 <div class="collapse navbar-collapse" id="nav-inner-primary">
	                 	<!-- 화면 크기가 작아졌을때 다음코드 생성(반응형) -->
	                   <div class="navbar-collapse-header">
	                     <div class="row">
	                       <div class="col-6 collapse-brand">
	                         <a href="./index.html">
	                           <img src="./assets/img/brand/blue.png">
	                         </a>
	                       </div>
	                       <div class="col-6 collapse-close">
	                         <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#nav-inner-primary" aria-controls="nav-inner-primary" aria-expanded="false" aria-label="Toggle navigation">
	                           <span></span>
	                           <span></span>
	                         </button>
	                       </div>
	                     </div>
	                   </div>
	                   <ul class="navbar-nav ml-lg-auto">	
	                   	<li class="nav-item dropdown">
	                       <a class="nav-link dropdown-toggle" href="javascript:;" id="nav-inner-primary_dropdown_1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
	                       <div class="dropdown-menu" aria-labelledby="nav-inner-primary_dropdown_1">
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=100">ALL</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=101">상의</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=102">하의</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=103">신발</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=104">시계</a>
	                       </div>
	                     </li>
	                     <li class="nav-item">
	                       <a class="nav-link" href="javascript:;">고객센터</a>
	                     </li>
	                     <li class="nav-item">
	                       <a class="nav-link" href="javascript:;">게시판</a>
	                     </li>                    
	                   </ul>
	                 </div>
                 </div>
               </div>
           </nav>
                
                
       