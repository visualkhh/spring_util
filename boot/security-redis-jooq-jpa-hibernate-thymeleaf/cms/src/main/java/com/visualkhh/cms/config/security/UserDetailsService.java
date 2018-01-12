package com.visualkhh.cms.config.security;

import com.visualkhh.cms.domain.security.UserDetails;
import com.visualkhh.cms.repository.security.UserDetailsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
	@Autowired private MessageSourceAccessor messageSource;
	@Autowired private UserDetailsRepository userDetailsRepository;
	@Autowired private SessionFactory sessionFactory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userDetailsRepository.findByAdmLginId(username);
		if (null == userDetails) {
			throw new UsernameNotFoundException(messageSource.getMessage("msg.error.login.fail"));
		}
		return userDetails;
	}

	//	@Transactional
	public Integer pulseLginFailCntByLginId(String lginId){
		Session s = ((SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory)).getSession();
		return s.getNamedQuery("UserDetails.pulseLginFailCntByLginId").setParameter("admLginId",lginId).executeUpdate();
		//		return userDetailsRepository.pulseLginFailCntByLginId(lginId);
	}
}