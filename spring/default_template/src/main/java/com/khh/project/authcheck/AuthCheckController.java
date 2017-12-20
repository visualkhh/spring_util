package com.khh.project.authcheck;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khh.DefaultController;
import com.khh.project.operator.service.OperatorService;
import com.khh.login.vo.LoginUserVO;
import com.khh.security.SecurityManager;

@Controller("AuthCheckController")
@RequestMapping("/authcheck")
public class AuthCheckController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	SecurityManager securityMng = SecurityManager.getInstance();
	@Autowired
	public SessionFactory  sessionFactory;
	
	@Resource(name="OperatorService")
	OperatorService operatorService;
	
//	@RequestMapping("/tpl/**")
//	public String layout(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
//		//return "auth/layout/"+fileName;
//		return request.getRequestURI();
//	}   


	// 프로필 수정 (jsp)
	@RequestMapping(value={"/card"}, params="!code")
	public String profile(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/authcheck/card";
	}
	@RequestMapping(value={"/card"}, params="code")
	public String codeCheck(@RequestParam(value="code",required=false)Integer code, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		LoginUserVO user = securityMng.getSecurityUser();
		String go = "/authcheck/card";
		if(user.getCheckCard().getCheckCode().getCode().equals(code)){
			user.setEnabled(true);
			go = "redirect:/";
		}else{
			user.setEnabled(false);
			model.put("message", code==null?"":"CODE 번호가 일치 하지 않습니다.");
		}
		return go;
	}
	
	
	
}
