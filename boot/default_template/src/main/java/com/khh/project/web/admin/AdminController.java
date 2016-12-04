package com.khh.project.web.admin;

import com.khh.project.web.admin.domain.User;
import com.khh.project.web.admin.repository.UserRepository;
import com.omnicns.java.db.hibernate.Hibernater;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Transactional
@javax.transaction.Transactional(rollbackOn = { Exception.class })
@Slf4j
public class AdminController {


    @Autowired
    public SessionFactory sessionFactory;

    @Autowired
    public UserRepository userRepository;

    @RequestMapping({"","/"})
    @ResponseBody
    String home() {
        return "admin main";
    }


    @RequestMapping("/list")
    @ResponseBody
    List<User> list() {
        Session currentSession = sessionFactory.getCurrentSession();
//        currentSession.beginTransaction();
        Criteria crit = currentSession.createCriteria(User.class);
//        crit.setFetchMode("authorities", FetchMode.JOIN); //강제로 지연로딩 사용안함
        //lazy로되어있다면 아래처럼 DISTINCT_ROOT_ENTITY를하던가  트랜젝션을 닫지말고 그냥 흘려보네 그냥 지연로딩을 사용해도된다. 지연로딩 사용하면 각각 쿼리날라간다
        ////crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//조인 쿼리가 날라간다
        //위처럼할수도 있지만 엔티티에도 넣어도된다 @Fetch(FetchMode.SELECT) 이것도 각각 날라간다
        List<User> list = crit.list();
//        List<User> list = (List<User>) crit.uniqueResult();
//        currentSession.getTransaction().commit();
        // currentSession.close();
        return list;
    }

    @RequestMapping("/list2")
    @ResponseBody
//    @Transactional
    List<User> list2() {
        List<User> list = userRepository.findAll();
//        list.forEach(at->{
//            log.debug(at.toString());
//        });
        return list;
    }
    @RequestMapping("/list3")
    @ResponseBody
//    @Transactional
    User list3() {
        User list = userRepository.findByUsername("khh");
//        list.forEach(at->{
//            log.debug(at.toString());
//        });
        return list;
    }





}
