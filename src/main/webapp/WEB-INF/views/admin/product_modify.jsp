<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
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

			
  	  		<h1>상품수정 페이지</h1>
		    <form role="form" method="post" autocomplete="off" enctype="multipart/form-data">
				<!--  수정과 삭제시 번호를 받아서 할꺼기 떄문에 hidden 으로 숨겨놈 -->
				<input type="hidden" name="product_number" value="${product.product_number}" />
				
				<div class="inputArea"> 
				 <label>1차 분류</label>
				 <select class="category1" name="category_code">
				  <option value="">전체</option>
				 </select>
				
				 <label>2차 분류</label>
				 <select class="category2" name="category_code_ref">
				  <option value="">전체</option>
				 </select>
				</div>
				
				<div class="inputArea">
				 <label for="gdsName">상품명</label>
				 <input type="text" id="product_name" name="product_name" value="${product.product_name}"/>
				</div>
				
				<div class="inputArea">
				 <label for="gdsPrice">상품가격</label>
				 <input type="text" id="product_price" name="product_price" value="${product.product_price}"/>
				</div>
				
				<div class="inputArea">
				 <label for="gdsStock">상품수량</label>
				 <!-- type="number" 하면 숫자만 입력할수있음 -->
				 <input type="number" id="product_stock" name="product_stock" value="${product.product_stock}">
				</div>
				
				<div class="inputArea">
				 <label for="gdsDes">상품소개</label>
				 <textarea rows="5" cols="50" id="product_desc" name="product_desc" >${product.product_desc}</textarea>
				</div>
				
				<!-- 해당 상품에 대한 상품이미지 첨부파일 목록 -->
				<div id="fileDiv">
				     <label for="gdsImg">(대표 썸네일 체크는 1개만 선택가능)</label>
  			 		<c:forEach var="row" items="${ThumbImg}" varStatus="var">		  	  			  
	  			 		<div>
	 	  			 		<input type="hidden" id="IDX" name="IDX_${var.index }" value="${row.file_number }"> <!-- 해당이미지 파일의 고유번호 --> 
	 	  			 		<input type="file" id="file_${var.index }" name="file_${var.index }">
	 	  			 		<img src="${pageContext.request.contextPath}/imgUpload/${row.stored_thumbNail}" id="name_${var.index }" name="name_${var.index }" width='500' />
	 	  			 		<a href="#this" class="btn" id="delete_${var.index }" name="delete_${var.index }">삭제</a>
	 	  			 		<!-- 대표이미지 체크여부 -->
	 	  			 		 <c:if test="${row.delegate_thumbNail == 'N' }">
	 	  			 		 	 <input id="delegate_thumbNail_${var.index }" name="delegate_thumbNail" type="checkbox" value="N"/>대표 썸네일 체크
	 	  			 		 	 <input type="hidden" class="checkList" name="checkList" value="N" />
	 	  			 		 </c:if>
	 	  			 		 <c:if test="${row.delegate_thumbNail == 'Y' }">
	 	  			 		 	 <input id="delegate_thumbNail_${var.index }" name="delegate_thumbNail" type="checkbox" value="Y" checked/>대표 썸네일 체크
	 	  			 		 	 <input type="hidden" class="checkList" name="checkList" value="Y" />
	 	  			 		 </c:if>
	  			 		 </div>
 	  			 	</c:forEach>
 	  			 </div>
				<!-- 저장되는 파일 실제경로 추출하기 -->
				<%=request.getRealPath("/") %>
				
				<a href="#this" class="btn" id="addFile">파일 추가</a>
				<div class="inputArea">
				  	<button type="submit" id="update_Btn" class="btn btn-primary">완료</button>
 					<button type="button" id="back_Btn" class="btn btn-warning">취소</button> 
				</div>
			
			</form>
					
   		 
   		 
   		
   		 
    		<%@ include file="/WEB-INF/include/footer.jsp"%>
	    	
    </div>
    	
    	<%@ include file="/WEB-INF/views/admin/include/admin_tail_import.jsp"%> 
    	
    	<!-- 여러개 이미지 파일 미리보는 방법 2 -->
    	<script type="text/javascript"> 
    		// 되도록이면 하나의 변수를 선언하고 사용하는게 좋음 여러개의 변수를 선언하게되면 그만큼 메모리를 차지하기 때문에 성능이 안좋아질수 있음
    	    var gfv_count = '${fn:length(ThumbImg)}'; //파일 Name 번호 (가져온 썸네일 사이즈 ex)썸네일이미지가 2장일경우 길이는 2임 )
	  		var gfn_count = '${fn:length(ThumbImg)}'; //파일 ID 번호 
	  		var gfm_count = '${fn:length(ThumbImg)}'; //파일 이미지 번호
	  		var tumImag_count = '${fn:length(ThumbImg)}'; 
	  		
	  		 //모든 HTML 태그가 렌더링 된후 아래 함수를 정의한다 
			  $(document).ready(function(){ 
				 
				  //파일 추가 버튼 
				  $("#addFile").on("click", function(e){ 
					  e.preventDefault(); 
					  fn_addFile(); 
					}); 
				
				  //삭제 버튼 
				  $("a[name^='delete']").on("click", function(e){ 
					  e.preventDefault(); 
					  fn_deleteFile($(this)); 
					});
				
				  //<기존의 해당 이미지파일에 관한 함수> 
				  for(var i=0;i<gfv_count;i++){
					  var Id = 'file_' + i; //해당파일ID 
					  var file = document.getElementById(Id);
					  var fileImg = 'name_' + i; //파일이미지 ID 
					  
					  var thumbNail_id = document.getElementById('delegate_thumbNail_' + i);
					  // 대표 썸네일 체크 함수 실행
					 thumbNail_id.onclick = function(e){
						    console.log("체크 여부 실행함수 실행중...");
						    //e.preventDefault(); 
					  		fn_thumbNailCheck($(this)); 
					 }
					
					  //해당 파일의 이벤트가 발생할경우 실행되는 함수 
					  file.onchange = function(e){
						  
						  const fileList = file.files; 
						  
						  //파일 읽기 (파일을 읽을수 있도록 도와주는 FileReader객체 선언 )
				 		  var fileReader = new FileReader();
						  
				 		  // readAsDataURL 함수를 사용해서 이미지파일을 읽음 
		    	 		  fileReader.readAsDataURL(fileList[0]);
				 		   
				 		  // 읽은 이미지 파일을 화면에 표시함
		    	 		  fileReader.onload = function() { 
		     	 		    document.getElementById(fileImg).src = fileReader.result;
		     	 		  }
					
					  };
				  }
			  });
	  	  		
	  		 // <파일추가 버튼 클릭시 실행함수> 
			  function fn_addFile(){ 
				  var fileInput = "<div><input type='file' id='file_"+(gfn_count)+"' name='file_"+(gfv_count++)+"'>"
				  	  fileInput += "<a href='#this' class='btn' name='delete'>삭제</a><img id='fileImg_"+(gfm_count)+"' src='' width='500'>"
				  	  fileInput += "<input id='delegate_thumbNail_"+(tumImag_count)+"' name='delegate_thumbNail' type='checkbox' value='N'/>대표 썸네일 체크<input type='hidden' class='checkList' name='checkList' value='N' /></div>";
				  $("#fileDiv").append(fileInput);
				  
				  //해당 파일 삭제 버튼함수 
				  $("a[name='delete']").on("click", function(e){ 
					    e.preventDefault(); 
				  		fn_deleteFile($(this)); 
				  }); 
				  
				  var fileImg = 'fileImg_' + gfm_count; //파일이미지 ID 
				  var fileId = 'file_' + gfn_count; //해당파일ID 
				  var file = document.getElementById(fileId);
				 
				 //썸네일 체크 함수 
				 var thumbNail_id = document.getElementById('delegate_thumbNail_' + tumImag_count);					
				 thumbNail_id.onclick = function(e){
					    console.log("체크 여부 실행함수 실행중...");
					    //e.preventDefault(); 
				  		fn_thumbNailCheck($(this)); 
				 }
				 
				  //해당 파일의 이벤트가 발생할경우 실행되는 함수 
				  file.onchange = function(e){
					  console.log("파일 업로드중...");
					  const fileList = file.files; 
					  
					  //파일 읽기 (파일을 읽을수 있도록 도와주는 FileReader객체 선언 )
			 		  var fileReader = new FileReader();
					  
			 		  // readAsDataURL 함수를 사용해서 이미지파일을 읽음 
	    	 		  fileReader.readAsDataURL(fileList[0]);
			 		   
			 		  // 읽은 이미지 파일을 화면에 표시함
	    	 		  fileReader.onload = function() { 
	     	 		    document.getElementById(fileImg).src = fileReader.result;
	     	 		  }
				
				  };
				  
				  tumImag_count++;
				  gfn_count++;
				  gfm_count++;
			  }
			
	  		 // <파일 삭제 버튼클릭시 실행함수>
			  function fn_deleteFile(obj){ 
				  obj.parent().remove(); 
			  }
	  		 
			// <대표 썸네일 체크여부 클릭시 실행함수>
			  function fn_thumbNailCheck(obj){ 
				  
				  console.log( "해당 썸네일 체크여부 클릭시 값"+obj.val()); // 해당 썸네일 체크여부 클릭시 값
				  console.log( "체크 여부시 클릭값의 히든값:"+obj.parent("div").children(".checkList").val());  
				  
				  if(obj.val() == 'N'){
					  obj.val('Y'); 
					  obj.parent("div").children(".checkList").val('Y');
				  }else if(obj.val() == 'Y'){
					  obj.val('N');
					  obj.parent("div").children(".checkList").val('N');
				  }
			  }
  	  </script>
  	  
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
    	<!-- CK 에디터  -->
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
		 
		<!-- 카테고리 설정 부분 -->
    	<script>
	    	// 컨트롤러에서 데이터 받기
			//var result = '${category}';
			//console.log("카테고리 데이터"+result);
			var jsonData = JSON.parse('${category}');									
			var cate1Arr = new Array();
			var cate1Obj = new Object();
			
			// 1차 분류 셀렉트 박스에 삽입할 데이터 준비(최상위 부모인것들-상의,하의,신발)
			for(var i = 0; i < jsonData.length; i++) {
				 if(jsonData[i].level == "1") {
					  cate1Obj = new Object();  //초기화
					  cate1Obj.cateCode = jsonData[i].category_code;
					  cate1Obj.cateName = jsonData[i].category_name;
					  cate1Arr.push(cate1Obj);
				 }
			}
			
			// 1차 분류 셀렉트 박스에 데이터 삽입
			var cate1Select = $("select.category1");
			for(var i = 0; i < cate1Arr.length; i++) {
			  cate1Select.append("<option value='" + cate1Arr[i].cateCode + "'>"+ cate1Arr[i].cateName + "</option>"); 
			} 
			
			//2차분류 카테고리 1차분류가 선택되었을시 실행됨 (1차 분류인 상의(101)가 선택되었다고 가정해보자)
			$(document).on("change", "select.category1", function(){
				 //console.log("1차분류 선택함!");  // 오케 
				 var cate2Arr = new Array(); // 2차분류(Level2)에 해당되는 모든 카테고리 항목들이 List 형태로 저장이됨 
				 var cate2Obj = new Object();
				 
				// 2차 분류 셀렉트 박스에 삽입할 데이터 준비 ( 셔츠(상의),롤렉스(시계) ..등등 ) 
				 for(var i = 0; i < jsonData.length; i++) {
					  if(jsonData[i].level == "2") {
						   cate2Obj = new Object();  //초기화
						   cate2Obj.cateCode = jsonData[i].category_code;
						   cate2Obj.cateName = jsonData[i].category_name;
						   cate2Obj.cateCodeRef = jsonData[i].category_ref_code;
						   cate2Arr.push(cate2Obj);
					  }
				 }
				
				// 2차분류에 들어갈 태그를 가져와서 변수에 담음
				var cate2Select = $("select.category2");
				
				//기존 2차분류 셀렉트에 들어가 있던 값을 모두 지움 
				cate2Select.children().remove();
	
				 // 2차분류 셀렉트에 1차분류에서 상위목록 선택시 해당되는 하위목록들을 넣어줌  ex) 상의(1차분류) -> 셔츠,반팔(2차분류)
				 $("option:selected", this).each(function(){
				  
					  var selectVal = $(this).val();  // 선택한 1차분류 카테고리 코드값을 가져옴 (상의 -> 101 , 하의  -> 102) 
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
			
			//기존의 카테고리 코드(상품을 등록했을때의) 
	    	var select_cateCode = '${product.category_code}';
	    	var select_cateCodeRef = '${product.category_ref_code}';
	    	var select_cateName = '${product.category_name}';
	
	    	if(select_cateCodeRef != null && select_cateCodeRef != '') {
		    	 $(".category1").val(select_cateCodeRef);
		    	 $(".category2").val(select_cateCode);
		    	 $(".category2").children().remove(); //전체 옵션이 남아있기때문에 지워줘야됨
		    	 $(".category2").append("<option value='"
		    	       + select_cateCode + "'>" + select_cateName + "</option>");
	    	} else {
	    	 	$(".category1").val(select_cateCode);
	    	 	$(".category2").children().remove();
	    	 //$(".category2").val(select_cateCode);
	    	 // select_cateCod가 부여되지 않는 현상이 있어서 아래 코드로 대체
	    	 //$(".category2").append("<option value="' + select_cateCode + '" selected='selected'>전체</option>");
	    	}
		</script>
		<!-- 취소버튼클릭시 -->
		<script>
			 var formObj = $("form[role='form']");
			 $("#back_Btn").click(function(){
				  //  history.back(); 이걸사용하면 DB를 안거치고 빠르게 갈수있음 
				  history.back(); //이전페이지로 이동이랑 똑같음 
			 });   
		</script>

  </body>
  	  
</html>





