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
  	  		<h2>장바구니</h2>
			    <c:choose>
			        <c:when test="${cartListCount == 0}">
			            	장바구니가 비어있습니다.
			        </c:when> 
			        <c:otherwise>	
			        	
			       			<div class="allCheck">
							   <input type="checkbox" name="allCheck" id="allCheck" /><label for="allCheck">모두 선택 또는 모두 선택해제 </label> 
							 </div>
							  
							 <div class="delBtn">
							   <button type="button" id="selectDelete">선택 삭제(체크박스에 체크한것만 삭제)</button> 
						  	</div><br>		
						 
						    
							
			              <c:forEach var="list" items="${cartList}" varStatus="status">
			                	<div class="row" style="margin-bottom: 30px;">
			                	
			                		<div class="col-6"> 				                
									   	<input type="checkbox" name="chBox[]"  value="${list.cart_number}" />
									   	<input type="hidden" id="cart_num" value="${list.cart_number}"/>
								   		<img src="${pageContext.request.contextPath}/imgUpload/${list.stored_thumbNail}" />
									</div> 
									
									<div class="col-6">
										 <span class="name mb-0 text-sm">상품명:</span>${list.product_name}<br>
										  <span>개당 가격:</span><fmt:formatNumber pattern="###,###,###" value="${list.product_price}" /> 원<br />
										   <div>
										      	구입수량 : <input type="number" style="width: 40px" name="product_count" id="product_count${status.index}"  value="${list.product_count}"/>										      
										      	<input type="hidden" id="cart_num" value="${list.cart_number}"/>										      											      	
											     <!-- 수정시 해당 카트번호와 변경할 수량을 같이 들고가야됨 -->													     									  
						                         <button id="cartUpdateBtn${status.index}">수정</button>	
					                        </div>
					                         <span>최종 가격:</span><fmt:formatNumber pattern="###,###,###" value="${list.product_price * list.product_count}" /> 원
					                     
					                     <a href="${pageContext.request.contextPath}/shop/cart/cartDelete?cart_number=${list.cart_number}" class="delete_btn">삭제</a>
									</div>
									
								</div>	
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
  	   var cartListCount = '${cartListCount}'; // 장바구니 상품리스트 사이즈 
  	 
  	 	for(var step = 0;step < cartListCount;step++){
  	 		console.log("현재인덱스"+step);
  	 		
  	 		(function(step){
  	 			var btn = document.getElementById('cartUpdateBtn'+step); // cartUpdateBtn0 , cartUpdateBtn1 ..
	 			console.log($(btn).parent().html());
  	 			
  	 			var cart_number = $(btn).parent().children("#cart_num").val();
  	 			/* var cart_stock = $(btn).parent().children("#cart_stock").val(); */
  	 			
  	 			//cartUpdateBtn0.onClick -> 이거랑 똑같게 됨 (수정하기 버튼클릭시 실행되는 함수)
  	 			btn.onclick = function () {
  	 				
  	 	            var a = document.getElementsByName("product_count")[step].value;
  	 	            console.log("현재인덱스"+step);
	 	            console.log("카트번호"+cart_num);
  	 	     		console.log("수정할 물품수량"+a);
  	 	     		
	  	 	     	var url = "${pageContext.request.contextPath}/shop/cart/cartUpdate";
	    			url = url + "?cart_number=" + cart_number;
	    			url = url + "&product_count=" + a;
	    							            
	    			location.href = url;
	    			alert("수량이 변경되었습니다!");
  	 	        }
  	 		})(step);
  	 	}
  	 	
  	   
  	   // 모두 선택 또는 모두 선택 해제 체크박스 함수 
  	   var check = false; //모든선택을 체크를 안한상태라면 false 상태임 
  	   document.getElementById('allCheck').onclick = function() {
  		 var ck =  document.getElementsByName("chBox[]"); //chBox를 배열형태로 만들어서 변수에 저장시킴 
  		 console.log("체크 박스 총길이"+ck.length);
  		   
  		   if(check == false){ // 체크박스를 모두체크할수 있는경우 
  				console.log("체크 박스 모두 체크하기 ");
  				check = true;
  				for(var i=0;i<ck.length;i++){
  					ck[i].checked = true; 
  				}
  		   }else{ // 체크박스를 모두 해제할수 있는 경우 
  			 	console.log("체크 박스 모두 해제하기 ");
  			 	check = false;
				for(var i=0;i<ck.length;i++){
					ck[i].checked = false; 
				}
  		   }
  	   }
  	   
  	   // 선택삭제 버튼 클릭시 삭제 함수 
  	   var DeleteCartList = []; //삭제할 카트번호를 담을 배열 선언 
  	   document.getElementById('selectDelete').onclick = function(){
	  		 var ck =  document.getElementsByName("chBox[]"); //chBox를 배열형태로 만들어서 변수에 저장시킴 
	  		 console.log("체크 박스 총길이"+ck.length);
	  		 
	  		for(var i=0;i<ck.length;i++){
	  			if(ck[i].checked == true){ //체크박스에 체크가 되어있는 상태인경우(삭제할 상품인경우)
	  				console.log("삭제할 장바구니 카트번호:"+$(ck[i]).parent().children("#cart_num").val() ); //삭제할 장바구니의 카트번호를 가져옴 
	  				DeleteCartList.push($(ck[i]).parent().children("#cart_num").val());
	  			}	
			}
	  		
	  		//삭제될 카트번호 출력하기 (정상적으로 수행됨)
	  		for(var i=0;i<DeleteCartList.length;i++){
	  			console.log("삭제할 카트번호 출력 : "+DeleteCartList[i]);	
	  		}
	  		
	  		var object = {
	  				"DeleteCartList" : DeleteCartList
	  		};
	  		
	  		//삭제할 카트번호를 배열로 담아서 컨트롤러로 보내기 (https://huskdoll.tistory.com/902)
	  		$.ajax({
			    url : "${pageContext.request.contextPath}/shop/cart/selectDelete",
			    dataType    :   "json",
			    contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
			    type : "post",
			    data : object,
			    success : function(result){
			    	console.log("결과값 출력:"+result);
			    	if(result == 1){
				     	alert("선택한 상품 삭제 성공");
				     	var url = "${pageContext.request.contextPath}/shop/cart/cartList";            
		    			location.href = url;
			    	}else{
			    		alert("선택한 상품 삭제 실패");
			    	}
			    },
			    error : function(){
			     	alert("선택한 상품 삭제 실패");
			    }
		    });
	  		
  	   }
  	   
  	 </script>
			
</html>


