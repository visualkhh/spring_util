package templates.security

import com.khh.project.config.web.WebSecurityConfigurerAdapter

//http://www.w3ii.com/ko/groovy/groovy_template_engines.html
layout 'layout.tpl', title: 'login',
  content:  contents {
    div{
        form(action:"${WebSecurityConfigurerAdapter.LOGIN_PROCESSING_URL}", method:'POST'){
            input(type:'text', name:'username', value:'')
            input(type:'password', name:'password', value:'')
            input(type:'hidden', name:"${_csrf?.parameterName}", value:"${_csrf?.token}")
            input(type:'submit', value:'submit')
        }
    }
  }