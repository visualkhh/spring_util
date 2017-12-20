package com.khh.login.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.khh.DefaultVO;
import lombok.Data;

import com.google.gson.annotations.Expose;
import com.omnicns.java.random.RandomUtil;

@Entity
@Table(name = "TB_CHECK_CARD")
public @Data class LoginCheckCardJoinVO extends DefaultVO {
	
	private static final long serialVersionUID = -8684813873054858585L;
	@Expose @Id @Column(name="CARD_NO")	String card_no	;//????	VARCHAR2
	@Expose @Column(name="CARD_ID")	String card_id	;//??ID	VARCHAR2
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="CARD_ID",referencedColumnName="CARD_ID" , nullable=true)
	List<LoginCheckCodeVO> codes = new ArrayList<LoginCheckCodeVO>();
	
	@Transient
	Integer checkCodeId;
	
	
	public void refreshCheckCodeId(){
//		ArrayList<Integer> list = new ArrayList<Integer>();
//		LoginCheckCodeVO at = null;
//		for (int i = 0; null!=getCheckCodeId() && null!=getCodes() &&  i < getCodes().size(); i++) {
//			list.add(getCodes().get(i).getCode_id());
//		}
//		checkCodeId = RandomUtil.getRandom(list);
		
		if(null!=getCodes()){
			setCheckCodeId(RandomUtil.getRandom(getCodes()).getCode_id());
		}else{
			setCheckCodeId(null);
		}
	}
	public LoginCheckCodeVO getCheckCode(){
		LoginCheckCodeVO at = null;
		for (int i = 0; null!=getCheckCodeId() && null!=getCodes() &&  i < getCodes().size(); i++) {
			if(getCheckCodeId().equals(getCodes().get(i).getCode_id())){
				at = getCodes().get(i); break;
			}
		}
		return at;
	}
}
