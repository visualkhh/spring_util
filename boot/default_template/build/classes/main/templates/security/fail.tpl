package templates.security

import com.khh.project.config.web.WebSecurityConfigurerAdapter

//http://www.w3ii.com/ko/groovy/groovy_template_engines.html
layout 'layout.tpl', title: 'login fail',
  content:  contents {
    div{
        yield  "login fail ${SPRING_SECURITY_LAST_EXCEPTION}"
    }
  }