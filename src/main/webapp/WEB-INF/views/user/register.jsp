<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회원가입 페이지</title>
	<%@ include file="/WEB-INF/include/head_import.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/include/header.jsp"%>
			
			<div>
				<div>
					<h3>회원가입</h3>
				</div>
				
				
				
			 <form  method="post" name="formOk">
				<table class="table">
				    <tbody>
				        <tr>
				            <td class="text-center">
				            	아이디<i class="ni ni-fat-remove pt-1"></i>
				            </td>
				            <td>
				            	<input type="text" id="user_id" name="user_id" required="required" />
				            	<!-- ID중복체크  -->
				            	<div class="check_font" id="id_check"></div>    
				            </td>  
				        </tr>
						<tr>
				            <td class="text-center">
				            	패스워드<i class="ni ni-fat-remove pt-1"></i>
				            </td>
				            <td>
				            	<input type="password" id="user_pwd" name="user_pwd" required="required" />  
				            	<div id="pwd"></div>      
				            </td>  
				        </tr>
				        <tr>
				            <td class="text-center">
				            	패스워드 확인<i class="ni ni-fat-remove pt-1"></i>
				            
				            </td>
				            <td>
				            	<input type="password" placeholder="비밀번호를 다시한번 더 입력하세요"  id="user_pwd_check" name="user_pwd_check"  required="required" />
				            	<div id="pwd_check"></div>       
				            </td>  
				        </tr>
				        <tr>
				            <td class="text-center">
				            	닉네임<i class="ni ni-fat-remove pt-1"></i>
				            </td>
				            <td>
				            	<input type="text" id="user_nickname" name="user_nickname" placeholder="닉네임을 입력해주세요" />      
				            </td>  
				        </tr>
				        <tr>
				            <td class="text-center">
				            	이름<i class="ni ni-fat-remove pt-1"></i>
				            </td>
				            <td>
				            	<input type="text" id="user_name" name="user_name" placeholder="이름을 입력해주세요" />      
				            </td>  
				        </tr>
				         <tr>
				            <td class="text-center">
				            	주소<i class="ni ni-fat-remove pt-1"></i>
				            </td>
				            <td>
				            	<input type="text"  id="sample6_postcode" style="width:100px;" maxlength="5" readonly="readonly" placeholder="우편번호">
								<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
								<input type="text" id="sample6_address" placeholder="주소" style="width: 47%;" readonly="readonly"><br>
								<input type="text" id="sample6_detailAddress" style="width: 47%;" placeholder="상세주소">
				            </td>  
				        </tr>
				        <tr>
				            <td class="text-center">
				            	연락처<i class="ni ni-fat-remove pt-1"></i>
				            </td>
				            <td>
				            	<input type="text" id="user_phone" name="user_phone" placeholder="연락처를 입력해주세요" />    
				            </td>  
				        </tr>
				        <tr>
				            <td class="text-center">
				            	이메일<i class="ni ni-fat-remove pt-1"></i>
				            </td>
				            <td>
				            	<input type="text" id="user_phone" name="user_phone" placeholder="연락처를 입력해주세요" />    
				            </td>  
				        </tr>
				         <tr>
				            <td class="text-center">
				            	이메일 수신여부<i class="ni ni-fat-remove pt-1"></i>
				            </td>
				            <td>
				            	 <div class="custom-control custom-checkbox mb-3">								
								  <input type="checkbox" id="email_check" name="email_check" /> 
								  <label>동의함</label>
								  <p>쇼핑몰에서 제공하는 유익한 이벤트 소식을 이메일로 받으실 수 있습니다.</p>
								</div>
				            </td>  
				        </tr>
				    </tbody>
				</table>
				<div>				
				  	 <button type="submit" class="btn btn-primary" >회원가입 취소</button>	
				 	 <button type="submit" id="reg_submit" class="btn btn-primary">회원가입</button>
				</div>
			 </form>   
			</div> 
			
		<%@ include file="/WEB-INF/include/footer.jsp"%>
	</div>
	
	<!-- JS -->
	<%@ include file="/WEB-INF/include/tail_import.jsp"%>
	
	
</body>
	
	
	<!-- 다음 우편변호API 사용 -->
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
	
	<!-- 회원가입 정규표현식 -->
	<script type="text/javascript">
	var validate = new Array; //배열만들기 

		for(let i=0; i<3; i++) { //validate[0]-아이디 , validate[1]-패스워드 ,validate[2]-패스워드 일치 
   		validate[i]=false;
   		console.log(validate[i]);
		}
   
  
// 아이디 유효성 검사(1 = 중복 / 0 != 중복)
	$("#user_id").keyup(function() {
		// id = "id_reg" / name = "userId"
		var user_id = $('#user_id').val();
		$.ajax({
			url : '${pageContext.request.contextPath}/idCheck?user_id='+ user_id,
			type : 'get',
			success : function(data) {
				console.log("1 = 중복o / 0 = 중복x : "+ data);							
				
				if (data == 1) {
						// 1 : 아이디가 중복되는 문구
						$("#id_check").text("사용중인 아이디입니다 :p");
						$("#id_check").css("color", "red");
						//$("#reg_submit").attr("disabled", true);
						validate[0] = false;
					} 
				else{
						let id = /^[가-힣a-zA-Z0-9]{2,10}$/; //ID정규표현식 
						console.log("중복되는아이디없음");
							if(user_id == ""){
							
							$('#id_check').text('아이디를 입력해주세요 :)');
							$('#id_check').css('color', 'red');
							//$("#reg_submit").attr("disabled", true);				
							validate[0] = false;
						} else if(!id.test(user_id)){
							
							$('#id_check').text("아이디는 소문자와 숫자 4~12자리만 가능합니다 :) :)");
							$('#id_check').css('color', 'red');
							//$("#reg_submit").attr("disabled", true);
							validate[0] = false;
						} else if(id.test(user_id)){
							//사용가능 
							$('#id_check').text("사용가능한ID입니다 :) :)");
							$('#id_check').css('color', 'blue');
							validate[0] = true;
						}
						
					}
				}, error : function() {
						console.log("실패");
				}
			});
		});
		
		//패스워드 유효성 검증
		$('#user_pwd').keyup(function() {
			var user_pwd = $('#user_pwd').val();
			let pwd = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
			let blank = /\s/g;
			if (!pwd.test($(this).val()) || blank.test($(this).val())) {
				//alert("패스워드"+$(this).val());
				$('#pwd').text("8~20자에 특수문자가 반드시 포함된 영어 대소문자,숫자를 사용하세요 :) :)");
				$('#pwd').css('color', 'red');
				validate[1] = false;
			} else {
				$('#pwd').text("사용가능한 패스워드");
				$('#pwd').css('color', 'blue');
				validate[1] = true;
			}
			
		});
		
		//패스워드 일치여부 확인
		$('#user_pwd_check,#user_pwd').keyup(function() {
			if ($('#user_pwd').val() != $('#user_pwd_check').val()) {
				$('#pwd_check').text("비밀번호가 다릅니다.");
				$('#pwd_check').css('color', 'red');
				validate[2] = false;
			} else {
				$('#pwd_check').text("비밀번호가 일치합니다.");
				$('#pwd_check').css('color', 'blue');
				validate[2] = true;
			}
			
		});
		
		//가입버튼 클릭시 공백 검증 + emailCheck 여부확인 
		$('button:submit').click(function() {
			
			//emailCheck 여부확인 
			var check = document.formOk.email_check.checked;
			console.log("체크여부:"+check);
			if(check == false){
				console.log("이메일 수신을 동의하지 않음");
				document.getElementById("email_check").value = 'N';
			}else{
				console.log("이메일 수신을 동의함");
				document.getElementById("email_check").value = 'Y';
			}
			
			//공백검증 
			for (let i = 0; i < validate.length; i++) {
				if (validate[i] == false) {
					console.log(i);
					switch (i) {
					case 0:
						$('#user_id').focus();
						$('#id_check').text('아이디를 입력해주세요 :)');
						$('#id_check').css('color', 'red');						
						return false;
					case 1:
						$('#user_pwd').focus();
						$('#pwd').text('패스워드를 입력해주세요 :)');
						$('#pwd').css('color', 'red');
						return false;
					case 2:
						$('#user_pwd_check').focus();
						$('#pwd_check').text('패스워드를 재입력해주세요 :)');
						$('#pwd_check').css('color', 'red');
						return false;
					}
				}
			}
			
		});	 
	</script>
	<!-- 회원가입 정규표현식(Juqery를 못불러옴) -->
	<!-- <script src="resources/register_regular_expression.js" type="text/javascript"></script> -->
	
</html>














