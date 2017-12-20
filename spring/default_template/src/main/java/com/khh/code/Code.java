package com.khh.code;

import java.util.Map;

public enum Code {
	
	///////status
	SUCCESS							(new CodeInfo("00000000", "성공 하였습니다."		)),
	FAIL							(new CodeInfo("99999999", "실패 하였습니다."		)),
	AUTH_FAIL						(new CodeInfo("99999998", "인증실패 하였습니다."	)),
	VALIDATE_FAIL					(new CodeInfo("99999997", "유효하지 않습니다."	)),
	VALIDATE_FILE_EXTENSION_FAIL	(new CodeInfo("99999101", "유효하지 않은 파일 확장자입니다."	)),
	VALIDATE_FILE_SIZE_FAIL			(new CodeInfo("99999102", "유효하지 않은 사이즈입니다."	)),
	TARGET_FAIL						(new CodeInfo("99999994", "대상이 아닙니다."		)),
	DATA_EMPTY						(new CodeInfo("99999993", "데이터가 없습니다."	)),
	TARGET_DONE						(new CodeInfo("99999993", "이미 처리되었습니다."	)),
	

	//////normal code
	CODE_COMPANY_ALL		(  new CodeInfo("0", "전체"			).field(new FieldInfo().setName("contents_all"))   	),
	CODE_COMPANY_SKT		(  new CodeInfo("1", "SKT"			).field(new FieldInfo().setName("contents_skt"))   	),
//	CODE_COMPANY_KTF		(  new CodeInfo("2", "KT"			).field(new FieldInfo().setName("contents_ktf")) 	),
	CODE_COMPANY_LGT		(  new CodeInfo("3", "LG U+"		).field(new FieldInfo().setName("contents_lgt")) 	),
	CODE_COMPANY_SKT_LTE	(  new CodeInfo("4", "SKT LTE"		).field(new FieldInfo().setName("contents_skt_lte")) ),
	CODE_COMPANY_KT_LTE		(  new CodeInfo("5", "KT LTE"		).field(new FieldInfo().setName("contents_kt_lte"))  ),
	CODE_COMPANY_LGU_LTE	(  new CodeInfo("6", "LG U+ LTE"	).field(new FieldInfo().setName("contents_lgu_lte")) ),
	CODES_COMPANY			(  new CodeInfo("9","회사그룹"		).putChild(
		CODE_COMPANY_ALL,CODE_COMPANY_SKT,CODE_COMPANY_LGT,CODE_COMPANY_SKT_LTE,CODE_COMPANY_KT_LTE,CODE_COMPANY_LGU_LTE
	)),
	
	//msg status
	CODE_MSG_STATUS_WAIT	(new CodeInfo("WAIT", 		"승인대기"		)),
	CODE_MSG_STATUS_READY	(new CodeInfo("READY", 		"즉시송출권한자 예약"	)),
	CODE_MSG_STATUS_APPROVED(new CodeInfo("APPROVED", 	"승인완료"	)),
	CODE_MSG_STATUS_REJECTD (new CodeInfo("REJECTED", 	"승인반려"	)),
	CODE_MSG_STATUS_SENDING	(new CodeInfo("SENDING", 	"전송중"	)), //뒷단 별도의 모듈이 돌면서 UPDATE하는값 
	CODE_MSG_STATUS_FINISHED(new CodeInfo("FINISHED", 	"전송완료"	)), //뒷단 별도의 모듈이 돌면서 UPDATE하는값
	//CODE_MSG_STATUS_FAILURE (new CodeInfo("FAILURE", 	"전송실패"	)), //TB_MSG_SUB_LOG 테이블에만 존재하는 상태값    통신사에서 받은 로그를 가지고 박는 것  뒷단 별도의 모듈이 돌면서 UPDATE하는값
	CODE_MSG_STATUS_DELETED	(new CodeInfo("DELETED", 	"삭제"	)),
	CODES_MSG_STATUS		(  new CodeInfo("MSG_STATUS","메시지 상태 그룹"		).putChild(
		CODE_MSG_STATUS_WAIT,CODE_MSG_STATUS_READY,CODE_MSG_STATUS_APPROVED,CODE_MSG_STATUS_REJECTD,CODE_MSG_STATUS_SENDING,CODE_MSG_STATUS_FINISHED,CODE_MSG_STATUS_DELETED	
	)),
	CODE_MSG_LEVEL_WARNING		(new CodeInfo("1", 		"경보"		)),
	CODE_MSG_LEVEL_WATCH		(new CodeInfo("2", 		"주의보"		)),
	CODE_MSG_LEVEL_FORECAST		(new CodeInfo("3", 		"예보"		)),
	CODE_MSG_LEVEL_CLEAR		(new CodeInfo("4", 		"해제"		)),
	CODE_MSG_LEVEL_INFO			(new CodeInfo("5", 		"정보"		)),
	CODES_MSG_LEVEL				(new CodeInfo("", 		"정보등급"	).putChild(
			CODE_MSG_LEVEL_WARNING,CODE_MSG_LEVEL_WATCH,CODE_MSG_LEVEL_FORECAST,CODE_MSG_LEVEL_CLEAR,CODE_MSG_LEVEL_INFO			
	)),
	
	CODE_CHANNEL_INFO		(new CodeInfo("4372", 		"안전 안내 문자(채널 : 4372)"		)),
	CODE_CHANNEL_EMERGENCY	(new CodeInfo("4371", 		"긴급 재난 문자(채널 : 4371)"		)),
	CODE_CHANNEL_CRITICAL	(new CodeInfo("4370", 		"위급 재난 문자(채널 : 4370)"		)),
	CODES_CHANNEL			(new CodeInfo("0000", 		"재난 문자 채널"	).putChild(
			CODE_CHANNEL_INFO,CODE_CHANNEL_EMERGENCY,CODE_CHANNEL_CRITICAL
	)),
	
	
	CODE_CALLBACK_TYPE_TEXT	(new CodeInfo("TXT", 	"텍스트"	)),
	
	CODE_OP_LEVEL_INFO_REGISTER		(new CodeInfo("1", 		"정보등록자"		)),
	CODE_OP_LEVEL_INFO_APPROVER		(new CodeInfo("2", 		"정보승인자"		)),
	CODE_OP_LEVEL_SELECTOR			(new CodeInfo("3", 		"단순조회자"		)),
	CODE_OP_LEVEL_MASTER			(new CodeInfo("4", 		"MASTER"		)),
	CODES_OP_LEVEL					(new CodeInfo().putChild(
			CODE_OP_LEVEL_INFO_REGISTER,CODE_OP_LEVEL_INFO_APPROVER,CODE_OP_LEVEL_SELECTOR,CODE_OP_LEVEL_MASTER
	)),
	
	CODE_RIGHT_GROUP_ADVANCED	(new CodeInfo("G_ADVANCED", 	"고급권한"		)),
	CODE_RIGHT_GROUP_CBS		(new CodeInfo("G_CBS", 			"재난문자전송"	)),
	CODE_RIGHT_GROUP_CBS2		(new CodeInfo("G_CBS2", 		"재난문자관리"	)),
	CODE_RIGHT_GROUP_STAT		(new CodeInfo("G_STAT", 		"통계관리"		)),
	CODE_RIGHT_GROUP_MASTER		(new CodeInfo("G_MASTER", 		"운영자관리"	)),
	CODES_RIGHT_GROUP			(new CodeInfo().putChild(
			CODE_RIGHT_GROUP_ADVANCED, CODE_RIGHT_GROUP_CBS, CODE_RIGHT_GROUP_CBS2, CODE_RIGHT_GROUP_STAT, CODE_RIGHT_GROUP_MASTER
	)),
	
	CODE_RIGHT_MANAGE_IM		(new CodeInfo("R_MANAGE_IM", 		"즉시송출권한"		)),
	CODE_RIGHT_MANAGE_APPROVE	(new CodeInfo("R_MANAGE_APPROVE", 	"송출승인권한"		)),
	CODE_RIGHT_MASTER_OPERATOR	(new CodeInfo("R_MASTER_OPERATOR", 	"운영자 관리"		)),
	CODE_RIGHT_MASTER_NOTICE	(new CodeInfo("R_MASTER_NOTICE", 	"공지사항 관리"		)),
	CODE_RIGHT_MSG_SUBMIT		(new CodeInfo("R_MSG_SUBMIT", 		"재난정보 등록"		)),
	CODE_RIGHT_MSG_WAIT			(new CodeInfo("R_MSG_WAIT", 		"송출예약리스트"	)),
	CODE_RIGHT_MSG_RESULT		(new CodeInfo("R_MSG_RESULT", 		"송출결과리스트"	)),
	CODE_RIGHT_MSG_AREA			(new CodeInfo("R_MSG_AREA", 		"그룹지역 관리"		)),
	CODE_RIGHT_MSG_TEMPLATE		(new CodeInfo("R_MSG_TEMPLATE", 	"송출문구 관리"		)),
	CODE_RIGHT_STAT_MSG			(new CodeInfo("R_STAT_MSG", 		"종합 통계"		)),
	CODE_RIGHT_MSG_LOG			(new CodeInfo("R_MSG_LOG", 			"이통사발송 리스트"	)),
	CODE_RIGHT_MASTER_WAIT		(new CodeInfo("R_MASTER_WAIT", 		"운영자 승인 관리"	)),
	CODE_RIGHT_MSG_AREA2		(new CodeInfo("R_MSG_AREA2", 		"지역관리"			)),
	CODE_RIGHT_STAT_TYPE		(new CodeInfo("R_STAT_TYPE", 		"재난유형별 통계"	)),
	CODE_RIGHT_STAT_AREA		(new CodeInfo("R_STAT_AREA", 		"송출지역별 통계"	)),
	CODES_RIGHT					(new CodeInfo().putChild(
			CODE_RIGHT_MANAGE_IM,CODE_RIGHT_MANAGE_APPROVE,CODE_RIGHT_MASTER_OPERATOR ,CODE_RIGHT_MASTER_NOTICE ,CODE_RIGHT_MSG_SUBMIT,CODE_RIGHT_MSG_WAIT,CODE_RIGHT_MSG_RESULT,CODE_RIGHT_MSG_AREA,CODE_RIGHT_MSG_TEMPLATE,
			CODE_RIGHT_STAT_MSG,CODE_RIGHT_MSG_LOG,CODE_RIGHT_MASTER_WAIT,CODE_RIGHT_MSG_AREA2,CODE_RIGHT_STAT_TYPE,CODE_RIGHT_STAT_AREA		
	)),
	
	
	
	UNKNOW		(new CodeInfo("00001000", "알수없음."			));
	
	
	private CodeInfo code;
	private Code(CodeInfo code) {
		this.code = code;
	}
	public CodeInfo code() {
		return code;
	}
	public FieldInfo field() {
		return code.getField();
	}
	public Code setCode(CodeInfo code){
		this.code=code;
		return this;
	}
	public Code setMsg(String msg){
		this.code.setMsg(msg);
		return this;
	}
	public String cd(){
		return code.getCode();
	}
	public Code child(String codeKey){
		Code atCode = code.getChilds().get(codeKey);
		if(null==atCode){
			atCode = Code.UNKNOW;
		}
		return atCode;
	}
	public Map<String,Code> childs(){
		return code.getChilds();
	}
	public String msg(){
		return code.getMsg();
	}
	
	public Code set(Object data) {
		code().setData(data);
		return this;
	}

}
