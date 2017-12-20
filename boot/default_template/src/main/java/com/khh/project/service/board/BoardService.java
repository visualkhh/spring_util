package com.khh.project.service.board;

import com.khh.project.service.board.domain.Board;
import com.khh.project.service.board.repository.BoardRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository repository;

    @Autowired
    public SessionFactory sessionFactory;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @RequestMapping({"","/"})
    @ResponseBody
    String index() {
        return "boar main";
    }



    @RequestMapping("/list")
    @ResponseBody
    public List<Board> list() {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();
        Criteria crit = currentSession.createCriteria(Board.class);
        //crit.add(Restrictions.like("contents", "%"+(null==dataTable.getSearchValue()?"":dataTable.getSearchValue())+"%"));
        //crit.addOrder(Order.desc("notice_id"));
        //crit.setFirstResult(dataTable.getStart());
        //crit.setMaxResults(dataTable.getLength());
        List<Board> list = crit.list();
        currentSession.getTransaction().commit();
//        currentSession.close();
        return list;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Board add(@RequestParam(name = "name",required = true) String name) {
        Board board = new Board();
        board.setAddr("asd");
        board.setName(name);
        board = repository.save(board);
        return board;
    }


//    public Session getSession() {
//        final HibernateEntityManagerFactory emFactory = (HibernateEntityManagerFactory) entityManagerFactory;
//        final SessionFactory sessionFactory = emFactory.getSessionFactory();
//        return sessionFactory.getCurrentSession(); //ERROR No CurrentSessionContext configured!
//
//        //This worked but I understand it to be BAD as spring should be managing open sessions.
//        //        try {
//        //            return sessionFactory.getCurrentSession();
//        //        } catch (Exception e) {
//        //            return sessionFactory.openSession();
//        //        }
//    }
}
