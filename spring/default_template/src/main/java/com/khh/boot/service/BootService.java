package com.khh.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import com.khh.boot.vo.BootRightGroupJoinVO;
import com.khh.boot.vo.BootRightVO;
import com.khh.project.msg.vo.MsgTemplateVVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.khh.boot.vo.BootCodeVO;
import com.khh.boot.vo.BootMenuVO;
import com.khh.project.area.vo.AreaVO;
import com.khh.project.msg.vo.MsgClassVO;
import com.omnicns.java.db.hibernate.Hibernater;


@Service("BootService")
@Transactional(rollbackOn = { Exception.class })
public class BootService extends DefaultService{
	@Autowired
	Hibernater hibernate;
	public List<BootMenuVO> getMenu(){
		Criteria crit = hibernate.createCriteria(BootMenuVO.class).add(Restrictions.eq("type", "M")).addOrder(Order.asc("right_id"));
		List<BootMenuVO> list = crit.list();
		return list;
	}
	/*http://www.mkyong.com/hibernate/hibernate-parameter-binding-examples/
	 String hql = "from Stock s where s.stockCode = :stockCode";
	List result = session.createQuery(hql)
	.setParameter("stockCode", "7277")
	.list();
	 */
	//http://www.journaldev.com/2954/hibernate-query-language-hql-example-tutorial
	public List<BootCodeVO> selectCode(){
		Criteria crit = hibernate.createCriteria(BootCodeVO.class);
		List list = crit.list();
		return list;
	}
	public List<BootCodeVO> selectCodeWhere(){
		Criteria crit = hibernate.createCriteria(BootCodeVO.class).add(Restrictions.eq("cd", "CODE100"));
		return crit.list();
	};
	public List<BootCodeVO> selectCodeQuery1(){
		
		/*
		 * Query query = session.createQuery("from Resource where emplteam = :team");
			query.setParameter("team", "Team1");
			List list = query.list();
		 //Get Employee with id
        query = session.createQuery("from Employee where id= :id");
        query.setLong("id", 3);
        Employee emp = (Employee) query.uniqueResult();
        System.out.println("Employee Name="+emp.getName()+", City="+emp.getAddress().getCity());
         
		 */
		List<BootCodeVO> personList = hibernate.createQuery("from BootCodeVO").list();
		return personList;
	};
	public List<BootCodeVO> selectCodeQuery2(){
		/*
		 Query q = sessionFactory.getCurrentSession().createQuery("from Contract c where :fullName = concat(c.firstname, ' ', c.lastname)");
	     q.setString("fullName", fullName);
	     return q.list();}
		 */
		hibernate.getNamedQuery("query");
		List<BootCodeVO> personList = hibernate.createSQLQuery("select * from BootCodeVO").list();
		return personList;
	};
	public Long selectCodeQueryCnt(){
		/*
			session session = getSessionFactory().beginSession();
		    Transaction transaction = null;
		
		    StringBuilder sql = new StringBuilder();
		
		    sql.append("SELECT DISTINCT B.COLUMN_NAME ");
		    sql.append("FROM ALL_CONSTRAINTS A, ALL_CONS_COLUMNS B, ARCTBL C ");
		    sql.append("WHERE A.TABLE_NAME = :TABLE_NAME AND A.CONSTRAINT_TYPE = 'P' "); 
		    sql.append("AND A.CONSTRAINT_NAME = B.CONSTRAINT_NAME ");
		    sql.append("AND C.TABLENAME=A.TABLE_NAME AND A.TABLE_NAME=B.TABLE_NAME ");
		
		    try{
		
		    **listOfValues = session.createSQLQuery(sql.toString()).setParameter("TABLE_NAME", table).list();**
		    //Change this to code below
		String returnObjByHibernate = session.createSQLQuery(//Hibernate method)
		    transaction.commit;
		    }
		    catch (Exception e){
		    //Logic here
		 */
		Long count = (Long)hibernate.createQuery("select COUNT(*) from BootCodeVO").uniqueResult();
		return count;
		/*
		 String hql = "select count(name) from Product";
		Query query = session.createQuery(hql);
		List listResult = query.list();
		Number number = (Number) listResult.get(0);
		System.out.println(number.intValue());
		 */
	};
	public Integer selectCodeCnt(){
		Criteria crit = hibernate.createCriteria(BootCodeVO.class);
		Integer count =  crit.list().size();
		return count;
	};
	
	
	public List<AreaVO> getArea() {
		List<AreaVO> list = null;		
		Criteria crit = hibernate.createCriteria(AreaVO.class);
		crit.addOrder(Order.asc("order_id1")).addOrder(Order.asc("order_id2"));
		list = crit.list();
		return list;
	};
	public List<MsgClassVO> getMsgClass() {
		List<MsgClassVO> list = null;
		Criteria crit = hibernate.createCriteria(MsgClassVO.class);
		crit.addOrder(Order.asc("class1_order")).addOrder(Order.asc("class2_order"));
		list = crit.list();
		return list;
	};
	public List<BootRightGroupJoinVO> getRightGroup() {
		List<BootRightGroupJoinVO> list = null;
		Criteria crit = hibernate.createCriteria(BootRightGroupJoinVO.class); 
		crit.addOrder(Order.asc("sort"));
		list = crit.list();
		for(int i=0; null!=list && i<list.size(); i++){
			BootRightGroupJoinVO at = list.get(i);
			at.setRights(hibernate.createCriteria(BootRightVO.class).add(Restrictions.eq("group_id",at.getGroup_id())).list());
		}
		return list;
	};

	public List<MsgTemplateVVO> getMsgTemplate() {
		List<MsgTemplateVVO> list = null;
		Criteria crit = hibernate.createCriteria(MsgTemplateVVO.class);
		crit.addOrder(Order.asc("class_id")).addOrder(Order.asc("template_id"));
		list = crit.list();
		return list;
	};
}
