package templates.board

import com.khh.project.config.web.WebSecurityConfigurerAdapter

//http://www.w3ii.com/ko/groovy/groovy_template_engines.html
layout 'layout.tpl', title: 'login',
  content:  contents {
    div{
        form(action:"${WebSecurityConfigurerAdapter.loginPage}", method:'POST'){
            input(type:'text', name:'username', value:'')
            input(type:'text', name:'password', value:'')
            input(type:'hidden', name:"${_csrf.parameterName}", value:"${_csrf.token}")
            input(type:'submit', value:'submit')
        }
    }
  }