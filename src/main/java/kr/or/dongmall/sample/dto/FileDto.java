package kr.or.dongmall.sample.dto;
/*
 	-- 첨부파일 정보를 저장할 테이블
	create table tb_file(
	IDX int primary key auto_increment,
	BOARD_IDX int not null, -- 게시글 번호 
	ORIGINAL_FILE_NAME varchar(260) not null, -- 원본파일이름
	STORED_FILE_NAME varchar(300) not null, -- 서버에 저장될 파일이름 
	FILE_SIZE int not null -- 파일 용량 
	);
 */
public class FileDto {
	
	private int IDX;
	private int BOARD_IDX;
	private String ORIGINAL_FILE_NAME;
	private String STORED_FILE_NAME;
	private int FILE_SIZE;
	
	
	public int getIDX() {
		return IDX;
	}
	public void setIDX(int iDX) {
		IDX = iDX;
	}
	public int getBOARD_IDX() {
		return BOARD_IDX;
	}
	public void setBOARD_IDX(int bOARD_IDX) {
		BOARD_IDX = bOARD_IDX;
	}
	public String getORIGINAL_FILE_NAME() {
		return ORIGINAL_FILE_NAME;
	}
	public void setORIGINAL_FILE_NAME(String oRIGINAL_FILE_NAME) {
		ORIGINAL_FILE_NAME = oRIGINAL_FILE_NAME;
	}
	public String getSTORED_FILE_NAME() {
		return STORED_FILE_NAME;
	}
	public void setSTORED_FILE_NAME(String sTORED_FILE_NAME) {
		STORED_FILE_NAME = sTORED_FILE_NAME;
	}
	public int getFILE_SIZE() {
		return FILE_SIZE;
	}
	public void setFILE_SIZE(int fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}
	
	
	
}
