package templates

import com.khh.project.config.web.WebSecurityConfigurerAdapter

//http://melix.github.io/blog/2014/02/markuptemplateengine.html
html {
  head {
  }
  body {
    yield 'PATH_ERROR_401'
  }
}
