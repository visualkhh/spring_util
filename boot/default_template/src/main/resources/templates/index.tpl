package templates.board

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository

//http://www.w3ii.com/ko/groovy/groovy_template_engines.html
layout 'layout.tpl', title: "page : ${springMacroRequestContext.getMessage('msg.main')}",
        content:  contents {
            div{
                //yield "방갑습니다. ${springMacroRequestContext} >>> ${springMacroRequestContext.getMessage('error.login.fail')} >>> ${spring.request} >>>${spring.response} >>> ${spring.getModelObject('_csrf').token}  "
                yield "content : ${springMacroRequestContext.getMessage('msg.hello')}"
            }


//            div(style:"margin:50px"){
//                SecurityContextHolder.context?.authentication?.authorities?.find{"ROLE_ANONYMOUS".equals(it.authority)}?.any{
//                    yield "ROLE_ANONYMOUS"
//                }
//            }
            div{
                yield SecurityContextHolder.context?.authentication
            }
//            div(style:"margin:50px"){
////                yield request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
//                //yield springMacroRequestContext.request.getSession().getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY+"_AUTHENTICATION");
//            }
//            div(style:"margin:50px") {
//                SecurityContextHolder.context?.authentication?.authorities?.each {sit->
//                    div {
//                        yield sit.authority
//                    }
//                }
//            }


        }
