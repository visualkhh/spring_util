package com.khh.project.operator;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.khh.code.Code;
import com.khh.project.operator.vo.OperatorReqVO;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khh.DefaultController;
import com.khh.project.operator.service.OperatorService;
import com.khh.project.operator.vo.CheckCardJoinVO;
import com.khh.project.operator.vo.OperatorReqRightJoinVO;
import com.khh.project.operator.vo.OperatorRightJoinVO;
import com.khh.project.operator.vo.OperatorSVO;
import com.khh.project.vo.DataTableResultVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.date.DateUtil;
import com.omnicns.java.db.hibernate.Hibernater;
import com.khh.security.SecurityManager;
import com.omnicns.web.spring.exception.JsonErrorsException;

@Controller("OperatorController")
@RequestMapping("/operator")
@Transactional(rollbackOn = { Exception.class })
public class OperatorController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	SecurityManager securityMng;
	@Resource(name="OperatorService")
	OperatorService service;
	@Autowired
	Hibernater hibernate;
	

	// 운영자  리스트  (jsp)
	@RequestMapping(value={"","/"}, params="!ac")
	public String index(HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return "/operator/list";
	}
	
	// 운영자 리스트 (json)
	@RequestMapping(value="/list", params="ac=ajax_list")
	public DataTableResultVO list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable ,HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		long cnt = -0;
		List<?> data = null; //OperatorRightJoinVO 올수도 있고 OperatorVO 올수도있다.
		OperatorSVO option = dataTable.getSearchJson(OperatorSVO.class);
		
		if(null!=option.getAuth_search_mode()&&option.getAuth_search_mode().length()>0){
			cnt = service.getOperatorRightJoinListCount(dataTable);
		 	data = service.getOperatorRightJoinList(dataTable);
		}else{
			cnt = service.getOperatorListCount(dataTable);
			data = service.getOperatorList(dataTable);
		}
		return dataTable.makeResult(cnt,data);
	}
	
	// 운영자  상세 (jsp)
	@RequestMapping(value="/detail", params="!ac")
	public String operator_form(HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return "/operator/detail";
	}

	// 운영자 상세 (json)
	@RequestMapping(value="/detail", params="ac=ajax_detail")
	public OperatorRightJoinVO getOperatorDetail(@RequestParam(value="operator_id") Integer operator_id, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		OperatorRightJoinVO o = service.getOperatorRightJoin(Restrictions.eq("operator_id", operator_id));
		return o;
	}
	//운영자 등록
	@RequestMapping(value="/save", params="ac=ajax_insert")
	public Code saveOperatorRightJoin(@Valid @ModelAttribute("OperatorRightJoin") OperatorRightJoinVO operatorRightJoin, HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		if(null!=operatorRightJoin.getLogin_pw() && operatorRightJoin.getLogin_pw().length()==0){
			throw new JsonErrorsException("비밀번호를 입력이 필요합니다");
		}
		if(null!=operatorRightJoin.getCard_no() && operatorRightJoin.getCard_no().length()>0 ){// DB에 존재하는 카드번호인지 체크
			Code code = service.isCard(operatorRightJoin.getCard_no());
			if(code==Code.FAIL){
				throw new JsonErrorsException(code.msg());
			}
		}else{
			operatorRightJoin.setCard_no(null);
		}
		operatorRightJoin.setIndv_key(securityMng.createSaltKey());
		operatorRightJoin.setLogin_pw(securityMng.encrypt(operatorRightJoin.getIndv_key(), operatorRightJoin.getLogin_pw()));
		operatorRightJoin.setLast_pwd_update(DateUtil.getDate("yyyyMMddHHmmss"));

		return service.saveOperatorRightJoin(operatorRightJoin);
	}
	//운영자 수정
	@RequestMapping(value="/save", params="ac=ajax_update")
	public Code modifyOperatorRightJoin(@Valid @ModelAttribute("OperatorRightJoin") OperatorRightJoinVO operatorRightJoin, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		
		if(null!=operatorRightJoin.getCard_no() && operatorRightJoin.getCard_no().length()>0 ){// DB에 존재하는 카드번호인지 체크
			Code code = service.isCard(operatorRightJoin.getCard_no());
			if(code==Code.FAIL){
				throw new JsonErrorsException(code.msg());
			}
		}else{
			operatorRightJoin.setCard_no(null);
		}
		
		if(null!=operatorRightJoin.getLogin_pw() && operatorRightJoin.getLogin_pw().length()==0){
			operatorRightJoin.setLogin_pw(null);
		}else{
			operatorRightJoin.setIndv_key(securityMng.createSaltKey());
			operatorRightJoin.setLogin_pw(securityMng.encrypt(operatorRightJoin.getIndv_key(), operatorRightJoin.getLogin_pw()));
			operatorRightJoin.setLast_pwd_update(DateUtil.getDate("yyyyMMddHHmmss"));
		}
		return service.modifyOperatorRightJoin(operatorRightJoin);
	}
	@RequestMapping(value="/delete", params="ac=ajax_delete")
	public Code deleteOperatorRightJoin(@RequestParam(value="operator_id") Integer operator_id, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
//		return service.deleteOperatorRight(operator_id);
		return service.deletedYOperator(operator_id);
	}
	

	////////////////REQ WAIT////////////승인관리//////
	// 운영자 승인 리스트 (jsp)
	@RequestMapping(value="/wait", params="!ac")
	public String operatorReqForm(HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return "/operator/wait";
	}	
	// 운영자 승인 리스트 (json)
	@RequestMapping(value="/wait/list", params="ac=ajax_list")
	public DataTableResultVO operatorReqList(@Valid @ModelAttribute("DataTable") DataTableVO dataTable ,HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		long cnt = service.getOperatorReqCount(Restrictions.like("name", "%"+dataTable.getSearchValue()+"%"));
		List<OperatorReqVO> data = service.getOperatorReqList(dataTable);
		return dataTable.makeResult(cnt,data);
	}
	// Card 유효성 체크 (json)
	@RequestMapping(value="/card", params="ac=ajax_check")
	public Code cardCheck(@Valid @ModelAttribute("CheckCardJoinVO") CheckCardJoinVO checkCardJoinVO ,HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		// DB에 존재하는 카드번호인지 체크
		return service.isCard(checkCardJoinVO.getCard_no());
		
	}
	

	// 운영자 승인 상세 (jsp)
	@RequestMapping(value="/wait/detail", params="!ac")
	public String operatorReqDetailForm(HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return "/operator/wait_detail";
	}
	// 운영자 승인 상세 (json)
	@RequestMapping(value="/wait/detail", params="ac=ajax_detail")
	public OperatorReqVO operatorReqDetail(@RequestParam(value="login_id")String login_id,HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return service.getOperatorReq(Restrictions.eq("login_id", login_id));
	}
	
	// 운영자 승인 상세 (save)
	@RequestMapping(value="/wait/save", params="ac=ajax_update")
	public Code operatorReqRightJoinmoveAndSave(@Valid @ModelAttribute("OperatorRightJoin") OperatorReqRightJoinVO operatorReqRightJoin, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		if(null!=operatorReqRightJoin.getCard_no() && operatorReqRightJoin.getCard_no().length()>0 ){// DB에 존재하는 카드번호인지 체크
			Code code = service.isCard(operatorReqRightJoin.getCard_no());
			if(code==Code.FAIL){
				throw new JsonErrorsException(code.msg());
			}
		}else{
			operatorReqRightJoin.setCard_no(null);
		}
		
		operatorReqRightJoin.setLast_pwd_update( DateUtil.getDate("yyyyMMddHHmmss"));
		service.moveAndsaveOperatorReqRightJoin(operatorReqRightJoin);
		return Code.SUCCESS;
	}
	// 운영자 승인 상세 (DELETE)
	@RequestMapping(value="/wait/delete", params="ac=ajax_delete")
	public Code operatorReqDelete(@RequestParam(value="login_id")String login_id, HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		service.deleteOperatorReq(login_id);
		return Code.SUCCESS;
	}
}