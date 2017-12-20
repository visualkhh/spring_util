package com.khh.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khh.login.vo.LoginRoleAuthRightGroupVO;
import com.khh.login.vo.LoginRoleAuthVVO;
import com.khh.login.vo.LoginUserVO;
import com.khh.security.spring.CustomGrantedObjAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omnicns.java.function.Encryptor;
import com.omnicns.java.random.RandomUtil;
import com.omnicns.java.security.AES256Coder;
import com.omnicns.java.security.MD5Coder;
import com.omnicns.java.security.SHACoder;
import com.omnicns.web.spring.security.SecurityUtil;

public class SecurityManager {
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	static private SecurityManager instance = null;
	//고객 개인정보 AES25 암호화 키 (양방향)
	private String privacyKey;// = "";//32byte
	static public SecurityManager getInstance(){
        if(instance==null)
            instance = new SecurityManager();
        return instance;
    }
	private Encryptor<String, String> privacyEncryptor = null; 
	private Encryptor<String, String> importantEncryptor = null; 
	
	
	
	
	
	
	
	
	
	private SecurityManager() {
	}
	
	
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		SecurityUtil.logout(request, response);
	}
	
	//32byte 개인키를 생성한다
	public String createSaltKey(){
		return RandomUtil.getRandomString(32);
	}

	//로그인된 고객의 정보를 추출한다  세션정보		
	public LoginUserVO getSecurityUser(){
		return SecurityUtil.getPrincipal(LoginUserVO.class);		
	}
	//로그인된 고객의 권한을 추출한다 세션정보
	public Collection<CustomGrantedObjAuthority> getSecurityAuthorities(){
		return (Collection<CustomGrantedObjAuthority>) SecurityUtil.getGrantedAuthorityList();
	}
	//ROLE_ 아닌것들을 가져온다.
	public ArrayList<CustomGrantedObjAuthority> getSecurityNotRoleAuthorities(){
		Collection<CustomGrantedObjAuthority> all = (Collection<CustomGrantedObjAuthority>) SecurityUtil.getGrantedAuthorityList();
		return all.stream().filter(at->at.getAuthority().indexOf("ROLE_")!=0).collect(Collectors.toCollection(ArrayList::new));
	}
	//로그인된 고객의 권한정보에 있는 ROLE_이 아닌것중에   LoginRoleAuthVO 값을 빼온다.
	public ArrayList<LoginRoleAuthVVO> getSecurityNotRoleLoginRightAuthorities(){
		Collection<CustomGrantedObjAuthority> all = (Collection<CustomGrantedObjAuthority>) SecurityUtil.getGrantedAuthorityList();
		return all.stream().filter(at->at.getAuthority().indexOf("ROLE_")!=0 && null!=at.getAuth()).map(at->at.getAuth()).collect(Collectors.toCollection(ArrayList::new));
	}
	//로그인된 고객의 권한정보에 있는 ROLE_인것중에   LoginRoleAuthRightGroup 값을 빼온다.
	//role에 있는 GROUP값을 리턴해준다  ROLE이 그룹이니깐  이프로젝트에서는.
	public ArrayList<LoginRoleAuthRightGroupVO> getSecurityRoleLoginRightGroupAuthorities(){
		Collection<CustomGrantedObjAuthority> all = (Collection<CustomGrantedObjAuthority>) SecurityUtil.getGrantedAuthorityList();
		return all.stream().filter(at->at.getAuthority().indexOf("ROLE_")==0 && null!=at.getAuth().getRight_group()).map(at->at.getAuth().getRight_group()).collect(Collectors.toCollection(ArrayList::new));
	}
	//고객으 넘겨준 GROUP ID랑 같은 LoginRoleAuthVO를 넘겨준다
	public ArrayList<LoginRoleAuthVVO> getSecuritAuthorities(LoginRoleAuthRightGroupVO group){
		 Collection<LoginRoleAuthVVO> auth = getSecurityNotRoleLoginRightAuthorities();
		return auth.stream().filter(at->group.getGroup_id()==at.getGroup_id()).collect(Collectors.toCollection(ArrayList::new));
//		return auth.stream().filter(at->{
//			log.debug(group.getGroup_id()+"     "+at.getGroup_id());
//			boolean sw = group.getGroup_id()==at.getGroup_id();
//			return sw;
//		}).collect(Collectors.toCollection(ArrayList::new));
		
	}
	
	
	//고객 개인정보 암호화
	public String privacyEncode(String src) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		return AES256Coder.encode(privacyKey, src);
	}
	//고객 개인정보 복호화
	public String privacyDecode(String desc) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		return AES256Coder.decode(privacyKey, desc);
	}
	//password  SHA512 단방향
	public String encrypt(String saltKey, String normalPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		String md5 = MD5Coder.encrypt(normalPassword);
		String saltMd5 = saltKey+md5;
		return SHACoder.encrypt512(saltMd5);//단방향 암호화
	}
//	public String encrypt(String key,String normalPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException{
//		String pwd		= key+normalPassword; 
//		String encode	= "";
//		encode = SHACoder.encrypt512(pwd);//단방향 암호화
//		return encode;
//	}

	public String getPrivacyKey() {
		return privacyKey;
	}
	public void setPrivacyKey(String privacyKey) {
		this.privacyKey = privacyKey;
	}
	
	
	
	

}
