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
		 	 float: left;
		}
	</style>
  </head>
  <body>
  	 
  	 <div class="container"> 
  	  
  	    	<%@ include file="/WEB-INF/include/header.jsp"%>
  	  		<h1>장바구니 확인하세요</h1>
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
						 
						    
							
			              <c:forEach var="list" items="${cartList}" varStatus="status">
			             
			                	<div>
			                		<div class='left-box'> 
						                  <div>
										   	<input type="checkbox" name="chBox"  value="${list.cart_num}" />
										  </div> 
									 
										<div>
									   		<img src="${pageContext.request.contextPath}/imgUpload${list.product_ThumbImg}" />
									   </div>
									</div> 
									
									<div>
										 <span>상품명:</span>${a.product_name}<br>
										  <span>개당 가격:</span><fmt:formatNumber pattern="###,###,###" value="${list.product_price}" /> 원<br />
										   <div>
										      	구입수량 : <input type="number" style="width: 40px" name="cart_stock" id="cart_stock${status.index}"  value="${list.cart_stock}" <%-- onkeyup="go${status.index}(this.value)" --%>>
										      <!-- 	<input type="hidden"  name="cart_stock" id="cart_stock"  min="1"> -->
										      	<input type="hidden" id="cart_num" value="${list.cart_num}"/>
										      	<%-- <a href="${pageContext.request.contextPath}/shop/cart/cartUpdate?cart_num=${list.cart_num}&cart_stock='document.getElementById('cart_stock').value'"></a> --%>										      	
											     <!-- 수정시 해당 카트번호와 변경할 수량을 같이 들고가야됨 -->													     									  
						                         <button id="cartUpdateBtn${status.index}" <%-- onClick="cartUpdate${status.index}(document.getElementById('cart_num').value,document.getElementById('cart_stock').value)" --%>>수정</button>	
					                        </div>
					                         <span>최종 가격:</span><fmt:formatNumber pattern="###,###,###" value="${list.product_price * list.cart_stock}" /> 원
					                     
									</div>
									
									 <div>
									    	<a href="${pageContext.request.contextPath}/shop/cart/cartDelete?cart_num=${list.cart_num}" class="delete_btn">삭제</a>
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
  	<!-- 서버단에서 반복문을 돌려서 수정을 처리하는 방법도 있음(서버가 하는일이 더많아짐) -->
  	 <script type="text/javascript">
  	 	var step = 0;
  	 	for(step = 0; step < ${cartListCount} ; step++){
  	 		console.log("현재인덱스"+step);
  	 		
  	 		(function(step){
  	 			var btn = document.getElementById('cartUpdateBtn'+step);
	 			console.log($(btn).parent().html());
  	 			var cart_num = $(btn).parent().children("#cart_num").val();
  	 			/* var cart_stock = $(btn).parent().children("#cart_stock").val(); */
  	 			
  	 			//cartUpdateBtn0.onClick -> 이거랑 똑같게 됨 
  	 			btn.onclick = function () {
  	 				console.log("현재인덱스"+step);
  	 	            console.log("카트번호"+cart_num);
  	 	            var a = document.getElementsByName("cart_stock")[step].value;
  	 	     		console.log("수정한 물품수량"+a);
  	 	     		
	  	 	     	var url = "${pageContext.request.contextPath}/shop/cart/cartUpdate";
	    			url = url + "?cart_num=" + cart_num
	    			url = url + "&cart_stock=" + a
	    							            
	    			location.href = url;
	    			alert("수량이 변경되었습니다!");
  	 	        }
  	 			
  	 			
  	 			/* var stock = document.getElementById('cart_stock'+step);
  	 			
  	 			stock.onkeyup = function () {
  	 				$(btn).parent().children("#cart_stock").val(cart_stock);
  	 				console.log(stock);
  	 	        } */
  	 			
  	 			/* var name = go + step;
  	 			function name(a){
  	 				consolo.log("함수이름"+name);
  	 				consolo.log("a"+a);
  	 				$(btn).parent().children("#cart_stock").val(a);
  	 			} */
  	 			
  	 		})(step);
  	 	}
  	 
  	 </script>
			
</html>


