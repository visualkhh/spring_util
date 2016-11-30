package templates.board

import org.springframework.security.core.context.SecurityContextHolder

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
        }