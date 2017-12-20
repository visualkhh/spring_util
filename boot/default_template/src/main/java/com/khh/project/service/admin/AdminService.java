package com.khh.project.service.admin;

import com.khh.project.service.admin.domain.Authority;
import com.khh.project.service.admin.domain.User;
import com.khh.project.service.admin.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@javax.transaction.Transactional(rollbackOn = { Exception.class })
@Slf4j
public class AdminService {


    @Autowired
    public SessionFactory sessionFactory;

    @Autowired
    public UserRepository userRepository;


    String home() {
        return "admin main";
    }



    public String save() {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        User u = new User();
        u.setUsername("zzz");
        u.setPassword("zzz");

        Authority a = new Authority();
        a.setUsername("zzz");
        a.setAuthority("vvv");
        List l= new ArrayList<>();
        l.add(a);
        u.setAuthorities(l);

        currentSession.save(u);

        currentSession.flush();
        currentSession.clear();
        currentSession.getTransaction().commit();

        return "ok";
    }



    public List<User> list() {
        Session currentSession = sessionFactory.getCurrentSession();

//        currentSession.beginTransaction();
        Criteria crit = currentSession.createCriteria(User.class);
//        crit.setFetchMode("authorities", FetchMode.JOIN); //강제로 지연로딩 사용안함
        //lazy로되어있다면 아래처럼 DISTINCT_ROOT_ENTITY를하던가  트랜젝션을 닫지말고 그냥 흘려보네 그냥 지연로딩을 사용해도된다. 지연로딩 사용하면 각각 쿼리날라간다
        ////crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//조인 쿼리가 날라간다
        //위처럼할수도 있지만 엔티티에도 넣어도된다 @Fetch(FetchMode.SELECT) 이것도 각각 날라간다
        List<User> list = crit.list();
//        currentSession.flush();
//        currentSession.clear();
//        currentSession.getTransaction().commit();

//        List<User> list = (List<User>) crit.uniqueResult();
//        currentSession.getTransaction().commit();
        // currentSession.close();
        return list;
    }
    public List<User> list2() {
        List<User> list = userRepository.findAll();
//        list.forEach(at->{
//            log.debug(at.toString());
//        });
        return list;
    }


    public User list3() {
        User list = userRepository.findByUsername("khh");
//        list.forEach(at->{
//            log.debug(at.toString());
//        });
        return list;
    }





}
