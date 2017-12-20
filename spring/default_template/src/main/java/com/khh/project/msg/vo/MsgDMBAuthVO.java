package com.khh.project.msg.vo;

import javax.validation.constraints.NotNull;

import com.khh.code.Code;
import org.springframework.validation.BindingResult;

import com.khh.DefaultVO;
import com.omnicns.web.spring.validation.ValidationUtil;

import lombok.Data;
public @Data class MsgDMBAuthVO extends DefaultVO {
	private static final long serialVersionUID = 3951786793896721440L;
	String approve_mode;// $approve_mode = $_POST["approve_mode"];
	String status;// $status = $_REQUEST["status"];
	@NotNull(message="id NotEmpty")
	Integer id;// $id = $_POST["id"];	//wait_id 임..
	Integer operator_id;// $operator_id = $_POST["operator_id"];
	
	public MsgDMBAuthVO(){
		init();
	}

	private void init() {
	}
	
	
	
	@Override
	public void validate(BindingResult errors) {
		Code apm = Code.CODES_COMPANY.child(getApprove_mode());
		if(apm != Code.CODE_MSG_STATUS_APPROVED  && apm != Code.CODE_MSG_STATUS_REJECTD){
			errors.addError(ValidationUtil.newError("approve_mode", "허용하지 않는 MODE입니다."));
		}
	}
}
