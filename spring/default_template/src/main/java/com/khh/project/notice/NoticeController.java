package com.khh.project.notice;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.khh.code.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.khh.DefaultController;
import com.khh.project.notice.service.NoticeService;
import com.khh.project.notice.vo.NoticeFileVO;
import com.khh.project.notice.vo.NoticeJoinVO;
import com.khh.project.notice.vo.NoticeVO;
import com.khh.project.vo.DataTableResultVO;
import com.khh.project.vo.DataTableVO;
import com.khh.config.ConfigManager;
import com.omnicns.java.string.StringUtil;
import com.khh.security.SecurityManager;

@Controller("NoticeController")
@RequestMapping("/notice")
public class NoticeController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	SecurityManager securityMng;
	@Autowired
	ConfigManager configMng;
	
	@Resource(name="NoticeService")
	NoticeService service;	

	// 공지사항  리스트 (jsp)
	@RequestMapping(value={"","/"}, params="!ac")
	public String index(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/notice/list";
	}   
	
	// 공지사항 리스트 (json)
	@RequestMapping(value="/list", params="ac=ajax_list")
	public DataTableResultVO list(@Valid @ModelAttribute("DataTable") DataTableVO dataTable , HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {//@RequestParam(value="search[value]", required=false) String search
		int totCnt = service.getCount(dataTable);
		List<NoticeJoinVO> data =  service.getList(dataTable);
		return dataTable.makeResult(totCnt,data);
	}   

	// 공지사항  상세 (jsp)
	@RequestMapping(value="/detail", params="!ac")
	public String detail(HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/notice/detail";
	}

	// 공지사항  상세 (json)
	@RequestMapping(value="/detail", params="ac=ajax_detail")
	public NoticeJoinVO detail_info(@RequestParam(value="notice_id")Integer notice_id, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return service.getNotice(notice_id);
	}
	
	// 공지사항 삭제 (action)
	@RequestMapping(value="/delete", params="ac=ajax_delete")
	public Code delete(@RequestParam(value="notice_id")Integer notice_id, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		return service.delete(notice_id);
	}
	
	@RequestMapping(value="/save", params={"ac=ajax_update","file=false"})
	public Code update(@Valid @ModelAttribute("Notice") NoticeVO notice, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		service.updateNotice(notice);
		return Code.SUCCESS; 
	}
	@RequestMapping(value="/save", params={"ac=ajax_update","file=true"})
	public Code update_file(@RequestParam(value="file", required=false) MultipartFile file, @Valid @ModelAttribute("Notice") NoticeVO notice, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		if(!file.isEmpty() && !StringUtil.isMatches(file.getOriginalFilename(),configMng.getParam("notice_data_name_regex"))){
			throw new Exception(Code.VALIDATE_FILE_EXTENSION_FAIL.msg());
		}
		if(null!=file && !file.isEmpty() && file.getSize()>Integer.parseInt(configMng.getParam("notice_max_upload_size"))){
			throw new Exception(Code.VALIDATE_FILE_SIZE_FAIL.msg());
		}
		service.updateNotice(notice);
		service.deleteInsertFile(new NoticeFileVO(notice.getNotice_id(),file) );
		return Code.SUCCESS; 
	}
	@RequestMapping(value="/save", params={"ac=ajax_insert","file=false"})
	public Code insert(@Valid @ModelAttribute("Notice")NoticeVO notice, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		service.insertNotice(notice);
		return Code.SUCCESS; 
	}
	@RequestMapping(value="/save", params={"ac=ajax_insert","file=true"})
	public Code insert_file(@RequestParam(value="file", required=false) MultipartFile file, @Valid @ModelAttribute("Notice")NoticeVO notice, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		if(null!=file && !file.isEmpty() && !StringUtil.isMatches(file.getOriginalFilename(),configMng.getParam("notice_data_name_regex"))){
			throw new Exception(Code.VALIDATE_FILE_EXTENSION_FAIL.msg());
		}
		if(null!=file && !file.isEmpty() && file.getSize()>Integer.parseInt(configMng.getParam("notice_max_upload_size"))){
			throw new Exception(Code.VALIDATE_FILE_SIZE_FAIL.msg());
		}
		service.insertNotice(notice);
		service.deleteInsertFile(new NoticeFileVO(notice.getNotice_id(),file) );
		return Code.SUCCESS; 
	}
	@RequestMapping(value="/detail", params="ac=file")
	public FileSystemResource file(@Valid @ModelAttribute("NoticeFile")  NoticeFileVO notice, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		FileSystemResource fr = service.fileDonwload(notice);
		return fr;  
	}
	@RequestMapping(value="/detail", params="ac=file_delete")
	public Code file_delete(@RequestParam(value="notice_id")Integer notice_id, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		return service.deleteFile(notice_id);
	}
}
