package com.company.service.monitor.model;

import java.util.Date;

//@AllArgsConstructor @NoArgsConstructor
public interface SignUpUserScope {
    String getMinRegDt();
    String getMaxRegDt();
    Integer getTotCnt();
    Integer getMem01();
    Double getMemRat01();
}
