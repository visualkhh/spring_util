package com.khh.project.template;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.khh.boot.service.BootService;
import com.khh.code.Code;
import com.khh.project.common.service.CommonService;
import com.khh.project.msg.vo.MsgClassVO;
import com.khh.project.msg.vo.MsgTemplateVVO;
import com.khh.project.template.service.TemplateService;
import com.khh.project.vo.DataTableResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khh.DefaultController;
import com.khh.boot.BootManager;
import com.khh.project.msg.vo.MsgTemplateVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.collection.html.HtmlTableList;
import com.omnicns.java.collection.list.poi.ExcelPOIList;
import com.khh.security.SecurityManager;

@Controller("TemplateController")
@RequestMapping("/template")
public class TemplateController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	SecurityManager sManager = SecurityManager.getInstance();
	
	@Resource(name="CommonService")
	CommonService common_service;
	
	@Resource(name="TemplateService")
	TemplateService service;
	
	@Autowired
	BootManager bootMng;
	@Resource(name="BootService")
	BootService bootService;
	
	// 송출문구관리 리스트
	@RequestMapping(value={"","/"}, params="!ac")
	public String index(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/template/list";
	}   
	
	@RequestMapping(value="/list", params="ac=ajax_list")
	public DataTableResultVO list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		int cnt = service.getMsgTemplateCount(dataTable, dataTable.getSearchValue());
		List<MsgTemplateVVO> data = service.getMsgTemplateList(dataTable, dataTable.getSearchValue());
		return dataTable.makeResult(cnt, data); 
		
	}
	@RequestMapping(value="/list", params="ac=excel")
	public ExcelPOIList<MsgTemplateVVO> list_excel(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		int cnt = service.getMsgTemplateCount(dataTable, dataTable.getSearchValue());
		dataTable.setStart(0);
		dataTable.setLength(cnt);
		List<MsgTemplateVVO> data = service.getMsgTemplateList(dataTable, dataTable.getSearchValue());
		ExcelPOIList<MsgTemplateVVO> list = new ExcelPOIList<MsgTemplateVVO>(); 
		list.addAll(data);
		return list; 
	}
	@RequestMapping(value="/list", params="ac=print")
	public HtmlTableList<MsgTemplateVVO> list_print(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		int cnt = service.getMsgTemplateCount(dataTable, dataTable.getSearchValue());
		dataTable.setStart(0);
		dataTable.setLength(cnt);
		List<MsgTemplateVVO> data = service.getMsgTemplateList(dataTable, dataTable.getSearchValue());
		HtmlTableList<MsgTemplateVVO> list = new HtmlTableList<MsgTemplateVVO>(); 
		list.addAll(data);
		return list; 
	}

	//  수정 폼
	@RequestMapping(value="/detail", params="!ac")
	public String detail(@Valid @ModelAttribute("DataTable") DataTableVO dataTable, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		List<MsgClassVO> data = common_service.getMsgClass();
		model.put("msgClass", data);
		return "/template/detail";
	}
	
	@RequestMapping(value="/detail", params="ac=ajax_detail")
	public MsgTemplateVVO detail_info(@RequestParam(value="template_id")Integer template_id,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return service.getMsgTemplate(template_id);
	}
	
	@RequestMapping(value="/delete", params="ac=ajax_delete")
	public Code delete(@RequestParam(value="template_id")Integer template_id, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		Code code = service.deleteMsgTemplate(template_id);
		bootMng.setMsgTemplate(bootService.getMsgTemplate());///템플릿 refresh
		return code;
	}
	@RequestMapping(value="/save", params="ac=ajax_update")
	public Code save_update(@Valid @ModelAttribute("msgTemplate") MsgTemplateVO msgTemplate,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		Code code = service.updateMsgTemplate(msgTemplate);
		bootMng.setMsgTemplate(bootService.getMsgTemplate());///템플릿 refresh
		return code;
	}
	@RequestMapping(value="/save", params="ac=ajax_create")
	public Code save_create(@Valid @ModelAttribute("msgTemplate") MsgTemplateVO msgTemplate,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		Code code = service.saveMsgTemplate(msgTemplate);
		bootMng.setMsgTemplate(bootService.getMsgTemplate());///템플릿 refresh
		return code;
	}
}
