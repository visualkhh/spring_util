//package com.nhis.ggij.exception;
//
//import CoreConfig;
//import com.omnicns.java.callstack.StackTraceUtil;
//import com.omnicns.java.convert.ConvertUtil;
//import com.omnicns.web.request.RequestUtil;
//import com.omnicns.web.spring.message.CustomReloadableResourceBundleMessageSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@ControllerAdvice(CoreConfig.ROOT_PACKAGE)
////@ControllerAdvice(annotations = RestController.class)
//@RestController
//@Slf4j
//public class ControllerAdviceException {
////	@Autowired
////	CustomReloadableResourceBundleMessageSource messageSource;
//
////	@ExceptionHandler(ErrorsException.class)
////	@ResponseBody
////	public String errosException(HttpServletRequest request, HttpServletResponse response,ErrorsException errors){
////		return  errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);;
////	}
////
//	/*
//	로그시간	O	YYYYMMDDHH24MISS 14byte 형식
//	로그레벨	O	FATAL, ERROR, WARN, INFO, DEBUG 5단계만 명시
//	API Code	X	API 처리 관련 로그 일 경우 전문번호 지정 필요
//	에러코드	O	에러코드 명시 (성공 case도 로그로 명시 함)
//	에러부연설명	X	에러부연설명
//	IP	X	접속 IP
//		[MSG] ID	X	처리 담당자 ID (사용자/관리자 등등 로그인상태 ID)
//		[MSG] Reserve field1	X	필요한 정보 추가 명시
//		[MSG] Reserve field2	X	필요한 정보 추가 명시
//		[MSG] Reserve field3	X	필요한 정보 추가 명시
//	ErrorTrace	O	"로그 생성 컨트롤러 명시 (APIController 포함이하 Trace) 	com.ko.omnicns.omnifit.api.APIController.api > com.ko.omnicns.omnifit.api.APIController.parse >…"
//	 */
//	@ExceptionHandler(Throwable.class)
//	@ResponseBody
//	public ResponseEntity<?> handleAnyException(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
////		try{
////			throw ex;
////		}catch(JsonSyntaxException e){
////			head.setResult_code(APIHead.CODE_PARAM_FAILE);//공통 필수 입력정보 부족
////		}catch(NullPointerException e){
////			head.setResult_code(APIHead.CODE_PARAM_FAILE);//공통 필수 입력정보 부족
////		}catch(IllegalArgumentException | IllegalStateException | MalformedJsonException | EOFException e){
////			head.setResult_code(APIHead.CODE_JSON_PARSE_FAIL);//파라미터 json 파싱 에러 시
////		}catch(TypeException e){
////			head.setResult_code(e.getType());
////		}catch(ErrorsException e){
////			head.setResult_code(APIHead.CODE_VALIDATE_FAIL);
////		}catch(SQLException e){
////			head.setResult_code(APIHead.CODE_DB_SQL_ERROR);
////		}catch(FileNotFoundException e){
////			head.setResult_code(APIHead.CODE_EMPTY_DATA);
////		}catch(InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e){
////			head.setResult_code(APIHead.CODE_ENCRYPT_ERROR);
////		}catch(IOException e){
////			head.setResult_code(APIHead.CODE_CREATE_FAIL);
////		}catch(Exception e){
////			head.setResult_code(APIHead.CODE_ERROR);
////		} catch (Throwable e) {
////			head.setResult_code(APIHead.CODE_ERROR);
////		}
////		String body = GsonUtil.toJsonExpose(head);
////		response.setHeader(configManager.getApiHeaderName(), body);
////
////
//
//		Throwable tex = StackTraceUtil.getLastCause(ex);
//		StackTraceElement[] stacks = tex.getStackTrace();
//		String trace = ConvertUtil.join(stacks," < ");
//		String ip = RequestUtil.getRemoteAddr(request);
//
//		StringBuffer logInfo = new StringBuffer();
//		logInfo.append(ex.getClass().getName());
//		logInfo.append("|").append(request.getRequestURI());//API CODE(URL)
////		logInfo.append("|").append(head.getResult_code());
////		BootCodeVO codevo = BootManager.getInstance().getCode(head.getResult_code());
////		logInfo.append("|").append( (codevo==null?messageSource.getMessage("word.unknown"):codevo.getCd_desc()) );
//		logInfo.append("|").append(ip);	// 접속IP
//		logInfo.append("|").append(null==ex.getMessage()?"":ex.getMessage().replace("\n","").replace("\r", ""));	//Exception Message
//		logInfo.append("|").append(trace);
//		log.error(logInfo.toString());
//
//
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		HttpStatus ststus = HttpStatus.INTERNAL_SERVER_ERROR;
//		if(MethodArgumentNotValidException.class.isAssignableFrom(ex.getClass())){
//			MethodArgumentNotValidException sex = (MethodArgumentNotValidException)ex;
//			sex.getBindingResult();
//			ststus = HttpStatus.BAD_REQUEST;
//		}
//
//		ResponseEntity<String> responseentity = new ResponseEntity<String>("ERROR", headers, ststus);
//		return responseentity;
//	}
//
//
//
//
//
//	protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
//		return new ResponseEntity<T>(body, new HttpHeaders(), status);
//	}
//}
