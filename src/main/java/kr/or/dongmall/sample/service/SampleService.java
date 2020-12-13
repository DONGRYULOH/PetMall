package kr.or.dongmall.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.dongmall.sample.dto.BoardDto;

// Service 인터페이스는 비즈니스 로직의 수행을 위한 메서드를 정의하는 부분 
public interface SampleService {

	//전체 게시글 목록 
	List<Map<String, Object>> selectBoardList(Map<String, Object> commandMap);

	//게시글 작성 
	void insertBoard(BoardDto board,HttpServletRequest request);

	Map<String, Object> BoardDetail(int board_number);

	//파일 다운로드 
	Map<String, Object> fileDownload(int board_number);

	void BoardUpdateOk(BoardDto board, HttpServletRequest request);
}
