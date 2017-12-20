package com.khh.project;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khh.DefaultController;
import com.khh.project.notice.service.NoticeService;
import com.khh.project.operator.service.OperatorService;
import com.khh.login.vo.LoginUserVO;
import com.khh.security.SecurityManager;
import com.omnicns.web.spring.security.SecurityUtil;

@Controller
public class CbisController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	SecurityManager sManager = SecurityManager.getInstance();
	
	@Resource(name="OperatorService")
	OperatorService operatorService;
	
	@Resource(name="NoticeService")
	NoticeService noticeService;
	
	//@RequestParam(value="type",required=false)String type,
	@RequestMapping(value="/", params="!ac")
	public String index(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		
		String go="/login";
		if(SecurityUtil.isLogin()){
			LoginUserVO user = sManager.getSecurityUser();
			if(user.isEnabled()){ //완전 허용된 사용자
				go = "/project";
			}else{ //카드 체크가 필요한 사용자
				go = "redirect:/authcheck/card";
			}
		}else{
			go="/login";
			model.put("notice", noticeService.getList(Restrictions.eq("noticed", "Y"), Order.desc("notice_id"), 0, 4));
		}
		return go;
	}   

	
}
