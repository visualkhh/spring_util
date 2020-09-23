package com.genome.dx.wcore.config.security;

import com.genome.dx.wcore.domain.security.UserDetails;
import com.genome.dx.core.repository.AdmRepository;
import com.genome.dx.wcore.repository.security.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
    private MessageSourceAccessor messageSource;

	@Autowired
    private UserDetailsRepository userDetailsRepository;

	@Autowired
    private AdmRepository admRepository;

	@Autowired
    private EntityManager entityManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = userDetailsRepository.findByAdmLginId(username);
		if (null == userDetails) {
			throw new UsernameNotFoundException(("msg.error.login.fail"));
		} else {
			System.out.println(userDetails.getAuthDetails().size()+"");
		}
		return userDetails;
	}
	public UserDetails save(UserDetails userDetails) throws UsernameNotFoundException {
		UserDetails i =userDetailsRepository.save(userDetails);
//		if (null == userDetails) {
//			throw new UsernameNotFoundException(messageSource.getMessage("msg.error.login.fail"));
//		}
		return i;
	}

	/*
	pringBoot에서의 설정 및 Transaction 속성
	SpringBoot를 이용하면 전통적인 Spring의 방식과는 다르게 기본적으로 Transaction 관리를 위한 별도 설정(XML, Javaconfig) 없이 트랜잭션 어노테이션사용이 바로 가능하다.  (CGLIB Proxy Default)
	아래는 일반적인 트랜잭션의 속성이다.

	트랜잭션의 속성 1 (전파 – Propagation)
	트랜잭션의 경계를 정의하며 시작 방법을 결정하는 속성이다.   실제 Business, 혹은 비기능적 요구사항에 대한 개발을 진행하다보면 트랜잭션 경계를 가져가야 할 경우가 있다.  이를 태면, 메인 로직이 진행되고 메인 로직은 트랜잭션으로 묶어야 할 여러 Action을 포함한다고 하자.  그리고 이와 동시에 어떤 Repository(DB)에 메인 로직이 진행을 알리는 처리LOG를 적재(Insert)하고자 한다면,  이 처리LOG는 메인 로직이 중간에 어떤 이유에 있어서든(의도한 Exception 이거나 아니더라도) 중단 되더라도 메인 로직의 commit or rollback과 관계 없이 적재가 필요할 수 있다.  그럴경우 메인 로직과는 별개의 트랜잭션이 시작되어야 할 것이다.  (새로운 트랜잭션 경계의 시작)

	> REQUIRED  :  Defualt 속성이다.  트랜잭션의 시작 시점에 이미 진행중인 트랜잭션이 있으면 해당 트랜잭션에 참여하며 없을 경우 새로운 트랜잭션을 시작한다.
	> REQUIRES_NEW  :  항상 새로운 트랜잭션을 시작한다.   이미 진행중인 트랜잭션이 있다면 잠깐 보류되고 해당 트랜잭션 경계가 종료 된 후 다시 시작된다.
	> SUPPORT  :  이미 진행중인 트랜잭션이 있으면 참여하고, 없을 경우 Non-transaction으로 시작한다.
	> MANDATORY  :  이미 진행중인 트랜잭션이 반드시 있어야만 해당 경계를 넘어 시작할 수 있다.  없을 경우 Exception을 발생시킨다.
	> NOT_SUPPORT  :  Non-transaction으로 시작하며, 이미 진행중인 트랜잭션이 있으면 잠시 보류시킨다.
	> NEVER  :  Non-transaction 상태에서만 해당 경계를 넘어갈 수 있다.  이미 진행중인 트랜잭션(parent)가 있으면 예외를 발생시킨다.
	> NESTED  :  이미 진행중인 트랜잭션(parent)이 있을 경우 중첩트랜잭션을 생성하여 시작한다.  생성된 중첩트랜잭션은 (parent)가 rollback되면 함께 되지만, 해당 트랜잭션안에서의 Commit/Rollback은 (parent)에 영향을 주지 않는다.  이미 진행중인 트랜잭션이 없을 경우 새로운 트랜잭션을 만든다.

	트랜잭션의 속성 2(격리수준-Isolation)
	동시에 여러 Thread 별로 여러 트랜잭션이 진행될 때, 결과를 타 트랜잭션에 어떻게 노출할 것인지를 결정한다.

	> DEFAULT  : 사용하는 데이터 액세스 기술 또는 DB드라이버의 디폴트 설정을 따른다.  대부분의 DB는 READ_COMMITTED를 기본으로 한다.
	> READ_UNCOMMITTED  :  가장 낮은 격리수준.   트랜잭션의 종료에 따른 Commit / Rollback이 이루어지지 않아도 해당 변화가 다른 트랜잭션에 노출된다. (성능은 가장 좋음)
	> READ_COMMITTED  :  Commit / Rollback 되지 않은 정보는 다른 트랜잭션에서 읽을 수 없다.  서로 다른 트랜잭션이 동시에 Row를 핸들링하는 경우 의도치 않은 결과가 올 수 있다.
	> REPEATABLE_READ  :  하나의 트랜잭션이 읽은 로우를 다른 트랜잭션이 수정하는 것을 막아준다.  하지만 New 로우 추가는 제한 않는다. SELECT 후 타 트랜잭션에서 추가로 결과가 달라질수도 있다.
	> SERIALIZABLE  :  가장 강력한 격리수준이다.  순차적으로 진행해서 여러 트랜잭션이 동시에 같은 테이블 정보를 엑세스하지 못하게 한다.  (성능은 가장 낮다)

	트랜잭션의 속성 3(Rollback, Commit 관련)
	선언적 트랜잭션에서는 트랜잭션 경계지점(Method)을 기점으로 다시 제어가 돌아왔을때 (해당 Method가 끝날때), 아무 Exception 발생없이 끝나면 Commit, RuntimeException이 throw 되면 Rollback 한다.   일반 Exception(checked)의 경우 commit 이  DEFAULT 이다.

	> rollback 조건-Exception 변경 (rollbackFor) : rollback이 이루어지는 Exception(throw된)을 변경 한다.
	> commit 조건-Exception 변경 (noRollbackFor) : 반대로 commit (noRollback) 이 이루어지는 Exception을 추가 해줄수도 있다.

	트랜잭션의 속성 4(읽기전용 – readOnly)
	트랜잭션을 읽기전용으로 설정한다.  성능최적화, 혹은 의도적으로 조회만을 위한 트랜잭션을 만들기위해 사용할 수 있다.
	 */
//	@Transactional(propagation = Propagation.REQUIRED )
	public void pulseLginFailCntByLginId(String admLginId){
		userDetailsRepository.pulseLginFailCntByLginId(admLginId);
	}
}
