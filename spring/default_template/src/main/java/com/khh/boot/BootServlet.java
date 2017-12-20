package com.khh.boot;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.khh.boot.service.BootService;
import com.omnicns.web.spring.application.ApplicationUtil;

/**
	<servlet>
		<description>
		</description>
		<display-name>BootServlet</display-name>
		<servlet-name>BootServlet</servlet-name>
		<servlet-class>com.ko.omnicns.project2.boot.BootServlet</servlet-class>
		<load-on-startup>3</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>BootServlet</servlet-name>
		<url-pattern>/BootServlet</url-pattern>
	</servlet-mapping>
 */
public class BootServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BootManager bootMng = BootManager.getInstance();
	@Autowired
	BootService bootService;	
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	public BootServlet() {
		log.debug("::BootServlet Start: new");
    }

    
	@Override
    public void init(ServletConfig config) throws ServletException {
		
		ApplicationUtil.injectionAutowireBean(ApplicationUtil.getWebApplicationContext(config.getServletContext()), this);
    	log.debug("::::::::::BootServlet Start::::::::::::::::"+bootService);
    	
    	bootMng.setRightGroup(bootService.getRightGroup());
    	bootMng.setMsgClass(bootService.getMsgClass());
    	bootMng.setArea(bootService.getArea());
    	bootMng.setMsgTemplate(bootService.getMsgTemplate());
    	
    	
//    	Object[] beans = ApplicationUtil.getWebApplicationBeans(config.getServletContext());
    	
//		List<BootMenuVO> menus = bootService.getMenu();
//		List<BootCodeVO> codes = bootService.selectCode();
//		for (int i = 0; i < codes.size(); i++) {
//			BootCodeVO code = codes.get(i);
//			bootManager.putCode(code.getCd(),code);
//		}
		
		
		
//		for(int i = 0; i < beans.length; i++){
//			Object bean = beans[i];
//			log.debug("bean -->:: "+bean);
//			try {
//				ReflectionUtil.findFieldFullDepth(bean, new FieldFilter() {
//					@Override
//					public boolean findAccept(Object ownerObject,Field ownerSubField) {
//						Object o = getField(ownerObject, ownerSubField);
//						if(null!=o){
//							Class ibatisProxy 		= Proxy.class;
//							return ibatisProxy.isAssignableFrom(o.getClass());
//						}
//						return false;
//					}
//					
//					@Override
//					public Object setField(Object ownerObject, Field ownerSubField) {
//						Object o = getField(ownerObject, ownerSubField);
//						log.debug("BootServlet field :: "+ownerSubField);
//						log.debug("BootServlet before Object :: "+o);
//						if(null==o){
//							return null;
//						}
//						o = ProxyUtil.setProxy(ownerSubField.getType(), new PrivacyProxyMapper(o));
//						log.debug("BootServlet after Object :: "+o);
//						return o;
//					}
//				});
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		
//		////////////////초가값셋팅//////////////////////////////
//		log.debug("::::::::::BootServlet code load::::::::::::::::"); 
//		List<BootCodeVO> codeList;
//		try {
//			codeList = bootService.selectCode();
//		 for (int i = 0; i < codeList.size(); i++) {
//			 BootCodeVO code = codeList.get(i);
//			 bootManager.putCode(code.getCd(), code);
//		 }
//		} catch (Exception e) {e.printStackTrace();}
//		log.debug("::::::::::BootServlet menu load::::::::::::::::");
//		List<BootMenuVO> menuList = null;
//		try{
//			menuList = bootServiceImpl.selectMenu();
//			for (int i = 0; i < menuList.size(); i++) {
//				BootMenuVO menu = menuList.get(i);
//				bootManager.putMenu(menu.getMnu_dir(), menu);
//			}
//		 }catch(Exception e){e.printStackTrace();}
		 
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
