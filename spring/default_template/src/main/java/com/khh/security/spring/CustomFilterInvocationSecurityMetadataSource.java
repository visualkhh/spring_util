package com.khh.security.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.khh.boot.BootManager;
import com.khh.config.ConfigManager;
import com.khh.login.vo.LoginRoleAuthVVO;
import com.khh.login.vo.LoginUserVO;
import com.khh.security.SecurityManager;
import com.omnicns.web.request.RequestUtil;
import com.omnicns.web.spring.security.SecurityUtil;

public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	BootManager bootMng = BootManager.getInstance();
	SecurityManager securityMng = SecurityManager.getInstance();
	@Autowired
	private ConfigManager configMng;
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException { 
		
		
		FilterInvocation fi = (FilterInvocation) object;
//        String url = fi.getHttpRequest().getRequestURI();
        String uri = RequestUtil.getURI(fi.getHttpRequest());
        String httpMethod = fi.getRequest().getMethod();
//        List<ConfigAttribute> attributes = new ArrayList<com.omnicns.web.spring.security.ConfigAttribute>();
        List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
        
        
        com.omnicns.web.spring.security.ConfigAttribute a = null;
        a = new com.omnicns.web.spring.security.ConfigAttribute("ROLE_SUPER");
        attributes.add(a);
        
        if(uri.equals(configMng.getParam("root_uri"))||uri.startsWith(configMng.getParam("anon_uri"))||uri.startsWith(configMng.getParam("sign_uri"))){
        	a = new com.omnicns.web.spring.security.ConfigAttribute("ROLE_ANONYMOUS");
			attributes.add(a);
			a = new com.omnicns.web.spring.security.ConfigAttribute("ROLE_AUTH");
			attributes.add(a);
        }
        
        
        
        
        //여기서 지정한 권한이 있어야지만 안쪽페이지로 들어갈수 있다!!!!!
    	if(null!=securityMng.getSecurityAuthorities() && SecurityUtil.isLogin()){
    		LoginUserVO user = securityMng.getSecurityUser();
    		if(user.isEnabled()){
	    		securityMng.getSecurityAuthorities().stream().forEach(at->{//.filter(at->user.isEnabled()) 
	    			LoginRoleAuthVVO auth = at.getAuth();
	    			if(null!=auth){
		    			String userURI= auth.getRight().getUrl();
		    			if(null!=uri && null!=userURI && uri.indexOf(userURI)==0){
		    				com.omnicns.web.spring.security.ConfigAttribute u = new com.omnicns.web.spring.security.ConfigAttribute(at.getAuthority());
		    				attributes.add(u);
		    			}
	    			}
	    		});
    		}else{//사용가능하지 않은사람 즉 카드 인증된지 않은사람은 /authcheck 도허용한다!
    	        if(uri.startsWith(configMng.getParam("authcheck_uri"))){
    				a = new com.omnicns.web.spring.security.ConfigAttribute("ROLE_AUTH");
    				attributes.add(a);
    	        }
    		}
    		
    		
    	}

//        BootMenuVO menu = bootManager.getMenu(url);
//        if(null!=menu){
//        	attributes.add(new CustomConfigAttribute(menu.getMnu_no()));
//        }
        // Lookup your database (or other source) using this information and populate the
        // list of attributes
//        for (int i = 0; null!=bootSelectMenu && i < bootSelectMenu.size(); i++) {
//        	BootMenuVO at = bootSelectMenu.get(i);
//        	if(url.equals(at.getMnu_dir())){
//        		attributes.add(new CustomConfigAttribute(bootSelectMenu.get(i).getMnu_no()));
//        	}
//		} 
//    	if("/".equals(url)){
//			attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("Anonymous"));
//		}else{
//			return null;
//		}
        return attributes;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
