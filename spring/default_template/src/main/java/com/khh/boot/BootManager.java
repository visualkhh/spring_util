package com.khh.boot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.khh.boot.vo.BootArea;
import com.khh.boot.vo.BootCodeVO;
import com.khh.boot.vo.BootMenuVO;
import com.khh.boot.vo.BootRightGroupJoinVO;
import com.khh.project.msg.vo.MsgClassVO;
import com.khh.project.msg.vo.MsgTemplateVVO;
import com.khh.project.area.vo.AreaVO;

public class BootManager {
	static BootManager instance = null;
	LinkedHashMap<String,BootCodeVO> codeMap = new LinkedHashMap<String, BootCodeVO>();
	LinkedHashMap<String,BootMenuVO> menuMap = new LinkedHashMap<String, BootMenuVO>();
	
	Map<Integer,BootArea> area 	= null;
	List<MsgClassVO> msgClass	= null;
	List<MsgTemplateVVO> msgTemplate	= null;
	List<BootRightGroupJoinVO> rightGroup;
	
	static public BootManager getInstance(){
        if(instance==null)
            instance = new BootManager();
        return instance;
    }
	private BootManager() {
	}
//	
//	
//	
	public void putMenu(String key, BootMenuVO value) {
		menuMap.put(key, value);
	}
	public void putCode(String key, BootCodeVO value) {
		codeMap.put(key, value);
	}
	
	
	
	
	public Map<Integer,BootArea> getArea() {
		return area;
	}
	public BootArea getArea(Integer area_id) {
		return area.get(area_id);
	}
	public void setArea(List<AreaVO> areaList) {
//		Map<Integer,BootArea> full = area.stream().map(at->new BootArea(at.getArea_id(),at.getArea1())).collect(Collectors.toMap(BootArea::getArea_id,Function.identity()));
//		Map<Integer,BootArea> root = area.stream().filter(at->"전체".equals(at.getArea2())).map(at->new BootArea(at.getArea_id(),at.getArea1())).collect(Collectors.toMap(BootArea::getArea_id,at->at));
		//root구한다
		area = areaList.stream().filter(at->"전체".equals(at.getArea2())).map(at->new BootArea(at.getArea_id(),at.getArea1(),at.getOrder_id1())).collect(
				Collectors.toMap(BootArea::getArea_id,Function.identity(),(a,b)->a,LinkedHashMap::new)
		);
		area.entrySet().stream().forEach(atRootAreaEntity->{
			Integer atRootArea_id  	= atRootAreaEntity.getKey();
			BootArea atRootArea 	= atRootAreaEntity.getValue();
			
			//2번째depth찾기   :  3번째 area가 없는것이 2번째 depth다
			areaList.stream().filter(a2t-> atRootArea_id!=a2t.getArea_id() && atRootArea.getArea().equals(a2t.getArea1()) &&  null==a2t.getArea3()).forEach(a2t->{ 
				BootArea area2 = new BootArea(a2t.getArea_id(),a2t.getArea2(),a2t.getOrder_id2(),atRootArea);
				atRootArea.addChild(area2);
				
				//3번째 depth찾기  
//				areaList.stream().filter(
//						a3t-> null!=a3t.getArea3() && atRootArea.getArea().equals(a3t.getArea1()) &&
//							  area2.getArea_id()!=a3t.getArea_id() && area2.getArea().equals(a3t.getArea2())
//						).forEach(a3t->{
//					BootArea area3 = new BootArea(a3t.getArea_id(),a3t.getArea3(),0,area2);
//					area2.addChild(area3);
//				});
				
			});
		});
	}
	

	public List<MsgClassVO> getMsgClass() {
		return msgClass;
	}
	public MsgClassVO getMsgClass(Integer class_id) {
		return msgClass.stream().filter(at->class_id.equals(at.getClass_id())).findFirst().orElse(null);
	}

	public List<MsgTemplateVVO> getMsgTemplate() {
		return msgTemplate;
	}

	public void setMsgTemplate(List<MsgTemplateVVO> msgTemplate) {
		this.msgTemplate = msgTemplate;
	}
	
	public void setMsgClass(List<MsgClassVO> msgClass) {
		this.msgClass = msgClass;
	}
	//	
//	public BootCodeVO getCode(String key) {
//		return codeMap.get(key);
//	}
//	public List<BootCodeVO> getCodeByGroup(final String groupCd) {
//		Map<String,BootCodeVO> map = ConvertUtil.accept(codeMap, new Acceptor<Entry>() {
//			@Override
//			public boolean accept(Entry pathname) {
//				BootCodeVO item = (BootCodeVO)pathname.getValue();
//				if(groupCd.equals(item.getCd_grp())){
//					return true;
//				}
//				return false;
//			}
//		});
//		return ConvertUtil.toList(map);
//	}
	public boolean hasCode(String key) {
		boolean sw = false;
		if(null!=codeMap && null!=codeMap.get(key)){
			sw = true;
		}
		return sw;
	}
//	
//	
	public void putCodeAll(HashMap<String,BootCodeVO> value) {
		codeMap.putAll(value);
	}
//	
	public List<BootCodeVO> getCode(){
		List<BootCodeVO> list = new ArrayList<BootCodeVO>(codeMap.values());
		return list;
	}
//	
//	
//	
//	public void putMenu(String key, BootMenuVO value) {
//		menuMap.put(key, value);
//	}
//	
//	public BootMenuVO getMenu(String key) {
//		return menuMap.get(key);
//	}
//	public List getMenuByHidden() {
//		return getMenuByHiddenYN("Y");
//	}
//	public List getMenuByShow() {
//		return getMenuByHiddenYN("N");
//	}
//	public List getMenuByHiddenYN(final String hiddenYN) {
//		Map<String,BootMenuVO> map = ConvertUtil.accept(menuMap, new Acceptor<Entry>() {
//			@Override
//			public boolean accept(Entry pathname) {
//				BootMenuVO item = (BootMenuVO)pathname.getValue();
//				if(hiddenYN.equals(item.getHidden_yn())){
//					return true;
//				}
//				return false;
//			}
//		});
//		return ConvertUtil.toList(map);
//	}
//	
//	
//	public void putMenuAll(HashMap<String,BootMenuVO> value) {
//		menuMap.putAll(value);
//	}
//	
//	public List<BootMenuVO> getMenu(){
//		List<BootMenuVO> list = new ArrayList<BootMenuVO>(menuMap.values());
//		return list;
//	}
	public void setRightGroup(List<BootRightGroupJoinVO> rightGroup) {
		this.rightGroup=rightGroup;
		
	}
	public List<BootRightGroupJoinVO> getRightGroup() {
		return rightGroup;
	}
	public void setArea(Map<Integer, BootArea> area) {
		this.area = area;
	}
	
	
	
	
	
	
}
