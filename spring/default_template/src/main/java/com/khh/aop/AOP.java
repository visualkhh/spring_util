package com.khh.aop;

import java.io.ByteArrayOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khh.DefaultVO;
import com.khh.code.Code;
import com.khh.config.ConfigManager;
import com.khh.sign.SignController;
import com.khh.sign.service.SignService;
import com.khh.sign.vo.OperatorLogVO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khh.project.operator.service.OperatorService;
import com.khh.project.operator.vo.OperatorVO;
import com.omnicns.java.aop.AOPUtil;
import com.omnicns.java.collection.html.HtmlTableList;
import com.omnicns.java.collection.list.poi.ExcelPOIList;
import com.omnicns.java.convert.ConvertUtil;
import com.omnicns.java.file.FileUtil;
import com.omnicns.java.gson.GsonUtil;
import com.omnicns.java.io.stream.Writer;
import com.omnicns.java.string.StringUtil;
import com.omnicns.java.type.TypeUtil;
import com.khh.login.vo.LoginUserVO;
import com.khh.security.ConvertToViewDefaultVOCrawler;
import com.khh.security.SecurityManager;
import com.omnicns.web.content.Content;
import com.omnicns.web.request.RequestUtil;
import com.omnicns.web.spring.exception.CustomErrorsException;
import com.omnicns.web.spring.exception.JsonErrorsException;
import com.omnicns.web.spring.exception.JspErrorsException;
import com.omnicns.web.spring.security.SecurityUtil;
@Aspect
public class AOP {
	
	enum VIEW_TYPE{
		JSON, RESPONSEENTITY,VIEWRESOLVER, FILE, EXCEL_FILE, HTMLTABLE, WRITER
	}
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public SessionFactory  sessionFactory;
	@Autowired
	public SecurityManager  securityMng;
	@Autowired
	public ConfigManager configMng;
	@Resource(name="SignService")
	SignService signService;
	@Resource(name="OperatorService")
	OperatorService operatorService;
	@Resource(name="SignController")
	SignController signController;
	
	
	
	
	
	public AOP() {
	}
	@Around("execution(public * com.omnicns.project..*Controller.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long time1 = System.currentTimeMillis();

		

		Object[] args 						= AOPUtil.getParamter(joinPoint);
		Class returnType 					= AOPUtil.getMethodReturnType(joinPoint); 
		MethodSignature methodSignature 	= (MethodSignature) joinPoint.getStaticPart().getSignature();
		Method method 						= methodSignature.getMethod();
		Annotation[] methodAnnotations 		= method.getAnnotations();
		ResponseBody body 					= method.getAnnotation(ResponseBody.class);
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		log.debug("CMSAOP execution(public * com.omnicns.project..*Controller.*(..)) start.("+method.getName()+")");
		VIEW_TYPE viewType 				= VIEW_TYPE.VIEWRESOLVER; 
		viewType = getViewType(returnType);
		
		
		
		
		
		
		
		HttpSession session = Content.getSession();
		HttpServletRequest request = Content.getRequest();
		HttpServletResponse response = Content.getResponse();
		
		///////loging
		if(StringUtil.isMatches(RequestUtil.getURIFULL(request),configMng.getParam("loging_uri_regex"))){
			OperatorLogVO operatorLog = new OperatorLogVO();
			operatorLog.setScript_name( RequestUtil.getURIFULL(request));
			operatorLog.setAccess_ip( RequestUtil.getRemoteAddr(request));
			operatorLog.setData("request["+time1+"]("+Thread.currentThread().getName()+") :"+RequestUtil.getParametersSerialize(request));
			if(operatorLog.getScript_name().getBytes().length>1000){
				byte[] nbyte = new byte[1000];
				System.arraycopy(operatorLog.getScript_name().getBytes(),0,nbyte,0,nbyte.length);
				operatorLog.setScript_name(new String(nbyte));
			}
			if(operatorLog.getData().getBytes().length>4000){
				byte[] nbyte = new byte[4000];
				System.arraycopy(operatorLog.getData().getBytes(),0,nbyte,0,nbyte.length);
				operatorLog.setData(new String(nbyte));
			}
			if(SecurityUtil.isLogin()){
				LoginUserVO user = securityMng.getSecurityUser();
				operatorLog.setOperator_id(user.getOperator_id());
				if(!"true".equals(configMng.getParam("want_dup_login"))){
					OperatorVO dbOperator=operatorService.getOperator(Restrictions.eq("operator_id", user.getOperator_id()));
					if( !user.getLast_login_ip().equals(dbOperator.getLast_login_ip()) || !user.getLast_login_session().equals(dbOperator.getLast_login_session()) ||!user.getLast_login_date().equals(dbOperator.getLast_login_date())){ //하나라도 같이 않은게 있으면 실패!
						CustomErrorsException ce = new CustomErrorsException();
						HttpHeaders headers = new HttpHeaders();
						headers.setLocation(new URI("/sign/expired?last_login_ip="+dbOperator.getLast_login_ip()));
						if(viewType == VIEW_TYPE.VIEWRESOLVER){
							ce.setHttpStatus(HttpStatus.TEMPORARY_REDIRECT);
						}else{
							ce.setHttpStatus(HttpStatus.FORBIDDEN);
						}
						
						ce.setHeaders(headers);
						throw ce;
						
					}
				}
				
			}
			signService.saveOperator(operatorLog);
		}
		
		

		
		
		///////////execute///////////////////////////////////////
		before(joinPoint, viewType);
        Object retVal = joinPoint.proceed(args);
        after(joinPoint, retVal);
        ///////////execute/////END///////////////////////////////
        if(null!=retVal){
	        viewType = getViewType(retVal.getClass());
	        if(viewType == VIEW_TYPE.JSON ){
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.setCharacterEncoding("UTF-8");
				
				if(Code.class.isAssignableFrom(retVal.getClass())){
					retVal =  ((Code)retVal).code();
				}
//				response.getOutputStream().write(GsonUtil.toJsonExpose(retVal).getBytes());
//				response.flushBuffer();
//				response.getOutputStream().flush();
//				response.getOutputStream().close();
				String json = GsonUtil.toJsonExpose(retVal);
				json = json.replaceAll("<", "&lt;");
				json = json.replaceAll(">", "&gt;");
				json = json.replaceAll("&", "&amp;");
				json = json.replaceAll("\"", "&quot;");
				json = json.replaceAll("&quot;", "\"");
				response.getWriter().write(json);
				response.getWriter().flush();
				response.getWriter().close();
				retVal = null;
			}else if(viewType == VIEW_TYPE.FILE && null!=retVal){
				if(null==response.getHeader("Content-Disposition")){
					response.setHeader("Content-Disposition", "attachment; filename=" + ((FileSystemResource) retVal).getFilename());//다운로드됨  //response.setHeader("Content-Disposition", "inline; filename="+file.getName());//바로열림
				}
				if(null==response.getContentType()){//("Content-Type")
					response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
					//response.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
				}
		        response.getOutputStream().write(FileUtil.readFileToByte(((FileSystemResource) retVal).getFile()));
		        response.flushBuffer();
		        response.getOutputStream().flush();
		        response.getOutputStream().close();
		        retVal = null;
			}else if(viewType == VIEW_TYPE.EXCEL_FILE && null!=retVal){//&& ((ExcelPOIList)retVal).size()>0
				ExcelPOIList elist = ((ExcelPOIList)retVal);
				if(null==response.getHeader("Content-Disposition")){
					response.setHeader("Content-Disposition", "attachment; filename=" + (elist.getName()==null?"excel.xls":elist.getName()+".xls") );//다운로드됨  //response.setHeader("Content-Disposition", "inline; filename="+file.getName());//바로열림
				}
				if(null==response.getContentType()){//("Content-Type")
					response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
				}
				elist.write(response.getOutputStream());
				response.flushBuffer();
				response.getOutputStream().flush();
		        response.getOutputStream().close();
				retVal=null;
				
			}else if(viewType == VIEW_TYPE.HTMLTABLE && null!=retVal){
				HtmlTableList elist = ((HtmlTableList)retVal);
				if(null==response.getContentType()){//("Content-Type")
					response.setContentType(MediaType.TEXT_HTML_VALUE+ ";charset=UTF-8");
				}
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				elist.write(outputStream);
				String rs = outputStream.toString();
//				rs = rs.replaceAll("script", "");
//				rs = rs.replaceAll("javascript", "");
				rs = rs.replaceAll("<", "&lt;");
				rs = rs.replaceAll(">", "&gt;");
				rs = rs.replaceAll("&", "&amp;");
				rs = rs.replaceAll("\"", "&quot;");
				response.getWriter().write(rs);
				outputStream.flush();
		        outputStream.close();
				retVal=null;
			}else if(viewType == VIEW_TYPE.WRITER && null!=retVal){
				Writer elist = ((Writer)retVal);
				Map<String,String> header = elist.getHeader();
				header.entrySet().stream().forEach(at->{
					response.setHeader(at.getKey(),at.getValue());
				});
				elist.setOutputStream(response.getOutputStream());
				elist.write();
				response.flushBuffer();
				response.getOutputStream().flush();
		        response.getOutputStream().close();
				retVal=null;
			}
        }
        
        

        
       /////////////////////////////////////////////////////////
//        if(null!=session){session.close();};
        long time2 = System.currentTimeMillis();
        log.debug("CMSAOP execution(public * com.omnicns.project..*Controller.*(..)) end.("+method.getName()+") end. Time mms(" + (time2 - time1) + ")");
        
        
        
        return retVal;
	}
	
	
	public void before(JoinPoint joinPoint, VIEW_TYPE type) throws Throwable {
		Object[] args = AOPUtil.getParamter(joinPoint);
	   for (Object signatureArg: args) {
		   if(null!=signatureArg && DefaultVO.class.isAssignableFrom(signatureArg.getClass())){
			   DefaultVO dv = (DefaultVO)signatureArg;
			   BindingResult bind = dv.getErrors();
			   if(null!=bind && bind.hasErrors()){
				   if(type == VIEW_TYPE.JSON){ //json
					   throw new JsonErrorsException(bind);
				   }else{//JSP
					   throw new JspErrorsException(bind);
				   }
			   }
		   }
	   }
	}
	
	public void after(JoinPoint joinPoint, Object retVal) throws Throwable {
		Object[] args = AOPUtil.getParamter(joinPoint);
		
		////modelMap
		for (Object signatureArg: args) {
		   if(null!=signatureArg && ModelMap.class.isAssignableFrom(signatureArg.getClass())){	//formatting
			   ModelMap modelMap = (ModelMap)signatureArg;
			   ConvertUtil.convertValueSet(modelMap, injection-> {
						if(injection!=null && !modelMap.equals(injection)&& (DefaultVO.class.isAssignableFrom(injection.getClass())||TypeUtil.isContainer(injection))){
							new ConvertToViewDefaultVOCrawler().startCrawling(injection);
						}
						return injection;
					});
		   }
		}
	   
		if(null!=retVal && getViewType(retVal.getClass()) == VIEW_TYPE.JSON){
			if(!retVal.getClass().isEnum()){
				new ConvertToViewDefaultVOCrawler().startCrawling(retVal);
			}
		}
	
	}
	
	public VIEW_TYPE getViewType(Class klass){
		VIEW_TYPE viewType = VIEW_TYPE.JSON;
		if(String.class.isAssignableFrom(klass) ){ //스트링이면 JSON
			viewType = VIEW_TYPE.VIEWRESOLVER;
		}else if(ResponseEntity.class.isAssignableFrom(klass)){
			viewType = VIEW_TYPE.RESPONSEENTITY;
		}else if(FileSystemResource.class.isAssignableFrom(klass)){
			viewType = VIEW_TYPE.FILE;
		}else if(ExcelPOIList.class.isAssignableFrom(klass)){
			viewType = VIEW_TYPE.EXCEL_FILE;
		}else if(HtmlTableList.class.isAssignableFrom(klass)){
			viewType = VIEW_TYPE.HTMLTABLE;
		}else if(Writer.class.isAssignableFrom(klass)){
			viewType = VIEW_TYPE.WRITER;
		}else{
			viewType = VIEW_TYPE.JSON;
		}
		return viewType;
	}
	
//	@Before("execution(public * com.ko.omnicns.project2.cms..*Controller.*(..))")
//	public void before(JoinPoint joinPoint) throws Throwable {
//	...
//	}
//	@AfterReturning(pointcut="execution(public * com.ko.omnicns.project2.cms..*Controller.*(..))",returning = "retVal")
//	...
//	}
	
	
	
	
}
