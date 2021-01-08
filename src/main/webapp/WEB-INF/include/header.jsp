<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

 	<nav class="navbar navbar-expand-lg navbar-dark bg-default" id="Main_navbar">
          <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
  
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-info" aria-controls="navbar-info" aria-expanded="false" aria-label="탐색 전환">
              <span class="navbar-toggler-icon"></span>
            </button>                   
              
            <div class="collapse navbar-collapse" id="navbar-info">
              <div class="navbar-collapse-header">
                <div class="row">
                  <div class="col-6 collapse-brand">
                    <a href="javascript:void(0)">
                      <img src="${pageContext.request.contextPath}/resources/images/logo.jpg">
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
	             		 <a class="nav-link nav-link-icon" href="${pageContext.request.contextPath}/myPage">
		             		 <span class="nav-link-inner--text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">
		             		  ${User.user_nickname}님 MyPage
		             		 </font></font></span>
						</a>		             		 
					</li>
					<c:if test="${User.user_role == 9}">
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
        
        <div class="mt-2 py-3 text-center">
            <img src="${pageContext.request.contextPath}/resources/images/logo.jpg" style="width:20%; height:100px;">
        </div>
        
        <!-- (상품페이지,고객센터,QnA) 메뉴바 -->
        <div>
         <nav class="navbar navbar-expand-lg navbar-dark bg-primary rounded" id="Navbar_2">
               <div class="row" id="Navbar_row">
               		<!-- 화면 크기가 작아졌을때 다음코드 생성(반응형) -->
             		<div class="col-lg-2">
             		  <ul class="navbar-toggler"  data-toggle="collapse" data-target="#nav-inner-primary" aria-controls="nav-inner-primary" aria-expanded="false" aria-label="Toggle navigation">	
	                   	<li class="nav-item dropdown" id="NavItem-1">
	                       <a class="nav-link dropdown-toggle" href="javascript:;" id="nav-inner-primary_dropdown_1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                       <strong style="color:#F2F2F2;">Shop</strong>
	                       </a>
	                       <div class="dropdown-menu" aria-labelledby="nav-inner-primary_dropdown_1">
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=100">ALL</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=101">봄/여름</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=102">가을/겨울</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=103">라이프</a>
	                       </div>
	                     </li>
	                     <li class="nav-item" id="NavItem-2">
	                       <a class="nav-link" href="#">
	                       		<strong style="color:#F2F2F2;">고객센터</strong>
	                       	</a>
	                     </li>
	                     <li class="nav-item" id="NavItem-3">
	                       <a class="nav-link" href="#">
	                       		<strong style="color:#F2F2F2;">문의게시판</strong>
	                       </a>
	                     </li>                    
	                   </ul>
                    </div> 
                     
                  <div class="col-lg-10" id="Navbar_menu">  
	                 <div class="collapse navbar-collapse" id="nav-inner-primary">
	                   <ul class="navbar-nav ml-lg-auto">	
	                   	<li class="nav-item dropdown" id="NavItem-1">
	                       <a class="nav-link dropdown-toggle" href="javascript:;" id="nav-inner-primary_dropdown_1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
	                       <div class="dropdown-menu" aria-labelledby="nav-inner-primary_dropdown_1">
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=100">ALL</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=101">봄/여름</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=102">가을/겨울</a>
	                         <a class="dropdown-item" href="${pageContext.request.contextPath}/shop/list?cateNum=103">라이프</a>
	                       </div>
	                     </li>
	                     <li class="nav-item" id="NavItem-2">
	                       <a class="nav-link" href="#">고객센터</a>
	                     </li>
	                     <li class="nav-item" id="NavItem-3">
	                       <a class="nav-link" href="#">게시판</a>
	                     </li>                    
	                   </ul>
	                 </div>
                 </div>
               </div>
           </nav>
         </div>       
                
       