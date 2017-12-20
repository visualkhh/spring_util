package com.khh.project.auth;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.khh.code.Code;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khh.DefaultController;
import com.khh.project.auth.vo.ContentVO;
import com.khh.project.msg.vo.MsgClassVO;
import com.khh.project.notice.service.NoticeService;
import com.khh.project.notice.vo.NoticeJoinVO;
import com.khh.project.operator.service.OperatorService;
import com.khh.project.operator.vo.OperatorVO;
import com.omnicns.java.date.DateUtil;
import com.omnicns.java.string.StringUtil;
import com.khh.login.vo.LoginUserVO;
import com.khh.security.SecurityManager;
import com.omnicns.web.spring.exception.JsonErrorsException;

@Controller("AuthController")
@RequestMapping("/auth")
public class AuthController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	SecurityManager sManager = SecurityManager.getInstance();
	@Autowired
	SecurityManager securityMng;
	@Autowired
	public SessionFactory  sessionFactory;
	
	@Resource(name="OperatorService")
	OperatorService operatorService;
	
	@Resource(name="NoticeService")
	NoticeService noticeService;
	
	@RequestMapping("/tpl/**")
	public String layout(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		//return "auth/layout/"+fileName;
		return request.getRequestURI();
	}   


	// 프로필 수정 (jsp)
	@RequestMapping(value={"/profile"}, params="!ac")
	public String profile(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/auth/profile";
	}
	

	// 프로필 수정 처리
	@RequestMapping(value="/profile", params="ac=ajax_update")
	public Code profile_save(@Valid @ModelAttribute("Operator") OperatorVO operator, @RequestParam(value="current_login_pw")String current_login_pw, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		current_login_pw = securityMng.encrypt(securityMng.getSecurityUser().getIndv_key(), current_login_pw);
		if(null==current_login_pw || current_login_pw.length()==0 || !current_login_pw.equals(securityMng.getSecurityUser().getLogin_pw())){
			throw new JsonErrorsException("현재 비밀번호가 틀렸습니다.");
		}
		
		if(null!=operator.getLogin_pw() && operator.getLogin_pw().length()==0){
			operator.setLogin_pw(null);
		}else{
			String saltKey = securityMng.createSaltKey();
			securityMng.getSecurityUser().setIndv_key(saltKey);
			operator.setPwd_update("N");
			operator.setIndv_key(saltKey);
			operator.setLogin_pw(securityMng.encrypt(saltKey, operator.getLogin_pw()));
			operator.setLast_pwd_update(DateUtil.getDate("yyyyMMddHHmmss"));
		}
		
		operator.setOperator_id(securityMng.getSecurityUser().getOperator_id());
		return operatorService.updateOperator(operator);
	}
	
	///////////////////////////test group 
	
	@RequestMapping("/test_boot")
	public String test_boot(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "/auth/test/bootTest";
	}
	
	
	@RequestMapping("/test")
	public List<MsgClassVO> test(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		
		/*
			 Type[] tipos = {IntegerType.INSTANCE, IntegerType.INSTANCE};
			Integer[] values = {1, 2};
			criteria.add(Restrictions.sqlRestriction("SELECT ({alias}.id * {alias}.code * "+ ? + " * " + ? + ") 
			as number from city HAVING number < 10", values, tipos ));
		 */
//		Projections.sqlProjection(sql, columnAliases, types);
		
//		Projection p = Projections.sqlProjection("substr({alias}.class2,1,2) as CLASS2", new String [] {"CLASS2"}, new Type [] {StringType.INSTANCE} ); 
//		Projection pg = Projections.sqlGroupProjection("sum({alias}.class2,1,2) as rowCount","this_.discriminatorColumn having rowCount>0",new String[]{"rowCount"},Helper.HIBERNATE_INTEGER_INSTANCE)
//		Projection p = Projections.sqlProjection("substr({alias}.class3,1,2) as class3_short", new String[]{"value"}, new Type[]{new LongType()}))
		//http://stackoverflow.com/questions/29482880/how-to-pass-value-to-sqlrestriction-of-hibernate
		//https://docs.jboss.org/hibernate/orm/3.3/reference/ko-KR/html/querycriteria.html
		
		
		//http://stackoverflow.com/questions/7773072/using-database-functions-to-transform-columns-in-hibernate-criteria
		ProjectionList plist = Projections.projectionList()
		.add(Projections.sqlProjection("substr({alias}.class2,1,2) as class2", new String [] {"class2"}, new Type [] {StringType.INSTANCE} ))
	    .add(Projections.groupProperty("{alias}.class2"));
		
		Criterion where = Restrictions.sqlRestriction("substr({alias}.class1, 1, 2)=?", "자연", StringType.INSTANCE);
		Session s = sessionFactory.openSession();
		Criteria crit = s.createCriteria(MsgClassVO.class).add(where);
		//crit.createAlias("products","p");
		//crit.setProjection(Projections.distinct(Projections.property("class1")));
		//crit.setProjection(Projections.sqlGroupProjection("substr({alias}.class2,1,2) as class2", "substr({alias}.class2,1,2) ", new String[] { "class2" }, new Type[] { StandardBasicTypes.STRING }));
		//crit.createAlias("class1",)
		
		crit.setProjection(plist);
		List<MsgClassVO> list = crit.list();
		s.close();
		return list;
	}   
	
	// 공지사항 리스트 (json)
	@RequestMapping(value="/notice", params="ac=ajax_list")
	public List<NoticeJoinVO> list(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {//@RequestParam(value="search[value]", required=false) String search
		return noticeService.getList(Restrictions.eq("noticed", "Y"), Order.desc("notice_id"), 0, 4);
	}  
	@RequestMapping(value="/auth", params="ac=ajax_auth")
	public LoginUserVO auth(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {//@RequestParam(value="search[value]", required=false) String search
		return securityMng.getSecurityUser();
	}
	
	
	//util
	@RequestMapping(value="/content", params="ac=util_content")
	public ResponseEntity<String> util_content(@ModelAttribute("content") ContentVO content, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		if(null!=content.getHeader()){
			content.getHeader().forEach((key,value)->{
				headers.add(key, value);
			});
		}
		ResponseEntity<String> responseentity = new ResponseEntity<String>(StringUtil.tagMetaCharToEscapeChar(null==content.getBody()?"":content.getBody()), headers, HttpStatus.valueOf(content.getStatus()));
		return responseentity;
	}
	
}
