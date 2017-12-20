package com.khh.project.log;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.khh.DefaultController;
import com.khh.code.Code;
import com.khh.project.log.service.LogService;
import com.khh.project.log.vo.LogCompanySVO;
import com.khh.project.log.vo.LogCompanyVO;
import com.khh.project.vo.DataTableResultVO;
import com.khh.project.vo.DataTableVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.omnicns.java.collection.list.poi.ExcelPOIList;
import com.omnicns.java.date.DateUtil;
import com.khh.security.SecurityManager;

@Controller("LogController")
@RequestMapping("/log")
public class LogController extends DefaultController {
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	SecurityManager sManager = SecurityManager.getInstance();
	@Resource(name="LogService")
	LogService service;

	// 이통사로그  리스트 (jsp)
	@RequestMapping(value={"","/"}, params="!ac")
	public String index(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/log/company";
	}   
	
	// 이통사로그 리스트 (json)
	@RequestMapping(value="/company", params="ac=ajax_list")
	public DataTableResultVO list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {//@RequestParam(value="search[value]", required=false) String search
		int totCnt = service.getLogCount(dataTable);
		List<LogCompanyVO> data =  service.getLogList(dataTable);
		return dataTable.makeResult(totCnt,data);
	}   
	@RequestMapping(value="/company", params="ac=excel")
	public ExcelPOIList<LogCompanyVO> excel_list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {//@RequestParam(value="search[value]", required=false) String search
		int totCnt = service.getLogCount(dataTable);
		dataTable.setStart(0);
		dataTable.setLength(totCnt);
		
		
		String start_dt = DateUtil.modifyDate(new Date(),Calendar.DATE,-7,"yyyyMMdd");
		String end_dt 	= DateUtil.dateFormat("yyyyMMdd",new Date());
		String company	= Code.CODE_COMPANY_ALL.msg();
		LogCompanySVO query=dataTable.getSearchJson(LogCompanySVO.class);
		if(null!=query && null!=query.getStart_dt()){
			start_dt = query.getStart_dt();
		}
		if(null!=query && null!=query.getEnd_dt()){
			end_dt = query.getEnd_dt();
		}
		if(null!=query && null!=query.getCompany_id()){
			company = Code.CODES_COMPANY.child(query.getCompany_id()).msg();
		}
		String filename = "sendMsgByCompany_" + start_dt + "_" + end_dt + "_" + company;

		
		
		
		
		ExcelPOIList<LogCompanyVO> data = new ExcelPOIList<LogCompanyVO>(filename); 
		List<LogCompanyVO> dataList = service.getLogList(dataTable);
		dataList.replaceAll(item->{
			item.setContents_all(item.getContentsFirst());
			return item;
		});
		
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
			cell.setCellValue("이통사 : ");
			cell = row.createCell(1);
			cell.setCellValue(meta[3]);
			
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
   
}
