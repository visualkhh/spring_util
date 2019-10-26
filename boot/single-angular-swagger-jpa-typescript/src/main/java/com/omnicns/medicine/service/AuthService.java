package com.omnicns.medicine.service;

import com.omnicns.medicine.domain.Adm;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service @Slf4j
public class AuthService {

//	@Autowired private SessionFactory sessionFactory;
//	@Autowired private HibernateTransactionManager hibernateTransactionManager;
//	@Autowired
//	private LocalSessionFactoryBean localSessionFactoryBean;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Object getGG(){



//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<Adm> q = builder.createQuery(Adm.class);
//		Root<Adm> root = q.from(Adm.class);
//		q.select(root);
//		List<Adm> a = em.createQuery(q).getResultList();
//



//		hibernateTransactionManager.
//		SessionHolder gg = (SessionHolder)TransactionSynchronizationManager.getResource(sessionFactory);
//		Session s = hibernateTransactionManager.getSessionFactory().getCurrentSession();
		Session s = entityManager.unwrap(Session.class);
		

		//		log.info("--{} {}",sessionFactory,hibernateTransactionManager);
//		Session s = gg.getSession();
//		s.cri
//		s.beginTransaction();

		Adm adm = new Adm();
		adm.setAdmNm("김");
		s.save(adm);

		adm = new Adm();
		adm.setAdmNm("현");
		s.save(adm);


		adm = new Adm();
		adm.setAdmNm("하");
		s.save(adm);

		return "";
	}

}
