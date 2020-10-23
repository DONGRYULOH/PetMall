/**
 * 
 */

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
			$('#user_pwd_check').keyup(function() {
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
			
			//가입버튼 클릭시 공백 검증
			$('button:submit').click(function() {
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
			