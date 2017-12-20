package com.khh.sign.service;

import javax.transaction.Transactional;

import com.khh.sign.vo.OperatorLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.omnicns.java.db.hibernate.Hibernater;
import com.khh.security.SecurityManager;


@Service("SignService")
@Transactional(rollbackOn = { Exception.class })
public class SignService extends DefaultService {
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	Hibernater hibernate;
	@Autowired
	SecurityManager securityMng;
	

	
	
	public void saveOperator(OperatorLogVO log){
		hibernate.flushClearBeginTransaction();
		hibernate.save(log);
		hibernate.flushClearCommit();
	}
	
}
