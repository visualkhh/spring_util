package com.khh.project.template.service;

import java.util.List;

import javax.transaction.Transactional;

import com.khh.code.Code;
import com.khh.project.msg.vo.MsgTemplateVVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.khh.project.msg.vo.MsgClassVO;
import com.khh.project.msg.vo.MsgTemplateVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.db.hibernate.Hibernater;


@Service("TemplateService")
@Transactional(rollbackOn = { Exception.class })
public class TemplateService extends DefaultService {
	@Autowired
	Hibernater hibernate;
	
	public List<MsgClassVO> getMsgClassList() {
		List<MsgClassVO> list=null;
		Criteria crit = hibernate.createCriteria(MsgClassVO.class).addOrder(Order.asc("class1_order")).addOrder(Order.asc("class2_order"));
		list = crit.list();
		return list;
	}
	public MsgClassVO getMsgClass(int class_id) {
		MsgClassVO at = null;
		Criteria crit = hibernate.createCriteria(MsgClassVO.class).add(Restrictions.eq("class_id", class_id));
		at = (MsgClassVO) crit.uniqueResult();
		return at;
	}

	public int getMsgTemplateCount(DataTableVO dataTable, String search) {
		int cnt = -1;
		Criteria crit = hibernate.createCriteria(MsgTemplateVVO.class);
		crit.add(Restrictions.like("title", "%"+(null==search?"":search)+"%"));
		crit.setProjection(Projections.rowCount());
		cnt = ((Long)crit.uniqueResult()).intValue();
		return cnt;
	}

	public List<MsgTemplateVVO> getMsgTemplateList(DataTableVO dataTable, String search) {
		List<MsgTemplateVVO> list =null;
		Criteria crit = hibernate.createCriteria(MsgTemplateVVO.class);
		crit.add(Restrictions.like("title", "%"+(null==search?"":search)+"%"));
		crit.addOrder(Order.desc("template_id"));
		crit.setFirstResult(dataTable.getStart());
		crit.setMaxResults(dataTable.getLength());
		list = crit.list();		 
		return list;
	}
	 
	public MsgTemplateVVO getMsgTemplate(int template_id) {
		MsgTemplateVVO at = null;
		Criteria crit = hibernate.createCriteria(MsgTemplateVVO.class).add(Restrictions.eq("template_id", template_id));
		at =  (MsgTemplateVVO)crit.uniqueResult();
		return at;
	}
	//http://www.codejava.net/frameworks/hibernate/hibernate-basics-3-ways-to-delete-an-entity-from-the-datastore
	//https://github.com/ufal/lindat-repository-obsolete/blob/master/sources/utilities/src/main/java/cz/cuni/mff/ufal/lindat/utilities/hibernate/HibernateUtil.java
	public Code deleteMsgTemplate(Integer template_id) {
		hibernate.flushClearBeginTransaction();
		hibernate.delete(new MsgTemplateVVO(template_id));
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}
	

	public List<MsgClassVO> getMsgClassList(DataTableVO dataTable) {
		List<MsgClassVO> list = null; 
		Criteria crit = hibernate.createCriteria(MsgClassVO.class).addOrder(Order.asc("class_id"));
		list = crit.list();		 
		return list;
	}
	public Code updateMsgTemplate(MsgTemplateVO msgTemplate) {
		hibernate.flushClearBeginTransaction();
		hibernate.update(msgTemplate);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}
	public Code saveMsgTemplate(MsgTemplateVO msgTemplate) {
		hibernate.flushClearBeginTransaction();
		hibernate.save(msgTemplate);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}
	
}
