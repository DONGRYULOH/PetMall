<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>MyPage 환불 작성 페이지 </title>
   

	
    <%@ include file="/WEB-INF/views/myPage/include/myPage_head_import.jsp"%>  
    
  </head>
  <body>
  	<!-- sidenav 영역 -->
	<%@ include file="/WEB-INF/views/myPage/include/myPage_sidenav.jsp"%>
  	
  	<!-- 메인 컨텐츠 영역 -->
  	<div class="main-content" id="panel">
  	  
	  	<!-- topnav 영역 -->
		<%@ include file="/WEB-INF/views/myPage/include/myPage_topnav.jsp"%>

			
  	  		<h2>환불처리 페이지</h2>
  	  		
		<!-- enctype="multipart/form-data" 파일을 올릴시 필수조건  -->
		<form role="form" method="post" autocomplete="off" enctype="multipart/form-data">  	  		
  	  		<div class="table-responsive">
  	  		
             <table class="table align-items-center table-flush">
                <thead class="thead-light">
                  <tr>
                       <th>상품이미지</th>
					   <th>상품명</th>
					   <th>주문금액(수량)</th>
					   <th>총 주문금액</th>
                  </tr>
                </thead>
                <tbody class="list">
                   <tr>
	                     <td style="width: 432px;">
				            <img src="${pageContext.request.contextPath}/imgUpload/${orderDetailInfo.stored_thumbNail}" class="thumbImg"/>
						 </td>
						 
						<!-- 상품명 -->
					    <td>
					    	<!-- 상품이름 클릭시 상세내역으로 이동(주문번호를 파라미터로 가지고 들어감) -->
					    	<h3>${orderDetailInfo.product_name}</h3>
					    </td>
					    
					    <!-- 주문금액(수량)-->
					    <td>
					    	 <!-- 상품가격 -->
					    	<fmt:formatNumber value="${orderDetailInfo.product_price}" pattern="###,###,###"/> 원 <br>
					    	<!-- 상품수량 -->
					    	<h3>${orderDetailInfo.product_count}개</h3>
					    </td>  
					    
					    <!-- 총 주문금액 -->
					    <td>
					    	 <fmt:formatNumber pattern="###,###,###" value="${orderDetailInfo.product_price * orderDetailInfo.product_count}" /> 원
					    </td>    
					             
	              </tr>             
                </tbody>
              </table>
            </div>  
              <!-- 환불하는 사유 입력/이미지 등록 -->
              <div class="row" style="margin-bottom: 50px;">
              		<div class="col-lg-3">
              			환불 사유 입력 
              		</div>
              		<div class="col-lg-9">
						 <input type="text" class="form-control" name="refund_reason" style="width: 526px;">              			
              		</div>
              </div>
               <div class="row" style="margin-bottom: 20px;">
              		<div class="col-lg-3">
              			환불 사진 등록<br>
              			(대표사진 1장만 등록가능)
              		</div>
              		<div class="col-lg-9">
						 <!-- 하나의 이미지  파일만 업로드가능 -->
						 <input type="file" id="refund_img" name="refund_img" />
						 <!-- 선택한 파일 미리보기 -->  
						 <img id="refundImg" src="" width="500" />           			
              		</div>
              </div>
                <!-- 주문상세정보(해당상품 주문번호) -->
	  	  		<input type="hidden" name="order_detail_number" value="${orderDetailInfo.order_detail_number}"/>
                <button type="submit" id="register_Btn" class="btn btn-primary">환불요청하기</button>
           </form>   
    		<%@ include file="/WEB-INF/include/footer.jsp"%>
	    	
    </div>
    	
    <%@ include file="/WEB-INF/views/myPage/include/myPage_tail_import.jsp"%> 

  </body>
  	  
  	   <!-- 파일선택후 이미지 업로드시 선택한 이미지를 보여줌(기본값)  -->
    	 <script>
    	 	var file = document.getElementById('refund_img');
    	 	
    	 	//ID가 upload 파일의 이벤트가 발생할경우 실행 
    	 	file.onchange = function(e){
    	 		
    	 		const fileList = file.files; //선택한 파일들이 파일정보가 List형태로 저장됨 
    	 		console.log("파일 리스트 정보"+fileList[0]);

    	 			//파일 읽기 (파일을 읽을수 있도록 도와주는 FileReader객체 선언 )
	 				var fileReader = new FileReader();
    	 		
    	 			// readAsDataURL 함수를 사용해서 이미지파일을 읽음 
    	 			fileReader.readAsDataURL(fileList[0]);

    	 		  fileReader.onload = function() { 
    	 		    document.getElementById("refundImg").src = fileReader.result;
    	 		  }
    	 	}
		 </script> 
</html>






