package com.khh.project.web.board;

import com.khh.project.web.board.domain.Board;
import com.khh.project.web.board.repository.BoardRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Controller
@RequestMapping(BoardController.PATH_ROOT)
//@Repository
//@Transactional
//@javax.transaction.Transactional
public class BoardController {

    public static final String PATH_ROOT    =   "/board";

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
    List<Board> list() {
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
    Board add(@RequestParam(name = "name",required = true) String name) {
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
