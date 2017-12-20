package com.khh.project.group;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.khh.code.Code;
import com.khh.project.group.service.GroupService;
import com.khh.project.group.vo.AreaGroupJoinVO;
import com.khh.project.group.vo.AreaGroupListVVO;
import com.khh.project.vo.DataTableResultVO;
import com.khh.project.vo.DataTableVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khh.DefaultController;
import com.omnicns.java.collection.html.HtmlTableList;
import com.omnicns.java.collection.list.poi.ExcelPOIList;

@Controller("GroupController")
@RequestMapping("/group")
public class GroupController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Resource(name="GroupService")
	GroupService service;
	
	
	// 그룹지역 리스트 (jsp) 
	@RequestMapping(value={"","/"}, params="!ac")
	public String group(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/group/list";
	}

	// 그룹지역 리스트 (json) 
	@RequestMapping(value="/list", params="ac=ajax_list")
	public DataTableResultVO list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {//@RequestParam(value="search[value]", required=false) String search
		long cnt = service.getAreaGroupCount(dataTable.getSearchValue());
		List<AreaGroupListVVO> data = service.getAreaGroupList(dataTable, dataTable.getSearchValue());
		return dataTable.makeResult(cnt, data);		
	}
	@RequestMapping(value="/list", params="ac=excel")
	public ExcelPOIList<AreaGroupListVVO> list_excel(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {//@RequestParam(value="search[value]", required=false) String search
		long cnt = service.getAreaGroupCount(dataTable.getSearchValue());
		dataTable.setStart(0);
		dataTable.setLength((int)cnt);
		List<AreaGroupListVVO> data = service.getAreaGroupList(dataTable, dataTable.getSearchValue());
		ExcelPOIList<AreaGroupListVVO> list = new ExcelPOIList<AreaGroupListVVO>(); 
		list.addAll(data);
		return list;
	}
	@RequestMapping(value="/list", params="ac=print")
	public HtmlTableList<AreaGroupListVVO> list_print(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {//@RequestParam(value="search[value]", required=false) String search
		long cnt = service.getAreaGroupCount(dataTable.getSearchValue());
		dataTable.setStart(0);
		dataTable.setLength((int)cnt);
		List<AreaGroupListVVO> data = service.getAreaGroupList(dataTable, dataTable.getSearchValue());
		HtmlTableList<AreaGroupListVVO> list = new HtmlTableList<AreaGroupListVVO>(); 
		list.addAll(data);
		return list;
	}

	// 그룹지역 상세 (jsp)
	@RequestMapping(value="/detail", params="!ac")
	public String detail(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/group/detail";
	}

	// 그룹지역 상세 (json)
	@RequestMapping(value="/detail", params="ac=ajax_detail") 
	public AreaGroupJoinVO detail_info(@RequestParam(value="group_id")Integer group_id, HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		return service.getAreaGroup(group_id);
	}
	
	
	
	
	@RequestMapping(value="/delete", params="ac=ajax_delete") 
	public Code delete(@RequestParam(value="group_id")Integer group_id, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		return service.deleteAreaGroup(group_id);
	}
	
	@RequestMapping(value="/save", params="ac=ajax_uAreaGroup_duSubArea") 
	public Code update(@Valid @ModelAttribute("AreaGroup") AreaGroupJoinVO areaGroup, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return service.uAreaGroup_duSubArea(areaGroup);
	}
	
}
