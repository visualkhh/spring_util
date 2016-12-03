package templates

import com.khh.project.config.web.WebSecurityConfigurerAdapter
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.security.core.context.SecurityContextHolder

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
          a(style:'padding:5px', class:'brand', href:WebSecurityConfigurerAdapter.ROOT_PATH) {
            yield '<HOME>'
          }

          SecurityContextHolder.context?.authentication?.authorities?.find{"ROLE_ANONYMOUS".equals(it.authority)}?.any{
            a(style:'padding:5px', href:"${WebSecurityConfigurerAdapter.LOGIN_PAGE}") {
              yield 'login'
            }
          }
          SecurityContextHolder.context?.authentication?.authorities?.find{"ROLE_USER".equals(it.authority)}?.any {
            a(style: 'padding:5px', href: "${WebSecurityConfigurerAdapter.LOGOUT_URL}") {
              yield 'logout'
            }
          }
          hr()
        }
      }
      h1(title)
      div { content() }
      hr()
      dir{
        yield LocaleContextHolder.locale
      }
    }
  }
}
