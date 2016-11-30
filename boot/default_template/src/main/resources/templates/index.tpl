package templates.board

import com.omnicns.web.spring.security.ConfigAttribute
import org.springframework.security.access.expression.SecurityExpressionHandler
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
import org.springframework.security.web.util.matcher.RequestMatcher

//http://www.w3ii.com/ko/groovy/groovy_template_engines.html
layout 'layout.tpl', title: '메인화면입니다.',
        content:  contents {
            div{
                yield  "방갑습니다. ${springMacroRequestContext} >>> ${springMacroRequestContext.getMessage('error.login.fail')} >>> ${spring.request} >>>${spring.response} >>> ${spring.getModelObject('_csrf').token}  "
            }
            Object authentication = SecurityContextHolder.getContext()?.getAuthentication();
            div{
                yield  authentication
            }




            SecurityExpressionHandler<FilterInvocation> securityExpressionHandler = new DefaultWebSecurityExpressionHandler();

            LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
            //map.put(new AntPathRequestMatcher("/test"), Arrays.<ConfigAttribute>asList(new SecurityConfig("permitAll")));
            MyFilterSecurityMetadataSource ms = new MyFilterSecurityMetadataSource();
            filterSecurityInterceptor.setSecurityMetadataSource(ms);
            try {
                filterSecurityInterceptor.afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }