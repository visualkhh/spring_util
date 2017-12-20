package com.khh.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	Logger log = LoggerFactory.getLogger(LoginController.class);
	
//	@RequestMapping("/logout")
//	public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
//		//session.invalidate();
//		SecurityUtil.logout(request, response);
//		return "redirect:/?logout";
//	}
}
