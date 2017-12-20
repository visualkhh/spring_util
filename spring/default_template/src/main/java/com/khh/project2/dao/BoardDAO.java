package com.khh.project2.dao;

import java.util.HashMap;
import java.util.List;

import javax.script.ScriptException;
import javax.transaction.Transactional;

import com.khh.DefaultDAO;
import com.khh.project2.vo.BoardVO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khh.project2.vo.BoardNormalVO;
import com.khh.project2.vo.BoardUserIDVO;
import com.khh.project2.vo.BoardWriteVO;

@Repository("BoardDAO")
@Transactional(rollbackOn = { Exception.class })
public class BoardDAO extends DefaultDAO {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public SessionFactory  sessionFactory;
	
//http://what-when-how.com/hibernate/advanced-query-options-hibernate/
	public List<BoardVO> getContentBoards(String like) {
//		JPAQuery query = new JPAQuery(sessionFactory.evictQueries());
		Session s = sessionFactory.openSession(); 
		Criteria crit = s.createCriteria(BoardVO.class);
		if(null!=like){
			crit.add(Restrictions.like("content", "%"+like+"%"));
		}
		List<BoardVO> l =  crit.list();
		s.close();
		return l;
	}

	//http://www.tutorialspoint.com/hibernate/hibernate_native_sql.htm
	public List<BoardWriteVO> getWriteBoards(String like) {
		Session s = sessionFactory.openSession();
	    StringBuilder sql = new StringBuilder();
	    sql.append(" SELECT");
	    sql.append(" USER_ID, USER_PWD, USER_NM, ROLE, SEQ, CONTENT, PWD, REGDT");
	    sql.append(" FROM T_USER A, T_BOARD B");
	    sql.append(" WHERE (ISNULL(:like, '1') = '1' or A.USER_NM LIKE :like)"); 
	    sql.append(" AND A.USER_ID = B.USER_ID");
//	    SQLQuery q = s.createSQLQuery(sql.toString());
	    SQLQuery q = s.createSQLQuery(sql.toString());
//	    q.addScalar("name", StringType.INSTANCE); 
//	    q.addScalar("sex", DateType.INSTANCE); 
	    //q.setResultTransformer(Transformers.aliasToBean(BoardWriteVO.class));
	  	q.setParameter("like", like!=null?"%"+like+"%":like);
	    q.addEntity(BoardWriteVO.class);
    	List<BoardWriteVO> l = q.list();
//    	for(Object object : l)
//        {
//           Map row = (Map)object;
//           System.out.print("user_id: " + row.get("user_id")); 
//           System.out.println(", USER_PWD: " + row.get("user_pwd")); 
//        }
//    	List<BoardWriteVO> l = q.list();
    	s.close();
		return l;
	}
	
	
	
	public List<BoardUserIDVO> getIDBoards(String like) {
		Session s = sessionFactory.openSession();
		Query q = s.getNamedQuery("getBoardById");
		q.setParameter("like", like!=null?"%"+like+"%":like);
		List<BoardUserIDVO> l = q.list();
		s.close();
		return l;
	}
	
	public List<BoardNormalVO> getNormalBoards3(String like) throws ScriptException{
		HashMap<String,String> where = new HashMap<String,String>();
		where.put("like", like);
		Session s = sessionFactory.openSession();
		List<BoardNormalVO> data = null;
		//data = executeNamedGQuerys(s, "getGBoard", where, BoardNormalVO.class);
		s.close();
		return data;
	}
	public List<BoardNormalVO> getNormalBoards2(String like){
		HashMap<String,String> where = new HashMap<String,String>();
		where.put("like", like);
		//return executeNamedQuerys("getBoard", where, BoardNormalVO.class);
		return null;
	}
	public List<BoardNormalVO> getNormalBoards(String like){
		Session s = sessionFactory.openSession();
		SQLQuery q = (SQLQuery)s.getNamedQuery("getBoard");
//	    q.addScalar("name", StringType.INSTANCE); 
//	    q.addScalar("sex", DateType.INSTANCE); 
	    q.setParameter("like", like!=null?"%"+like+"%":like);
//	    q.setProjection(Projections.property("name").as("name"));
	   // List<BoardNormalVO> l2 = q.list();
	    q.setResultTransformer(Transformers.aliasToBean(BoardNormalVO.class));
	    q.addScalar("user_id");
	    q.addScalar("user_pwd");
	    q.addScalar("user_nm");
	    q.addScalar("role");
	    q.addScalar("seq");
	    q.addScalar("content");
	    q.addScalar("pwd");
	    q.addScalar("regdt");
    	List<BoardNormalVO> l = q.list();
    	
//    	for(Object object : l)
//        {
//           Map row = (Map)object;
//           System.out.print("user_id: " + row.get("user_id")); 
//           System.out.println(", USER_PWD: " + row.get("user_pwd")); 
//        }
//    	List<BoardWriteVO> l = q.list();
    	s.close();
		return l;
	}
	
	
	//http://howtodoinjava.com/hibernate/hibernate-insert-query-tutorial/
	public Integer setEntityBoard(BoardVO bvo) {
		Session s = sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		//bvo.setRegdt(new Date());
		Integer l = (Integer) s.save(bvo);
		//s.getTransaction().commit(); //db반영
		tx.commit();
//		if (!tx.wasCommitted())
//		    tx.commit();
		//session.update(s);
		s.close();
		return l;
	}
	public Integer setNamedBoard(BoardVO bvo) {
//		int l = executeNamedUpdate("pushBoard",bvo);
		int l=0;
		return l;
	}

}
