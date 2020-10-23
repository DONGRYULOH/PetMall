<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                  <a class="nav-link nav-link-icon" href="#">
                    <span class="nav-link-inner--text"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">장바구니</font></font></span>
                  </a>
                </li>
              </ul>
            </div>
            
          </div>
        </nav>
        
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary mt-4">
          <div class="container">
            <h3 style="background-color: # 212529;">홈</h3>
			<h3>회사소개</h3>
         
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                	상품
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 54px, 0px); top: 0px; left: 0px; will-change: transform;">
                  <a class="dropdown-item" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">동작</font></font></a>
                  <a class="dropdown-item" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">또 다른 행동</font></font></a>
                  <a class="dropdown-item" href="#"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">여기에 다른 것</font></font></a>
                </div>
            </div>
            
            <h3>고객센터</h3>
          </div>
        </nav>