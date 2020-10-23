package kr.or.dongmall.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import net.coobird.thumbnailator.Thumbnails;
//폴더 생성과 파일 저장, 썸내일 생성의 작업을 합니다.
public class UploadFileUtils {
  
 static final int THUMB_WIDTH = 300;
 static final int THUMB_HEIGHT = 300;
 
	 public static String fileUpload(String uploadPath,
	         String fileName,
	         byte[] fileData, String ymdPath) throws Exception {
	
	  //파일을 업로드할 때, 같은 이름의 파일을 업로드하면 기존 파일이 다른 파일로 덮어씌워질 수 있다.
	  //128비트의 랜덤 UUID 코드생성 
	  UUID uid = UUID.randomUUID();
	  
	  String newFileName = uid + "_" + fileName;//4730dd0a-da36-4380-ba95-e8bc88b96b19(uid) + _ + A.png(업로드한 파일명)
	  String imgPath = uploadPath + ymdPath;// imgUpload/2020/09/07 (파일이업로드될 장소지정)
	
	  File target = new File(imgPath, newFileName);
	  FileCopyUtils.copy(fileData, target);
	  
	  String thumbFileName = "s_" + newFileName;//썸네일 이미지 파일명 
	  File image = new File(imgPath + File.separator + newFileName); //File.separator(파일구분자) \\ -> 이것을뜻함 
	
	  File thumbnail = new File(imgPath + File.separator + "s" + File.separator + thumbFileName);
	
	  if (image.exists()) {
	   thumbnail.getParentFile().mkdirs();
	   Thumbnails.of(image).size(THUMB_WIDTH, THUMB_HEIGHT).toFile(thumbnail);
	  }
	  return newFileName;
	 }

	 public static String calcPath(String uploadPath) {
		  Calendar cal = Calendar.getInstance();
		  String yearPath = File.separator + cal.get(Calendar.YEAR);
		  String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		  //String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		  makeDir(uploadPath, yearPath, monthPath); //일반 파일경로 생성 (년도/월 까지 )
		  makeDir(uploadPath, yearPath, monthPath + "\\s"); //썸네일 파일경로 생성 
		
		  return monthPath;
	 }

	 private static void makeDir(String uploadPath, String... paths) {
	
		  if (new File(paths[paths.length - 1]).exists()) { return; }
		
		  for (String path : paths) {
		   File dirPath = new File(uploadPath + path);
		
		   if (!dirPath.exists()) {
		    dirPath.mkdir();
		   }
		  }
	 }
}