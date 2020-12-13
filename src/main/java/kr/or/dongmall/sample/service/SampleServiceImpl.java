package kr.or.dongmall.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.or.dongmall.sample.dao.SampleDao;
import kr.or.dongmall.sample.dto.BoardDto;
import kr.or.dongmall.utils.FileUtils;

//Service 인터페이스를 통해 정의된 메서드(함수)를 실제로 구현하는 클래스 
@Service("sampleService")
public class SampleServiceImpl implements SampleService {
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	// 데이터의 접근을 위한 DAO(data access Object) 객체를 선언함 
	@Resource(name="sampleDAO")
	private SampleDao sampleDao;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> commandMap) {
		return sampleDao.selectBoardList(commandMap);
	}

	//게시글 작성 
	@Override
	public void insertBoard(BoardDto board, HttpServletRequest request) {
		
		Map<String,Object> boardMap = new HashMap<String,Object>();
		boardMap.put("idx",board.getIdx()); //글번호 : 0
		boardMap.put("title",board.getTitle());
		boardMap.put("contents",board.getContents());
		
		// 게시글 작성 (파일제외)
		sampleDao.insertBoard(boardMap);
		
		// 업로드한 파일 정보 서버와 DB에 저장 
		try {
			List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(boardMap, request);
			for(int i=0;i<list.size();i++) {
				// 첫번째 리스트 항목에 대한 Map , 두번째 리스트 항목에 대한 Map 이런식으로 차레대로 꺼내옴 
				// 파일을 여러개 업로드 헀으면 그만큼 for문도 여러번 돈다 
				System.out.println("파일이 저장될 글번호 : "+list.get(i).get("BOARD_IDX"));
				sampleDao.insertFile(list.get(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 게시판 상세 페이지 
	@Override
	public Map<String, Object> BoardDetail(int board_number) {
		// 글번호를 int 로 조회를 해서 Map으로 해당 글번호에 대한 목록들을 받아옴
		
		Map<String,Object> resultMap = new HashMap<String,Object>(); // 해당 게시글목록과 게시글에 대한 파일을 저장할 Map선언 
		Map<String, Object> detailBoard = sampleDao.BoardDetail(board_number);
		System.out.println("Map으로 가져온글번호"+detailBoard.get("idx"));
		System.out.println("Map으로 가져온글제목"+detailBoard.get("title"));
		System.out.println("Map으로 가져온글내용"+detailBoard.get("contents"));
		resultMap.put("detailBoard",detailBoard); // 게시글목록 Map에 넣기 
		
		// 해당 게시글에 대한 파일도 가져와야됨 
		List<Map<String, Object>> fileList = sampleDao.BoardFileList(board_number);
		for(int i=0;i<fileList.size();i++) {
			System.out.println(i+"번쨰 게시글 번호:"+fileList.get(i).get("BOARD_IDX"));
			System.out.println(i+"번쨰 파일 명:"+fileList.get(i).get("ORIGINAL_FILE_NAME"));
		}
		resultMap.put("fileList",fileList); // 해당 게시글의 파일들을 Map에 넣기 
		
		return resultMap;
	}
	
	//파일 다운로드 서비스
	@Override
	public Map<String, Object> fileDownload(int board_number) {
		
		//DB로부터 해당 게시글에 대한 파일정보가 있는지 조회해서 있으면 Map에 담기 
		Map<String, Object> result = sampleDao.fileDownload(board_number);
		return result;
	}
	
	//해당 게시글 수정(게시글 + 첨부파일) 
	@Override
	public void BoardUpdateOk(BoardDto board, HttpServletRequest request) {
		
		//첨부파일을 제외한 게시물 업데이트처리 
		sampleDao.updateBoard(board);
		
		Map<String,Object> boardMap = new HashMap<String,Object>();
		String bno = Integer.toString(board.getIdx()); // Type mismatch: cannot convert from int to String
		boardMap.put("idx",bno); //게시글 번호 
		
		//기존의 첨부파일을 수정하거나 신규로 등록한 첨부파일만 서버의 파일저장소에 저장한다
		try {
			List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(boardMap, request);
			for(int i=0;i<list.size();i++) {
				//신규로 등록한 첨부파일의 경우만 서버에 새로 등록함 
				if(list.get(i).get("IS_NEW").equals("Y")) {
					System.out.println("============ 신규로 등록할 첨부파일 정보 ==============");
					System.out.println("첨부파일이 저장되는 게시글 번호:"+list.get(i).get("BOARD_IDX"));
					System.out.println("클라이언트가 업로드한 첨부파일 이름:"+list.get(i).get("ORIGINAL_FILE_NAME"));
					System.out.println("서버에 저장될 첨부파일 이름:"+list.get(i).get("STORED_FILE_NAME"));
					System.out.println("파일 사이즈:"+list.get(i).get("FILE_SIZE"));
					sampleDao.insertFile(list.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("첨부파일 수정중 에러발생"+e.getMessage());
			e.printStackTrace();
		}
		
		
	}


}























