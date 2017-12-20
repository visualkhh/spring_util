package com.khh.project.group.service;

import java.util.List;

import javax.transaction.Transactional;

import com.khh.code.Code;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.khh.project.group.vo.AreaGroupJoinVO;
import com.khh.project.group.vo.AreaGroupListVVO;
import com.khh.project.group.vo.AreaGroupMapVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.db.hibernate.Hibernater;


@Service("GroupService")
@Transactional(rollbackOn = { Exception.class })
public class GroupService extends DefaultService {
	@Autowired
	Hibernater hibernate;
	
	public long getAreaGroupCount(String search) {
		Criteria crit = hibernate.getCriteria(AreaGroupListVVO.class);
		crit.add(Restrictions.eq("status", "Y"));
		crit.add(Restrictions.like("name", "%"+(null==search?"":search)+"%"));
		crit.setProjection(Projections.rowCount());

		//long cnt = hibernate.getCriteriaCount(AreaGroupJoinVO.class, Restrictions.like("name", "%"+(null==search?"":search)+"%"), Projections.rowCount());
		long cnt = ((Long) crit.uniqueResult()).intValue();
		return cnt;
	}

	public List<AreaGroupListVVO> getAreaGroupList(DataTableVO dataTable, String search) {
		List<AreaGroupListVVO> list = null;
		Criteria crit = hibernate.getCriteria(AreaGroupListVVO.class);
		crit.add(Restrictions.eq("status", "Y"));
		crit.add(Restrictions.like("name", "%"+(null==search?"":search)+"%"));
		crit.addOrder(Order.desc("group_id"));
		crit.setFirstResult(dataTable.getStart());
		crit.setMaxResults(dataTable.getLength());
		list = crit.list();
		
		return list;
	}
	public List<AreaGroupListVVO> getAllAreaGroupList() {
		List<AreaGroupListVVO> list = null;
		Criteria crit = hibernate.getCriteria(AreaGroupListVVO.class);
		crit.add(Restrictions.eq("status", "Y"));
		crit.addOrder(Order.asc("name"));
		list = crit.list();
		
		return list;
	}
	public AreaGroupJoinVO getAreaGroup(Hibernater s, Integer group_id) {
		Criteria crit = s.createCriteria(AreaGroupJoinVO.class).add(Restrictions.eq("group_id", group_id));
		return (AreaGroupJoinVO)crit.uniqueResult();
	}


	public AreaGroupJoinVO getAreaGroup(int group_id) {
		AreaGroupJoinVO at = null;
		Criteria crit = hibernate.createCriteria(AreaGroupJoinVO.class).add(Restrictions.eq("group_id", group_id));
		at = (AreaGroupJoinVO)crit.uniqueResult();
		return at;
	}

	public Code deleteAreaGroup(int group_id) {
		hibernate.flushClearBeginTransaction();

		Query query = hibernate.createQuery("update AreaGroupVO set status = 'N' WHERE GROUP_ID = :group_id");
		query.setParameter("group_id", group_id);
		query.executeUpdate();
		
		/*
		Query query = hibernate.createQuery("delete AreaGroupMapVO WHERE GROUP_ID = :group_id");
		query.setParameter("group_id", group_id);
		query.executeUpdate();
		
		
		query = hibernate.createQuery("delete AreaGroupVO WHERE GROUP_ID = :group_id");
		query.setParameter("group_id", group_id);
		query.executeUpdate();
		*/
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}

	public Code createAreaGroup(AreaGroupJoinVO areaGroup) {
		hibernate.flushClearBeginTransaction();
		hibernate.saveList(areaGroup.getAreas(),AreaGroupMapVO.class);
		hibernate.save(areaGroup);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}

	public Code updateAreaGroup(AreaGroupJoinVO areaGroup) {
		hibernate.flushClearBeginTransaction();
		hibernate.update(areaGroup);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}

	//HSQL  --> http://www.mkyong.com/hibernate/hibernate-query-examples-hql/
	public Code uAreaGroup_duSubArea(AreaGroupJoinVO areaGroup) {
		hibernate.flushClearBeginTransaction();
		
		
		if(null!=areaGroup.getGroup_id()){ // 처음이 아니면 우선자식들 지워라 
			hibernate.update(areaGroup);
			Query query = hibernate.createQuery("delete AreaGroupMapVO WHERE GROUP_ID = :group_id");
			query.setParameter("group_id", areaGroup.getGroup_id());
			query.executeUpdate();
		}else{ //처음이면 insert
			hibernate.save(areaGroup);
		}
		
		if(areaGroup.getAreas().size()>0){
			areaGroup.getAreas().forEach(at->{
				at.setGroup_id(areaGroup.getGroup_id());
			});
		}
		
		hibernate.saveList(areaGroup.getAreas(),AreaGroupMapVO.class);
        hibernate.flushClearCommit();
        return Code.SUCCESS;
	}

}
