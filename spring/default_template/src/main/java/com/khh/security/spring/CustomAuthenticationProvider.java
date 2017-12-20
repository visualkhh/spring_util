package com.khh.security.spring;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.khh.code.Code;
import com.khh.config.ConfigManager;
import com.khh.login.service.LoginService;
import com.khh.login.vo.LoginRoleAuthRightVO;
import com.khh.login.vo.LoginRoleAuthVVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.omnicns.java.date.DateUtil;
import com.omnicns.java.random.RandomUtil;
import com.omnicns.java.string.StringUtil;
import com.khh.login.vo.LoginUserVO;
import com.omnicns.web.spring.security.AuthenticationProvider;

public class CustomAuthenticationProvider extends AuthenticationProvider<LoginUserVO> {
	@Autowired
    private CustomPasswordEncoder customPasswordEncoder;
	@Autowired
	private LoginService loginService;
	@Autowired
	private ConfigManager configMng;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public LoginUserVO findUserById(Authentication authentication) throws Exception {
		WebAuthenticationDetails detail = (WebAuthenticationDetails) authentication.getDetails();
		String remoteIP = detail.getRemoteAddress();
		String user_id = (String)authentication.getPrincipal();		
		String user_pw = (String)authentication.getCredentials();
		LoginUserVO users= loginService.getLoginUsers(user_id);
		log.info("Login try ip : -> "+detail.getRemoteAddress()+" input id("+user_id+") info:"+users);
		if(null==users ){
			throw new UsernameNotFoundException("로그인 정보가 올바르지 않습니다.");
		}
		
		
		
		//////ipcheck
		//<!--OP_LEVLE=4    MASTER권한자들은 아래 IP에서만 접근이 가능하다. 또한 MASTER권한자는   실패 카운터 및 LOCK처리를 하지 않는다. 아래는 MASTER접근 가능IP REGEX이다-->
		if(new Integer(Code.CODE_OP_LEVEL_MASTER.cd()).equals(users.getOp_level())){
			if(!StringUtil.isMatches(remoteIP, configMng.getParam("master_ip_regex"))){
				throw new UsernameNotFoundException("허용되지 않은 IP입니다. 운영자에게 문의해주세요.");
			}
		}
		
		if(null!=users.getIp() && null!=users.getIp().getIp_number() && users.getIp().getIp_number().length()>0){
			String ip = users.getIp().getIp_number();
			String[] ips = ip.split(",");
			boolean authIP = false;
			for(String atIP : ips){
				atIP = atIP.trim();
				atIP = atIP.replaceAll("\\.", "\\\\\\.");
				atIP = atIP.replaceAll("\\*", "\\.*");
				atIP = atIP.replaceAll("\\+", "\\.+");
				if(StringUtil.isMatches(remoteIP, atIP)){
					authIP = true;
					break;
				}
			}
			if(authIP==false){
				throw new UsernameNotFoundException("허용되지 않은 IP입니다. 운영자에게 문의해주세요.");
			}
		}
		
		
		
		

		
		////after process
		//카드 체크
		if(null == users.getCard_no()){ 
			users.setEnabled(true);
		}else{
			users.setEnabled(false); //카드번호가 있으면 카드 체크해야된다     로그인 로직 한단계 더있다.
			if(null!=users.getCheckCard()){
				users.getCheckCard().refreshCheckCodeId();
			};
		} 
		
		//pwd expired 체크
		if(null==users.getLast_pwd_update()){
			users.setPwdNonExpired(false);
		}else{
			Date atDate = DateUtil.getDate("yyyyMMddHHmmss",users.getLast_pwd_update()); 
			Date mDate = DateUtil.modifyDate(atDate, Calendar.DATE, Integer.parseInt(configMng.getParam("login_pwd_expired_day")));
			if(mDate.getTime()<=new Date().getTime()){
				users.setPwdNonExpired(false);
			}else{
				users.setPwdNonExpired(true);
			}
		}
		
		return users;
	}

	@Override
	public List<? extends CustomGrantedObjAuthority> getPermission(LoginUserVO userInfo)  {
		
		List<LoginRoleAuthVVO> roleAuths = loginService.getRoleAuth(userInfo.getOperator_id());
		List<CustomGrantedObjAuthority> list = new ArrayList<CustomGrantedObjAuthority>();
		HashMap<String, LoginRoleAuthVVO> roleSet = new HashMap<String, LoginRoleAuthVVO>() ;
		for (int i = 0; null!=roleAuths && i < roleAuths.size(); i++) {
			LoginRoleAuthVVO at = roleAuths.get(i);
			list.add(new CustomGrantedObjAuthority(at.getRight().getCode(), at));//일반 메뉴
			roleSet.put(at.getRight_group().getCode(),at);
		}
		
		//롤자체를 넣는다. 권한있는자만 허용가능한 페이지 넣는다.
		LoginRoleAuthVVO auth = new LoginRoleAuthVVO();
		LoginRoleAuthRightVO right = new LoginRoleAuthRightVO();
		right.setUrl(configMng.getParam("auth_uri"));
		auth.setRight(right);
		list.add(new CustomGrantedObjAuthority("ROLE_AUTH", auth)); //로그인된사람은 이롤을 무조건 갖는다.
		
		//ROLE은  prefix가 ROLE_ 이여야한다. 그러면 access="hasRole('/board_R')" 또는 ROLE_/board..등으로찾을수 있다 
		//hasRole이라는 함수로 찾을수 있다.
		roleSet.entrySet().stream().forEach(at->{
			list.add(new CustomGrantedObjAuthority("ROLE_"+at.getKey(), at.getValue()));
		}); //그룹은 ROLE
		
		//user session 에 권한셋팅
		userInfo.setAuthorities(list);
		return list;
	}

	@Override
	public boolean isValidate(Authentication authentication, LoginUserVO getUserInfo) {
		
		//Lock Check
		if(!getUserInfo.isAccountNonLocked()){
			throw new UsernameNotFoundException("계정이 잠겼습니다. 운영자에게 문의해주세요.");
		}
		
		String user_id = (String)authentication.getPrincipal();		
		String user_pw = (String)authentication.getCredentials();
		customPasswordEncoder.setKey(getUserInfo.getIndv_key());
		return customPasswordEncoder.matches(user_pw, getUserInfo.getLogin_pw());
	}
	

	@Override	// 로그인 성공 했을시..
	public void succse(Authentication authentication, LoginUserVO userInfo) throws Exception {
		WebAuthenticationDetails detail = (WebAuthenticationDetails) authentication.getDetails();
		userInfo.setLast_login_ip(detail.getRemoteAddress());
		userInfo.setLogin_fail(0);
		userInfo.setLocked("N");
		userInfo.setLast_login_session(RandomUtil.getRandomString(100));
		userInfo.setLast_login_date(DateUtil.getDate("yyyyMMddHHmmss"));
		log.info("Login succse ip : -> "+detail.getRemoteAddress()+"  info:"+userInfo);
		/*
		 localhost에서 테스트 하는 경우 0:0:0:0:0:0:0:1 값으로 넘어 오는 경우가 있다.
		 이 값은 IPv6 에서 IPv4의 127.0.0.1 과 같은 값이다.
		 Tomcat으로  개발시 이게 문제가 되는 경우 vm arguments에  -Djava.net.preferIPv4Stack=true 값을 넣어 주면 된다.
		 */
//		log.debug("Login User_IP -> "+detail.getRemoteAddress());
//		log.debug("Login User_IP -> "+Content.getRequest().getRemoteHost());
//		log.debug("Login User_IP -> "+RequestUtil.getRemoteAddr(Content.getRequest()));
//		log.debug("Login User_IP -> "+Content.getRequest().getRemoteAddr());
		loginService.updateLogin(userInfo);
	}

	@Override
	public void fail(Authentication authentication, LoginUserVO userInfo) throws Exception {
		WebAuthenticationDetails detail = (WebAuthenticationDetails) authentication.getDetails();
		
		//master이면  실패 회수를 늘리지 않는다., LOCK처리 하지 않는다
		if(new Integer(Code.CODE_OP_LEVEL_MASTER.cd()).equals(userInfo.getOp_level())){
			userInfo.setLogin_fail(0); 
			userInfo.setLocked("N");
		}else{
			userInfo.setLogin_fail(userInfo.getLogin_fail()+1);
			if(userInfo.getLogin_fail()>= Integer.parseInt(configMng.getParam("login_fail_max"))){
				userInfo.setLocked("Y");
			}
		}
		
		log.info("Login fail ip : -> "+detail.getRemoteAddress()+"  info:"+userInfo);
		loginService.updateLogin(userInfo);
	}

	

}
