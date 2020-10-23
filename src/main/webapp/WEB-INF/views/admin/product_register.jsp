<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>상품등록 페이지</title>
   

	
   <%@ include file="/WEB-INF/views/admin/include/admin_head_import.jsp"%> 
    
  </head>
  <body>
  	<!-- sidenav 영역 -->
	<%@ include file="/WEB-INF/views/admin/include/admin_sidenav.jsp"%>
  	
  	<!-- 메인 컨텐츠 영역 -->
  	<div class="main-content" id="panel">
  	  
	  	<!-- topnav 영역 -->
		<%@ include file="/WEB-INF/views/admin/include/admin_topnav.jsp"%>

			
  	  		<h1>상품등록 페이지</h1>
  	  		<!-- enctype="multipart/form-data" 파일을 올릴시 필수조건  -->
		    <form role="form" method="post" autocomplete="off" enctype="multipart/form-data">

				<div class="inputArea"> 
				 <label>1차 분류</label>
				 <select class="category1" name="product_category">
				  <option value="">전체</option>
				 </select>
				
				 <label>2차 분류</label>
				 <select class="category2" name="product_category_2">
				  <option value="">전체</option>
				 </select>
				</div>
				
				<div class="inputArea">
				 <label for="gdsName">상품명</label>
				 <input type="text" id="product_name" name="product_name" />
				</div>
				
				<div class="inputArea">
				 <label for="gdsPrice">상품가격</label>
				 <input type="text" id="product_price" name="product_price" />
				</div>
				
				<div class="inputArea">
				 <label for="gdsStock">상품수량</label>
				 <!-- type="number" 하면 숫자만 입력할수있음 -->
				 <input type="number" id="product_stock" name="product_stock" min="0" max="1000">
				</div>
				
				<div class="inputArea">
				 <label for="gdsDes">상품소개</label>
				 <textarea rows="5" cols="50" id="product_desc" name="product_desc"></textarea>
				</div>
				
				<div class="inputArea">
					 <label for="gdsImg">이미지</label>
					 <input type="file" id="gdsImg" name="file" />
					 <div class="select_img"><img src="" /></div>
				</div>
				<!-- 저장되는 파일 실제경로 추출하기 -->
				<%=request.getRealPath("/") %>
				
				<div class="inputArea">
				 <button type="submit" id="register_Btn" class="btn btn-primary">등록</button>
				</div>
			
			</form>
					
   		 
   		 
   		
   		 
    		<%@ include file="/WEB-INF/include/footer.jsp"%>
	    	
    </div>
    	
    	<%@ include file="/WEB-INF/views/admin/include/admin_tail_import.jsp"%> 
    	<!-- 상품수량,가격 숫자만 입력할수있는 정규표현식  -->
    	<script>
			var regExp = /[^0-9]/gi;
			
			$("#product_price").keyup(function(){ numCheck($(this)); });
			$("#product_stock").keyup(function(){ numCheck($(this)); });
			
			function numCheck(selector) {
				 var tempVal = selector.val();
				 selector.val(tempVal.replace(regExp, ""));
			}
		</script>
    	
    	<script>
			 var ckeditor_config = {
			   resize_enaleb : false,
			   enterMode : CKEDITOR.ENTER_BR,
			   shiftEnterMode : CKEDITOR.ENTER_P,
			   //CK에디터로 업로드될 주소  
			   filebrowserUploadUrl : "${pageContext.request.contextPath}/admin/ckUpload"
			 };
			 //textarea 상품소개를 CK에디터로 바꿈 
			 CKEDITOR.replace("product_desc", ckeditor_config);
		</script>

    	 <script>
			  $("#gdsImg").change(function(){
			   if(this.files && this.files[0]) {
			    var reader = new FileReader;
			    reader.onload = function(data) {
			     $(".select_img img").attr("src", data.target.result).width(500);        
			    }
			    reader.readAsDataURL(this.files[0]);
			   }
			  });
		 </script>
    	
    	<script>
		// 컨트롤러에서 데이터 받기
		//var result = '${category}';
		//console.log("카테고리 데이터"+result);
		var jsonData = JSON.parse('${category}');
		
		
		
		var cate1Arr = new Array();
		var cate1Obj = new Object();
		
		console.log("카테고리이름"+cate1Obj.cateName);
		// 1차 분류 셀렉트 박스에 삽입할 데이터 준비
		for(var i = 0; i < jsonData.length; i++) {
		 
		 if(jsonData[i].level == "1") {
		  cate1Obj = new Object();  //초기화
		  cate1Obj.cateCode = jsonData[i].category_code;
		  cate1Obj.cateName = jsonData[i].category_name;
		  cate1Arr.push(cate1Obj);
		 }
		}
		
		//console.log("카테고리이름"+cate1Obj.cateName);
		console.log(cate1Arr);
		
		// 1차 분류 셀렉트 박스에 데이터 삽입
		var cate1Select = $("select.category1")
		
		for(var i = 0; i < cate1Arr.length; i++) {
		 cate1Select.append("<option value='" + cate1Arr[i].cateCode + "'>"
		      + cate1Arr[i].cateName + "</option>"); 
		} 
		
		$(document).on("change", "select.category1", function(){

			 var cate2Arr = new Array();
			 var cate2Obj = new Object();
			 
			 // 2차 분류 셀렉트 박스에 삽입할 데이터 준비
			 for(var i = 0; i < jsonData.length; i++) {
			  
			  if(jsonData[i].level == "2") {
			   cate2Obj = new Object();  //초기화
			   cate2Obj.cateCode = jsonData[i].category_code;
			   cate2Obj.cateName = jsonData[i].category_name;
			   cate2Obj.cateCodeRef = jsonData[i].category_code_ref;
			   
			   cate2Arr.push(cate2Obj);
			  }
			 }
			 
			 var cate2Select = $("select.category2");

			 cate2Select.children().remove();

			 $("option:selected", this).each(function(){
			  
			  var selectVal = $(this).val();  // 1차분류 카테고리 코드 
			  //console.log("this가 뭐지??"+selectVal);
			  cate2Select.append("<option value=''>전체</option>");
			  
			  for(var i = 0; i < cate2Arr.length; i++) {
			   if(selectVal == cate2Arr[i].cateCodeRef) {
			    cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
			         + cate2Arr[i].cateName + "</option>");
			   }
			  }
			  
			 });
			 
		});
		</script>
  </body>
  	  
</html>



