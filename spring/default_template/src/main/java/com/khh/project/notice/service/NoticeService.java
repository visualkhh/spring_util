package com.khh.project.notice.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.khh.code.Code;
import com.khh.config.ConfigManager;
import com.khh.project.notice.vo.NoticeJoinVO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.khh.DefaultService;
import com.khh.project.notice.vo.NoticeFileVO;
import com.khh.project.notice.vo.NoticeVO;
import com.khh.project.vo.DataTableVO;
import com.omnicns.java.db.hibernate.Hibernater;
import com.omnicns.java.file.FileUtil;
import com.omnicns.java.string.StringUtil;
import com.khh.security.SecurityManager;

@Service("NoticeService")
@Transactional(rollbackOn = { Exception.class })
public class NoticeService extends DefaultService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ConfigManager configMng;
	@Autowired
	Hibernater hibernate;

	public List<NoticeJoinVO> getList(DataTableVO dataTable) {
		Criteria crit = hibernate.createCriteria(NoticeJoinVO.class);
		crit.add(Restrictions.like("contents", "%"+(null==dataTable.getSearchValue()?"":dataTable.getSearchValue())+"%"));
		crit.addOrder(Order.desc("notice_id"));
		crit.setFirstResult(dataTable.getStart());
		crit.setMaxResults(dataTable.getLength());
		
		List<NoticeJoinVO> list = crit.list();
		return list;
	}
	public List<NoticeJoinVO> getList(Criterion where) {
		List<Criterion> list = new ArrayList<Criterion>();list.add(where);
		return getList(list);
	}
	public List<NoticeJoinVO> getList(Order order) {
		List<Order> olist = new ArrayList<Order>();olist.add(order);
		return getList(new ArrayList<Criterion>(),olist);
	}
	public List<NoticeJoinVO> getList(Criterion where,Order order) {
		List<Criterion> list = new ArrayList<Criterion>();list.add(where);
		List<Order> olist = new ArrayList<Order>();olist.add(order);
		return getList(list,olist);
	}
	public List<NoticeJoinVO> getList(Criterion where,Order order,int first,int max) {
		List<Criterion> list = new ArrayList<Criterion>();list.add(where);
		List<Order> olist = new ArrayList<Order>();olist.add(order);
		return getList(list,olist,first,max);
	}
	public List<NoticeJoinVO> getList(List<Criterion> where) {
		return getList(where,new ArrayList<Order>());
	}
	public List<NoticeJoinVO> getList(List<Criterion> where,List<Order> order) {
		Criteria crit = hibernate.createCriteria(NoticeJoinVO.class);
		
		if(null!=where)
		where.stream().forEach(at->{crit.add(at);});
		if(null!=order)
		order.stream().forEach(at->{crit.addOrder(at);});
		List<NoticeJoinVO> list = crit.list();
		return list;
	}
	public List<NoticeJoinVO> getList(List<Criterion> where,List<Order> order,int first, int max) {
		Criteria crit = hibernate.createCriteria(NoticeJoinVO.class);
		
		if(null!=where)
			where.stream().forEach(at->{crit.add(at);});
		if(null!=order)
			order.stream().forEach(at->{crit.addOrder(at);});
		crit.setFirstResult(first);
		crit.setMaxResults(max);
		List<NoticeJoinVO> list = crit.list();
		return list;
	}

	public int getCount(DataTableVO dataTable) {
		Criteria crit = hibernate.createCriteria(NoticeJoinVO.class);
		crit.add(Restrictions.like("contents", "%"+(null==dataTable.getSearchValue()?"":dataTable.getSearchValue())+"%"));
        crit.setProjection(Projections.rowCount());
        int cnt = ((Long)crit.uniqueResult()).intValue();
		return cnt;
	}

	public NoticeJoinVO getNotice(Integer notice_id) {
		Criteria crit = hibernate.createCriteria(NoticeJoinVO.class).add(Restrictions.eq("notice_id", notice_id));
		NoticeJoinVO at = (NoticeJoinVO) crit.uniqueResult();
		return at;
	}
	
	public Code delete(Integer notice_id) {
		hibernate.flushClearBeginTransaction();
		Query query = hibernate.createQuery("delete NoticeVO WHERE notice_id = :notice_id");
		query.setParameter("notice_id", notice_id);
		query.executeUpdate();
		
		query = hibernate.createQuery("delete NoticeFileVO WHERE notice_id = :notice_id");
		query.setParameter("notice_id", notice_id);
		query.executeUpdate();
		
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}

	public Code updateNotice(NoticeVO notice) {
		notice.setCreate_id(SecurityManager.getInstance().getSecurityUser().getOperator_id());
		hibernate.flushClearBeginTransaction();
		hibernate.update(notice);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}

	public Code insertNotice(NoticeVO notice) throws UnsupportedEncodingException {
//		notice.setTitle(new String(notice.getTitle().getBytes("8859_1"),"UTF-8"));
//		notice.setContents(new String(notice.getContents().getBytes("8859_1"),"UTF-8"));
		notice.setCreate_id(SecurityManager.getInstance().getSecurityUser().getOperator_id());
		hibernate.flushClearBeginTransaction();
		hibernate.save(notice);
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}
	public Code deleteInsertFile(NoticeFileVO notice) throws Exception {
		MultipartFile file = notice.getFile();
		if(null!=file && !file.isEmpty()){
			
			if(!StringUtil.isMatches(file.getOriginalFilename(),configMng.getParam("notice_data_name_regex"))){
				throw new Exception(Code.VALIDATE_FILE_EXTENSION_FAIL.msg());
			}
			if(null!=file && !file.isEmpty() && file.getSize()>Integer.parseInt(configMng.getParam("notice_max_upload_size"))){
				throw new Exception(Code.VALIDATE_FILE_SIZE_FAIL.msg());
			}
			hibernate.flushClearBeginTransaction();
			
			notice.setFile_name(file.getOriginalFilename());
//			Query query = hibernate.createQuery("delete NoticeFileVO WHERE notice_id = :notice_id");
//			query.setParameter("notice_id", notice.getNotice_id());
//			query.executeUpdate();
//			hibernate.flushClear();
			Query query = hibernate.createQuery("delete NoticeFileVO where notice_id=:notice_id");
			query.setParameter("notice_id", notice.getNotice_id());
			query.executeUpdate();
			
			
			hibernate.save(notice);
			
			
			//file save
			String fileFolder = configMng.getBindParam("notice_data");
//			String fileName = notice.getNotice_id()+"_"+notice.getFile_id()+"_"+file.getOriginalFilename();
			String fileName = String.valueOf(notice.getNotice_id());
			log.debug(fileName);
			FileUtil.mkdirs(fileFolder);
			
			FileSystemResource upfile = new FileSystemResource(fileFolder+fileName);
			file.transferTo(upfile.getFile());
			
			hibernate.flushClearCommit();
		}
		return Code.SUCCESS;
	}
	public Code deleteFile(Integer notice_id) throws IllegalStateException, IOException {
		hibernate.flushClearBeginTransaction();
		Query query = hibernate.createQuery("delete NoticeFileVO where notice_id=:notice_id");
		query.setParameter("notice_id", notice_id);
		query.executeUpdate();
		hibernate.flushClearCommit();
		return Code.SUCCESS;
	}
	
	public FileSystemResource fileDonwload(NoticeFileVO notice) throws IllegalStateException, IOException {
		String fileFolder = configMng.getBindParam("notice_data");
		String fileName = String.valueOf(notice.getNotice_id());
		hibernate.refresh(notice);
		
		FileSystemResource file = new FileSystemResource(fileFolder+fileName){
			@Override
			public String getFilename() {
				try {
					return StringUtil.urlEncode(notice.getFile_name(), StringUtil.SET_UTF_8);
				} catch (UnsupportedEncodingException e) {
					return notice.getFile_name();
				}
			}
		};
		
		
		if(!file.exists()){
			throw new FileNotFoundException("파일이 존재 하지 않습니다.");
		}
		
		return file;
	}
	
	

}
