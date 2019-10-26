package com.omnicns.medicine.code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel("게시판 카테고리 코드")
public enum BrdCateCd {
    BCC001, //NOTICE("BCC001","공지사항"),
    BCC002, //HELP("BCC002", "도움말"),
    BCC003, //GUIDE("BCC003", "가이드")
}
