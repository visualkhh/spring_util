package com.khh.project.notice.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.khh.DefaultVO;
import lombok.Data;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "TB_NOTICE_FILE")
public @Data class NoticeFileVO extends DefaultVO {
	private static final long serialVersionUID = -4227105278896155910L;
	
	@Expose @Column(name="FILE_ID")	Integer file_id	;//파일ID	NUMBER
	@Expose @Column(name="FILE_NAME")	String file_name	;//파일명	VARCHAR2
	@Expose @Id @Column(name="NOTICE_ID")	Integer notice_id	;//공지ID	NUMBER
	
	@Transient
	MultipartFile file;
	public NoticeFileVO() {
		init();
	}
	public NoticeFileVO(Integer notice_id) {
		this.notice_id = notice_id;
		init();
	}
	public NoticeFileVO(Integer notice_id, MultipartFile file) {
		this.notice_id = notice_id;
		setFile_name(file.getOriginalFilename());
		setFile(file);
		init();
	}
	
	private void init() {
		if(null==file_id){
			file_id=0;
		}
	}
}
