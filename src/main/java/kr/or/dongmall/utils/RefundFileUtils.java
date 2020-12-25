package kr.or.dongmall.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("RefundFileUtils")
public class RefundFileUtils {

	//환불 내역 관련 파일이 저장될 위치를 정해줌 
	private static final String filePath = "C:\\Spring\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ShoppingMall_Project\\resources\\refund_img\\";
	
	public void refundFileUpload(String storedFileName,MultipartFile fileInfo) {
		
		//파일을 저장할 경로에 해당폴더가 없으면 폴더를 생성함 
		File file = new File(filePath); // 서버에 실제파일이 저장될 경로와 저장될 파일설정
		MultipartFile multipartFile = null; 
		if(file.exists() == false){ 
			file.mkdirs(); 
		}

		System.out.println("여기까지 @@@@@@@@@@@@@");
		
		try {
			FileOutputStream fos = new FileOutputStream(filePath+storedFileName);
			
			InputStream fis = fileInfo.getInputStream();
			
			int readCount = 0;
			byte[] buffer = new byte[1024];
			
			 while ((readCount = fis.read(buffer)) != -1) {
	                //  파일에서 가져온 fileInputStream을 설정한 크기 (1024byte) 만큼 읽고
	                
	                fos.write(buffer, 0, readCount);
	                // 위에서 생성한 fileOutputStream 객체에 출력하기를 반복한다
	            }
			 
			 fis.close();
			 fos.close();

		} catch (IllegalStateException e) {
			System.out.println("파일 업로드중 에러발생!!"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일 업로드중 에러발생!!"+e.getMessage());
			e.printStackTrace();
		}finally {
			
		}
	}
	
}
