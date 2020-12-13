package kr.or.dongmall.sample.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dongmall.sample.dto.BoardDto;
import kr.or.dongmall.sample.dto.FileDto;


// DAO는 DB에 접근하여 데이터를 Select,Insert,Delete,Update 이런작업들만 수행을 한다 
@Repository("sampleDAO")
public class SampleDao {
	
	//  SQL 쿼리가 작성되어 있는 mapper.xml 안에 접근이 가능 
	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	
	public List<Map<String, Object>> selectBoardList(Map<String, Object> commandMap) {
		
		// sqlSession.selectBoardList("xml에서 설정한 namespace 이름.해당쿼리ID",commandMap);
		return sqlSession.selectList("board.selectBoardList",commandMap);
	}

	// 게시글 작성(파일 제외) 
	public void insertBoard(Map<String,Object> boardMap) {
		sqlSession.insert("board.insertBoard",boardMap);
	}
	
	// 게시글 파일 정보 입력 
	public void insertFile(Map<String, Object> map) {
		sqlSession.insert("board.insertFile",map);
	}
	
	
	// 해당 게시글 상세 페이지 (하나의 행만 반환)
	@SuppressWarnings("unchecked") // 이클립스 컴파일러가 알려주는 노란색 경고 표시를 없애주는 역할을 한다.
	public Map<String, Object> BoardDetail(int board_number) {
		return (Map<String,Object>)sqlSession.selectOne("board.detailBoard",board_number);
	}
	
	// 해당 게시글의 파일 가져오기 
	public List<Map<String, Object>> BoardFileList(int board_number) {
		
		// resultType이 HashMap 으로 된 여러개의 파일정보들을 List 형태로 받아서 가져온다
		List<Map<String, Object>> fileDto = sqlSession.selectList("board.fileListBoard",board_number);
		for(int i=0;i<fileDto.size();i++) {
			System.out.println("해당 게시글의 첨부파일 이름:"+fileDto.get(i).get("ORIGINAL_FILE_NAME"));
		}		

		return fileDto;
	}
	
	// 해당 게시글의 파일 다운로드 
	@SuppressWarnings("unchecked")
	public Map<String, Object> fileDownload(int board_number) {
		return (Map<String,Object>)sqlSession.selectOne("board.fileDownload",board_number);
	}
	
	// 첨부파일을 제외한 게시물 업데이트 
	public void updateBoard(BoardDto board) {
		sqlSession.update("board.updateBoard",board);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
