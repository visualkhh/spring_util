package com.khh.project.common;

import javax.annotation.Resource;

import com.khh.DefaultController;
import com.khh.project.common.service.CommonService;
import com.khh.security.SecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("CommonController")
@RequestMapping("/log")
public class CommonController extends DefaultController {
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	SecurityManager sManager;
	@Resource(name="CommonService")
	CommonService service;
	
}
