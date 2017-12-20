package com.khh.project.anon;

import com.khh.code.Code;
import com.khh.config.ConfigManager;
import com.khh.project.msg.service.MsgService;
import com.khh.project.msg.vo.*;
import com.khh.project.notice.service.NoticeService;
import com.khh.project.notice.vo.NoticeJoinVO;
import com.khh.project.operator.vo.OpRightJoinVO;
import com.khh.project.operator.vo.OperatorReqVO;
import com.khh.project.operator.vo.OperatorRightJoinVO;
import com.khh.project.operator.vo.OperatorVO;
import com.khh.security.spring.CustomAuthenticationProvider;
import com.khh.DefaultController;
import com.khh.project.notice.vo.NoticeFileVO;
import com.khh.project.operator.service.OperatorService;
import com.omnicns.java.db.hibernate.Hibernater;
import com.omnicns.java.file.FileUtil;
import com.omnicns.java.io.stream.Writer;
import com.omnicns.java.random.RandomUtil;
import com.omnicns.java.string.StringUtil;
import com.khh.security.SecurityManager;
import com.omnicns.web.request.RequestUtil;
import com.omnicns.web.spring.exception.BodyErrorsException;
import com.omnicns.web.spring.mail.Mailer;
import com.omnicns.web.spring.security.SecurityUtil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.nio.cs.ext.EUC_KR;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller("AnonController")
@RequestMapping("/anon")
public class AnonController extends DefaultController{
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	SecurityManager securityMng;
	@Autowired
	ConfigManager configMng;
	@Autowired
	public Hibernater hibernate;
	
	@Resource(name="NoticeService")
	NoticeService noticeService;
	@Resource(name="OperatorService")
	OperatorService operatorService;
	@Resource(name="MsgService")
	MsgService msgService;
	@Resource(name="customAuthenticationProvider")
	CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired 
	Mailer mailSender;
	
	@RequestMapping(value={"","/"}, params="!ac")
	public String index(HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return "";
	}   

	
	
	
	
	
	
	///////////////////////////
	@RequestMapping(value="/operator_req", params="ac=ajax_check")
	public Code idCheck(@RequestParam(value="login_id") String login_id, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		long cnt = operatorService.getOperatorOrReqCount(Restrictions.eq("login_id",login_id));
		Boolean checked = (cnt==0?true:false);
		return Code.SUCCESS.set(checked);
	}
	
	
	@RequestMapping(value="/operator_req", params="ac=ajax_save")
	public Code idRequest(@Valid @ModelAttribute("OperatorReqVO") OperatorReqVO operatorReq, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		if(operatorService.getOperatorOrReqCount(Restrictions.eq("login_id", operatorReq.getLogin_id()))<1){
			String indv_key = securityMng.createSaltKey();
			operatorReq.setIndv_key(indv_key);
			operatorReq.setLogin_pw(securityMng.encrypt(indv_key, operatorReq.getLogin_pw()));
			operatorService.saveOperatorReq(operatorReq);
			return Code.SUCCESS;
		}
		return Code.FAIL;
	}
	
	@RequestMapping(value="/notice", params="ac=ajax_detail")
	public NoticeJoinVO notice_detail(@Valid @ModelAttribute("OperatorReqVO") OperatorReqVO operatorReq, HttpServletRequest request, HttpServletResponse reponse, ModelMap model) throws Exception {
		return noticeService.getList(Restrictions.eq("noticed", "Y"), Order.desc("notice_id")).get(0);
	}
	@RequestMapping(value="/notice", params="ac=file")
	public FileSystemResource file(@Valid @ModelAttribute("NoticeFile") NoticeFileVO notice, HttpServletRequest request,HttpServletResponse reponse, ModelMap model) throws Exception {
		return noticeService.fileDonwload(notice);
	}
	
	@RequestMapping(value="/pwdFind", params="ac=ajax_pwdFind")
	public Code find(@RequestParam(value="login_id")String login_id, @RequestParam(value="name")String name, @RequestParam(value="email")String email, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		List<Criterion> list = new ArrayList<Criterion>();
		list.add(Restrictions.eq("login_id", login_id));
		list.add(Restrictions.eq("name", name));
		list.add(Restrictions.eq("email", email));
		
		OperatorVO operator = operatorService.getOperator(list);
		
		if (null != operator) {
			String title = "[]변경된 패스워드.";
//			char[] chars = {'!', '@', '#', '$', '%', '^', '*', '(', ')', '-', '_', '+', '\\', '/'};
			char[] chars = {'!','$'};
			String randomPwd = RandomUtil.getRandomString(3) + RandomUtil.getRandomString(3,chars) + RandomUtil.getRandom(100, 999, 1)[0];
			List<String> letters = Arrays.asList(randomPwd.split(""));
			Collections.shuffle(letters);
			
			// Update Info
			operator.setIndv_key(securityMng.createSaltKey());
			operator.setLogin_pw(securityMng.encrypt(operator.getIndv_key(), String.join("", letters)));
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			operator.setLast_pwd_update(format.format(new Date()));
			operator.setPwd_update("Y");
			operator.setLocked("N");
			operator.setLogin_fail(0);
			
			operatorService.updateOperatorPwd(operator);
			Map<String,String> injectionParam = new HashMap<String,String>();
			injectionParam.putAll(mailSender.getParam());
			injectionParam.put("pwd", String.join("", letters));
			String tmp = FileUtil.readeFileToString(RequestUtil.getRealPath(request, mailSender.getParam("template_path")));
			String content = StringUtil.inJection(tmp, injectionParam);
			String sendEmail = operator.getEmail();
			mailSender.send(sendEmail, title, content, new File[]{});
			return Code.SUCCESS;
		} else {
			return Code.FAIL;
		}
	}
	
	
	
	
	///////////외부연동
	
	/*
		1.	 연동
		-	예약 리스트 XML ( 첨부파일 alert.tpl 참고 )
		-	특정 아이피(비로그인) or 로그인 인증이 된 사람만 접속 가능
		$query = "select * from vw_msg_wait where status = ? order by wait_date, wait_time";
		$stm = $db->prepare($query);
		$res = $db->execute($stm, MSG_STATUS_WAIT);      
		* Select * from vw_msg_wait where status = 'WAIT' order by wait_date, wait_time
		
		<list>
			<count>{* $count *}</count>
			<detail>
				{* foreach name=list key=key item=item from=$list *}
					<message>
						<date>{* $item.wait_date *}</date>
						<time>{* $item.wait_time *}</time>
						<grade>{* $item.grade_name *}</grade>
						<class>{* $item.class_name *}</class>
						<position>{* $item.create_position *}</position>
						<writer>{* $item.create_name *}</writer>
					</message>
				{* /foreach *}
			</detail>
		</list> 
	 */
	@RequestMapping(value="//login")
	public String login(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		String _ip_regex = configMng.getParam("_ip_regex");
		String user_ip = RequestUtil.getRemoteAddr(request);
		if(!StringUtil.isMatches(user_ip, _ip_regex)){
			throw new Exception(Code.AUTH_FAIL.msg());
		}
		
		String url = request.getParameter("url");
		url = configMng.getParam("_login_success_"+url+"_url");
		if(null==url){
			url = configMng.getParam("_login_success_default_url");
		}
		//자동로그인
		String login_id = configMng.getParam("_login_id");
		String login_pwd = configMng.getParam("_login_pwd");
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login_id, login_pwd);
		WebAuthenticationDetails details = new WebAuthenticationDetails(request); 
		authentication.setDetails(details);
		SecurityUtil.setAuthentication(customAuthenticationProvider.authenticate(authentication));
		return "redirect:"+url;
	}
	@RequestMapping(value="/")
//	@PreAuthorize("hasIpAddress('192.168.11.200/204')")
	public Writer<List<MsgWaitVVO>> (HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		String _ip_regex = configMng.getParam("_ip_regex");
		String user_ip = RequestUtil.getRemoteAddr(request);
		
		if(!SecurityUtil.isLogin() && !StringUtil.isMatches(user_ip, _ip_regex)){
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE+";charset=UTF-8");
			BodyErrorsException error = new BodyErrorsException((r,p)->{return Code.AUTH_FAIL.msg();});
			error.setHeaders(header);
			throw error;
		}
		
		List<MsgWaitVVO> list = msgService.getMsgWaitList(Restrictions.eq("status", Code.CODE_MSG_STATUS_WAIT.cd()));
		return new Writer<List<MsgWaitVVO>>().putHeader("Content-Type", MediaType.TEXT_XML_VALUE).setExecutor(os->{
			StringBuffer str = new StringBuffer();
			str.append("<list>");
			str.append("<count>"+list.size()+"</count>");
			str.append(" <detail>");
			list.forEach(at->{
				str.append("  <message>");
				str.append("   <date>"+at.getWait_date()+"</date>");
				str.append("   <time>"+at.getWait_time()+"</time>");
				str.append("   <grade>"+at.getGrade_name()+"</grade>");
				str.append("   <class>"+at.getClass_name()+"</class>");
				str.append("   <position>"+at.getCreate_position()+"</position>");
				str.append("   <writer>"+at.getCreate_name()+"</writer>");
				str.append("  </message>");
			});
			str.append(" </detail>");
			str.append("</list>");
			try {os.write(str.toString().getBytes());} catch (IOException e) {log.error("writer Exception",e);throw new RuntimeException(e);}
		});
	}
	
	//dmb 예약리스트  xml
	@RequestMapping(value="/dmb/wait")
	public Writer<?> dmb_wait(@Valid @ModelAttribute("MsgDMBWaitSubmitVO") MsgDMBWaitSubmitVO msgDMBWaitSubmit, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String _ip_regex = configMng.getParam("dmb_ip_regex");
		String user_ip = RequestUtil.getRemoteAddr(request);
		if(!SecurityUtil.isLogin() && !StringUtil.isMatches(user_ip, _ip_regex)){
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE+";charset=UTF-8");
			BodyErrorsException error = new BodyErrorsException((r,p)->{return Code.AUTH_FAIL.msg();});
			error.setHeaders(header);
			throw error;
		}

		List<Criterion> where = new ArrayList<Criterion>();
		where.add(Restrictions.ge("wait_date", msgDMBWaitSubmit.getStart_dt()));
		where.add(Restrictions.le("wait_date", msgDMBWaitSubmit.getEnd_dt()));
		if(null!=msgDMBWaitSubmit.getGrade_id() && msgDMBWaitSubmit.getGrade_id().length()>0){
			where.add(Restrictions.le("grade_id", msgDMBWaitSubmit.getGrade_id()));
		}
		if(null!=msgDMBWaitSubmit.getClass_id() && msgDMBWaitSubmit.getClass_id().length()>0){
			where.add(Restrictions.le("class_id", msgDMBWaitSubmit.getClass_id()));
		}
		if(null!=msgDMBWaitSubmit.getStatus() && msgDMBWaitSubmit.getStatus().length()>0){
			where.add(Restrictions.le("status", msgDMBWaitSubmit.getStatus()));
		}
		
		if(null!=msgDMBWaitSubmit.getSearch_mode() && "contents".equals(msgDMBWaitSubmit.getSearch_mode())){
			Criterion all =  Restrictions.eq("contents_all", msgDMBWaitSubmit.getSearch_key());
			Criterion skt =  Restrictions.eq("contents_skt", msgDMBWaitSubmit.getSearch_key());
			Criterion ktf =  Restrictions.eq("contents_ktf", msgDMBWaitSubmit.getSearch_key());
			Criterion lgt =  Restrictions.eq("contents_lgt", msgDMBWaitSubmit.getSearch_key());
			where.add(Restrictions.and(Restrictions.or(all, skt, ktf, lgt)));
		}else if(null!=msgDMBWaitSubmit.getSearch_mode() && msgDMBWaitSubmit.getSearch_mode().length()>0){
			where.add(Restrictions.eq(msgDMBWaitSubmit.getSearch_mode(), msgDMBWaitSubmit.getSearch_key()));
		}
		
		///////order by
		List<Order> orders = new ArrayList<Order>();
		if(null!=msgDMBWaitSubmit.getSort_key() && msgDMBWaitSubmit.getSort_key().length()>0){
			orders.add(Order.desc("wait_date"));
		}else if(null!=msgDMBWaitSubmit.getSort_key() && "wait_date".equals(msgDMBWaitSubmit.getSort_key())){
			if(null!=msgDMBWaitSubmit.getSort_mode() && "DESC".equals(msgDMBWaitSubmit.getSort_mode().toUpperCase().trim())){
				orders.add(Order.desc("wait_time"));
			}else if(null!=msgDMBWaitSubmit.getSort_mode() && "ASC".equals(msgDMBWaitSubmit.getSort_mode().toUpperCase().trim())){
				orders.add(Order.asc("wait_time"));
			}
		}
		
		
		
		//total 구하고요 
		long count = msgService.getMsgWaitListCount(where);
		List<MsgWaitVVO> list = msgService.getMsgWaitList(where, orders, msgDMBWaitSubmit.getStart(), msgDMBWaitSubmit.getLength());
		
		return new Writer<>().putHeader("Content-Type", MediaType.TEXT_XML_VALUE).setExecutor(os->{
			StringBuffer str = new StringBuffer();
			str.append("<list>");
			str.append("<count>"+count+"</count>");
			str.append(" <detail>");
			list.forEach(at->{
				str.append("  <message>");
				str.append("   <wait_id>"+at.getWait_id()+"</wait_id>");
				str.append("   <created>"+at.getCreate_date()+"</created>");
				str.append("   <waited>"+at.getWait_date()+"</waited>");
				str.append("   <company>"+at.getCompanyFullName()+"</company>");
				str.append("   <content>"+at.getContentsFull()+"</content>");
				str.append("   <grade_name>"+at.getGrade_name()+"</grade_name>");
				str.append("   <class_name>"+at.getClass_name()+"</class_name>");
				str.append("   <create_name>"+at.getCreate_name()+"</create_name>");
				str.append("   <status>"+at.getStatus()+"</status>");
				str.append("  </message>");
			});
			str.append(" </detail>");
			str.append("</list>");
			try {os.write(str.toString().getBytes());} catch  (IOException e) {log.error("writer Exception",e);throw new RuntimeException(e);}
		});
	}
	//dmb 전송결과리스트  xml
	@RequestMapping(value="/dmb/submit")
	public Writer<?> dmb_sibmit(@Valid @ModelAttribute("MsgDMBWaitSubmitVO") MsgDMBWaitSubmitVO msgDMBWaitSubmit, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {
		String _ip_regex = configMng.getParam("dmb_ip_regex");
		String user_ip = RequestUtil.getRemoteAddr(request);
		if(!SecurityUtil.isLogin() && !StringUtil.isMatches(user_ip, _ip_regex)){
			throw new Exception(Code.AUTH_FAIL.msg());
		}
		

		List<Criterion> where = new ArrayList<Criterion>();
		where.add(Restrictions.ge("submit_date", msgDMBWaitSubmit.getStart_dt()));
		where.add(Restrictions.le("submit_date", msgDMBWaitSubmit.getEnd_dt()));
		if(null!=msgDMBWaitSubmit.getGrade_id() && msgDMBWaitSubmit.getGrade_id().length()>0){
			where.add(Restrictions.le("grade_id", msgDMBWaitSubmit.getGrade_id()));
		}
		if(null!=msgDMBWaitSubmit.getClass_id() && msgDMBWaitSubmit.getClass_id().length()>0){
			where.add(Restrictions.le("class_id", msgDMBWaitSubmit.getClass_id()));
		}
		if(null!=msgDMBWaitSubmit.getStatus() && msgDMBWaitSubmit.getStatus().length()>0){
			where.add(Restrictions.le("status", msgDMBWaitSubmit.getStatus()));
		}
		
		if(null!=msgDMBWaitSubmit.getSearch_mode() && "contents".equals(msgDMBWaitSubmit.getSearch_mode())){
			Criterion all =  Restrictions.eq("contents_all", msgDMBWaitSubmit.getSearch_key());
			Criterion skt =  Restrictions.eq("contents_skt", msgDMBWaitSubmit.getSearch_key());
			Criterion ktf =  Restrictions.eq("contents_ktf", msgDMBWaitSubmit.getSearch_key());
			Criterion lgt =  Restrictions.eq("contents_lgt", msgDMBWaitSubmit.getSearch_key());
			where.add(Restrictions.and(Restrictions.or(all, skt, ktf, lgt)));
		}else if(null!=msgDMBWaitSubmit.getSearch_mode()&&msgDMBWaitSubmit.getSearch_mode().length()>0){
			where.add(Restrictions.eq(msgDMBWaitSubmit.getSearch_mode(), msgDMBWaitSubmit.getSearch_key()));
		}
		
		///////order by
		List<Order> orders = new ArrayList<Order>();
		if(null!=msgDMBWaitSubmit.getSort_key() && msgDMBWaitSubmit.getSort_key().length()>0){
			orders.add(Order.desc("submit_date"));
		}else if(null!=msgDMBWaitSubmit.getSort_key() && "submit_date".equals(msgDMBWaitSubmit.getSort_key())){
			if(null!=msgDMBWaitSubmit.getSort_mode() && "DESC".equals(msgDMBWaitSubmit.getSort_mode().toUpperCase().trim())){
				orders.add(Order.desc("submit_time"));
			}else if(null!=msgDMBWaitSubmit.getSort_mode() && "ASC".equals(msgDMBWaitSubmit.getSort_mode().toUpperCase().trim())){
				orders.add(Order.asc("submit_time"));
			}
		}
		
		
		
		//total 구하고요 
		long count = msgService.getMsgSubmitListCount(where);
		List<MsgSubmitVVO> list = msgService.getMsgSubmitList(where, orders, msgDMBWaitSubmit.getStart(), msgDMBWaitSubmit.getLength());
		
		return new Writer<>().putHeader("Content-Type", MediaType.TEXT_XML_VALUE).setExecutor(os->{
			StringBuffer str = new StringBuffer();
			str.append("<list>");
			str.append("<count>"+count+"</count>");
			str.append(" <detail>");
			list.forEach(at->{
				str.append("  <message>");
				str.append("   <wait_id>"+at.getWait_id()+"</wait_id>");
				str.append("   <company>"+at.getCompanyFullName()+"</company>");
				str.append("   <content>"+at.getContentsFull()+"</content>");
				str.append("   <grade_name>"+at.getGrade_name()+"</grade_name>");
				str.append("   <class_name>"+at.getClass_name()+"</class_name>");
				str.append("   <create_name>"+at.getCreate_name()+"</create_name>");
				str.append("   <status>"+at.getStatus()+"</status>");
				str.append("  </message>");
			});
			str.append(" </detail>");
			str.append("</list>");
			try {os.write(str.toString().getBytes());} catch (IOException e) {log.error("writer Exception",e);throw new RuntimeException(e);}
		});
	}
	
	//dmb 승인반려 리스트  xml
	@RequestMapping(value="/dmb/auth")
	public Writer<?> dmb_auth(@Valid @ModelAttribute("MsgDMBAuthVO") MsgDMBAuthVO msgDMBAuth, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String _ip_regex = configMng.getParam("dmb_ip_regex");
		String user_ip = RequestUtil.getRemoteAddr(request);
		if(!SecurityUtil.isLogin() && !StringUtil.isMatches(user_ip, _ip_regex)){
			throw new Exception(Code.AUTH_FAIL.msg());
		}
		
		//WAIT만 우선가져와
		List<Criterion> where = new ArrayList<Criterion>();
		where.add(Restrictions.eq("wait_id",msgDMBAuth.getId()));
		where.add(Restrictions.eq("status",Code.CODE_MSG_STATUS_WAIT.cd()));
		MsgWaitVO msgWait = msgService.getMsgWait(where); //없으면 여기서 에러남 
		if(null==msgWait){
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE+";charset=UTF-8");
			BodyErrorsException error = new BodyErrorsException((r,p)->{return Code.TARGET_FAIL.msg();});
			error.setHeaders(header);
			throw error;
		}
		msgWait.setStatus(msgDMBAuth.getApprove_mode());
		if(Code.CODES_MSG_STATUS.child(msgDMBAuth.getApprove_mode()) == Code.CODE_MSG_STATUS_APPROVED){ //approve일때만.
			msgWait.refreshWaiteDateTimeFromNow();
		}
		Code rcode = msgService.updateMsgWaitStatus(msgWait);
		if(Code.SUCCESS!=rcode){
			throw new BodyErrorsException((r,p)->{return rcode.msg();});
		}
		return new Writer<>().putHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE).setExecutor(os->{
			try {os.write("OK".getBytes());} catch (IOException e) {log.error("writer Exception",e);throw new RuntimeException(e);}
		});
	}
	
	
	
	@RequestMapping(value="/reg")
	public Writer<List<MsgWaitVVO>> anonReg(@Valid @ModelAttribute("MsgWait") MsgRegWaitVO reg, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		log.info("/anon/reg  request parameter "+RequestUtil.getParametersSerialize(request));
		String _ip_regex = configMng.getParam("reg_ip_regex");
		String user_ip = RequestUtil.getRemoteAddr(request);
		
		log.info("/anon/reg  ("+user_ip+") request["+reg+"]");
		
		if(!SecurityUtil.isLogin() && !StringUtil.isMatches(user_ip, _ip_regex)){
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE+";charset=UTF-8");
			BodyErrorsException error = new BodyErrorsException((r,p)->{return Code.AUTH_FAIL.msg();});
			error.setHeaders(header);
			log.error("/anon/reg  ip reject  user_ip["+user_ip+"]",error);
			throw error;
		}
		
		MsgWaitJoinVO wait = new MsgWaitJoinVO();
		
		OperatorRightJoinVO right = operatorService.getOperatorRightJoin(Restrictions.eq("operator_id", reg.getOperator_id()));
		log.info("/anon/reg   right["+right+"]");
		if(null==right || null==right.getOpRights() || right.getOpRights().size()<=0){
			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE+";charset=UTF-8");
			BodyErrorsException error = new BodyErrorsException((r,p)->{return Code.TARGET_FAIL.msg();});
			error.setHeaders(header);
			log.error("/anon/reg  right  fail ",error);
			throw error;
		}else{
			for(OpRightJoinVO atRight : right.getOpRights()){
				Code atCode = Code.CODES_RIGHT.child(atRight.getRights().getCode());
				if(Code.CODE_RIGHT_MANAGE_IM == atCode || Code.CODE_RIGHT_MANAGE_IM.equals(atCode)){
					wait.setStatus(Code.CODE_MSG_STATUS_READY.cd());
					break;
				}
			}
		}
		
		wait.setCreate_id(reg.getOperator_id());
		wait.setTest_ch(reg.getTest_ch());
		wait.setGrade_id(reg.getGrade_id());
		wait.setClass_id(reg.getClass_id());
		wait.setCh_id(null==reg.getCh_id()?Code.CODE_CHANNEL_INFO.cd():reg.getCh_id());
		wait.setCallback_data(null);
		
		//company
		if(null!=reg.getCompany_id() && reg.getCompany_id().size()>0){
			ArrayList<MsgCompanyVO> list = new ArrayList<MsgCompanyVO>();
			reg.getCompany_id().forEach(at->{
				if(at!=null){
					MsgCompanyVO company = new MsgCompanyVO();company.setCompany_id(at);
					list.add(company);
				}
			});
			wait.setCompanys(list);
		}else{
			BodyErrorsException e = new BodyErrorsException((r,p)->{return "FAIL";});
			log.info("/anon/reg   company null",e);
			throw e;
		}
		//area
		if(null!=reg.getArea_id() && reg.getArea_id().size()>0){
			ArrayList<MsgAreaVO> list = new ArrayList<MsgAreaVO>();
			reg.getArea_id().forEach(at->{
				if(null!=at){
					MsgAreaVO area = new MsgAreaVO();area.setArea_id(at);
					list.add(area);
				}
			});
			wait.setAreas(list);
		}else{
			BodyErrorsException e = new BodyErrorsException((r,p)->{return "FAIL";});
			log.info("/anon/reg   area null",e);
			throw e;
		}
		
		//contents
		if(null!=reg.getContents()){
			for (int i = 0; i < reg.getContents().size(); i++) {
				Code code = Code.CODES_COMPANY.child(String.valueOf(i));
				String contents = reg.getContents().get(i);
				log.info("/anon/reg  content_company ["+code+"] : "+contents);
				//2G 메시지초과를 하면 실패!
				if(null!=contents && contents.length()>0 && contents.getBytes(new EUC_KR()).length > Integer.parseInt(configMng.getParam("2G_msg_max"))){
					HttpHeaders header = new HttpHeaders();
					header.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE+";charset=UTF-8");
					BodyErrorsException error = new BodyErrorsException((r,p)->{return Code.VALIDATE_FILE_SIZE_FAIL.msg();});
					error.setHeaders(header);
					log.error("/anon/reg   length exception",error);
					throw error;
				}
				
				switch (code) {
					case CODE_COMPANY_ALL		: wait.setContents_all(contents); 		break;
					case CODE_COMPANY_SKT		: wait.setContents_skt(contents); 		break;
					case CODE_COMPANY_LGT		: wait.setContents_lgt(contents); 		break;
					case CODE_COMPANY_SKT_LTE	: wait.setContents_skt_lte(contents); 	break;
					case CODE_COMPANY_KT_LTE	: wait.setContents_kt_lte(contents); 	break;
					case CODE_COMPANY_LGU_LTE	: wait.setContents_lgu_lte(contents); 	break;
					default:break;
				}
			}
		}
		
		try{
		log.info("/anon/reg   addMsg["+wait+"]");
		msgService.addMsg(wait);
		}catch (Exception e) {
			log.error("/anon/reg   other exception",e);
			throw new BodyErrorsException((r,p)->{return "FAIL ";});
		}
		return new Writer<List<MsgWaitVVO>>().putHeader("Content-Type", MediaType.TEXT_PLAIN_VALUE).setExecutor(os->{
			try {os.write("OK".getBytes());} catch (IOException e) {log.error("writer Exception",e);throw new RuntimeException(e);}
		});
	}

	
}
