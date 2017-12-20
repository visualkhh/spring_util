package com.khh.project.common.service;

import java.util.List;

import javax.transaction.Transactional;

import com.khh.project.msg.vo.MsgClassVO;
import com.khh.project.msg.vo.MsgTemplateVVO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.khh.project.area.vo.AreaVO;
import com.omnicns.java.db.hibernate.Hibernater;

@Service("CommonService")
@Transactional(rollbackOn = { Exception.class })
public class CommonService extends DefaultService {
	@Autowired
	Hibernater hibernate;
	
	public List<AreaVO> getArea() {
		Criteria crit = hibernate.createCriteria(AreaVO.class).addOrder(Order.desc("area_id"));
		List<AreaVO> list = crit.list();
		return list;
	}

	// 시도 단위
	public List<AreaVO> getArea1() {
		Criteria crit = hibernate.createCriteria(AreaVO.class);
		crit.add(Restrictions.ne("area1", "전국"));
		crit.add(Restrictions.eq("area2", "전체"));
		crit.add(Restrictions.isNull("area3"));
		crit.addOrder(Order.asc("area_id"));
		List<AreaVO> list = crit.list();
		return list;
	}
	
	// 시도 단위
	public List<AreaVO> getArea2() {
		Criteria crit = hibernate.createCriteria(AreaVO.class);
		crit.add(Restrictions.ne("area1", "전국"));
		crit.add(Restrictions.eq("area2", "전체"));
		crit.add(Restrictions.isNull("area3"));
		crit.addOrder(Order.asc("area_id"));
		List<AreaVO> list = crit.list();
		return list;
	}	
	public List<AreaVO> getAreaAll() {
		Criteria crit = hibernate.createCriteria(AreaVO.class).add(Restrictions.gt("order_id1", 0)).addOrder(Order.asc("order_id1"));
		List<AreaVO> list = crit.list();
		return list;
	}

	public List<MsgClassVO> getMsgClass() {
		Criteria crit = hibernate.createCriteria(MsgClassVO.class);
		List<MsgClassVO> list = crit.list();
		return list;
	}

	public List<MsgTemplateVVO> getMsgTemplate() {
		Criteria crit = hibernate.createCriteria(MsgTemplateVVO.class);
		List<MsgTemplateVVO> list = crit.list();
		return list;
	}
}
