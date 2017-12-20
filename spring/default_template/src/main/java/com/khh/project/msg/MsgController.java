package com.khh.project.msg;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.khh.DefaultController;
import com.khh.boot.BootManager;
import com.khh.boot.service.BootService;
import com.khh.code.Code;
import com.khh.config.ConfigManager;
import com.khh.project.group.service.GroupService;
import com.khh.project.group.vo.AreaGroupListVVO;
import com.khh.project.log.service.LogService;
import com.khh.project.log.vo.LogCompanyVO;
import com.khh.project.msg.service.MsgService;
import com.khh.project.operator.service.OperatorService;
import com.khh.project.operator.vo.OperatorGrantVO;
import com.khh.project.template.service.TemplateService;
import com.khh.project.vo.DataTableResultVO;
import com.khh.project.vo.DataTableVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khh.project.msg.vo.MsgAreaVO;
import com.khh.project.msg.vo.MsgClassVO;
import com.khh.project.msg.vo.MsgSubmitSVO;
import com.khh.project.msg.vo.MsgSubmitVVO;
import com.khh.project.msg.vo.MsgTemplateVVO;
import com.khh.project.msg.vo.MsgWaitJoinVO;
import com.khh.project.msg.vo.MsgWaitVO;
import com.khh.project.msg.vo.MsgWaitVVO;
import com.omnicns.java.collection.list.poi.ExcelPOIList;
import com.omnicns.java.date.DateUtil;
import com.omnicns.java.string.StringUtil;
import com.khh.security.SecurityManager;
@Slf4j
@Controller("MsgController")
@RequestMapping("/msg")
public class MsgController extends DefaultController {
	@Autowired
	SecurityManager securityMng;;
	@Resource(name="MsgService")
	MsgService service;
	@Autowired
	ConfigManager configMng;
	@Autowired
	BootManager bootMng;

	@Resource(name="TemplateService")
	TemplateService templateService;
	@Resource(name="LogService")
	LogService logService;
	@Resource(name="GroupService")
	GroupService groupService;
	@Resource(name="OperatorService")
	OperatorService operatorService;
	@Resource(name="BootService")
	BootService bootService;
	
	/////////////////////////////// 송출 등록 (jsp) 
	@RequestMapping(value="/add",params="!ac")
	public String add(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		List<AreaGroupListVVO> allAreaGroupList = groupService.getAllAreaGroupList();
		model.put("allAreaGroupList", allAreaGroupList);
		List<OperatorGrantVO> grants = operatorService.getOperatorGrant(Restrictions.eq("operator_id", securityMng.getSecurityUser().getOperator_id()));
		
		Map<Integer,OperatorGrantVO> grantsMap = new LinkedHashMap<Integer,OperatorGrantVO>();
		
		grants.forEach(at->{
			grantsMap.put(at.getArea_id(), at);
		});
		
		model.put("grants", grantsMap);
		
		
		return "/msg/add";
	}
	
	// 송출 등록 처리  
	@RequestMapping(value="/add/save",params="ac=ajax_save")
	public Code msgSave(@Valid @ModelAttribute("MsgWait") MsgWaitJoinVO msgWait, BindingResult result, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		log.debug(msgWait.toString());
		msgWait.setCreate_id(securityMng.getSecurityUser().getOperator_id());
		
		//사용자 권한있는것만 처리하도록 
		List<Integer> area_id_list = msgWait.getAreas().stream().map(at->at.getArea_id()).collect(Collectors.toCollection(ArrayList::new));
		List<Criterion> where = new ArrayList<Criterion>();
		where.add(Restrictions.eq("operator_id", securityMng.getSecurityUser().getOperator_id()));
		where.add(Restrictions.in("area_id", area_id_list));
		List<OperatorGrantVO> grants = operatorService.getOperatorGrant(where);
		if(grants.size()>0){
			List<MsgAreaVO> grantArea = grants.stream().map(at->{return new MsgAreaVO(at.getArea_id());}).collect(Collectors.toCollection(ArrayList::new));
			msgWait.setAreas(grantArea);
		}
		service.addMsg(msgWait);
		return Code.SUCCESS;
	}
	// 송출 수정 처리  
	@RequestMapping(value="/add/save",params="ac=ajax_update")
	public Code msgUpdate(@Valid @ModelAttribute("MsgWait") MsgWaitJoinVO msgWait,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		log.debug(msgWait.toString());
		
		//사용자 권한있는것만 처리하도록 
		List<Integer> area_id_list = msgWait.getAreas().stream().map(at->at.getArea_id()).collect(Collectors.toCollection(ArrayList::new));
		List<Criterion> where = new ArrayList<Criterion>();
		where.add(Restrictions.eq("operator_id", securityMng.getSecurityUser().getOperator_id()));
		where.add(Restrictions.in("area_id", area_id_list));
		List<OperatorGrantVO> grants = operatorService.getOperatorGrant(where);
		if(grants.size()>0){
			List<MsgAreaVO> grantArea = grants.stream().map(at->{return new MsgAreaVO(at.getArea_id());}).collect(Collectors.toCollection(ArrayList::new));
			msgWait.setAreas(grantArea);
		}
		service.updateMsg(msgWait);
		return Code.SUCCESS;
	}
	
	
	
	
	
	/////////////////////////////////////////////// 송출메시지 템플릿   
	@RequestMapping(value="/template",params="ac=ajax_detail")
	public MsgTemplateVVO templateDetail(@RequestParam(value="template_id")Integer template_id, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		return templateService.getMsgTemplate(template_id);
	}
	
	
	

	
	
	
	////////////////////////////////////////////// 송출예약 리스트 (jsp)
	@RequestMapping(value="/wait",params="!ac")
	public String wait(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/msg/wait";
	}
	
	// 송출예약 리스트 (json)
	@RequestMapping(value="/wait/list",params="ac=ajax_list")
	public DataTableResultVO wait_list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		int cnt = service.getMsgWaitCount(dataTable);
		List<MsgWaitVVO> data = service.getMsgWaitList(dataTable);
		
		data.replaceAll(item->{
			String company = item.getCompany();
			String companyStr = "";
			if(company!=null){
				List<String> companyArr = Arrays.asList(company.split(","));
				companyArr.replaceAll(companyItem->{
					return Code.CODES_COMPANY.child(companyItem).msg();
				});
				
				companyStr = StringUtil.join(companyArr.toArray(new String[companyArr.size()]), "<br>");
			}
			
			item.setCompany(companyStr);
			item.setMine(item.getCreate_id().equals(securityMng.getSecurityUser().getOperator_id())?true:false);
			return item;
		});
		return dataTable.makeResult(cnt, data);
	}

	// 그룹지역 상세 (json)
	@RequestMapping(value="/wait/detail", params="ac=ajax_detail") 
	public MsgWaitJoinVO detail_info(@RequestParam(value="wait_id")Integer wait_id, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return service.getMsgWaitJoin(wait_id);
	}
	
	
	////////////////////////////////////////////// 송출결과 리스트 (jsp)
	@RequestMapping(value="/result",params="!ac")
	public String result(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/msg/result";
	}

	// 송출결과 리스트 (json)
	@RequestMapping(value="/result/list",params="ac=ajax_list")
	public DataTableResultVO result_list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable ,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		StopWatch stopWatch = new StopWatch();

		stopWatch.start("service.getMsgSubmitCount");
		int totCnt = service.getMsgSubmitCount(dataTable);
		stopWatch.stop();log.debug("took:("+stopWatch.getLastTaskName()+")"+stopWatch.getLastTaskTimeMillis());

		stopWatch.start("service.getMsgSubmitList");
		List<MsgSubmitVVO> list = service.getMsgSubmitList(dataTable);
		stopWatch.stop();log.debug("took:("+stopWatch.getLastTaskName()+")"+stopWatch.getLastTaskTimeMillis());

		stopWatch.start("process");
		list.replaceAll(item->{
			String company = item.getCompany();
			String companyStr = "";
			if(company!=null){
				List<String> companyArr = Arrays.asList(company.split(","));
				companyArr.replaceAll(companyItem->{
					return Code.CODES_COMPANY.child(companyItem).msg();
				});
				
				companyStr = StringUtil.join(companyArr.toArray(new String[companyArr.size()]), "<br>");
			}
			item.setMine(item.getCreate_id().equals(securityMng.getSecurityUser().getOperator_id())?true:false);
			item.setCompany(companyStr);
			return item;
		});
		stopWatch.stop();log.debug("took:("+stopWatch.getLastTaskName()+")"+stopWatch.getLastTaskTimeMillis());
		log.debug(stopWatch.prettyPrint());
		return dataTable.makeResult(totCnt, list);		
	}
	@RequestMapping(value="/result/list",params="ac=excel")
	public ExcelPOIList<MsgSubmitVVO> result_excel_list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable ,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		int totCnt = service.getMsgSubmitCount(dataTable);
		dataTable.setStart(0);
		dataTable.setLength(totCnt);
		List<MsgSubmitVVO> dataList = service.getMsgSubmitList(dataTable);
		dataList.replaceAll(item->{
			String company = item.getCompany();
			if(null!=company && company.length()>0){
				String[] companys = company.split(",");
				List<String> comapanyMsgs = new ArrayList<String>();
				for(String atCompany : companys){
					comapanyMsgs.add(Code.CODES_COMPANY.child(atCompany).msg());
				}
				item.setCompany(comapanyMsgs.stream().collect(Collectors.joining(", ")));
			}
			item.setAreas(Optional.ofNullable(item.getAreas_group()).map(at->at+",").orElse("")+Optional.ofNullable(item.getAreas()).orElse(""));
			item.setMine(item.getCreate_id().equals(securityMng.getSecurityUser().getOperator_id())?true:false);
			item.setDescription(item.getDescription().replaceAll("\\<.*?>", " "));
			item.setContents_all(item.getContentsFirst());
			return item;
		});
		 
		
		String start_dt = DateUtil.modifyDate(new Date(), Calendar.DATE, -7, "yyyyMMdd");
		String end_dt 	= DateUtil.dateFormat("yyyyMMdd",new Date());
		String grade	= "등급전체";
		String msgClass	= "정보분류전체";
		String status	= "전송전체";
		String area		= "지역전체";
		MsgSubmitSVO query=dataTable.getSearchJson(MsgSubmitSVO.class);		
		if(null!=query && null!=query.getStart_dt()){
			start_dt = query.getStart_dt();
		}
		if(null!=query && null!=query.getEnd_dt()){
			end_dt = query.getEnd_dt();
		}
		if(null != query && null != query.getGrade_id() && query.getGrade_id() != 0){
			grade = Code.CODES_MSG_LEVEL.child(String.valueOf(query.getGrade_id())).msg();
		}
		if(null != query && null != query.getClass_id() && query.getClass_id() != 0){
			MsgClassVO msgclass = bootMng.getMsgClass(query.getClass_id());
			if(null!=msgclass){
				msgClass = msgclass.getClass1()+" "+msgclass.getClass2();
			}
		}
		if(null != query && null != query.getStatus()){
			status =  Code.CODES_MSG_STATUS.child(query.getStatus()).msg();
		}
		if(null != query && null != query.getAreas()){
			area = query.getAreas();
		}
		
		String filename = "sendMsgresult_" + start_dt + "_" + end_dt + "_" + grade + "_"+ msgClass + "_" + status + "_" + area;
		
		
		
		ExcelPOIList<MsgSubmitVVO> data = new ExcelPOIList<MsgSubmitVVO>(filename); 
		data.addAll(dataList);
		data.setBeforeWrite((sheet)->{
			String[] meta = filename.split("_");
			int rowIdx = 0;
			Row row = sheet.createRow(rowIdx++);
			Cell cell = row.createCell(0);
			cell.setCellValue("조회기간 : ");
			cell = row.createCell(1);
			cell.setCellValue(meta[1]+" ~ "+meta[2]);
			
			row = sheet.createRow(rowIdx++);
			cell = row.createCell(0);
			cell.setCellValue("정보등급 : ");
			cell = row.createCell(1);
			cell.setCellValue(meta[3]);
			
			row = sheet.createRow(rowIdx++);
			cell = row.createCell(0);
			cell.setCellValue("정보분류 : ");
			cell = row.createCell(1);
			cell.setCellValue(meta[4]);
			
			row = sheet.createRow(rowIdx++);
			cell = row.createCell(0);
			cell.setCellValue("전송결과 : ");
			cell = row.createCell(1);
			cell.setCellValue(meta[5]);
			
			row = sheet.createRow(rowIdx++);
			cell = row.createCell(0);
			cell.setCellValue("지역 : ");
			cell = row.createCell(1);
			cell.setCellValue(meta[6]);
			
			row = sheet.createRow(rowIdx++);
			cell = row.createCell(0);
			cell.setCellValue("작성일자 : ");
			cell = row.createCell(1);
			cell.setCellValue(DateUtil.dateFormat("yyyyMMdd",new Date()));
			
			rowIdx++;
			return rowIdx;
		});
		return data;
	}
	@RequestMapping(value="/result/list",params="ac=print")
	public String result_print_list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable ,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		int totCnt = service.getMsgSubmitCount(dataTable);
		dataTable.setStart(0);
		dataTable.setLength(totCnt);
		List<MsgSubmitVVO> list = service.getMsgSubmitList(dataTable);
		list.replaceAll(item->{
			String company = item.getCompany();
			if(null!=company && company.length()>0){
				String[] companys = company.split(",");
				List<String> comapanyMsgs = new ArrayList<String>();
				for(String atCompany : companys){
					comapanyMsgs.add(Code.CODES_COMPANY.child(atCompany).msg());
				}
				item.setCompany(comapanyMsgs.stream().collect(Collectors.joining(",")));
			}
			item.setMine(item.getCreate_id().equals(securityMng.getSecurityUser().getOperator_id())?true:false);
			return item;
		});
		request.setAttribute("printData", list);
		return "/msg/printWindow";
	}

	/////////////// 송출 승인 (jsp)
	@RequestMapping(value="/approve",params="!ac")
	@PreAuthorize("hasRole('ROLE_G_ADVANCED')")
	public String approve(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/msg/approve";
	}

	
	
	// 송출 승인 처리 (java)  승인, 반려, 삭제 등등..
	@RequestMapping(value="/approve",params="ac=ajax_approve") 
	@PreAuthorize("hasRole('ROLE_G_ADVANCED')")
	public Code approve_save(@Valid @ModelAttribute("MsgWait") MsgWaitVO msgWait, @RequestParam(value="agree_pw") String agree_pw, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		if(Code.CODES_MSG_STATUS.child(msgWait.getStatus())==Code.UNKNOW){
			throw new Exception(Code.VALIDATE_FAIL.msg());
		}
		msgWait.setApprove_id(securityMng.getSecurityUser().getOperator_id());
		msgWait.refreshWaiteDateTimeFromNow();
		msgWait.refreshApproveDateTimeFromNow();
		
		String saltKey = securityMng.getSecurityUser().getIndv_key();

		//반려면 비밀번호 묻지도 따지지도 않음,
		if(Code.CODES_MSG_STATUS.child(msgWait.getStatus()) == Code.CODE_MSG_STATUS_REJECTD || (null!=agree_pw && securityMng.encrypt(saltKey,agree_pw).equals(securityMng.getSecurityUser().getLogin_pw())) ){
			return service.updateMsgWaitStatus(msgWait);
		}else{
			throw new Exception(Code.AUTH_FAIL.msg());
		}
	}
	
	
	//자기자신 예약메시지 삭제
	@RequestMapping(value="/approve",params="ac=ajax_mine_delete") 
	public Code approve_mine_delete(@RequestParam(value="wait_id")Integer wait_id, @RequestParam(value="agree_pw") String agree_pw, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		
		
		MsgWaitVO dbMsgWait = service.getMsgWait(Restrictions.eq("wait_id",wait_id));
		if(null==dbMsgWait || !securityMng.getSecurityUser().getOperator_id().equals(dbMsgWait.getCreate_id())){
			throw new Exception("현재 로그인된 사용자의 메시지가 아니거나 이미 처리되었습니다.(삭제 불가능)");
		}
		
		MsgWaitVO msgWait = new MsgWaitVO();
		msgWait.setWait_id(wait_id);
		msgWait.setStatus(Code.CODE_MSG_STATUS_DELETED.cd());
		if(Code.CODES_MSG_STATUS.child(msgWait.getStatus())==Code.UNKNOW){
			throw new Exception(Code.VALIDATE_FAIL.msg());
		}
		
		msgWait.setApprove_id(securityMng.getSecurityUser().getOperator_id());
		msgWait.refreshWaiteDateTimeFromNow();
		msgWait.refreshApproveDateTimeFromNow();
		String saltKey = securityMng.getSecurityUser().getIndv_key();
		if(null!=agree_pw && securityMng.encrypt(saltKey,agree_pw).equals(securityMng.getSecurityUser().getLogin_pw())){
			return service.updateMsgWaitStatus(msgWait);
		}else{
			throw new Exception(Code.AUTH_FAIL.msg());
		}
	}

	
	
	
	////////////////////////////// 송출결과 로그 (json)
	@RequestMapping(value="/result/log",params="ac=ajax_log")
	public List<LogCompanyVO> result_log(@RequestParam(value="wait_id")Integer wait_id, HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		List<LogCompanyVO> list = logService.getLog(wait_id);
		return list;		
	}
}
