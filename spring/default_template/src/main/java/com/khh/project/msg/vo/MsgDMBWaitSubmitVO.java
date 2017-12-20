package com.khh.project.msg.vo;

import com.khh.DefaultVO;
import com.omnicns.java.date.DateUtil;

import lombok.Data;
public @Data class MsgDMBWaitSubmitVO extends DefaultVO {
	private static final long serialVersionUID = 3957786795896721440L;
	String startYear; 	//	$startYear = $_GET["startYear"];
	String startMonth; 	//	$startMonth = $_GET["startMonth"];
	String startDay; 	//	$startDay = $_GET["startDay"];
	String endYear; 	//	$endYear = $_GET["endYear"];
	String endMonth; 	//	$endMonth = $_GET["endMonth"];
	String endDay; 		//	$endDay = $_GET["endDay"];
	String grade_id; 	//	$grade_id = $_GET["grade_id"];
	String class_id; 	//	$class_id = $_GET["class_id"];
	String status; 		//	$status = $_GET["status"];
	String search_mode; //	$search_mode = $_GET["search_mode"];
	String search_key; 	//	$search_key = $_GET["search_key"];
	String sort_mode; 	//	$sort_mode = $_GET["sort_mode"];
	String sort_key;	//  $sort_key = $_GET["sort_key"];
	Integer current_page;
	
	int length = 15;//페이지당 게시물 개수
	public MsgDMBWaitSubmitVO(){
		init();
	}

	private void init() {
		if(null == this.startYear){
			this.startDay="2006";
		}
		if(null == this.startMonth){
			this.startMonth = DateUtil.getDate("MM");
		}
		if(null == this.startDay){
			this.startDay = DateUtil.getDate("dd");
		}
		if(null == this.endYear){
			this.endYear = DateUtil.getDate("yyyy");
		}
		if(null == this.endMonth){
			this.endMonth = DateUtil.getDate("MM");
		}
		if(null == this.endDay){
			this.endDay = DateUtil.getDate("dd");
		}
		if(null == this.sort_key){
			this.sort_mode="desc";
			this.sort_key="submit_date";
		}
		if(null == this.current_page){
			this.current_page=1;
		}else if(this.current_page.equals(0)){
			this.current_page=1;
		}
	}
	
	public String getStart_dt(){
		return getStartYear()+getStartMonth()+getStartDay();
	}
	public String getEnd_dt(){
		return getEndYear()+getEndMonth()+getEndDay();
	}
	
	public Integer getStart(){
		return (getCurrent_page()*length)-length;
	}
	
}
