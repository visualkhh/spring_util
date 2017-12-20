package com.khh.project.msg.vo;

import java.util.ArrayList;

import lombok.Data;

import com.khh.DefaultVO;
public @Data class MsgRegWaitVO extends DefaultVO {
	private static final long serialVersionUID = 3957786793896721440L;
	String 	dummy;				//=&//	dummy=&
	Integer operator_id;		//=2564&//	operator_id=2564&
	String 	test_ch;			//=N&//	test_ch=N&
	Integer send_mode;			//=1&//	send_mode=1&
	Integer area_group;			//=&//	area_group=&
	String 	callback_type;		//=TXT&//	callback_type=TXT&
	Integer callback_data;		//=1&//	callback_data=1&
	Integer grade_id;			//=1&//	grade_id=1&
	Integer class_id;			//=1&//	class_id=1&
	String ch_id;				//ch_id=4372&  안들어올수도있음
	Integer template_id;		//=2013&//	template_id=2013&
	ArrayList<Integer> 	company_id 		= new ArrayList<Integer>();		//[0]=0&//	company_id[0]=0&
	ArrayList<String> 	contents 		= new ArrayList<String>();		//[0]=메시지&//	contents[0]=메시지&
	ArrayList<Integer> 	area_id 		= new ArrayList<Integer>();		//[0]=236&//	area_id[0]=236&
//	area_id[1]=25&//	area_id[1]=25&
//	area_id[2]=41&//	area_id[2]=41&
//	area_id[….] 개수만큼 넘어옴//	area_id[….] 개수만큼 넘어옴

	
}
