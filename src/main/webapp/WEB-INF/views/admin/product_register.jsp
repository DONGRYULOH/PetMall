<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

			
  	  		<!-- <h2>1차분류 선택후 2차분류를 무조건 선택해야됨!!!</h2> -->
  	  		<!-- enctype="multipart/form-data" 파일을 올릴시 필수조건  -->
		    <form role="form" method="post" autocomplete="off" enctype="multipart/form-data">
					
				<!-- 1차분류 선택후 2차분류를 무조건 선택해야됨!!!  -->
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
				
				
				<div class="inputArea" id="fileDiv">
					 <label for="gdsImg">(대표 썸네일 체크는 1개만 선택가능) <strong>*대표 썸네일 무조건 체크하기!!</strong></label>
					 
					 <!-- 파일업로드 방법1) -->
					 <!-- <input type="file" id="image" accept="image/*" onchange="setThumbnail(event);" multiple/> 
					 <div id="image_container"></div> -->

					 <!-- 파일업로드 방법2) -->
					 <p>
						 <!-- 여러개의 파일을 업로드할경우 -->
						 <input type="file" id="upload_0" name="upload_0" />
						 <a href="#this" class="btn" id="delete" name="delete">삭제</a>
						 <img id="fileImg_0" src="" width="500" /> 
						 <input id="delegate_thumbNail_0" name="delegate_thumbNail" type="checkbox" value="N"/>대표 썸네일 체크
						 <!-- 체크되지 않은 값도 가져오기 위해 hidden으로 체크박스 선택이 N인 것을 추출함 (체크 박스 선택시 체크를 안하면 서버에서 못받음) -->
						 <!-- 참고 : https://stackoverflow.com/questions/11729564/httpservlet-request-getparameter-of-unchecked-and-checked-check-boxes-in-java -->
						 <input type="hidden" id="checkList" name="checkList" value="N" />
					 </p>						 
	 				
	                  
	               
	                
				</div>
				
				<!-- 저장되는 파일 실제경로 추출하기 -->
				<%=request.getRealPath("/") %>
				
				<a href="#this" class="btn" id="addFile">파일 추가</a>
				<div class="inputArea">
				 <button type="submit" id="register_Btn" class="btn btn-primary">상품등록</button>
				</div>
			
			</form>
					
   		 
   		 
   		
   		 
    		<%@ include file="/WEB-INF/include/footer.jsp"%>
	    	
    </div>
    	
    	<%@ include file="/WEB-INF/views/admin/include/admin_tail_import.jsp"%> 
    	<!--  
    		어렵게 짜놓은 코드는 나중에 다시 볼때 이해하기 편하게 주석을 달아놓는 습관을 가지자!!! 
    	-->
    	
    	<!-- 여러개 이미지 파일 미리보는 방법 1 -->
    	<script type="text/javascript">
    	function setThumbnail(event) { 
    		$("#image_container").empty();
    		
    		
    		for (var image of event.target.files) { 
    			var reader = new FileReader(); 
    			reader.onload = function(event){ 
    				var img = document.createElement("img"); 
    				img.setAttribute("src", event.target.result); 
    				document.querySelector("div#image_container").appendChild(img); 
    			}; 
    			console.log(image); 
    			reader.readAsDataURL(image); 
    		} 
    	}
			// 참고 : https://sinna94.tistory.com/entry/JavaScript-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%85%EB%A1%9C%EB%93%9C-%EB%AF%B8%EB%A6%AC%EB%B3%B4%EA%B8%B0-%EB%A7%8C%EB%93%A4%EA%B8%B0
    	</script>
    	
    	<!-- 여러개 이미지 파일 미리보는 방법 2 -->
    	<script type="text/javascript"> 
	  		var gfv_count = 1; //파일 Name 번호 
	  		var gfn_count = 1; //파일 ID 번호 
	  		var gfm_count = 1; //파일 이미지 번호
	  		var delegate_thumbNail = 1; //대표썸네일 체크여부 번호 
	  		
	  		 //모든 HTML 태그가 렌더링 된후 아래 함수를 정의한다 
			  $(document).ready(function(){ 
				 
				  //파일 추가 버튼 
				  $("#addFile").on("click", function(e){ 
					  e.preventDefault(); 
					  fn_addFile(); 
					}); 
				
				  //삭제 버튼 
				  $("a[name='delete']").on("click", function(e){ 
					  e.preventDefault(); 
					  fn_deleteFile($(this)); 
					});
				  
				 //대표 썸네일 체크여부 클릭시 실행함수
				$("input[name='delegate_thumbNail']").on("click", function(e){ 
				  		fn_thumbNailCheck($(this)); 
				  });  
	
			  });
	  	  		
	  		 // 파일추가 버튼 클릭시 실행함수 
			  function fn_addFile(){ 
				  var fileInput = "<p><input type='file' id='upload_"+(gfn_count)+"' name='upload_"+(gfv_count++)+"'><a href='#this' class='btn' name='delete'>삭제</a><img id='fileImg_"+(gfm_count)+"' src='' width='500'><input id='delegate_thumbNail_"+(delegate_thumbNail)+"' name='delegate_thumbNail' type='checkbox' value='N'/>대표 썸네일 체크<input type='hidden' id='checkList' name='checkList' value='N' /></p>";   
				  $("#fileDiv").append(fileInput);
				  
				  //해당 파일 삭제 버튼함수 
				  $("a[name='delete']").on("click", function(e){ 
					    e.preventDefault(); 
				  		fn_deleteFile($(this)); 
				  }); 
				  
				 var thumbNail_id = document.getElementById('delegate_thumbNail_' + delegate_thumbNail);
				 
				 // 바닐라 자바스크립트 + 제이쿼리 혼용가능 
				 thumbNail_id.onclick = function(e){
					    console.log("체크 여부 실행함수 실행중...");
					    //e.preventDefault(); 
				  		fn_thumbNailCheck($(this)); 
				 }
				  
				  var fileImg = 'fileImg_' + gfm_count; //파일이미지 ID 
				  var fileId = 'upload_' + gfn_count; //해당파일ID 
				  var file = document.getElementById(fileId);
				  
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
				  
				  delegate_thumbNail++;
				  gfn_count++;
				  gfm_count++;
			  }
			
	  		 // 파일 삭제 버튼클릭시 실행함수
			  function fn_deleteFile(obj){ 
				  obj.parent().remove(); 
			  }
	  		 
			  // 대표 썸네일 체크여부 클릭시 실행함수
			  function fn_thumbNailCheck(obj){ 
				  
				  console.log( "해당 썸네일 체크여부 클릭시 값"+obj.val()); // 해당 썸네일 체크여부 클릭시 값
				  console.log( "체크 여부시 클릭값의 히든값:"+obj.parent("p").children("#checkList").val());  
				  
				  if(obj.val() == 'N'){
					  obj.val('Y'); 
					  obj.parent("p").children("#checkList").val('Y');
				  }else if(obj.val() == 'Y'){
					  obj.val('N');
					  obj.parent("p").children("#checkList").val('N');
				  }
			  }
  	  </script>
  	  	
  	     <!-- 파일선택후 이미지 업로드시 선택한 이미지를 보여줌(기본값)  -->
    	 <script>
    	 	var file = document.getElementById('upload_0');
    	 	
    	 	//ID가 upload 파일의 이벤트가 발생할경우 실행 
    	 	file.onchange = function(e){
    	 		
    	 		const fileList = file.files; //선택한 파일들이 파일정보가 List형태로 저장됨 
    	 		console.log("파일 리스트 길이"+fileList.length);

    	 		//파일 읽기 (파일을 읽을수 있도록 도와주는 FileReader객체 선언 )
	 			var fileReader = new FileReader();
    	 		
    	 		let count = 0;
    	 		var cnt = 0;
    	 		//fileList에 저장된 파일들을 하나씩 꺼내옴 
    	 		for(const file of fileList){
    	 			
    	 			var srcName = "fileImg_"+count;
    	 			console.log("이름은??:"+srcName);
    	 			console.log("파일크기:"+file.size);

    	 			// readAsDataURL 함수를 사용해서 이미지파일을 읽음 
    	 			fileReader.readAsDataURL(fileList[cnt]);

    	 		  fileReader.onload = function() { 
    	 		    document.getElementById(srcName).src = fileReader.result;
    	 		  }
    	 		  
    	 		  count++;
    	 		  cnt++;
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
		// 컨트롤러에서 데이터 받기
		//var result = '${category}';
		//console.log("카테고리 데이터"+result);
		var jsonData = JSON.parse('${category}');
		
		var cate1Arr = new Array();
		var cate1Obj = new Object();
		
		console.log("카테고리이름"+cate1Obj.cateName);
		// 1차 분류 셀렉트 박스에 삽입할 데이터 준비(최상위 부모인것들-상의,하의,신발)
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
		
		//상위 카테고리(1차분류)의 SELECT가 변경되면 함수 실행 
		$(document).on("change", "select.category1", function(){

			 var cate2Arr = new Array();
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
				  console.log("2차분류 객체형태는??"+cate2Obj);
			 }
			 
			 // 2차분류에 들어갈 태그를 가져와서 변수에 담음 
			 var cate2Select = $("select.category2");
			 
			 //기존 2차분류 셀렉트에 들어가 있던 값을 모두 지움 
			 cate2Select.children().remove();
			 
			 // 2차분류 셀렉트에 1차분류에서 상위목록 선택시 해당되는 하위목록들을 넣어줌  ex) 상의(1차분류) -> 셔츠,반팔(2차분류)
			 $("option:selected", this).each(function(){
				  var selectVal = $(this).val();  // 1차분류 카테고리 코드 
				  console.log("this가 뭐지??"+selectVal);
				  cate2Select.append("<option value=''>전체</option>");
				  
				  for(var i = 0; i < cate2Arr.length; i++) {
						// 1차분류 부모카테고리 코드와 2차분류의 카테고리 참조값이 똑같을 경우에만실행 
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



