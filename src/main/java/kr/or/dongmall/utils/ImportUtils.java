package kr.or.dongmall.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// 아임포트 관련 클래스 
public class ImportUtils {

	//환불을 하기위한 액세스토큰 발급(액세스 토큰 지속시간 :발행시간으로부터 30분)메서드 
	public String getAccessToken() throws Exception{
		/*	
	 	 <아임포트 API 요청 정보 > 
	 	 curl -H "Content-Type: application/json" POST 
	 	      -d '{"imp_key": "REST API키", "imp_secret":"REST API Secret"}' https://api.iamport.kr/users/getToken
		*/
		HttpURLConnection conn = null;
		String access_token = null; // 발급받을 액세스 토큰 
		URL url = new URL("https://api.iamport.kr/users/getToken"); //액세스 토큰을 받아올 주소입력 
		conn = (HttpURLConnection)url.openConnection();
		
		// 요청방식 : POST 
		conn.setRequestMethod("POST"); 
		
		// Header 설정 (application/json 형식으로 데이터를 전송) 
		conn.setRequestProperty("Content-Type", "application/json"); 
		conn.setRequestProperty("Accept", "application/json"); // 서버로부터 받을 Data를 JSON 형식 타입으로 요청함 
		//conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		
		// Data 설정 
		conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션
		//conn.setDoInput(true); // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
		
		//서버로 보낼 데이터 JSON 형태로 변환 (imp_apikey,imp_secret)
		JSONObject obj = new JSONObject();
		obj.put("imp_key","6724290352514148");
		obj.put("imp_secret","IKli0sBCdN5uI6QdpnlRzQKLsVb5jRv1BQkCjVfwJl0ssRGRe2JNStzlpKqICVKdM5Q505BJrCcTtSKH");
		System.out.println("JSON 변환 결과값 : " +obj.toString());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(obj.toString());
		bw.flush();
		bw.close();
		
		System.out.println("*** 여기까지 오면 Request는 성공 *** ");
		
		// 서버로부터 응답 데이터 받기 
		int responseCode = conn.getResponseCode(); //응답코드 받기 
		System.out.println("응답 코드는 ??"+responseCode); //응답코드 400이면 요청이 잘못된건데... (요청시 오타작성 발견) 
		if(responseCode == 200) { //성공 
			 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			 StringBuilder sb = new StringBuilder();
			 String line = null;  
			 while ((line = br.readLine()) != null) {  
			        sb.append(line + "\n");  
			 }
			 br.close();
			 System.out.println("" + sb.toString());
			 
			 //JSONParser 라이브러리를 사용(JSON 형태로 되어있는 데이터들중 원하는 것들을 추출하기 위해 사용)
			 JSONParser jsonParser = new JSONParser();
			 //json 데이터를 JSON 객체 형태로 변환 
			 JSONObject jsonObj = (JSONObject)jsonParser.parse(sb.toString());
			 //응답 데이터를 가져옴 
			 JSONObject responseData = (JSONObject)jsonObj.get("response");
			 //응답 데이터중에서 Key가 access_token Value값을 가져옴 
			 access_token = (String)responseData.get("access_token");
			 System.out.println("가져온 access_token 값 : "+access_token);
			 return access_token;
		}else{ //실패 
		    System.out.println(conn.getResponseMessage());  
		    return null;
		}  
	}

	//아임포트 서버로부터 환불 요청 처리하는 메서드 
	public int refundProcess(String access_token,String merchant_uid) throws Exception{
		HttpURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/cancel"); 
		conn = (HttpURLConnection)url.openConnection();
		
		// 요청방식 : POST 
		conn.setRequestMethod("POST"); 
		
		// Header 설정 (application/json 형식으로 데이터를 전송) 
		conn.setRequestProperty("Content-Type", "application/json"); 
		conn.setRequestProperty("Authorization", access_token);  
		//conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		
		// Data 설정 
		conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션
		conn.setDoInput(true); // InputStream으로 서버로 부터 응답을 받겠다는 옵션.

		//서버로 보낼 데이터 JSON 형태로 변환 (imp_apikey,imp_secret)
		JSONObject obj = new JSONObject();
		obj.put("merchant_uid",merchant_uid);
		System.out.println("JSON 변환 결과값 : " +obj.toString());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		bw.write(obj.toString());
		bw.flush();
		bw.close();
		
		System.out.println("*** 여기까지 오면 Request는 성공 *** ");
		
		// 서버로부터 응답 데이터 받기 
		int result = 0;
		int responseCode = conn.getResponseCode(); //응답코드 받기 
		System.out.println("응답 코드는 ??"+responseCode); //응답코드 400이면 요청이 잘못된건데... (요청시 오타작성 발견) 
		if(responseCode == 200) { //성공 
			System.out.println("환불성공!!!!!");
			 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			 StringBuilder sb = new StringBuilder();
			 String line = null;  
			 while ((line = br.readLine()) != null) {  
			        sb.append(line + "\n");  
			 }
			 br.close();
			 System.out.println("" + sb.toString());
			 result = 1; //환불 성공시 정수값 1반환 
		}else{ //실패 
		    System.out.println(conn.getResponseMessage()); //환불 실패시 정수값 0반환(응답코드 400,404..등등) 
		}  
		
		return result;
	}
	
	
	
	
	//결제 정보 조회 함수 
	public void paymentSelect(String access_token,String imp_uid) throws Exception{

		HttpURLConnection conn = null;
		URL url = new URL("https://api.iamport.kr/payments/"+imp_uid); //액세스 토큰을 받아올 주소입력 
		conn = (HttpURLConnection)url.openConnection();
		
		// 요청방식 : GET 
		conn.setRequestMethod("GET");
		
		// Header 설정  
		conn.setRequestProperty("Authorization", access_token); 
		
		// 서버로부터 응답 데이터 받기 
		int responseCode = conn.getResponseCode(); //응답코드 받기 
		System.out.println("응답 코드는 ??"+responseCode); //응답코드 400이면 요청이 잘못된건데... (요청시 오타작성 발견) 
		if(responseCode == 200) { //성공 
			System.out.println("환불성공!!!!!");
			 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			 StringBuilder sb = new StringBuilder();
			 String line = null;  
			 while ((line = br.readLine()) != null) {  
			        sb.append(line + "\n");  
			 }
			 br.close();
			 System.out.println("" + sb.toString());
		}else{ //실패 
		    System.out.println(conn.getResponseMessage());  
		} 
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

























