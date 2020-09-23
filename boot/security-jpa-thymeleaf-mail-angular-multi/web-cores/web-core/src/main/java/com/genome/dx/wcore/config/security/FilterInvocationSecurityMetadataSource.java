package com.genome.dx.wcore.config.security;

import com.genome.dx.core.code.CrudTypeCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.wcore.domain.security.AuthDetail;
import com.genome.dx.wcore.domain.security.UserDetails;
import com.omnicns.java.string.StringUtil;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
public class FilterInvocationSecurityMetadataSource implements org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource {
//	@Autowired
//	private ResourceMetaService resourceMetaService;
//


	@Override
	public Collection<ConfigAttribute> getAttributes(Object object){

		FilterInvocation fi             = (FilterInvocation) object;
		HttpServletRequest request 		= fi.getHttpRequest();
//		HttpServletResponse response 	= fi.getResponse();
		String uri                      = request.getRequestURI();
		String method                   = request.getMethod();

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		log.debug("url:{}, method:{}, Authentication:{} FilterInvocationSecurityMetadataSource >> {}", uri, method, authentication, object.toString());


//		if(uri.startsWith(WebSecurityConfigurerAdapter.SECURITY_PATH)){
//			return null;
//		}
		List<ConfigAttribute> attributes = new ArrayList<>();
		attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_SUPER"));
		if(uri.equals(WebSecurityConfigurerAdapter.ROOT_PATH) || uri.equals(WebSecurityConfigurerAdapter.LOGIN_PAGE) || uri.startsWith(WebSecurityConfigurerAdapter.ANON_PATH) || uri.startsWith(WebSecurityConfigurerAdapter.API_PATH)){
			attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_ANONYMOUS"));
			attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_AUTH"));
		}
		if(uri.startsWith(WebSecurityConfigurerAdapter.AUTH_PATH)){
			attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_AUTH"));
		}
//		if(uri.startsWith("/swagger")  || uri.startsWith("/v2/api-docs")){
//			attributes.add(new com.omnicns.web.spring.security.ConfigAttribute("ROLE_AUTH"));
//		}

		//여기서 지정한 권한이 있어야지만 안쪽페이지로 들어갈수 있다!!!!!
		if(SecurityUtil.isUsernamePasswordAuthentication()){
			UserDetails userDetails = (UserDetails)SecurityUtil.getGrantedAuthorityDetails();
			userDetails.getAuthorities().stream().forEach(at->{
				for (AuthDetail auth : ListUtils.emptyIfNull(at.getAuth())) {
					String userURI = auth.getUrl();
					boolean userRegexp = UseCd.USE001.equals(auth.getRegexpCd());
					CrudTypeCd userCrudType = auth.getCrudTypeCd();
//					log.debug("uri: "+ uri + ", userURI: "+userURI);
					if(null!=uri && null!=userURI && (userRegexp ? StringUtil.isMatches(uri, userURI) : uri.equals(userURI)) && null!=method && null!=userCrudType && method.equals(userCrudType.name())){
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
		return Collections.emptyList();
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
