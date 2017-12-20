package com.khh.login.service;

import java.util.List;

import javax.transaction.Transactional;

import com.khh.code.Code;
import com.khh.login.vo.LoginRoleAuthVVO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omnicns.java.db.hibernate.Hibernater;
import com.khh.login.vo.LoginUserVO;


@Service("LoginService")
@Transactional(rollbackOn = { Exception.class })
public class LoginService {
	@Autowired
	Hibernater hibernate;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public LoginUserVO getLoginUsers(String id) throws Exception{
		Criteria crit = hibernate.createCriteria(LoginUserVO.class).add(Restrictions.eq("login_id", id)).add(Restrictions.eq("deleted", "N"));
		crit.setFetchMode("checkCard.codes", FetchMode.JOIN);
		LoginUserVO l =null;
		try{
		 l = (LoginUserVO) crit.uniqueResult();
		}catch (org.hibernate.ObjectNotFoundException e) {
			 throw new Exception("인증에 필요한정보가 제대로 설정되지 않았습니다.");
		}
		return l;
	}
	
	//https://docs.jboss.org/hibernate/core/3.3/reference/ko-KR/html/querycriteria.html
	public List<LoginRoleAuthVVO> getRoleAuth(int operator_id){
		List<LoginRoleAuthVVO> list = null;
		
		Criteria crit = hibernate.createCriteria(LoginRoleAuthVVO.class);
		crit.add(Restrictions.eq("operator_id", operator_id));
		crit.createAlias("right", "r");
		crit.add(Restrictions.ne("r.type", "D"));
		crit.addOrder(Order.asc("group_id"));
		crit.addOrder(Order.asc("right_id"));
		list = crit.list();		
		
		return list;
	}
	
	

	public Code updateLogin(LoginUserVO userInfo) {
		hibernate.save(userInfo);
		hibernate.flushClear();
		return Code.SUCCESS;
		
	}
}
