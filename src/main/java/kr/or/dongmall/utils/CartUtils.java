package kr.or.dongmall.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.dongmall.user.dto.UserDto;

//카트(장바구니) 관련 클래스 
public class CartUtils {

	//사용자브라우저의 쿠키값과 서버의 세션값이 일치하는게 없는경우의 함수 
	public String notCookie_Session(HttpSession session,HttpServletResponse response) {
	 	 //128비트의 랜덤 UUID 코드생성한 값을 서버의 세션과 브라우저의 쿠키에 저장시킴
		 UUID uid = UUID.randomUUID();
		 String guest = uid + "guest";
		 
		 //쿠키이름은 guest라는 이름으로 부여 
		 System.out.println("생성된 쿠키값->"+guest);				 
		 //비회원 식별값을 세션에 저장시킴
		 session.setAttribute("guest",guest);
		 
		 Cookie setCookie = new Cookie("guest",guest);
		 setCookie.setPath("/"); //쿠키가 유효한 경로 설정(전역 페이지) 
		 setCookie.setMaxAge(60*60*24); //쿠키 유효시간 
		 response.addCookie(setCookie); //쿠키 저장하기 
		 
		 return guest;
	}
	
	//서버에 저장되어있는 모든 세션을 가져오는 함수 
	public ArrayList<String> allSession(HttpSession session){
		Enumeration session_values = session.getAttributeNames();
		int cnt = 0;
		String session_name = "";
		String session_value = "";
		
		ArrayList<String> s_list = new ArrayList<String>();
		
		//nextElement 하면 서버로부터 뽑아올 세션데이터 1씩 줄어들기 떄문에 ... 
		while(session_values.hasMoreElements()) {
			cnt++; 
			session_name = session_values.nextElement().toString();
			session_value = session.getAttribute(session_name).toString();
			System.out.println(cnt+"번째 세션이름->"+session_name);
			System.out.println(cnt+"번째 세션값->"+session_value);
			s_list.add(session_value);
		}
		return s_list;
	}
	
	
	//카트(장바구니)를 담을때 회원 OR 비회원 여부 파악 
	public String nowCartDivide(UserDto user,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		
		String user_id = null;
		//사용자브라우저에 쿠키값과 서버의 세션값의 일치여부 
		boolean check = false; 
		
		//서버에 저장되어있는 모든 세션을 ArrayList에 담아서 가져오기
		//서버의 세션에 저장되어 있는 값들 (키,값 -> map으로 저장시킬까??)
		ArrayList<String> s_list = allSession(session);	
		
		if(user != null) { //로그인이 되어있는 경우(회원인 경우) - 회원ID를넣음 	
			user_id = user.getUser_id();
		}else { // 비회원일경우 장바구니에 물품을 담는 경우 
			
			// 1.사용자 브라우저에 저장되있는 쿠키값을 가져와서 서버의 세션값과 비교 
			//중첩문을 탈출하는 방법(함수로 만들어서 return 해주기 , 라벨 붙이기 , flag 세우기)
			Cookie[] getCookie = request.getCookies();
			root:for(int k=0;k<getCookie.length;k++) {
				String value = getCookie[k].getValue(); //쿠키값 
				String name = getCookie[k].getName(); //쿠키이름 
				//1-1.사용자 브라우저의 쿠키값과 서버의 세션값이 일치하는 경우(사용자 브라우저의 쿠키값으로 장바구니에 물품을 등록함)
				//브라우저의 쿠키값 과 서버에 저장되어있는 세션값들(1부터...N까지 돌면서 비교-구구단과 비슷)
				for(int j=0;j<s_list.size();j++) {
					System.out.println("서버의 값->"+s_list.get(j));
					//사용자 브라우저의 쿠키값과 서버의 세션값이 매칭이 되는 경우 실행 
					if(value.equals(s_list.get(j))){
						user_id = value;
						check = true;
						break root;
					}	
				}	
			}//root:END 
			
			//2.사용자브라우저의 쿠키값과 서버의 세션값이 일치하는게 없는경우(비회원식별값을 랜덤으로 만들어서 세션에 저장시킨다음 사용자 브라우저의 쿠키값으로 전달)
			if(check == false) {
				 user_id = notCookie_Session(session,response);
			}
		}
		
		return user_id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
