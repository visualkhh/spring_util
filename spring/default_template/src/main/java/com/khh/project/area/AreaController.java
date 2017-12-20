package com.khh.project.area;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.khh.code.Code;
import com.khh.project.area.service.AreaService;
import com.khh.project.area.vo.AreaJoinVO;
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
import com.khh.project.area.vo.AreaVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.collection.html.HtmlTableList;
import com.omnicns.java.collection.list.poi.ExcelPOIList;
import com.khh.security.SecurityManager;

@Controller("AreaController")
@RequestMapping("/area")
public class AreaController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	SecurityManager securityMng;
	@Resource(name="AreaService")
	AreaService service;
	
	// 지역정보 리스트 (jsp) 
	@RequestMapping(value={"","/"}, params="!ac")
	public String index(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/area/list";
	}
	
	// 지역정보 리스트 (json)
	@RequestMapping(value="/list",params="ac=ajax_list")
	public DataTableResultVO list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		long cnt = service.getAreaCount(dataTable);
		List<AreaVO> data = service.getAreaList(dataTable);
		return dataTable.makeResult(cnt, data);
	}
	@RequestMapping(value="/list",params="ac=excel")
	public ExcelPOIList<AreaVO> list_excel(@Valid @ModelAttribute("DataTable") DataTableVO dataTable ,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		long cnt = service.getAreaCount(dataTable);
		dataTable.setStart(0);
		dataTable.setLength((int)cnt);
		List<AreaVO> data = service.getAreaList(dataTable);
		ExcelPOIList<AreaVO> list = new ExcelPOIList<AreaVO>(); 
		list.addAll(data);
		return list;
	}
	@RequestMapping(value="/list",params="ac=print")
	public HtmlTableList<AreaVO> list_print(@Valid @ModelAttribute("DataTable") DataTableVO dataTable ,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		long cnt = service.getAreaCount(dataTable);
		dataTable.setStart(0);
		dataTable.setLength((int)cnt);
		List<AreaVO> data = service.getAreaList(dataTable);
		HtmlTableList<AreaVO> list = new HtmlTableList<AreaVO>(); 
		list.addAll(data);
		return list;
	}

	//  지역정보 상세 정보 (jsp)
	@RequestMapping(value="/detail", params="!ac")
	public String detail(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/area/detail";
	}
	
	@RequestMapping(value="/detail", params="ac=ajax_detail")
	public AreaJoinVO detail_info(@RequestParam(value="area_id") Integer area_id, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		return service.getArea(area_id);
	}
	
	@RequestMapping(value="/save", params="ac=u")
	public Code update(@Valid @ModelAttribute("Area") AreaVO area, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		return service.updateArea(area);
	}
	@RequestMapping(value="/save", params="ac=i")
	public Code insert(@Valid @ModelAttribute("Area") AreaVO area, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return service.insertArea(area);  
	}
	

}
