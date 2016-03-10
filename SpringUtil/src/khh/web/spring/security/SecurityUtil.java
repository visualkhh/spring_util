package khh.web.spring.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityUtil {

	public static void logout(HttpServletRequest request, HttpServletResponse response){
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
	    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
	    cookieClearingLogoutHandler.logout(request, response, null);
	    securityContextLogoutHandler.logout(request, response, null);
	}
}
