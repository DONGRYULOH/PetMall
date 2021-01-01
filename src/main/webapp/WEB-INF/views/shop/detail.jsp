<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>도르가 무역 메인 페이지</title>
   
	 
    <%@ include file="/WEB-INF/include/head_import.jsp"%>
    
	<style>
		 section.replyForm { padding:30px 0; }
		 section.replyForm div.input_area { margin:10px 0; }
		 section.replyForm textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:500px;; height:150px; }
		 section.replyForm button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
		 
		 section.replyList { padding:30px 0; }
		 section.replyList ol { padding:0; margin:0; }
		 section.replyList ol li { padding:10px 0; border-bottom:2px solid #eee; }
		 section.replyList div.userInfo { }
		 section.replyList div.userInfo .userName { font-size:24px; font-weight:bold; }
		 section.replyList div.userInfo .date { color:#999; display:inline-block; margin-left:10px; }
		 section.replyList div.replyContent { padding:10px; margin:20px 0; }
		 
		 section.replyList div.replyFooter button { font-size:14px; border: 1px solid #999; background:none; margin-right:10px; }
		 
		 div.replyModal { position:relative; z-index:1; display:none;}
		 div.modalBackground { position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0, 0, 0, 0.8); z-index:-1; }
		 div.modalContent { position:fixed; top:20%; left:calc(50% - 250px); width:500px; height:250px; padding:20px 10px; background:#fff; border:2px solid #666; }
		 div.modalContent textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:450px; height:150px; }
		 div.modalContent button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
		 div.modalContent button.modal_cancel { margin-left:20px; }
	</style>
	
	<!-- 댓글목록 가져오기 -->
	 <script> 

		function replyList(){
			 var p = ${product.product_number};	
			 var session_id = '${User.user_id}';
			 var session_nickname = '${User.user_nickname}';
			  
			 console.log("서버로부터 받은 현재PC세션의 아이디"+session_id);
			 console.log("서버로부터 받은 현재PC세션의 닉네임"+session_nickname);
			 
			 	  $.getJSON("${pageContext.request.contextPath}/shop/view/replyList?n=" + p, function(data){
				  var str = "";
				  
				  $(data).each(function(){
				   	
					   console.log("글작성자 닉네임"+this.writer_nickname);
					   
					   /* depth 체크 */
		   				let depthCss = "";
		   				let depthStyle="";
		   				let reCmt ="";
		   				
		   				if(this.reply_depth == 0){ //depth 0일경우 
		   					//답글쓰기 버튼 추가 
		   					reCmt += "<div id='replyForm'>";
		   					reCmt += "<button type='button' class='replyBtn' reply_num='" + this.reply_number + "'>답글달기</button>"
		   					reCmt += "</div>"
		   				}else{ //depth 1이상일경우 
		   					depthStyle = " background-color : #FFC5D0; width:250px;";
		   					depthCss = " padding-left: 50px;";
		   				}
		   				
		   			   //한국 시각으로 바꿈 
					   var reply_date = new Date(this.reporting_date);
					   reply_date = reply_date.toLocaleDateString("ko-US");
					   
					   str += "<li data-gdsNum='" + this.product_number + "' style='"+depthCss+"'>"
					     + "<div class='userInfo' style='"+depthStyle+"'>"
					     + 	"<span class='userName'>" + this.writer_nickname + "</span>"
					     + 	"<span class='date'>" + reply_date + "</span>"
					     + "</div>"
					     + "<div class='replyContent'>" + this.content + "</div>"		
	     					
					     //로그인 상태일때만 답글,수정,삭제 가능
					     //로그인을 한사람만 신고가 가능하다 아니다?? 
 						 if(session_id != null && session_id != ''){
 							 str += reCmt; 
					     }
					     		    
					     //로그인한 유저의 세션의 닉네임이 작성한 해당 상품에 작성한 닉네임과 똑같을경우 수정과 삭제가 가능 
					     if(session_nickname==this.writer_nickname){
						     str +=  "<div class='replyFooter'>"
						     //수정폼으로 이동시 
					    	 str += "<button type='button' class='modify' reply_num='" + this.reply_number + "'>수정</button>"
					    	 //삭제시 해당 댓글의 고유번호,참조번호,depth 를 받음 
					    	 str += "<button type='button' class='delete' reply_num='" + this.reply_number + "'  origin_ref='" + this.reply_orgin_number + "' group_layer='" + this.reply_depth + "'>삭제</button>"
					    	 str += "</div>"
					     }else{
					    	str += "<div class='replyFooter'>"
						    str += "<button type='button' class='report' reply_num='" + this.reply_number + "'>신고</button>"
						    str += "</div>"
					     }					     	
					    
					     
					     + "</li>";           
				  });
				  
				  $("section.replyList ol").html(str);
			 });
		}
	</script>	
	
  </head>
  <%
	Cookie[] c = request.getCookies();
	if (c != null) {
		for (Cookie cf : c) {
			
		}
	}
	%>
  <body>
  	 
  	 <div class="container"> 
  	  
  	    	<%@ include file="/WEB-INF/include/header.jsp"%>
   		 	

  	  		<form role="form" method="post" autocomplete="off">
  	  			<!--  수정과 삭제시 번호를 받아서 할꺼기 떄문에 hidden 으로 숨겨놈 -->
				<input type="hidden" name="n" value="${product.product_number}" /> 
			</form>
			
				<div class="row" style="margin-top: 30px;">
					<!-- 상품 이미지  -->
					<div class="col-8">
						
						<!-- 대표 이미지인 경우 -->
						<div class="thumbNail" style="float:left;">
							<img src="${pageContext.request.contextPath}/imgUpload/${delegate_image.stored_thumbNail}" id="image_0" style="width: 500px;height: 500px;" /> 			
						</div>
							
						<c:forEach items="${product_image}" var="imageList" varStatus="var" >					
							<!-- 기본 이미지인 경우 -->
							<div class="imageList">
								<img src="${pageContext.request.contextPath}/imgUpload/${imageList.stored_thumbNail}" class="subImage" id="image_${var.count}" style="width: 100px;padding-bottom: 10px;padding-left: 10px;"/> 
							</div>
						</c:forEach>
						<!-- 이미지 변환 함수 -->
						<script type="text/javascript">
							window.onload = function(){
								var imageCount = '${fn:length(product_image)}'; // 상품에 대한 이미지 총개수
								
								for(var i=1;i<imageCount+1;i++){
									
									var imageMouseOver = document.getElementById('image_'+i);
									
									//해당 이미지에 마우스를 올렸을경우 실행할 함수 
									imageMouseOver.onmouseover = function(e){
										//console.log($(this).parent("div").children(".subImage").attr('src'));
										var change = $(this).parent("div").children(".subImage").attr('src'); // 마우스를 오버한 이미지의 src 값을 가져옴 
										console.log($(this).parent().parent().children(".thumbNail").children("#image_0").attr('src',change)); // 메인 썸네일을 마우스를 오버한 이미지의 src값으로 교체
									};
								}
							};
						</script>
					</div>
					
					<div class="col-4">
						<div class="table-responsive">
							<table >
								<%-- <caption>기본 정보</caption> --%>
								<tbody>
									<tr class="#">
										<td colspan="2">
											<!-- 상품명 -->
											<span style="font-size:16px;color:#000000;font-weight:bold;">${product.product_name}</span>
										</td>
									</tr>
									<tr class="#">
										<td colspan="2">
											<!-- 간략한 상품설명 -->
											<span style="font-size:12px;color:#555555;">${product.product_desc}</span>
										</td>
									 </tr>
									<tr class="#">
										<td colspan="2">
											<!-- 가격 -->
											<span style="font-size:16px;color:#000000;font-weight:bold;"><fmt:formatNumber pattern="###,###,###" value="${product.product_price}" /> 원</span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>							
						<%-- <h4 class="display-4 mb-0">카테고리 : ${product.product_category}</h4> --%>
						<h4 class="display-4 mb-0" >
							 재고 : <fmt:formatNumber pattern="###,###,###" value="${product.product_stock}" /> EA
						</h4>
						<p class="cartStock">
						   <span>구입 수량</span>
						   <!-- Submit로 전송한것만 name값이 서버로 들어감?? Form태그 안에 있어야지 전송이 됨-->
						   <input type="number" min="1" max="${product.product_stock}" value="1" id="p_count" />  
						</p>
						<div>
							<div>
								<form action="${pageContext.request.contextPath}/shop/order_page_b" method="post">
								
				                    <input type="hidden" name="product_number" value="${product.product_number}" />
				                    <input type="hidden" id="product_count" name="product_count" value="1"/>  
				                  	<input class="btn btn-default" style="width: 228px;" type="submit" value="구매하기"> 
			                   </form>
							</div>
							<div>
							  <a href="#" class="btn btn-lg btn-white btn-icon mb-3 mb-sm-0">
			                    <span class="btn-inner--text" id="addCart_btn">ADD CART</span>
			                  </a>
			                  <!-- 장바구니 담기 함수  -->
			                   <script>
									  $("#addCart_btn").click(function(){
										var product_number = ${product.product_number};	
									    var product_count = $("#p_count").val(); 
									    console.log("상품번호"+product_number);
									    console.log("선택한 수량"+product_count);
									     var data = {
											    product_number : product_number,
											    product_count : product_count
									     	};
									   
										    $.ajax({
											    url : "${pageContext.request.contextPath}/shop/addCart",
											    type : "post",
											    data : data,
											    success : function(result){
											    	if(result == 1){
												     	alert("카트 담기 성공");
												     	$("#numBox").val("1");
											    	}else{
											    		alert("카트 담기 실패");
												     	$("#numBox").val("1");
											    	}
											    },
											    error : function(){
											     	alert("카트 담기 실패");
											    }
										    });
									  });
									  
									/* 상품 개수 선택칸의 값이 변경될경우 이벤트 함수 */
									$("#p_count").change(function(){
										console.log("상품 개수변경 ->"+$('#p_count').val());
										$('#product_count').val($('#p_count').val());
									});
									
								</script>
				              <a href="#" class="btn btn-lg btn-white btn-icon mb-3 mb-sm-0" >
			                     <span class="btn-inner--text">WISH LIST</span>
			                  </a>
			                </div>
						</div>					
					</div>
				</div>
				
				<!-- 전체 이미지 출력 -->
				 <c:forEach items="${product_image}" var="imageList" varStatus="var" >					
					<!-- 기본 이미지인 경우 -->
					<div class="imageList" style="position:absoulte;width:100%;height:100%;">
						<img src="${pageContext.request.contextPath}/imgUpload/${imageList.stored_thumbNail}" class="subImage" id="image_${var.count}" style="position:absoulte;width:100%;height:100%;margin-top: 30px;"/> 
					</div>
				 </c:forEach>
				 
				<div id="reply">
					<!-- 로그인 되지 않은 상태일떄 -->
					 <c:if test="${User == null }">
					  <p>소감을 남기시려면 <a href="${pageContext.request.contextPath}/login">로그인</a>해주세요</p>
					 </c:if>
					 <!-- 로그인이 된경우 -->
					 <c:if test="${User != null}">
						 <section class="replyForm">
						  <form role="form" method="post" autocomplete="off">
						  	 <!-- name 값을 DTO에 있는 컬럼이랑 다르게 작성하면 자동매칭이 안되서 값이 안들어감  -->
							  <input type="hidden" name="product_number" id="product_number" value="${product.product_number}">
							   <div class="input_area">
							    <textarea name="reply_content" id="reply_content"></textarea>
							   </div>
							   
							   <div class="input_area">
							    <!-- button 타입을 submit으로 하니까 Ajax도 타고 form도 작동이된다...   -->
							    <!-- 해결방안  type="button" 바꾸어서 form 태그가 작동을 못하게 함  -->
							    <button type="button" id="reply_btn">소감 남기기</button>
							    
							    <!-- 상품에 대한 댓글 작성시 이벤트 발생 함수  -->
							    <script>
									 $("#reply_btn").click(function(){									  
										  var formObj = $(".replyForm form[role='form']");
										  var product_number = $("#product_number").val(); //상품번호 
										  var reply_content = $("#reply_content").val(); //댓글내용
										  
										  var data = {
											  product_number : product_number,
											  content : reply_content
										    };
										  
										  $.ajax({
										  	 url : "${pageContext.request.contextPath}/shop/view/replyInsert",
										  	 type : "post",
										   	 data : data,
										     success : function(){
										    	 replyList();
										    	 $("#reply_content").val("");
										     }
										  });
									 });
								</script>
								
							   </div>
						   
						  </form>
						 </section>
					 </c:if>
					 				 
					 <!-- 댓글 리스트  -->
					 <section class="replyList">
					 
					 		<!-- 댓글 목록이 들어가는 부분  -->
						    <ol>
						    
						    </ol>
						    <!-- 댓글 목록이 들어가는 부분   -->
						    <script> 
								replyList();
							</script>
							
							<!-- 댓글 삭제 함수 -->
							<script>
								 $(document).on("click", ".delete", function(){
								  
								 var deleteConfirm = confirm("정말로 삭제하시겠습니까?");
								 
									 if(deleteConfirm){
										 
										 	var data = {
										 			reply_number : $(this).attr("reply_num"), //고유번호
										 			reply_orgin_number : $(this).attr("origin_ref"), //부모참조댓글 
										 			reply_depth : $(this).attr("group_layer") //깊이 
											};
										   
										 	console.log("삭제할 파라미터값 얻어오기"+data);
										 	
										  $.ajax({
											   url : "${pageContext.request.contextPath}/shop/view/replyDelete",
											   type : "post",
											   data : data,
											   success : function(){
											     replyList();
											   }
										  });
									 }									 
								 
								 });
							</script> 
					</section>
				</div>
				
				
   		 	
    		<%@ include file="/WEB-INF/include/footer.jsp"%> 
	    	
    	</div> 
    	
    	 <%@ include file="/WEB-INF/include/tail_import.jsp"%> 
    	 
    	 <!-- 댓글 수정이될 폼부분 -->
    	<div class="replyModal">

			 <div class="modalContent">
			  
			  <div>
			   <textarea class="modal_repCon" name="modal_repCon"></textarea>
			  </div>
			  
			  <div>
			   <button type="button" class="modal_modify_btn">수정</button>
			   <button type="button" class="modal_cancel">취소</button>
			  </div>
			  
			 </div>
			
			 <div class="modalBackground"></div>
		 
		</div>
		
		<!-- 댓글 수정 부분  START-->
		<script>
			$(document).on("click", ".modify", function(){
				 //$(".replyModal").attr("style", "display:block;");
				 $(".replyModal").fadeIn(200);
				 
				 var reply_num = $(this).attr("reply_num");
				 var reply_content = $(this).parent().parent().children(".replyContent").text();
				 //console.log("현재 this 가라키는 것은??"+$(this).html());
				 
				 $(".modal_repCon").val(reply_content);
				 $(".modal_modify_btn").attr("reply_num", reply_num);		 
			});
		</script>
		<script>
			$(".modal_cancel").click(function(){
				$(".replyModal").fadeOut(200); 
			});
			$(".modal_modify_btn").click(function(){
				 var modifyConfirm = confirm("정말로 수정하시겠습니까?");
				 
				 //수정완료 버튼을 정상적으로 눌렀을경우 실행 
				 if(modifyConfirm) {
					 
				    var data = {
						  reply_number : $(this).attr("reply_num"), //댓글번호
						  content : $(".modal_repCon").val() //내용 
				    };  
				  
					  $.ajax({
						   url : "${pageContext.request.contextPath}/shop/view/replyModify",
						   type : "post",
						   data : data,
						   success : function(result){				   
						     	replyList();
						    	$(".replyModal").fadeOut(200);
						   },
						   error : function(){
						    	alert("로그인하셔야합니다.")
						   }
					  });
				 }
			 
			});
		</script>
		<!-- 댓글 수정 부분  END-->
		
		<!-- 답글을 작성할수 있는 폼 생성 -->
		<script>
			$(document).on("click", ".replyBtn", function(){
	
				 //부모댓글 번호를 가져옴 
				 var reply_ref = $(this).attr("reply_num");
				
				 //추가될 답글 폼태그 
				 var replyTag = "";
				 replyTag += "<div class='input_area'><textarea name='reply_content' id='reply_content' class='reply_content'></textarea>";
				 replyTag += "<button type='button' class='reply_insert'";
				 replyTag += "reply_ref='"+$(this).attr("reply_num")+"'>작성</button>";
				 replyTag += "<button type='button' class='reply_cancel'";
				 replyTag += "reply_ref='"+$(this).attr("reply_num")+"'>취소</button>";
				 replyTag += "</div>";
					  
				 $(this).parent().parent().children("#replyForm").html(replyTag);
			});
			$(document).on("click", ".reply_cancel", function(){
				
				 //추가될 답글 폼태그 
				 var replyTag = "";
				 	replyTag += "<div id='replyForm'>"
					replyTag += "<button type='button' class='replyBtn' reply_num='" + $(this).attr("reply_ref") + "'>답글달기</button>"
					replyTag += "</div>"
					  
				 $(this).parent().parent().parent().children("#replyForm").html(replyTag);
			});
		</script>
		
		
		<!-- 댓글에 대한 답글 작성시 AJAX로 INSERT -->
		<!--  
			  ex) 1번댓글(부모댓글)에 대한 답글 ( 1번댓글 ref - 1 , step - 0 , depth - 0) 
			  1.처음 답글일시 ref - 1 , step(순서) - 1 , depth(깊이) - 1 
			  2.두번째 답글일시 ref - 1 , step - 2 , depth - 1
		-->
		<script>
			var product_number = ${product.product_number};	
		 	var session_nickname = '${User.user_nickname}';
		 	
			$(document).on("click", ".reply_insert", function(){
				//textarea 안에 있는 값은 val 받아야지 넘어온다 
			 	var cnt = $(this).parent().children("#reply_content").val();
				console.log("댓글의 내용"+cnt);
				
				if( cnt == "" || cnt == null) {
	   				alert("댓글을 입력하세요.");
	   				$(this).parent().children("#reply_content").focus();
	   				return false;
	   			} 
				
				/*  
					부모댓글번호,상품번호,작성자닉네임,답글내용  
				*/
				var data = {
						reply_orgin_number :  $(this).attr("reply_ref"), 
						product_number : product_number,
						writer_nickname : session_nickname,
						content : cnt
	   			}
				console.log(data);
				 
				$.ajax(
	   				{
	   					url : "${pageContext.request.contextPath}/shop/view/reCmtInsert",
	   					// dataType : "JSON" , -> 이걸써주니까   Unexpected end of JSON input 에러가 남 
	   					type : "POST", 
	   					data : data, 
	   					success : function()
	   						{	
	   							replyList();
	   						},
	   					error:function(request,status,error){
	   			        	alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
	   			        }

	   				}					
		   		);
				
			});
		</script>
		
  </body>
  	  
</html>










































