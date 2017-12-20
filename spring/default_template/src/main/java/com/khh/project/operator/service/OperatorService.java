package com.khh.project.operator.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.khh.DefaultService;
import com.khh.code.Code;
import com.khh.project.vo.DataTableVO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.project.operator.vo.CheckCardJoinVO;
import com.khh.project.operator.vo.OpRightJoinVO;
import com.khh.project.operator.vo.OpRightVO;
import com.khh.project.operator.vo.OperatorGrantVO;
import com.khh.project.operator.vo.OperatorReqRightJoinVO;
import com.khh.project.operator.vo.OperatorReqVO;
import com.khh.project.operator.vo.OperatorRightJoinVO;
import com.khh.project.operator.vo.OperatorSVO;
import com.khh.project.operator.vo.OperatorVO;
import com.omnicns.java.db.hibernate.Hibernater;
import com.khh.security.SecurityManager;
import com.omnicns.web.spring.exception.JsonErrorsException;

@Service("OperatorService")
@Transactional(rollbackOn = { Exception.class })
public class OperatorService extends DefaultService {
	Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	Hibernater hibernate;
	@Autowired
	SecurityManager securityMng;
	
//	public List<MsgClassVO> getMsgClassList() {
//		Criteria crit = hibernate.createCriteria(MsgClassVO.class).addOrder(Order.asc("class1_order")).addOrder(Order.asc("class2_order"));
//		List<MsgClassVO> list = crit.list();
//		return list;
//	}
//	public MsgClassVO getMsgClass(int class_id) {
//		Criteria crit = hibernate.createCriteria(MsgClassVO.class).add(Restrictions.eq("class_id", class_id));
//		MsgClassVO at = (MsgClassVO) crit.uniqueResult();
//		return at;
//	}
//
//	public List<MsgTemplateVVO> getMsgTemplateList() {
//		Criteria crit = hibernate.createCriteria(MsgTemplateVVO.class).addOrder(Order.asc("template_id")).addOrder(Order.asc("class_id"));
//		List<MsgTemplateVVO> list = crit.list();
//		return list;
//	}
	

	//////////DELETE------------
	//http://www.codejava.net/frameworks/hibernate/hibernate-basics-3-ways-to-delete-an-entity-from-the-datastore
	//https://github.com/ufal/lindat-repository-obsolete/blob/master/sources/utilities/src/main/java/cz/cuni/mff/ufal/lindat/utilities/hibernate/HibernateUtil.java
	
	// 운영자  deleted = Y 수정
	public Code deletedYOperator(Integer operator_id) {
		hibernate.flushClearBeginTransaction();
		OperatorVO operator = new OperatorVO(operator_id);
		hibernate.refresh(operator);//DB에서 가져옴 SELECT
		operator.setDeleted("Y");
		operator.setLocked("Y");
		operator.setPhone1("");
		operator.setPhone2("");
		operator.setPart("");
		operator.setPosition("");
		operator.setEmail("");
		hibernate.update(operator);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
		//return at;
	} 
	// 운영자 삭제
	public void deleteOperator(int operator_id) {
		hibernate.flushClearBeginTransaction();
		hibernate.delete(new OperatorVO(operator_id));
		hibernate.flushClearCommit();
		//return at;
	} 
	
	// 운영자 승인 삭제
	public void deleteOperatorReq(String login_id) {
		hibernate.flushClearBeginTransaction();
		hibernate.delete(new OperatorReqVO(login_id));
		hibernate.flushClearCommit();
	} 
	public Code deleteOperatorRight(int operator_id) {
		int i = 0;
		hibernate.flushClearBeginTransaction();
		Query query = hibernate.createQuery("delete OpRightVO WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator_id);
		i += query.executeUpdate();
		
		//query = hibernate.createQuery("delete OperatorVO WHERE OPERATOR_ID = :operator_id");
		query = hibernate.createQuery("update OperatorVO SET deleted = 'Y' WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator_id);
		i += query.executeUpdate();
		
		//delete!!! ip
		query = hibernate.createQuery("delete OperatorIPVO WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator_id);
		i += query.executeUpdate();
		
		
		hibernate.flushClearCommit();
		
		Code code = Code.FAIL;
		if(i>0){
			code = Code.SUCCESS.set(i);
		}
		return code;
	} 
	  
	
	/* count */
	public long getOperatorOrReqCount(Criterion where) {
		return getOperatorCount(where)+getOperatorReqCount(where);
	}	
	public long getOperatorOrReqCount(List<Criterion> where) {
		return getOperatorCount(where)+getOperatorReqCount(where);
	}
	
	// 운영자 승인 합계
	public long getOperatorReqCount() {
		return getOperatorReqCount(new ArrayList<Criterion>());
	}
	public long getOperatorReqCount(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();list.add(where);
		return getOperatorReqCount(list);
	}
	public long getOperatorReqCount(List<Criterion> where) {
		return hibernate.getCriteriaCount(OperatorReqVO.class, where);
	}

	public OperatorReqVO getOperatorReq(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();list.add(where);
		return getOperatorReq(list);
	}
	public OperatorReqVO getOperatorReq(List<Criterion> where) {
		Criteria crit = hibernate.getCriteria(OperatorReqVO.class,where);
		OperatorReqVO at = (OperatorReqVO)crit.uniqueResult();
		return at;
	} 
	public List<OperatorReqVO> getOperatorReqList(DataTableVO dataTable) {
		Criteria crit = hibernate.createCriteria(OperatorReqVO.class).add(Restrictions.like("name", "%"+dataTable.getSearchValue()+"%")).addOrder(Order.desc("create_date"));
		crit.setFirstResult(dataTable.getStart());//Pagination in Hibernate with Criteria API
		crit.setMaxResults(dataTable.getLength());
		List<OperatorReqVO> list = crit.list();
		return list;
	}
	
	// 운영자 합계
	public long getOperatorCount() {
		return getOperatorCount(new ArrayList<Criterion>());
	}
	public long getOperatorCount(List<Criterion> where) {
		return hibernate.getCriteriaCount(OperatorVO.class, where);
	}
	// 운영자 합계 DB
	public long getOperatorCount(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();
		list.add(where);
		return getOperatorCount(list);
	}

	public int getOperatorListCount(DataTableVO dataTable) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		OperatorSVO option = dataTable.getSearchJson(OperatorSVO.class);
		Criteria crit = hibernate.createCriteria(OperatorVO.class);
		crit.add(Restrictions.eq("deleted", "N"));
		String operator_field_name 	= option.getSearch_mode();//mode
		String operator_field_val 	= option.getSearch_key();//mode
		if(null!=operator_field_name && operator_field_name.length()>0 && null!=operator_field_val && operator_field_val.length()>0){
			crit.add(Restrictions.like(operator_field_name, "%"+operator_field_val+"%"));
		}
		crit.setProjection(Projections.rowCount());
		int cnt = ((Long)crit.uniqueResult()).intValue();
		return cnt;
	}  
	
	// 운영자 리스트 DB
	public List<OperatorVO> getOperatorList(DataTableVO dataTable) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {			//selectOption처리
		
		OperatorSVO option = dataTable.getSearchJson(OperatorSVO.class);
		Criteria crit = hibernate.createCriteria(OperatorVO.class);
		crit.add(Restrictions.eq("deleted", "N"));
		
		String operator_field_name 	= option.getSearch_mode();//mode
		String operator_field_val 	= option.getSearch_key();//mode
		if(null!=operator_field_name && operator_field_name.length()>0 && null!=operator_field_val && operator_field_val.length()>0){
			crit.add(Restrictions.like(operator_field_name, "%"+operator_field_val+"%"));
		}
		crit.addOrder(Order.desc("operator_id"));
		crit.setFirstResult(dataTable.getStart());
		crit.setMaxResults(dataTable.getLength());
		List<OperatorVO> list = crit.list();
		return list;
	}
	public int getOperatorRightJoinListCount(DataTableVO dataTable) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

		OperatorSVO option = dataTable.getSearchJson(OperatorSVO.class);

//		Criteria crit = hibernate.createCriteria(OperatorVO.class);
		Criteria crit = hibernate.createCriteria(OperatorRightJoinVO.class);
		crit.add(Restrictions.eq("deleted", "N"));

		String code 		= option.getAuth_search_mode();//right_code
		String operator_field_name 	= option.getSearch_mode();//mode
		String operator_field_val 	= option.getSearch_key();//mode
		if(null!=code && code.length()>0){
			crit.createAlias("opRights", "opRights");
			crit.createAlias("opRights.rights", "opRights_rights");
			crit.setFetchMode("opRights", FetchMode.JOIN);
			crit.setFetchMode("opRights.rights", FetchMode.JOIN);
			crit.add(Restrictions.eq("opRights_rights.code", code));
		}
		if(null!=operator_field_name && operator_field_name.length()>0 && null!=operator_field_val && operator_field_val.length()>0){
			crit.add(Restrictions.like(operator_field_name, "%"+operator_field_val+"%"));
		}

        crit.setProjection(Projections.rowCount());
        int cnt = ((Long)crit.uniqueResult()).intValue();
		return cnt;
	}  
	
	// 운영자 리스트 DB
	public List<OperatorRightJoinVO> getOperatorRightJoinList(DataTableVO dataTable) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {			//selectOption처리
		
		OperatorSVO option = dataTable.getSearchJson(OperatorSVO.class);
		
//		Criteria crit = hibernate.createCriteria(OperatorVO.class);
		Criteria crit = hibernate.createCriteria(OperatorRightJoinVO.class);
		crit.add(Restrictions.eq("deleted", "N"));

		String code 		= option.getAuth_search_mode();//right_code
		String operator_field_name 	= option.getSearch_mode();//mode
		String operator_field_val 	= option.getSearch_key();//mode
		if(null!=code && code.length()>0){
			crit.createAlias("opRights", "opRights");
			crit.createAlias("opRights.rights", "opRights_rights");
			crit.setFetchMode("opRights", FetchMode.JOIN);
			crit.setFetchMode("opRights.rights", FetchMode.JOIN);
			crit.add(Restrictions.eq("opRights_rights.code", code));
		}
		if(null!=operator_field_name && operator_field_name.length()>0 && null!=operator_field_val && operator_field_val.length()>0){
			crit.add(Restrictions.like(operator_field_name, "%"+operator_field_val+"%"));
		}
		
		
		crit.addOrder(Order.desc("operator_id"));
		crit.setFirstResult(dataTable.getStart());
		crit.setMaxResults(dataTable.getLength());
		List<OperatorRightJoinVO> list = crit.list();
		return list;
	}
	
	
	/* atContent detail*/
	public OperatorVO getOperator(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();list.add(where);
		return getOperator(list);
	}
	public OperatorVO getOperator(List<Criterion> where) {
		Criteria crit = hibernate.getCriteria(OperatorVO.class,where);
		OperatorVO at = (OperatorVO)crit.uniqueResult();
		return at;
	}
	
	public OperatorRightJoinVO getOperatorRightJoin(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();list.add(where);
		return getOperatorRightJoin(list);
	}
	public OperatorRightJoinVO getOperatorRightJoin(List<Criterion> where) {
		Criteria crit = hibernate.getCriteria(OperatorRightJoinVO.class, where);
		OperatorRightJoinVO o = (OperatorRightJoinVO)crit.uniqueResult();
		return o;
	}
	
	public List<OperatorGrantVO> getOperatorGrant(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();list.add(where);
		return getOperatorGrant(list);
	}
	public List<OperatorGrantVO> getOperatorGrant(List<Criterion> where) {
		Criteria crit = hibernate.getCriteria(OperatorGrantVO.class, where);
		List<OperatorGrantVO> o = crit.list();
		return o;
	}
	
	
	
	//save update delete
	public void saveOperatorReq(OperatorReqVO operatorReq) {
		hibernate.flushClearBeginTransaction();
		hibernate.save(operatorReq);
		hibernate.flushClearCommit();
	}
	
	
	public Code modifyOperatorRightJoin(OperatorRightJoinVO operatorRightJoin) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		int operator_id = securityMng.getSecurityUser().getOperator_id();
		operatorRightJoin.setCreate_id(operator_id);
		for(OpRightJoinVO at : operatorRightJoin.getOpRights()){
			at.setCreate_id(operator_id);
		}
		
		
		///Transaction Start
		hibernate.flushClearBeginTransaction();
		
		//master update
		OperatorVO operator = hibernate.refresh(operatorRightJoin, OperatorVO.class);//DB에서 가져옴 SELECT
		operatorRightJoin.setCreate_date(null);
		operatorRightJoin.setCreate_time(null);
		operator.importSourceNotNullField(operatorRightJoin);
		operator.setCard_no(operatorRightJoin.getCard_no());
		//operator.set_ip(operatorRightJoin.get_ip());
		hibernate.save(operator);//save     //hibernate.merge(operator);//save
		
		//delete!!! right
		Query query = hibernate.createQuery("delete OpRightVO WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator.getOperator_id());
		query.executeUpdate();
		
		//delete!!! area operator_grant
		query = hibernate.createQuery("delete OperatorGrantVO WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator.getOperator_id());
		query.executeUpdate();
		
		
		//delete!!! ip
		query = hibernate.createQuery("delete OperatorIPVO WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator.getOperator_id());
		query.executeUpdate();
		
		
		//insert
		if(null!=operatorRightJoin.getIp()&&operatorRightJoin.getIp().getIp_number().length()>0){
			operatorRightJoin.getIp().setOperator_id(operator.getOperator_id());
			operatorRightJoin.getIp().setId(operator.getOperator_id());
			hibernate.save(operatorRightJoin.getIp());
		}
		hibernate.saveList(operatorRightJoin.getOpRights(), OpRightVO.class);
		hibernate.saveList(operatorRightJoin.getGrants());
		///Transaction End
		hibernate.flushClearCommit();
		
		return Code.SUCCESS;
	}
	
	
	
	public Code updateOperator(OperatorVO operator) {
		OperatorVO refreshOperator = hibernate.refresh(operator,OperatorVO.class);
		if(refreshOperator.getLogin_pw().equals(operator.getLogin_pw())){
			throw new JsonErrorsException("동일한 비밀번호로 변경하실수 없습니다.");
		}
		
		hibernate.flushClearBeginTransaction();
		hibernate.update(refreshOperator.importSourceNotNullField(operator));
		hibernate.flushClearCommit();
		
		securityMng.getSecurityUser().setPwd_update(refreshOperator.getPwd_update());
		securityMng.getSecurityUser().setLogin_pw(refreshOperator.getLogin_pw());
		securityMng.getSecurityUser().setName(refreshOperator.getName());
		securityMng.getSecurityUser().setPosition(refreshOperator.getPosition());
		securityMng.getSecurityUser().setPart(refreshOperator.getPart());
		securityMng.getSecurityUser().setPhone1(refreshOperator.getPhone1());
		securityMng.getSecurityUser().setPhone2(refreshOperator.getPhone2());
		securityMng.getSecurityUser().setEmail(refreshOperator.getEmail());
		return Code.SUCCESS;
	}
	
	
	public Code updateOperatorPwd(OperatorVO operator) {
		hibernate.flushClearBeginTransaction();
		hibernate.update(operator);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}
	
	
	
	// 권한 저장
	public Code saveOperatorRightJoin(OperatorRightJoinVO operatorRightJoin) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		int operator_id = securityMng.getSecurityUser().getOperator_id();
		operatorRightJoin.setCreate_id(operator_id);
		
		///Transaction Start
		hibernate.flushClearBeginTransaction();
		long cnt = getOperatorOrReqCount(Restrictions.eq("login_id", operatorRightJoin.getLogin_id()));
		if(cnt>0){
			throw new JsonErrorsException("중복된 사용자 입니다.");
		}
		
		OperatorVO operator = hibernate.save(operatorRightJoin, OperatorVO.class);
		for(OpRightJoinVO at : operatorRightJoin.getOpRights()){
			at.setCreate_id(operator_id);
			at.setOperator_id(operator.getOperator_id());
		}
		
		
		//insert
		if(null!=operatorRightJoin.getIp()&&operatorRightJoin.getIp().getIp_number().length()>0){
			operatorRightJoin.getIp().setOperator_id(operator.getOperator_id());
			operatorRightJoin.getIp().setId(operator.getOperator_id());
			hibernate.save(operatorRightJoin.getIp());
		}
		
		
		
		
		
		//insert
		hibernate.saveList(operatorRightJoin.getOpRights(),OpRightVO.class);
		hibernate.flushClearCommit();
		///Transaction End
		
		return Code.SUCCESS;
	}
	
	
	// 권한 업데이트
	public void moveAndsaveOperatorReqRightJoin(OperatorReqRightJoinVO operatorReqRightJoin) {
		hibernate.flushClearBeginTransaction();
		
		OperatorReqVO req = hibernate.refresh(operatorReqRightJoin, OperatorReqVO.class);
		hibernate.delete(req); //우선 REQ쪽에서 지운다
		operatorReqRightJoin.importMyNullField(req);
		OperatorVO operator = hibernate.save(operatorReqRightJoin, OperatorVO.class); 
		
		//delete!!! right
		Query query = hibernate.createQuery("delete OpRightVO WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator.getOperator_id());
		query.executeUpdate();
		
		int operator_id = securityMng.getSecurityUser().getOperator_id();
		if(null!=operatorReqRightJoin.getOpRights()){
			for(OpRightVO at : operatorReqRightJoin.getOpRights()){
				at.setOperator_id(operator.getOperator_id());
				at.setCreate_id(operator_id);
			}
		}
		query = hibernate.createQuery("delete OperatorGrantVO WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator.getOperator_id());
		query.executeUpdate();
		
		for(OperatorGrantVO at : operatorReqRightJoin.getGrants()){
			at.setOperator_id(operator.getOperator_id());
		}
		
		
		
		//delete!!! ip
		query = hibernate.createQuery("delete OperatorIPVO WHERE OPERATOR_ID = :operator_id");
		query.setParameter("operator_id", operator.getOperator_id());
		query.executeUpdate();
		
		
		//insert
		if(null!=operatorReqRightJoin.getIp()&&operatorReqRightJoin.getIp().getIp_number().length()>0){
			operatorReqRightJoin.getIp().setOperator_id(operator.getOperator_id());
			operatorReqRightJoin.getIp().setId(operator.getOperator_id());
			hibernate.save(operatorReqRightJoin.getIp());
		}
		
		
		
		
		
		
		hibernate.saveList(operatorReqRightJoin.getOpRights());
		hibernate.saveList(operatorReqRightJoin.getGrants());
		
		hibernate.flushClearCommit();
	}

	public Code isCard(String card_no) {
		long cnt = getCardCount(Restrictions.eq("card_no", card_no));
		if(cnt<1){
			return Code.FAIL.setMsg("일치하는 카드 정보가 없습니다.");
		}
//		long checkCardOperatorCnt = getOperatorCount(Restrictions.eq("card_no", card_no));
//		if(checkCardOperatorCnt>0){
//			return Code.FAIL.setMsg("사용하실수 없는 카드 입니다.");
//		}
		return Code.SUCCESS;
	}
	public long getCardCount(Criterion where) {
		return hibernate.getCriteriaCount(CheckCardJoinVO.class, where);
	}
	
	
	
}
