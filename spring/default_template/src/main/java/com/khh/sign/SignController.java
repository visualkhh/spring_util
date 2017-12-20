package com.khh.sign;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khh.project.operator.service.OperatorService;
import com.khh.project.operator.vo.OperatorVO;
import com.khh.login.vo.LoginUserVO;
import com.khh.security.SecurityManager;
import com.khh.sign.service.SignService;
import com.omnicns.web.spring.security.SecurityUtil;

@Controller("SignController")
@RequestMapping("/sign")
public class SignController {
	@Autowired
	public SecurityManager  securityMng;
	@Resource(name="SignService")
	SignService signService;
	@Resource(name="OperatorService")
	OperatorService operatorService;
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/group/list";
	}
	//로그아웃
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		SecurityUtil.logout(request, response);
		HttpSession session = request.getSession();
		if(null==session){
			session = request.getSession(true);
		}
		session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new SessionAuthenticationException("정상 로그아웃 되었습니다."));
//		return "redirect:/?msg=logout";
		return "redirect:/";
	}
	//session 시간타임아웃
	@RequestMapping(value="/invalid")
	public String invalid(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		HttpSession session = request.getSession();
		if(null==session){
			session = request.getSession(true);
		}
		
		Exception ex = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		String msg = "";
		if(null!=ex){
			msg = ex.getMessage()+"  ";
		}
		msg += "세션 연결시간이 만료되어 자동 로그아웃 되었습니다.";
		session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new SessionAuthenticationException(msg));
		return "redirect:/";
//		return "redirect:/?msg=invalid";
	}
	//session 다른사용자 로그인으로 인한 session fail
	@RequestMapping(value="/expired")
	public String expired(@RequestParam(value="last_login_ip",required=false) String last_login_ip, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		if(null==last_login_ip){
			LoginUserVO user = securityMng.getSecurityUser();
			OperatorVO db_operator=operatorService.getOperator(Restrictions.eq("operator_id", user.getOperator_id()));
			last_login_ip = db_operator.getLast_login_ip();
		}
		SecurityUtil.logout(request, response);
		request.getSession(true).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new SessionAuthenticationException(last_login_ip+"에 의해 자동 로그아웃 되었습니다."));
//		return "redirect:/?msg=expired_"+last_login_ip;
		return "redirect:/";
	}
	@RequestMapping(value="/authentication")
	public String authentication(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		return "redirect:/?msg=authentication";
	}
	
	
}
