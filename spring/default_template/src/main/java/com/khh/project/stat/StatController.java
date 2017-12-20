package com.khh.project.stat;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.khh.project.stat.service.StatService;
import com.khh.project.stat.vo.StatVO;
import com.khh.project.vo.DataTableResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khh.DefaultController;
import com.khh.project.stat.vo.StatSearchVO;
import com.khh.security.SecurityManager;

@Controller
public class StatController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	SecurityManager sManager = SecurityManager.getInstance();
	
	@Resource(name="StatService")
	StatService service;

	// 송출 지역별 통계
	@RequestMapping(value="/stat/area", params="!ac")
	public String stat_area(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/stat/area";
	}
	
	// 송출 지역별 통계 List
	@RequestMapping(value="/stat/area/list", params="ac=ajax_list")
	public DataTableResultVO areaList(@Valid @ModelAttribute("StatSearchVO") StatSearchVO search , HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		List<StatVO> data = service.getAreaList(search);
		return search.makeResult(data);
	}
	
	// 송출 지역별 통계 Print
	@RequestMapping(value="/stat/area/print", params="!ac")
	public String areaPrint(@Valid @ModelAttribute("StatSearchVO") StatSearchVO search , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		search.setSearch_start(search.getSearch_start().replaceAll("-", "").substring(0, search.getDate_query()));
		search.setSearch_end(String.valueOf(Integer.parseInt(search.getSearch_end().replaceAll("-", "").substring(0, search.getDate_query())) + 1));
		List<StatVO> data = service.getAreaList(search);
		model.put("searchData", data);
		model.put("type", "area");
		return "/stat/statWindow";
	}

	// 재난 유형별 통계
	@RequestMapping(value="/stat/type", params="!ac")
	public String stat_type(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/stat/type";
	}
	
	// 재난 유형별 통계 List
	@RequestMapping(value="/stat/type/list", params="ac=ajax_list")
	public DataTableResultVO typeList(@Valid @ModelAttribute("StatSearchVO") StatSearchVO search , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		List<StatVO> data = service.getTypeList(search);
		return search.makeResult(data);
	}
	
	// 재난 유형별 통계 Print
	@RequestMapping(value="/stat/type/print", params="!ac")
	public String typePrint(@Valid @ModelAttribute("StatSearchVO") StatSearchVO search , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		search.setSearch_start(search.getSearch_start().replaceAll("-", "").substring(0, search.getDate_query()));
		search.setSearch_end(String.valueOf(Integer.parseInt(search.getSearch_end().replaceAll("-", "").substring(0, search.getDate_query())) + 1));
		List<StatVO> data = service.getTypeList(search);
		model.put("searchData", data);
		model.put("type", "type");
		return "/stat/statWindow";
	}
	
	// 종합 통계
	@RequestMapping("/stat/all")
	public String stat_all(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/stat/all";
	}
	
	// 종합 통계 List
	@RequestMapping(value="/stat/all/list", params="ac=ajax_list")
	public DataTableResultVO allList(@Valid @ModelAttribute("StatSearchVO") StatSearchVO search , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		List<StatVO> data = service.getAllList(search);
		return search.makeResult(data);
	}
	
	// 종합 통계 Print
	@RequestMapping(value="/stat/all/print", params="!ac")
	public String allPrint(@Valid @ModelAttribute("StatSearchVO") StatSearchVO search , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		search.setSearch_start(search.getSearch_start().replaceAll("-", "").substring(0, search.getDate_query()));
		search.setSearch_end(String.valueOf(Integer.parseInt(search.getSearch_end().replaceAll("-", "").substring(0, search.getDate_query())) + 1));
		List<StatVO> data = service.getAllList(search);
		model.put("searchData", data);
		model.put("type", "all");
		return "/stat/statWindow";
	}
//	@RequestMapping(value="/stat/all/excel", params="!ac")
//	public Writer<?> allExcel(@Valid @ModelAttribute("StatSearchVO") StatSearchVO search , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
//		search.setSearch_start(search.getSearch_start().replaceAll("-", "").substring(0, search.getDate_query()));
//		search.setSearch_end(String.valueOf(Integer.parseInt(search.getSearch_end().replaceAll("-", "").substring(0, search.getDate_query())) + 1));
//		List<StatVO> data = service.getAllList(search);
////		model.put("searchData", data);
////		model.put("type", "all");
//
//		return new Writer<OutputStream>().setExecutor((os)->{
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			HSSFFont my_font = workbook.createFont();
//			my_font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//			HSSFCellStyle headerStyle;
//			HSSFCellStyle style = workbook.createCellStyle();
//			style.setFont(my_font);
//			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//	        style.setBorderTop((short) 1); 
//	        style.setBorderBottom((short) 1);
//	        style.setBorderLeft((short) 1); 
//	        style.setBorderRight((short) 1);
//			headerStyle = style;
//			HSSFSheet sheet = workbook.createSheet();
//			
//			Map<String,Integer> left = new LinkedHashMap<String, Integer>(); 
//			Map<String,Integer> top = new LinkedHashMap<String, Integer>(); 
//			Set<String> h = new LinkedHashSet();
//			
////			Row row = sheet.createRow(0);
////			Cell cell = row.createCell(columnIdx);
//			
//			
//			
//			try {
//				workbook.write(os);
//				workbook.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		});
//	}
}
