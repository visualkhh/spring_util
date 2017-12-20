package com.khh.boot;

import java.util.List;

import javax.annotation.Resource;

import com.khh.boot.service.BootService;
import com.khh.boot.vo.BootMenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.khh.DefaultController;
import com.khh.boot.vo.BootCodeVO;


@Controller("BootController")
public class BootController extends DefaultController {
	Logger log = LoggerFactory.getLogger(BootController.class);
	
	BootManager bootManager = BootManager.getInstance();
	
	@Resource(name="BootService")
	BootService bootService;
	
	public BootController() {
		log.debug("bootController new");
	}

	public List<BootMenuVO> selectMenu() throws Exception {
		//비지니스 로직..
//		return bootMapper.selectMenu();
		return null;
	}


	public List<BootCodeVO> selectCode() throws Exception {
		return null;
//		return bootMapper.selectCode();
	}
	
//	@Autowired
//	ServletContext context;
	
//	@PostConstruct
//	public void postConstruct() throws Exception{
//		log.debug("PostConstruct");
//	}
//	 @PreDestroy
//	 public void preDes() throws Exception{
//		 log.debug("preDes");
//	 }
	
}
