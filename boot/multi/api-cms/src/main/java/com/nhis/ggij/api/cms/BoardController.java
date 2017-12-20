package com.nhis.ggij.api.cms;

import com.nhis.ggij.api.cms.domain.primary.Board;
import com.nhis.ggij.api.cms.domain.inner.Board2;
import com.nhis.ggij.api.cms.domain.inner.TBGGIJ20;
import com.nhis.ggij.api.cms.repository.inner.Board2Repository;
import com.nhis.ggij.api.cms.repository.inner.TBGGIJ20Repository;
import com.nhis.ggij.api.cms.repository.primary.BoardRepository;
import com.nhis.ggij.api.cms.vo.Where;
import com.nhis.ggij.api.core.base.ControllerBase;
import com.nhis.ggij.api.core.security.HttpBodyEncryptor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/board")
@Slf4j
public class BoardController extends ControllerBase {


	@Autowired BoardRepository pboard;
	@Autowired Board2Repository iboard;
	@Autowired TBGGIJ20Repository tbggij20Repository;

	@Autowired
	@Qualifier("primarySessionFactory")
	SessionFactory primarySessionFactory;

	@Autowired
	@Qualifier("innerSessionFactory")
	SessionFactory userSessionFactory;

	@Autowired
	HttpBodyEncryptor httpBodyEncryptor;

	@Autowired MessageSourceAccessor messageSourceAccessor;
	@PostMapping(path = "/get")
//	@RequestMapping(name="/get", method=RequestMethod.POST)
//	public BoardWhere get(@Valid @ModelAttribute("CounselPulseSearchVO") BoardWhere board, HttpServletRequest request){
	public Where get(@Valid @RequestBody Where board, HttpServletRequest request){
		log.debug("----msg-->"+messageSourceAccessor.getMessage("NotEmpty"));
		int i = 5/board.getNumber();
		board.setNumber(i);
		return board;
	}


	@GetMapping(path = "/list")
	public Board list(){
		Board board = new Board();
		board.setId(pId++);
//		board.setTitle("a");
		board.setContent("김현아");
		return board;
	}
	@GetMapping(path = "/admin")
	public String admin(){
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



	@GetMapping(path = "/select")
	public List<TBGGIJ20> select(){
		return tbggij20Repository.findAll();
	}


}
