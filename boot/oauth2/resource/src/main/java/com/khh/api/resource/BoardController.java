package com.khh.api.resource;

import com.khh.api.resource.domain.inner.Board2;
import com.khh.api.resource.domain.primary.Board;
import com.khh.api.resource.repository.primary.BoardRepository;
import com.omnicns.web.spring.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController extends Controller{


	@Autowired
	BoardRepository pboard;
	@Autowired
	com.khh.api.resource.repository.inner.Board2Repository iboard;


	@Autowired
	@Qualifier("primarySessionFactory")
	SessionFactory primarySessionFactory;

	@Autowired
	@Qualifier("userSessionFactory")
	SessionFactory userSessionFactory;


	@GetMapping(path = "/list")
	public Board list(){
		SecurityUtil.getGrantedAuthorityList().forEach(at->{
			log.debug("-----"+at);
		});
		Board board = new Board();
		board.setId(pId++);
		board.setTitle("a");
		return board;
	}
	@GetMapping(path = "/admin")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PreAuthorize("#oauth2.hasScope('babo')")
	public String admin(){
		SecurityUtil.getGrantedAuthorityList().forEach(at->{
			log.debug("-----"+at);
		});
		return "ok";
	}



	long pId=1;
	long iId=100;
	@GetMapping(path = "/pinsert")
	public String pinsert(){
		Board board = new Board();
		board.setId(pId++);
		board.setTitle("a");
		pboard.save(board);
		return "o1k";
	}


	@GetMapping(path = "/iinsert")
	public String iinsert(){
		Board2 board = new Board2();
		board.setId(iId++);
		board.setTitle("b");
		iboard.save(board);
		return "ok2";
	}

	//hibernate
	@GetMapping(path = "/pselect")
	public List<Board> pselect(){
		Session currentSession = primarySessionFactory.getCurrentSession();
		currentSession.beginTransaction();
		Criteria crit = currentSession.createCriteria(Board.class);
		List<Board> list = crit.list();
		currentSession.getTransaction().commit();
//        currentSession.close();
		return list;
	}


	@GetMapping(path = "/iselect")
	public List<Board2> iselect(){
		Session currentSession = userSessionFactory.getCurrentSession();
		currentSession.beginTransaction();
		Criteria crit = currentSession.createCriteria(Board2.class);
		List<Board2> list = crit.list();
		currentSession.getTransaction().commit();
//        currentSession.close();
		return list;
	}
	@GetMapping(path = "/oselect")
	public List<Board> oselect(){
		Session currentSession = userSessionFactory.getCurrentSession();
		currentSession.beginTransaction();
		Criteria crit = currentSession.createCriteria(Board.class);
		List<Board> list = crit.list();
		currentSession.getTransaction().commit();
//        currentSession.close();
		return list;
	}



}
