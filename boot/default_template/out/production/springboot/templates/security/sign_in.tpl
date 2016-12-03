package templates.security

import com.khh.project.config.web.WebSecurityConfigurerAdapter

//http://www.w3ii.com/ko/groovy/groovy_template_engines.html
layout 'layout.tpl', title: 'login sign_in',
  content:  contents {
    div{
        yield  "login sign_in"
    }
  }