<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!-- top 네브바 영역  --> 		
  	 <nav class="navbar navbar-top navbar-expand navbar-dark bg-primary border-bottom">
      <div class="container-fluid">
        	<div class="collapse navbar-collapse" id="navbarSupportedContent">
        	
	        	<ul class="navbar-nav align-items-center  ml-md-auto ">
		            <li class="nav-item d-xl-none">
		              <!-- Sidenav toggler -->
		              <div class="pr-3 sidenav-toggler sidenav-toggler-dark" data-action="sidenav-pin" data-target="#sidenav-main">
		                <div class="sidenav-toggler-inner">
		                  <i class="sidenav-toggler-line"></i>
		                  <i class="sidenav-toggler-line"></i>
		                  <i class="sidenav-toggler-line"></i>
		                </div>
		              </div>
		            </li>
		            <!-- <li class="nav-item d-sm-none">
		              <a class="nav-link" href="#" data-action="search-show" data-target="#navbar-search-main">
		                <i class="ni ni-zoom-split-in"></i>
		              </a>
		            </li> -->
		                      
	         	 </ul>
	         	 <c:if test="${User.verify == 9}">
	         		<h2 class="h2 text-white d-inline-block mb-0">
						<a href="${pageContext.request.contextPath}/admin/index" style="margin-right: 10px;">
							관리자 페이지
						</a>
					</h2>
					<h2 class="h2 text-white d-inline-block mb-0">
						<a href="${pageContext.request.contextPath}/" style="margin-right: 10px;">
							메인 
						</a>
					</h2>
					<h2 class="h2 text-white d-inline-block mb-0">
						<a href="${pageContext.request.contextPath}/signOut">
							로그아웃
						</a>	
					</h2>
				</c:if>
	         	
        	</div>
      </div>
     </nav>