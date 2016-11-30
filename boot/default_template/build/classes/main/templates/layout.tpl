package templates

import com.khh.project.config.web.WebSecurityConfigurerAdapter

//http://melix.github.io/blog/2014/02/markuptemplateengine.html
html {
  head {
    title(title)
    //link(rel:'stylesheet', href:'/css/bootstrap.min.css')
  }
  body {
    div(class:'container') {
      div(class:'navbar') {
        div(class:'navbar-inner') {
          a(style:'padding:5px', class:'brand', href:'/') {
              yield '<HOME>'
          }
          a(style:'padding:5px', href:"${WebSecurityConfigurerAdapter.LOGIN_PAGE}") {
              yield 'login'
          }
          a(style:'padding:5px', href:"${WebSecurityConfigurerAdapter.LOGOUT_URL}") {
              yield 'logout'
          }
          hr()
        }
      }
      h1(title)
      div { content() }
      hr()

    }
  }
}
