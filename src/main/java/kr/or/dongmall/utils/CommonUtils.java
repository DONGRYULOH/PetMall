package kr.or.dongmall.utils;

import java.util.UUID;

public class CommonUtils {
	
	//32글자의 랜덤한 문자열(숫자포함)을 만들어서 반환해주는 메서드 
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-","");
	}
	
}
