package com.khh.security.spring;

import com.khh.login.vo.LoginRoleAuthVVO;
import com.omnicns.web.spring.security.GrantedObjAuthority;

public class CustomGrantedObjAuthority extends GrantedObjAuthority<LoginRoleAuthVVO> {
	private static final long serialVersionUID = 3792653664216159161L;
	private LoginRoleAuthVVO auth; 
	public CustomGrantedObjAuthority(String role) {
		super(role);
	}
	public CustomGrantedObjAuthority(String role, LoginRoleAuthVVO roleAuth) {
		super(role,roleAuth);
	}

	

}
