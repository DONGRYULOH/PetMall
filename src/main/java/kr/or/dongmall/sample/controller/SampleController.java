package kr.or.dongmall.sample.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.or.dongmall.sample.dto.BoardDto;
import kr.or.dongmall.sample.service.SampleService;

@Controller
@RequestMapping("/board/*") 
public class SampleController {
	
	//SampleController에 한해서 로그설정하기 
	protected Log log = LogFactory.getLog(SampleController.class);
		
	// Service 영역의 접근을 위한 선언 
	// @Resource 사용해서 필요한 Bean객체(자바객체)를 수동으로 등록함
	// 스프링 빈객체란? 자바 객체랑 똑같는 의미이며 스프링 컨테이너에 의해서 자바객체가 만들어진다하여 빈객체라고 불리는것임 
	@Resource(name="sampleService")
	private SampleService sampleService;
	
	// 게시판 리스트 
	@RequestMapping(value="/List") 
	public ModelAndView openSampleBoardList(Map<String,Object> commandMap) throws Exception{ 
		ModelAndView mv = new ModelAndView("/board/List"); 
		List<Map<String,Object>> list = sampleService.selectBoardList(commandMap); 
		mv.addObject("list", list); 
		return mv; 
	}
	
	// 게시판 글 수정페이지 이동(GET)
	@RequestMapping(value="/Update",method=RequestMethod.GET) 
	public String BoardUpdate(@RequestParam("n") int board_number,Model model) throws Exception{ 
		
		System.out.println("글번호:"+board_number);
		
		Map<String,Object> map = sampleService.BoardDetail(board_number);
		model.addAttribute("detailBoard",map.get("detailBoard"));
		model.addAttribute("fileList",map.get("fileList"));
		
		return "/board/Update"; 
	}
	
	// 게시판 글 수정(POST)
	@RequestMapping(value="/Update",method=RequestMethod.POST) 
	public ModelAndView BoardUpdateOk(BoardDto board,HttpServletRequest request) throws Exception{ 
		
		// 첨부파일을 제외한 게시글 수정 
		sampleService.BoardUpdateOk(board,request);
		
		ModelAndView mv = new ModelAndView("redirect:/board/Detail");
		mv.addObject("n", board.getIdx());
		
		return mv; 
	}
		
	// 게시판 글 상세정보 이동(GET) 
	@RequestMapping(value="/Detail",method=RequestMethod.GET) 
	public String BoardDetail(@RequestParam("n") int board_number,Model model) throws Exception{ 
		
		System.out.println("글번호:"+board_number);
		
		Map<String,Object> map = sampleService.BoardDetail(board_number);
		model.addAttribute("detailBoard",map.get("detailBoard"));
		model.addAttribute("fileList",map.get("fileList"));
		
		return "/board/Detail"; 
	}
	
	// 게시판 작성 페이지 이동(GET)
	@RequestMapping(value="/Write",method=RequestMethod.GET) 
	public String BoardWrite() throws Exception{ 
		
		return "/board/Write"; 
	}
	
	// 게시판 작성(POST)
	/*
	 	HttpServletRequest 통해 전송된 파일정보도 받을수 있도록함 
	 */
	@RequestMapping(value="/Write",method=RequestMethod.POST) 
	public String BoardWrite(BoardDto board,HttpServletRequest request) throws Exception{ 
		System.out.println("작성중...");
		//HttpServletRequest 이용해 전송된 파일을 가져온다 
		MultipartHttpServletRequest MultipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = MultipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		while(iterator.hasNext()) {
			multipartFile = MultipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				
				 log.debug("-----------file start--------------");
				 log.debug("name :"+multipartFile.getName());
				 log.debug("filename"+multipartFile.getOriginalFilename());
				 log.debug("size:"+multipartFile.getSize());
				 log.debug("-----------file start--------------");
				
			}
		}
		
		//서비스단에서 게시판 작성 로직을 실행 
		sampleService.insertBoard(board, MultipartHttpServletRequest);
		//request.getAttribute("TITLE");
		return "/board/Write"; 
	}
	
	
	//게시글 파일 다운로드 
	@RequestMapping(value="/fileDownload",method=RequestMethod.GET) 
	public void fileDownload(@RequestParam("n") int board_number,HttpServletResponse response) throws Exception{ 
		
		//클라이언트로부터 요청받은 첨부파일정보 가져오기 
		Map<String, Object> fileInfo = sampleService.fileDownload(board_number);
		String storedFileName = (String)fileInfo.get("STORED_FILE_NAME");  //서버에 실제 저장된 파일 
		String originalFileName = (String)fileInfo.get("ORIGINAL_FILE_NAME"); //업로드한 실제 파일이름 

		System.out.println("서버에 실제 저장된 파일 :"+storedFileName);
		System.out.println("업로드한 실제 파일이름 :"+originalFileName);
		
		//실제로 파일이 저장된 위치에서 해당 첨부파일을 읽어서 byte 형태로 변환을함
		// pom.xml에 추가했던 org.apache.commons.io 라이브러리를 사용해 Byte 형식으로 간단하게 변환할수 있음 
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\Spring\\File\\"+storedFileName));
		
		//클라이언트가 요청한 첨부파일정보를 보내주기 위해서는 서버에서는 Response(응답)을 통해 클라이언트에게 보내주게 된다 
		//Response에다 파일정보를 담아서 다운로드 할수 있도록 세팅을 한다음 클라이언트한테 보내줌 
		response.setContentType("application/octet-stream"); 
		response.setContentLength(fileByte.length); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.getOutputStream().write(fileByte); 
		response.getOutputStream().flush(); 
		response.getOutputStream().close();

	
		
	}
	
}

















