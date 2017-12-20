package com.khh.project.auth.vo;

import java.util.Map;

import com.khh.DefaultVO;
import org.springframework.http.HttpStatus;

import lombok.Data;

public @Data class ContentVO extends DefaultVO {
	Map<String,String> header;
	String body;
	Integer status;
	
	public ContentVO(){
		init();
	}

	private void init() {
		status = HttpStatus.OK.value();
	}
}
