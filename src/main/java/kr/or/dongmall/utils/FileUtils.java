package kr.or.dongmall.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

@Component("fileUtils")
public class FileUtils {
	
	//파일이 저장될 위치를 정해줌 
	private static final String filePath = "C:\\Spring\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ShoppingMall_Project\\resources\\imgUpload\\";
	
	// 만들어질 썸네일의 가로 세로길이를 정해줌 
	static final int THUMB_WIDTH = 300;
	static final int THUMB_HEIGHT = 300;
	
	
	public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request,String[] checkList) throws Exception{ 
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request; 
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames(); 
		
		MultipartFile multipartFile = null; 
		String originalFileName = null; 
		String originalFileExtension = null; 
		String storedFileName = null; 
		String thumbFileName = null; //썸네일 이미지 
		int checkListIndex = 0; // 썸네일 대표 이미지 체크여부 
		
		//클라이언트에서 전송된 파일정보를 담아서 반환을 해줄것(추후에 다중파일 전송을 위한것) 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(); 
		Map<String, Object> listMap = null; 
		
		String product_number = (String)map.get("product_number"); // 상품 번호 (java.lang.Integer cannot be cast to java.lang.String 에러 주의하기) 
		String requestName = null; 
		String idx = null;
		System.out.println("이미지 파일이 저장될 상품번호 :"+product_number);
		
		//파일을 저장할 경로에 해당폴더가 없으면 폴더를 생성함 
		File file = new File(filePath); 
		if(file.exists() == false){ 
			file.mkdirs(); 
		}

		//가져온 파일이 있을경우 실행 
		while(iterator.hasNext()){ 
			multipartFile = multipartHttpServletRequest.getFile(iterator.next()); 
			//1.파일이 있을경우 실행 (신규로 업로드한 파일이거나 기존의 파일을 새로운 파일로 대체해서 업로드한 경우) 
			/*
				만약 게시글에서 첨부파일이 있을경우 해당파일을 수정하지 않고 수정을 할경우 multipartFile은 비어있게된다
				그러므로  파일정보가 없는경우(기존에 저장된 첨부파일을 수정하지 않고 게시글만 수정한 경우와 첨부파일을 삭제한경우만)를 구분해야됨 
			*/
			if(multipartFile.isEmpty() == false){ 
				   	System.out.println("올린 파일 존재함O");
					originalFileName = multipartFile.getOriginalFilename(); //원본파일의 이름을 받아옴 (exam.png)
					originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 해당원본파일의 확장명을 뽑아냄 (png)  
					storedFileName = CommonUtils.getRandomString() + originalFileExtension; // 32자리의 랜덤파일이름 + 확장명 
					thumbFileName = "Thumb" + storedFileName; // 썸네일이라는 표시 + 32자리 랜덤파일 이름 

					file = new File(filePath + storedFileName); // 서버에 실제파일이 저장될 경로와 저장될 파일설정
					multipartFile.transferTo(file); // 업로드한 파일을 서버의 지정된 폴더에 저장함  
					
					//썸네일 이미지 생성 (참고 : https://m.blog.naver.com/PostView.nhn?blogId=heronj&logNo=220627515961&proxyReferer=https:%2F%2Fwww.google.com%2F)
					BufferedImage orginal = ImageIO.read(file);
					BufferedImage thumbnail = new BufferedImage(THUMB_WIDTH,THUMB_HEIGHT,BufferedImage.TYPE_3BYTE_BGR);
					//썸네일 그리기 
					Graphics2D g = thumbnail.createGraphics();
					g.drawImage(orginal,0,0,THUMB_WIDTH,THUMB_HEIGHT,null);
					//파일생성
					FileOutputStream out = new FileOutputStream(filePath+thumbFileName);
					ImageIO.write(thumbnail,"jpg",out);
					
					listMap = new HashMap<String,Object>(); 
					listMap.put("IS_NEW","Y"); //신규로 업로드한 파일이거나 기존의 파일을 새로운 파일로 대체해서 업로드한 경우(Y) 
					listMap.put("product_number", product_number); // 이미지 파일이 저장될 상품번호 
					listMap.put("ORIGINAL_FILE_NAME", originalFileName); // 업로드한 원본파일 이름  
					listMap.put("STORED_ThumbNail", thumbFileName); // 서버에 저장될 썸네일 파일 이름
					listMap.put("delegate_thumbNail", checkList[checkListIndex]); // 썸네일 대표 이미지 체크여부 ( Y OR N) 
					listMap.put("STORED_FILE_NAME", storedFileName); // 서버에 저장될 파일 이름 
					listMap.put("FILE_SIZE", multipartFile.getSize()); // 파일크기 
					list.add(listMap); 
					
					checkListIndex++; // 썸네일 대표 이미지 체크여부
			} 
			//2.기존에 저장된 상품이미지파일을 수정하지 않고 상품세부목록만 수정한 경우,상품이미지파일을 삭제한경우,파일만 추가한 경우 
			else {
				System.out.println("올린 파일 존재안함"); //파일만 추가한경우도 일로탐 
					/*
					 	<input type="hidden" id="IDX" name="IDX_숫자" value="파일번호">
						-> 기존에 해당 게시글에 저장이 된 파일의 경우는 위에 태그가 있음(위 태그의 값이 있을경우 기존에 저장된 파일임을 알수 있음) 
					 */
					//<input type="file" id="file_${var.index }" name="file_${var.index }">
				    listMap = new HashMap<String,Object>(); 
				    System.out.println("썸네일 대표 이미지 체크여부:"+checkList[checkListIndex]);
				    listMap.put("delegate_thumbNail",checkList[checkListIndex++]); // 대표 썸네일 체크여부  
				    
					requestName = multipartFile.getName(); // type="file"인 name값을 가져옴 -> "file_1"
					idx = "IDX_"+requestName.substring(requestName.indexOf("_")+1); //IDX_0, IDX_1 이런식으로 나옴

					String value = (String)request.getParameter(idx); //file_number(해당 이미지파일의 고유번호값을 가져옴) 
					System.out.println("기존에 첨부된 파일의 고유번호:"+value); //파일만 추가하고 파일을 올리지 않고 수정한경우에는 null값이 나옴 
					map.put(idx, value); // 기존에 업로드했던 파일 정보 ( 파일이름 : IDX_0 , 해당파일의 고유번호 : value="${row.IDX }" ) 
					
					// IDX_숫자의 값이 있거나 기존에 등록한 파일이 지워지지 않고 존재할경우 실행 (파일만 추가한경우에는 아래 IF구문을 안탐) 
					if(map.containsKey(idx) == true && map.get(idx) != null){ 
						System.out.println("기존에 등록했던 첨부파일임->"+idx);
						listMap.put("IS_NEW", "N"); // 신규파일이 아니라는 뜻(N) 
						listMap.put("FILE_IDX", map.get(idx)); // 이미지 파일 고유번호 
						list.add(listMap); 
					}
					
			} 
		}
		
		return list; 
		
	}

	
}


































