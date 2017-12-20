package com.visualkhh.cms.config.security;

import com.visualkhh.cms.domain.security.Auth;
import com.visualkhh.cms.domain.security.UserDetails;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class FilterInvocationSecurityMetadataSource implements org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource {
//	@Autowired
//	private ResourceMetaService resourceMetaService;
//
//	@Autowired
//	private CacheManager cacheManager;


	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		FilterInvocation fi = (FilterInvocation) object;
		HttpServletRequest request 		= fi.getHttpRequest();
		HttpServletResponse response 	= fi.getResponse();
		String uri = request.getRequestURI();
		String method = request.getMethod();

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		log.debug("url:{}, method:{}, Authentication:{} FilterInvocationSecurityMetadataSource >> {}", uri, method, authentication, object.toString());



		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_SUPER"));
		if(uri.equals(WebSecurityConfigurerAdapter.ROOT_PATH) || uri.equals(WebSecurityConfigurerAdapter.LOGIN_PAGE)){
			attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_ANONYMOUS"));
			attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_AUTH"));
		}
		if(uri.startsWith(WebSecurityConfigurerAdapter.AUTH_PATH)){
			attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_AUTH"));
		}

		//여기서 지정한 권한이 있어야지만 안쪽페이지로 들어갈수 있다!!!!!
		if(SecurityUtil.isUsernamePasswordAuthentication()){
			UserDetails userDetails = (UserDetails)SecurityUtil.getGrantedAuthorityDetails();
			userDetails.getAuthorities().stream().forEach(at->{
				for (Auth auth:ListUtils.emptyIfNull(at.getAuth())) {
					String userURI = auth.getUrl();
					String crudType = auth.getCrudType();
					if(null!=uri && null!=userURI && uri.equals(userURI) && null!=method && null!=crudType && method.equals(crudType)){
						com.omnicns.web.spring.security.ConfigAttribute u = new com.omnicns.web.spring.security.ConfigAttribute(at.getAuthority());
						attributes.add(u);
					}
				}
			});
		}
//		return SecurityConfig.createList(roles);
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

//	@Override
//	public void afterPropertiesSet() throws Exception {
//		resourceMetaService.findAllResources();
//	}
}
