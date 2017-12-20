package com.khh.project.log.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.khh.project.log.vo.LogCompanySVO;
import com.khh.project.log.vo.LogCompanyVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.date.DateUtil;
import com.omnicns.java.db.hibernate.Hibernater;

@Service("LogService")
@Transactional(rollbackOn = { Exception.class })
public class LogService extends DefaultService {
	@Autowired
	Hibernater hibernate;
	
	public List<LogCompanyVO> getLogList(DataTableVO dataTable) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		
		LogCompanySVO query = dataTable.getSearchJson(LogCompanySVO.class);	
		
		Criteria crit = hibernate.createCriteria(LogCompanyVO.class);	 
		
		if(null!=query && null!=query.getStart_dt()) crit.add(Restrictions.ge("create_date", query.getStart_dt() ));
		else crit.add(Restrictions.ge("create_date", DateUtil.modifyDate(new Date(),Calendar.DATE,-7,"yyyyMMdd")));
		if(null!=query && null!=query.getEnd_dt()) crit.add(Restrictions.le("create_date", query.getEnd_dt() ));
		else crit.add(Restrictions.le("create_date", DateUtil.dateFormat("yyyyMMdd",new Date())));
		
		if(null!=query && null!=query.getCompany_id() && !"0".equals(query.getCompany_id())  ) crit.add(Restrictions.eq("company_id", query.getCompany_id()));
		crit.addOrder(Order.desc("wait_id")); 		
		crit.setFirstResult(dataTable.getStart());
		crit.setMaxResults(dataTable.getLength());		
		List<LogCompanyVO> list = crit.list();
		
		return list;
	}
	public List<LogCompanyVO> getLog(Integer wait_id) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Criteria crit = hibernate.createCriteria(LogCompanyVO.class).add(Restrictions.eq("wait_id", wait_id));			 
		List<LogCompanyVO> list =  crit.list();
		return list;
	}

	public int getLogCount(DataTableVO dataTable) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		LogCompanySVO query=dataTable.getSearchJson(LogCompanySVO.class);	
		
		Criteria crit = hibernate.createCriteria(LogCompanyVO.class);			 
		if(null!=query && null!=query.getStart_dt()) crit.add(Restrictions.ge("create_date", query.getStart_dt() ));
		else crit.add(Restrictions.ge("create_date", DateUtil.modifyDate(new Date(),Calendar.DATE,-7,"yyyyMMdd")));
		if(null!=query && null!=query.getEnd_dt()) crit.add(Restrictions.le("create_date", query.getEnd_dt() ));
		else crit.add(Restrictions.le("create_date", DateUtil.dateFormat("yyyyMMdd",new Date())));
		if(null!=query && null!=query.getCompany_id() && !"0".equals(query.getCompany_id()) ) crit.add(Restrictions.eq("company_id", query.getCompany_id()));
		 
        crit.setProjection(Projections.rowCount());
        int cnt = ((Long)crit.uniqueResult()).intValue();
//		int count = ((Long)session.createQuery("select COUNT(*) from BootCodeVO").uniqueResult()).intValue();
		//( (Integer) session.createQuery("select count(*) from ....").iterate().next() ).intValue()
		return cnt;
	}  
}
