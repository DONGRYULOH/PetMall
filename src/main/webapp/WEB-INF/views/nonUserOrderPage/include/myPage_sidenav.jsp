<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<nav class="sidenav navbar navbar-vertical  fixed-left  navbar-expand-xs navbar-light bg-white" id="sidenav-main">
    <div class="scrollbar-inner">
      <!-- Brand -->
      <div class="sidenav-header  align-items-center">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/index">
          <img src="${pageContext.request.contextPath}/resources/assets/img/brand/blue.png" class="navbar-brand-img" alt="...">
        </a>
      </div>
      <div class="navbar-inner">
        <!-- Collapse -->
        <div class="collapse navbar-collapse" id="sidenav-collapse-main">
          <!-- Nav items -->
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/myPage/nonUserOrderList?orderNumber=${orderNum}">
                <i class="ni ni-tv-2 text-primary"></i>
                <span class="nav-link-text">주문내역조회</span>
              </a>
            </li>       
          </ul>
          <!-- Divider -->
          <hr class="my-3">
        
        </div>
      </div>
    </div>
  </nav>