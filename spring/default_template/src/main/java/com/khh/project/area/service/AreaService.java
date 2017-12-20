package com.khh.project.area.service;

import java.util.List;

import javax.transaction.Transactional;

import com.khh.code.Code;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.khh.project.area.vo.AreaVO;
import com.khh.project.area.vo.AreaJoinVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.db.hibernate.Hibernater;

@Service("AreaService")
@Transactional(rollbackOn = { Exception.class })
public class AreaService extends DefaultService {
	@Autowired
	Hibernater hibernate;
	
	public long getAreaCount(DataTableVO dataTable) {
		Criteria crit = hibernate.createCriteria(AreaVO.class);
		Disjunction or = Restrictions.disjunction();
					or.add(Restrictions.like("area1", "%"+ dataTable.getSearchValue() + "%" )); 
					or.add(Restrictions.like("area2", "%"+ dataTable.getSearchValue() + "%" )); 
					or.add(Restrictions.like("area3", "%"+ dataTable.getSearchValue() + "%" ));
		crit.add(or);
		crit.setProjection(Projections.rowCount());
		int cnt = ((Long) crit.uniqueResult()).intValue();
		
		return cnt;
	}


	public List<AreaVO> getAreaList(DataTableVO dataTable) {
		Criteria crit = hibernate.createCriteria(AreaVO.class);
		Disjunction or = Restrictions.disjunction();
					or.add(Restrictions.like("area1", "%"+ dataTable.getSearchValue() + "%" )); 
					or.add(Restrictions.like("area2", "%"+ dataTable.getSearchValue() + "%" )); 
					or.add(Restrictions.like("area3", "%"+ dataTable.getSearchValue() + "%" )); 
		crit.add(or);
		crit.addOrder(Order.asc("area_id"));
		crit.setFirstResult(dataTable.getStart());//Pagination in Hibernate with Criteria API
		crit.setMaxResults(dataTable.getLength());
		List<AreaVO> list = crit.list();
		return list;
	}


	public AreaJoinVO getArea(int area_id) {
		Criteria crit = hibernate.createCriteria(AreaJoinVO.class).add(Restrictions.eq("area_id", area_id));
		AreaJoinVO at = (AreaJoinVO) crit.uniqueResult();
		return at;
	}


	public Code updateArea(AreaVO area) {
		hibernate.flushClearBeginTransaction();
		hibernate.update(area);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}


	public Code insertArea(AreaVO area) {
		hibernate.flushClearBeginTransaction();
		hibernate.save(area);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}
}

