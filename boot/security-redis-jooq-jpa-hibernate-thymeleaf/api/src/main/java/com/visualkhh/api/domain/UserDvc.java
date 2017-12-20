package com.visualkhh.api.domain;

import com.visualkhh.common.domain.UserDvcBase;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false) @Entity
@Table(name = "T_USER_DVC") @AllArgsConstructor @NoArgsConstructor
public class UserDvc extends UserDvcBase {

    public UserDvc(int userDvcSeq, String ageCd, String genCd, String lstLginIp, Integer userSeq, Integer dvcSeq, ZonedDateTime regDt) {
        super(userDvcSeq, ageCd, genCd, lstLginIp, userSeq, dvcSeq, regDt);
    }

    @ManyToOne
    @JoinColumn(name="USER_SEQ", referencedColumnName = "USER_SEQ", insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="DVC_SEQ", referencedColumnName = "DVC_SEQ", insertable=false, updatable=false)
    private DvcInfo dvcInfo;
}
