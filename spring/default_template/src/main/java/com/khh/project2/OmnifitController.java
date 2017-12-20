package com.khh.project2;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khh.DefaultController;
import com.khh.project2.service.OmnifitService;
import com.khh.project2.vo.BoardNormalVO;
import com.khh.project2.vo.BoardUserIDVO;
import com.khh.project2.vo.BoardVO;
import com.khh.project2.vo.BoardWriteVO;
import com.khh.security.SecurityManager;

@Controller
public class OmnifitController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource(name="OmnifitService")
	OmnifitService omnifitService;	
	
	SecurityManager sManager = SecurityManager.getInstance();
//	@Resource(name="LoginService")
//	LoginService loginService;	
	
	public OmnifitController() {
		log.debug("OmnifitController new");
	}
	//forward
//	@RequestMapping("/cms")
//	public String index(HttpServletRequest request, ModelMap model) throws Exception {
////		LoginUserVO a = loginService.getUser("asdasd");
//		Authentication a = SecurityContextHolder.getContext().getAuthentication();
//		log.debug(a.toString()); 
//		return request.getRequestURI();
//	}

	@RequestMapping(value="/board/read" ,params="!ax")
	public String board(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/board/read";
	}
	@RequestMapping(value="/board/read",params="ax=content_list")
	public List<BoardVO> boardContentAjax(@RequestParam(value="like",required = false) String like, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return omnifitService.getContentBoards(like);
	}
	@RequestMapping(value="/board/read",params="ax=write_list")
	public List<BoardWriteVO> boardWriteAjax(@RequestParam(value="like",required = false) String like, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return omnifitService.getWriteBoards(like);
	}
	@RequestMapping(value="/board/read",params="ax=id_list")
	public List<BoardUserIDVO> boardIDAjax(@RequestParam(value="like",required = false) String like, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return omnifitService.getIDBoards(like);
	}
	@RequestMapping(value="/board/read",params="ax=normal_list")
	public List<BoardNormalVO> boardNormalAjax(@RequestParam(value="like",required = false) String like, HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		return omnifitService.getNormalBoards(like);
	}
	@RequestMapping("/board/write")
	public String board_writer(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/board/write";
	}
	@RequestMapping(value="/board/write",params="ax=write_entity")
	public Integer board_writerAjax(@Valid @ModelAttribute("CounselPulseSearchVO") BoardVO bvo,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		bvo.setUser_id(sManager.getSecurityUser().getLogin_id());
		Integer i = omnifitService.setEntityBoard(bvo);
//		JsonMap j = new JsonMap();j.put("result", i);
		//bvo.get
		return i;
	}
	@RequestMapping(value="/board/write",params="ax=write_named")
	public Integer board_writerNamedAjax(@Valid @ModelAttribute("CounselPulseSearchVO") BoardVO bvo,HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		bvo.setUser_id(sManager.getSecurityUser().getLogin_id());
		Integer i = omnifitService.setNamedBoard(bvo);
		return i;
	}
	@RequestMapping("/board/admin")
	public String board_admin(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/board/admin";
	}


}
