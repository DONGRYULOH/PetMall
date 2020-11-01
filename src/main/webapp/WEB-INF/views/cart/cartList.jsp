<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>도르가 무역 메인 페이지</title>
   
	 
    <%@ include file="/WEB-INF/include/head_import.jsp"%>
	<style>

		 		.left-box {
		  background: red;
		  float: left;
		}

		
	</style>
  </head>
  <body>
  	 
  	 <div class="container"> 
  	  
  	    	<%@ include file="/WEB-INF/include/header.jsp"%>
  	  		
  	  		<h2>장바구니 확인</h2>
			    <c:choose>
			        <c:when test="${cartListCount == 0}">
			            	장바구니가 비어있습니다.
			        </c:when> 
			        <c:otherwise>	
			        	
			       			<div class="allCheck">
							   <input type="checkbox" name="allCheck" id="allCheck" /><label for="allCheck">모두 선택</label> 
							 </div>
							  
							 <div class="delBtn">
							   <button type="button" class="selectDelete_btn">선택 삭제</button> 
						  	</div><br>		
						 
						    
							
			              <c:forEach var="a" items="${cartList}" >
			             
			                	<div>
			                		<div class='left-box'> 
						                  <div>
										   	<input type="checkbox" name="chBox"  value="${a.cart_num}" />
										  </div> 
									 
										<div>
									   		<img src="${pageContext.request.contextPath}/imgUpload${a.product_ThumbImg}" />
									   </div>
									</div> 
									
									<div>
										 <span>상품명:</span>${a.product_name}<br>
										  <span>개당 가격:</span><fmt:formatNumber pattern="###,###,###" value="${a.product_price}" /> 원<br />
										   <div>
										      	구입수량 : <input type="number" style="width: 40px" name="cart_stock" id="cart_stock" value="${a.cart_stock}" min="1">
											     <!-- 수정시 해당 카트번호와 변경할 수량을 같이 들고가야됨 -->
						                        <button type="button" id="cartUpdate">수정</button>
					                        </div>
					                         <span>최종 가격:</span><fmt:formatNumber pattern="###,###,###" value="${a.product_price * a.cart_stock}" /> 원
									</div>
									
									 <div>
									    	<a href="${pageContext.request.contextPath}/shop/cart/cartDelete?cart_num=${a.cart_num}" class="delete_btn">삭제</a>
									   </div> 
									
									   
							     </div><br>
							 
							 </c:forEach>   
								
							
							   <div>
							   		장바구니 금액 합계 : <fmt:formatNumber pattern="###,###,###" value="${total}"/><br>
						                        배송료 : ${fee}<br>
						                        전체 주문금액  :<fmt:formatNumber pattern="###,###,###" value="${total_fee}"/>
							   </div> 

			        
			        </c:otherwise>
			    </c:choose>

   		 	
   		 	
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
    	 
    	 
  </body>
  	 	
										<!--  <script>
						                        $(document).on('click', '#cartUpdate', function(e){
						                			e.preventDefault();
						                			
						                			var cart_num = ${cartList.cart_num};
						                			var cart_stock = document.getElementById("cart_stock").value;
						                			
						                			console.log("카트번호"+cart_num);
						                			console.log("물품수량"+cart_stock);
						                	
						                			var url = "${pageContext.request.contextPath}/shop/cart/cartUpdate";
						                			url = url + "?cart_num=" + cart_num
						                			url = url + "&cart_stock=" + cart_stock
						                							            
						                			location.href = url;
						                			alert("수량이 변경되었습니다!");
						                			
						                			console.log(url);
						                	
						                		});	 
					                        </script> 		 -->				

</html>


