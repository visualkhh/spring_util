package com.khh.project.stat.service;

import java.util.List;

import javax.script.ScriptException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khh.DefaultService;
import com.khh.project.stat.vo.StatSearchVO;
import com.khh.project.stat.vo.StatVO;
import com.omnicns.java.db.hibernate.Hibernater;
import com.omnicns.java.string.StringUtil;

@Service("StatService")
@Transactional(rollbackOn = { Exception.class })
public class StatService extends DefaultService {
	@Autowired
	Hibernater hibernate;
	public List<StatVO> getAreaList(StatSearchVO search) throws ScriptException {
		List<StatVO> list = null;
		search.setSearch_telecom(StringUtil.SQLInjectionFilter(search.getSearch_telecom()));
		list = hibernate.executeNamedGQuerys("getAreaStat", search, StatVO.class);
		return list;
	}
	
	public List<StatVO> getTypeList(StatSearchVO search) throws ScriptException {
		List<StatVO> list = null;
		search.setSearch_telecom(StringUtil.SQLInjectionFilter(search.getSearch_telecom()));
		list = hibernate.executeNamedGQuerys("getTypeStat", search, StatVO.class);
		return list;
	}
	
	public List<StatVO> getAllList(StatSearchVO search) throws ScriptException {
		List<StatVO> list = null;
		search.setSearch_telecom(StringUtil.SQLInjectionFilter(search.getSearch_telecom()));
		list = hibernate.executeNamedGQuerys("getAllStat", search, StatVO.class);
		return list;
	}
	
}
