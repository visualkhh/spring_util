package com.khh.project.msg.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.khh.code.Code;
import com.khh.config.ConfigManager;
import com.khh.project.msg.vo.MsgAreaVO;
import com.khh.project.msg.vo.MsgCompanyVO;
import com.khh.project.msg.vo.MsgSubmitVVO;
import org.apache.http.HttpResponse;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.khh.project.msg.vo.MsgSubmitLogVVO;
import com.khh.project.msg.vo.MsgSubmitSVO;
import com.khh.project.msg.vo.MsgWaitJoinVO;
import com.khh.project.msg.vo.MsgWaitVO;
import com.khh.project.msg.vo.MsgWaitVVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.date.DateUtil;
import com.omnicns.java.db.hibernate.Hibernater;
import com.omnicns.java.net.http.HttpClient;
import com.khh.security.SecurityManager;

@Service("MsgService")
@Transactional(rollbackOn = { Exception.class })
public class MsgService extends DefaultService {

	@Autowired
	SecurityManager securityMng;;
	@Autowired
	ConfigManager configMng;
	@Autowired
	Hibernater hibernate;

	Logger log = LoggerFactory.getLogger(this.getClass());

	// https://examples.javacodegeeks.com/enterprise-java/hibernate/pagination-in-hibernate-with-criteria-api/
	public List<MsgSubmitVVO> getMsgSubmitList(DataTableVO dataTable)
			throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		MsgSubmitSVO query = dataTable.getSearchJson(MsgSubmitSVO.class);
		Criteria crit = hibernate.createCriteria(MsgSubmitVVO.class);

		// 권한 정보가 없거나 승인자 이하의 권한이면 본인이 등록한 재난메시지만 조회 가능
		if (null == securityMng.getSecurityUser().getOp_level() || securityMng.getSecurityUser().getOp_level() < 2) {
			crit.add(Restrictions.eq("create_id", securityMng.getSecurityUser().getOperator_id()));
		}

		if (null != query && null != query.getStart_dt())
			crit.add(Restrictions.ge("submit_date", query.getStart_dt()));
		else
			crit.add(Restrictions.ge("submit_date", DateUtil.modifyDate(new Date(),Calendar.DATE,-7,"yyyyMMdd")));
		if (null != query && null != query.getEnd_dt())
			crit.add(Restrictions.le("submit_date", query.getEnd_dt()));
		else
			crit.add(Restrictions.le("submit_date", DateUtil.dateFormat("yyyyMMdd",new Date())));
		if (null != query && null != query.getGrade_id() && query.getGrade_id() != 0)
			crit.add(Restrictions.eq("grade_id", query.getGrade_id()));
		if (null != query && null != query.getClass_id() && query.getClass_id() != 0)
			crit.add(Restrictions.eq("class_id", query.getClass_id()));
		if (null != query && null != query.getAreas())
			crit.add(Restrictions.like("areas", "%" + query.getAreas() + "%"));
		if (null != query && null != query.getStatus())
			crit.add(Restrictions.eq("status", query.getStatus()));
		crit.addOrder(Order.desc("wait_id"));
		crit.setFirstResult(dataTable.getStart());// Pagination in Hibernate
		crit.setMaxResults(dataTable.getLength());
		List<MsgSubmitVVO> list = crit.list();
		return list;
	}

	public List<MsgSubmitVVO> getMsgSubmitList(List<Criterion> where, List<Order> orders)
			throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		Criteria crit = hibernate.getCriteria(MsgSubmitVVO.class, where, orders);
		List<MsgSubmitVVO> list = crit.list();
		return list;
	}

	public int getMsgSubmitCount(DataTableVO dataTable) throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		MsgSubmitSVO query = dataTable.getSearchJson(MsgSubmitSVO.class);
		Criteria crit = hibernate.createCriteria(MsgSubmitVVO.class);

		// 권한 정보가 없거나 승인자 이하의 권한이면 본인이 등록한 재난메시지만 조회 가능
		if (null == securityMng.getSecurityUser().getOp_level() || securityMng.getSecurityUser().getOp_level() < 2) {
			crit.add(Restrictions.eq("create_id", securityMng.getSecurityUser().getOperator_id()));
		}

		if (null != query && null != query.getStart_dt())
			crit.add(Restrictions.ge("submit_date", query.getStart_dt()));
		else
			crit.add(Restrictions.ge("submit_date", DateUtil.modifyDate(new Date(),Calendar.DATE,-7,"yyyyMMdd")));
		if (null != query && null != query.getEnd_dt())
			crit.add(Restrictions.le("submit_date", query.getEnd_dt()));
		else
			crit.add(Restrictions.le("submit_date", DateUtil.dateFormat("yyyyMMdd",new Date())));
		if (null != query && null != query.getGrade_id() && query.getGrade_id() != 0)
			crit.add(Restrictions.eq("grade_id", query.getGrade_id()));
		if (null != query && null != query.getClass_id() && query.getClass_id() != 0)
			crit.add(Restrictions.eq("class_id", query.getClass_id()));
		if (null != query && null != query.getAreas())
			crit.add(Restrictions.like("areas", "%" + query.getAreas() + "%"));
		if (null != query && null != query.getStatus())
			crit.add(Restrictions.eq("status", query.getStatus()));
		crit.setProjection(Projections.rowCount());
		int cnt = ((Long) crit.uniqueResult()).intValue();
		return cnt;
	}

	// 로그
	public int getMsgSubmitLogCount(String search) {
		Criteria crit = hibernate.createCriteria(MsgSubmitLogVVO.class);

		crit.add(Restrictions.eq("wait_id", (null == search ? "" : search)));
		crit.setProjection(Projections.rowCount());
		int cnt = ((Long) crit.uniqueResult()).intValue();
		return cnt;
	}

	public List<MsgSubmitLogVVO> getMsgSubmitLogList(DataTableVO dataTable) {
		Criteria crit = hibernate.createCriteria(MsgSubmitLogVVO.class);

		crit.add(Restrictions.eq("wait_id", dataTable.getSearchValue()));
		crit.addOrder(Order.desc("wait_id"));
		crit.setFirstResult(dataTable.getStart());// Pagination in Hibernate
		crit.setMaxResults(dataTable.getLength());
		List<MsgSubmitLogVVO> list = crit.list();
		return list;
	}

	// 예약
	public int getMsgWaitCount(DataTableVO dataTable) throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		MsgSubmitSVO query = dataTable.getSearchJson(MsgSubmitSVO.class);
		Criteria crit = hibernate.createCriteria(MsgWaitVVO.class);

		// 권한 정보가 없거나 승인자 이하의 권한이면 본인이 등록한 재난메시지만 조회 가능
		if (null == securityMng.getSecurityUser().getOp_level() || securityMng.getSecurityUser().getOp_level() < 2) {
			crit.add(Restrictions.eq("create_id", securityMng.getSecurityUser().getOperator_id()));
		}

		if (null != query && null != query.getStart_dt())
			crit.add(Restrictions.ge("create_date", query.getStart_dt()));
		if (null != query && null != query.getEnd_dt())
			crit.add(Restrictions.le("create_date", query.getEnd_dt()));
		if (null != query && null != query.getGrade_id() && query.getGrade_id() != 0)
			crit.add(Restrictions.eq("grade_id", query.getGrade_id()));
		if (null != query && null != query.getClass_id() && query.getClass_id() != 0)
			crit.add(Restrictions.eq("class_id", query.getClass_id()));
		if (null != query && null != query.getStatus())
			crit.add(Restrictions.eq("status", query.getStatus()));
		crit.setProjection(Projections.rowCount());
		int cnt = ((Long) crit.uniqueResult()).intValue();
		return cnt;
	}

	public List<MsgWaitVVO> getMsgWaitList(DataTableVO dataTable) throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		MsgSubmitSVO query = dataTable.getSearchJson(MsgSubmitSVO.class);
		Criteria crit = hibernate.createCriteria(MsgWaitVVO.class);

		// 권한 정보가 없거나 승인자 이하의 권한이면 본인이 등록한 재난메시지만 조회 가능
		if (null == securityMng.getSecurityUser().getOp_level() || securityMng.getSecurityUser().getOp_level() < 2) {
			crit.add(Restrictions.eq("create_id", securityMng.getSecurityUser().getOperator_id()));
		}
		if (null != query && null != query.getStart_dt())
			crit.add(Restrictions.ge("create_date", query.getStart_dt()));
		if (null != query && null != query.getEnd_dt())
			crit.add(Restrictions.le("create_date", query.getEnd_dt()));
		if (null != query && null != query.getGrade_id() && query.getGrade_id() != 0)
			crit.add(Restrictions.eq("grade_id", query.getGrade_id()));
		if (null != query && null != query.getClass_id() && query.getClass_id() != 0)
			crit.add(Restrictions.eq("class_id", query.getClass_id()));
		if (null != query && null != query.getStatus())
			crit.add(Restrictions.eq("status", query.getStatus()));
		crit.addOrder(Order.desc("wait_id"));
		crit.setFirstResult(dataTable.getStart());// Pagination in Hibernate
		crit.setMaxResults(dataTable.getLength());
		List<MsgWaitVVO> list = crit.list();
		return list;
	}

	public List<MsgWaitVVO> getMsgWaitList(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();
		list.add(where);
		return getMsgWaitList(list);
	}

	public List<MsgWaitVVO> getMsgWaitList(List<Criterion> where){
		return hibernate.getCriteria(MsgWaitVVO.class, where).list();
	}
	public List<MsgWaitVVO> getMsgWaitList(List<Criterion> where,List<Order> order,int firstIdx, int max){
		Criteria crit = hibernate.getCriteria(MsgWaitVVO.class, where,order);
		crit.setFirstResult(firstIdx);
		crit.setMaxResults(max);
		return crit.list();
	}
	public List<MsgWaitVVO> getMsgSubmitList(List<Criterion> where){
		return hibernate.getCriteria(MsgSubmitVVO.class, where).list();
	}
	public List<MsgSubmitVVO> getMsgSubmitList(List<Criterion> where,List<Order> order,int firstIdx, int max){
		Criteria crit = hibernate.getCriteria(MsgSubmitVVO.class, where,order);
		crit.setFirstResult(firstIdx);
		crit.setMaxResults(max);
		return crit.list();
	}

	public List<MsgWaitVVO> getMsgWaitList(List<Criterion> where, List<Order> orders)
			throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		return hibernate.getCriteria(MsgWaitVVO.class, where, orders).list();
	}

	public long getMsgSubmitListCount(List<Criterion> where){
		return hibernate.getCriteriaCount(MsgSubmitVVO.class, where);
	}
	public long getMsgWaitListCount(List<Criterion> where){
		return hibernate.getCriteriaCount(MsgWaitVVO.class, where);
	}
	
	public Code saveMsgWait(MsgWaitJoinVO msgWait) {
		hibernate.flushClearBeginTransaction();
		hibernate.save(msgWait);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}

	public Code saveApprove(MsgWaitJoinVO msgWait) {
		hibernate.flushClearBeginTransaction();
		hibernate.save(msgWait);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}

	public Code addMsg(MsgWaitJoinVO msgWait) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		hibernate.flushClearBeginTransaction();
		hibernate.save(msgWait);

		for (MsgCompanyVO c : msgWait.getCompanys()) {
			c.setWait_id(msgWait.getWait_id());
		}
		hibernate.saveList(msgWait.getCompanys());
		for (MsgAreaVO c : msgWait.getAreas()) {
			c.setWait_id(msgWait.getWait_id());
		}
		hibernate.saveList(msgWait.getAreas());

		hibernate.flushClearCommit();
		
		//외부연동
		sendStatusByApprovedorReady(msgWait);
		
		
		return Code.SUCCESS;
	}
	//업데이트 메시지 update WaitMsg 우선 이건 테스트가 필요하다  화면단 만들어지면 테스트를 해야한다.
	public Code updateMsg(MsgWaitJoinVO msgWait) throws Exception {
		hibernate.flushClearBeginTransaction();
		
		MsgWaitVO wait = null;
		
		try{
			wait = (MsgWaitVO) hibernate.getCriteria(MsgWaitVO.class,Restrictions.eq("wait_id", msgWait.getWait_id())).uniqueResult();
			if(null==wait){
				throw new Exception("이미 처리된건이거나 삭제된건입니다.");
			}
		}catch (org.hibernate.UnresolvableObjectException e) {
			throw new Exception("이미 처리된건이거나 삭제된건입니다.");
		}
		
		//상태에 따른 step logic 처리 
		//기존 상태값이 WAIT 이여야 한다.
		Code dbStatus = Code.CODES_MSG_STATUS.child(wait.getStatus());
		if( dbStatus != Code.CODE_MSG_STATUS_WAIT ){
			throw new Exception("이미 처리되었습니다.");
		}
		
		
		
		msgWait.setCreate_date(null);
		msgWait.setCreate_time(null);
		wait.importSourceNotNullField(msgWait);
		
		hibernate.update(wait);
		
		Query query = hibernate.createQuery("delete MsgCompanyVO WHERE WAIT_ID = :wait_id");
		query.setParameter("wait_id", msgWait.getWait_id());
		query.executeUpdate();
		for (MsgCompanyVO c : msgWait.getCompanys()) {
			c.setWait_id(msgWait.getWait_id());
		}
		hibernate.saveList(msgWait.getCompanys());
		
		
		query = hibernate.createQuery("delete MsgAreaVO WHERE WAIT_ID = :wait_id");
		query.setParameter("wait_id", msgWait.getWait_id());
		query.executeUpdate();
		for (MsgAreaVO c : msgWait.getAreas()) {
			c.setWait_id(msgWait.getWait_id());
		}
		hibernate.saveList(msgWait.getAreas());
		
		hibernate.flushClearCommit();
		
		//외부연동
		sendStatusByApprovedorReady(wait);
		return Code.SUCCESS;
	}



	public MsgWaitVO getMsgWait(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();
		list.add(where);
		return getMsgWait(list);
	}
	public MsgWaitVO getMsgWait(List<Criterion> where) {
		// Object o = hibernate.getCriteria(MsgWaitVO.class,
		// where).uniqueResult();
		// return o==null?null:(MsgWaitVO)o;
		return (MsgWaitVO) hibernate.getCriteria(MsgWaitVO.class, where).uniqueResult();
	}

	public MsgWaitJoinVO getMsgWaitJoin(int wait_id) {
		MsgWaitJoinVO at = null;
		Criteria crit = hibernate.createCriteria(MsgWaitJoinVO.class).add(Restrictions.eq("wait_id", wait_id));
		at = (MsgWaitJoinVO)crit.uniqueResult();
		return at;
	}
	
	public Code updateMsgWaitStatus(MsgWaitVO msgWait) throws Exception {
		hibernate.flushClearBeginTransaction();
		MsgWaitVO selectWait = null;
		try{
			selectWait = hibernate.refresh(msgWait, MsgWaitVO.class);// select
		}catch (org.hibernate.UnresolvableObjectException e) {
			throw new Exception("이미 처리된건이거나 삭제된건입니다.");
		}
		//상태에 따른 step logic 처리 
		//APPROVED, REJECTED, DELETED 로 UPDATE칠때에는   기존 상태값이 WAIT 이여야 한다.
		Code inStatus = Code.CODES_MSG_STATUS.child(msgWait.getStatus());
		Code dbStatus = Code.CODES_MSG_STATUS.child(selectWait.getStatus());
		if( (inStatus==Code.CODE_MSG_STATUS_APPROVED || inStatus==Code.CODE_MSG_STATUS_REJECTD ||inStatus==Code.CODE_MSG_STATUS_DELETED) && dbStatus != Code.CODE_MSG_STATUS_WAIT ){
			throw new Exception("이미 처리되었습니다.");
		}
		
		
		
		msgWait.setCreate_date(null);
		msgWait.setCreate_time(null);
		MsgWaitVO saveWait = (MsgWaitVO) selectWait.importSourceNotNullField(msgWait);
		hibernate.save(saveWait);// save //hibernate.merge(operator);//save
		hibernate.flushClearCommit();
		//외부연동
		sendStatusByApprovedorReady(saveWait);

		return Code.SUCCESS;
	}
	
	//외부연계쪽.. 승인완료 또는 즉시송출권한예약 처리
	private void sendStatusByApprovedorReady(MsgWaitJoinVO msgWait) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		sendStatusByApprovedorReady(msgWait.copyField(MsgWaitVO.class));
	}
	public void sendStatusByApprovedorReady(MsgWaitVO wait){
		Code atCode = Code.CODES_MSG_STATUS.child(wait.getStatus());
		if (atCode == Code.CODE_MSG_STATUS_APPROVED || atCode == Code.CODE_MSG_STATUS_READY) {

			Criteria cri = hibernate.getCriteria(MsgAreaVO.class, Restrictions.eq("wait_id", wait.getWait_id()));
			List<MsgAreaVO> areas = (List<MsgAreaVO>) cri.list();
			String area = areas.stream().map(at -> String.valueOf(at.getArea_id())).collect(Collectors.joining(";"));
			
			new Thread(()->{
				///// ->안전디딤돌,KT스마트 알리미 HTTP호출/////////////////
				// 안전디딤돌
				String url = "";
				HttpClient client = null;
				long retry_wait_millis = Long.parseLong(configMng.getParam("publish_retry_wait_millis"));
				String publish_success_retry = configMng.getParam("publish_success_retry");
				for(int i=0;i<Integer.parseInt(configMng.getParam("publish_safe_ss_retry_cnt"));i++){
					try {
						client = new HttpClient();
						client.setCharSet("UTF-8");
						url = configMng.getParam("publish_safe_ss");
						LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
						param.put("dtype", String.valueOf(wait.getClass_id()));
						param.put("stype", "cbs");
						param.put("area", area);
						param.put("wait_id", String.valueOf(wait.getWait_id()));
						param.put("msg", wait.getContentsFirst());
						log.info("http call publish_safe_ss [start](try:"+i+"): "+url+"       param : "+param);
						HttpResponse p = client.post(url, null, param, null);
						int statusCode = p.getStatusLine().getStatusCode();
						client.close();
						log.info("http call publish_safe_ss [close](try:"+i+")(status:"+statusCode+"): "+url+"       param : "+param);
						if(statusCode==200){
							if(!"true".equals(publish_success_retry)){
								break;
							}
						}
						try {Thread.sleep(retry_wait_millis);} catch (InterruptedException e1) {e1.printStackTrace();}
					} catch (Exception e) {
						log.error("안전디딤돌 publish_safe_ss Send HttpCall Exception(try:"+i+")", e);
						try {Thread.sleep(retry_wait_millis);} catch (InterruptedException e1) {e1.printStackTrace();}
					}
				}

				// KT스마트 알림이
				for(int i=0;i<Integer.parseInt(configMng.getParam("publish_kt_smart_retry_cnt"));i++){
					try {
						client = new HttpClient();
						client.setCharSet("UTF-8");
						url = configMng.getParam("publish_kt_smart");
						LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
						param.put("dtype", String.valueOf(wait.getClass_id()));
						param.put("stype", "cbs");
						param.put("area", area);
						param.put("wait_id", String.valueOf(wait.getWait_id()));
						param.put("msg", wait.getContentsFirst());
						log.info("http call publish_kt_smart [start](try:"+i+"): "+url+"       param : "+param);
						HttpResponse p = client.post(url, null, param, null);
						int statusCode = p.getStatusLine().getStatusCode();
						client.close();
						log.info("http call publish_kt_smart [close](try:"+i+")(status:"+statusCode+"): "+url+"       param : "+param);
						if(statusCode==200){
							if(!"true".equals(publish_success_retry)){
								break;
							}
						}
						try {Thread.sleep(retry_wait_millis);} catch (InterruptedException e1) {e1.printStackTrace();}
					} catch (Exception e) {
						log.error("KT스마트 알림이 HttpCall Exception(try:"+i+")", e);
						try {Thread.sleep(retry_wait_millis);} catch (InterruptedException e1) {e1.printStackTrace();}
					}
				}
				// omnicns 알림이
				for(int i=0;i<Integer.parseInt(configMng.getParam("publish_omnicns_retry_cnt"));i++){
					try {
						client = new HttpClient();
						client.setCharSet("UTF-8");
						url = configMng.getParam("publish_omnicns");
						LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
						param.put("dtype", String.valueOf(wait.getClass_id()));
						param.put("stype", "cbs");
						param.put("area", area);
						param.put("wait_id", String.valueOf(wait.getWait_id()));
						param.put("msg", wait.getContentsFirst());
						log.info("http call publish_omnicns [start](try:"+i+"): "+url+"       param : "+param);
						HttpResponse p = client.post(url, null, param, null);
						int statusCode = p.getStatusLine().getStatusCode();
						client.close();
						log.info("http call publish_omnicns [close](try:"+i+")(status:"+statusCode+"): "+url+"       param : "+param);
						if(statusCode==200){
							if(!"true".equals(publish_success_retry)){
								break;
							}
						}
						try {Thread.sleep(retry_wait_millis);} catch (InterruptedException e1) {e1.printStackTrace();}
					} catch (Exception e) {
						log.error("omnicns 알림이 HttpCall Exception("+i+")", e);
						try {Thread.sleep(retry_wait_millis);} catch (InterruptedException e1) {e1.printStackTrace();}
					}
				}
				/////////////////////
			}).start();
			
		}
	}

}
