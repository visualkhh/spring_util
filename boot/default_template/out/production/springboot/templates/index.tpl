package templates.board

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder

//http://www.w3ii.com/ko/groovy/groovy_template_engines.html
layout 'layout.tpl', title: '메인화면입니다.',
        content:  contents {
            div{
                yield "방갑습니다. ${springMacroRequestContext} >>> ${springMacroRequestContext.getMessage('error.login.fail')} >>> ${spring.request} >>>${spring.response} >>> ${spring.getModelObject('_csrf').token}  "
            }


            div(style:"margin:50px"){
                SecurityContextHolder.context?.authentication?.authorities?.find{"ROLE_ANONYMOUS"==it.authority}?.any{
                    yield "ROLE_ANONYMOUS"
                }
            }

            div{
                yield SecurityContextHolder.context?.authentication
            }
            div(style:"margin:50px") {
                SecurityContextHolder.context?.authentication?.authorities?.each {sit->
                    div {
                        yield sit.authority
                    }
                }
            }
//            def a = 4/0;

//            div(style:"margin:50px") {
//                SecurityContextHolder.context?.authentication.authorities?.each {
//                    yield it.authority
//                }
//            }


//            .each{
//                a{
//                    yield it
//                }
//            };

//            def authentication = SecurityContextHolder.getContext()?.getAuthentication()
//            div(style:"margin:50px"){
//                yield  authentication
//            }




//            SecurityExpressionHandler<FilterInvocation> securityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//            LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
//            //map.put(new AntPathRequestMatcher("/test"), Arrays.<ConfigAttribute>asList(new SecurityConfig("permitAll")));
//            MyFilterSecurityMetadataSource ms = new MyFilterSecurityMetadataSource();
//            filterSecurityInterceptor.setSecurityMetadataSource(ms);
//            try {
//                filterSecurityInterceptor.afterPropertiesSet();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            }
