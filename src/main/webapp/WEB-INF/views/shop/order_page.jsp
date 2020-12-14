<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>도르가 무역 주문서 페이지</title>
   
	 
    <%@ include file="/WEB-INF/include/head_import.jsp"%>
    
	
	
	
  <body>
  
  	 <div class="container"> 
  	  
  	    	<%@ include file="/WEB-INF/include/header.jsp"%>
   		 	
	  	  	<div class="card" style="margin-top: 30px;">
	            <div class="card-header">
	              <h3 class="mb-0">주문/결제</h3>
	            </div>

	            <!-- 배송지 정보  -->
	            <div class="card-body">
	              <h3 class="ct-title">배송지 정보</h3>
	              <div class="row">
	              	 <div class="col-lg-3" style="margin-top: 20px;">배송지 선택</div>
	              	 <div class="col-lg-9">
	              	 	<div class="nav-wrapper">
	              	 		 <!-- 기존 또는 신규 배송지인지 확인여부 (처음에 주문페이지로 들어오면 "UserD"로 되있음 )  
					            	value 값이 UserD -> 기존 배송지 , NewD -> 신규 배송지						            	
					            -->
					        <input type="hidden" id="D_Check" value="UserD" />
					        
						    <ul class="nav nav-pills nav-fill flex-column flex-md-row" id="tabs-icons-text" role="tablist">
						    	<!-- 기존(회원) 배송지 -->
						        <li class="nav-item">
						            <a class="nav-link mb-sm-3 mb-md-0 active" id="tabs-icons-text-1-tab" data-toggle="tab" href="#tabs-icons-text-1" role="tab" aria-controls="tabs-icons-text-1" aria-selected="true">고정(회원) 배송지</a>						      
						        </li>
						        <!-- 신규 배송지 -->
						        <li class="nav-item">
						            <a class="nav-link mb-sm-3 mb-md-0" id="tabs-icons-text-2-tab" data-toggle="tab" href="#tabs-icons-text-2" role="tab" aria-controls="tabs-icons-text-2" aria-selected="false">신규 배송지</a>
						        </li>
						    </ul>
						</div>
	              	 </div>
	              </div>
	              <hr class="my-4">
	              
	              <div class="card shadow">
				    <div class="card-body">
				        <div class="tab-content" id="myTabContent">
				         	<!-- 회원(기존) 배송지  -->
				            <div class="tab-pane fade show active" id="tabs-icons-text-1" role="tabpanel" aria-labelledby="tabs-icons-text-1-tab">
				               	  <div class="row">
					              	 <div class="col-lg-3">수령인</div>
					              	 <div class="col-lg-9">정우찬</div>
					              </div>
					              <hr class="my-4">
					              <div class="row">
					              	 <div class="col-lg-3">전화번호</div>
					              	 <div class="col-lg-9">010-ㅌㅌㅌㅌ-ㅌㅌㅌㅌ</div>
					              </div>
					              <hr class="my-4">
					              <div class="row">
					              	 <div class="col-lg-3">배송지 주소</div>
					              	 <div class="col-lg-9">나사 기지 공항국</div>	              	 
					              </div>
				            </div>
				            <!-- 신규 배송지  -->
				            <div class="tab-pane fade" id="tabs-icons-text-2" role="tabpanel" aria-labelledby="tabs-icons-text-2-tab">
				                <div class="row">
					              	 <div class="col-lg-3">수령인</div>
					              	 <div class="col-lg-9">
					              	 	<input type="text" id="new_receiver">
					              	 </div>
					              </div>
					              <hr class="my-4">
					              <div class="row">
					              	 <div class="col-lg-3">전화번호</div>
					              	 <div class="col-lg-9">
					              	 	<div id="cellPhone">
					              	 		<input type="text" maxlength="3" id="new_phone_first">-
					              	 		<input type="text" maxlength="4" id="new_phone_mid">-
					              	 		<input type="text" maxlength="4" id="new_phone_last">
					              	 	</div>
					              	 </div>
					              </div>
					              <hr class="my-4">
					              <div class="row">
					              	 <div class="col-lg-3">배송지 주소</div>
					              	 <div class="col-lg-9">
					              	 	<input type="text"  id="new_sample6_postcode" style="width:100px;" maxlength="5" readonly="readonly" placeholder="우편번호">
										<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
										<input type="text" id="new_sample6_address" placeholder="주소" style="width: 47%;" readonly="readonly"><br>
										<input type="text" id="new_sample6_detailAddress" style="width: 47%;" placeholder="상세주소">
					              	 </div>	              	 
					              </div>
				            </div>
				            
				        </div>
				    </div>
				</div>
	              
	               
	              
	              
	            </div>
	            <!-- 배송지 정보 Card-body END -->
	             
	             
	            <!-- 주문자 정보  -->
	            <div class="card-body">
	              <h3 class="ct-title">주문자 정보</h3>
	              <div class="row">
	              	 <div class="col-lg-3">이름</div>
	              	 <div class="col-lg-9">오동률</div>
	              </div>
	              <hr class="my-4">
	               <div class="row">
	              	 <div class="col-lg-3">전화번호</div>
	              	 <div class="col-lg-9">010-xxxx-xxxx</div>
	              </div>
	              <hr class="my-4">
	              <div class="row">
	              	 <div class="col-lg-3">이메일</div>
	              	 <div class="col-lg-9">admin@naver.com</div>	              	 
	              </div>
	            </div>
	            
	        </div>
	        
	        <!-- 상품 정보  -->
	        <div class="card" style=" margin-top: 30px;">
	            <div class="card-header">
	              <h3 class="ct-title">상품 정보</h3>
	            </div>
	            
	            <div class="table-responsive">
		             <table class="table align-items-center table-flush">
		                <thead class="thead-light">
		                  <tr>
		                       <th>상품이미지</th>
		                       <th>상품명</th>
							   <th>수량</th>
							   <th>가격</th>
							   <th>총합금액</th>
		                  </tr>
		                </thead>
		                <tbody class="list">
		                   
		                   <tr>
			                    <td>
				                   	상품이미지 
							    <td>
							    	상품이름 : 아이언맨 슈트 
							    </td>
							    <td> 10개 </td>
							    <td>
							    	10만원 
							    </td>				
							    <td>
							    	100만원		
							    </td>                
			              </tr>    
		                        
		                </tbody>
		              </table>
           		 </div>
	        </div>
				
   		 	 <!-- 결제 정보   -->
	        <div class="card" style="margin-top: 30px;">
	            <div class="card-header">
	              <h3 class="ct-title">결제 정보</h3>
	            </div>
	        </div>
	        
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
    	 
    	
		
  </body>
  	  
  	 <script type="text/javascript">
  	 
  	 	/* 고정 배송지 클릭시 실행 함수 */
	  	 document.getElementById('tabs-icons-text-1-tab').onclick = function(){
	  		 console.log("고정 배송지 클릭함!");
	  		 console.log("배송지 값"+$(this).parent().parent().parent().children("#D_Check").val());
	  		 $(this).parent().parent().parent().children("#D_Check").val("UserD"); //고정배송지를 뜻하는 UserD로 바꿔줌 
	  	 };
  	 
	  	/* 신규 배송지 클릭시 실행 함수 */
	  	document.getElementById('tabs-icons-text-2-tab').onclick = function(){
	 		 console.log("신규 배송지 클릭함!");
	 		 console.log("배송지 값"+$(this).parent().parent().parent().children("#D_Check").val());
	 		 $(this).parent().parent().parent().children("#D_Check").val("NewD"); //신규배송지를 뜻하는 NewD로 바꿔줌 
	 	 };
  	 
  	 
 
  	 </script>
  	 
  	  <!-- 다음 우편변호API 사용 -->
  	<script type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    function sample6_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수
	
	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }
	                	//경기 화성시 동탄순환대로20길 45(주소정보 추가)
	                	document.getElementById("sample6_address").value = addr;
	                	
	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다. 
	                    // 경기 화성시 동탄순환대로20길 45 + 참고항목 (목동, 동탄 금강펜테리움 센트럴파크Ⅳ) 
	                    // 즉.. 아파트명이나 무슨무슨동을 추가 
	                    document.getElementById("sample6_address").value += extraAddr;
	                
	                } else {
	                    document.getElementById("sample6_address").value = addr;
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample6_postcode').value = data.zonecode;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("sample6_detailAddress").focus();
	            }
	        }).open();
	    }
	</script>	
	
	
		
</html>










































