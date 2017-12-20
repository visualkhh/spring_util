package com.visualkhh.api.domain;

import com.visualkhh.common.domain.UserBase;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_USER") @AllArgsConstructor @NoArgsConstructor
public class User extends UserBase{

    public User(Integer userSeq, String cponId, String useYn, ZonedDateTime lstLginDt, ZonedDateTime regDt) {
        super(userSeq, cponId, useYn, lstLginDt, regDt);
    }
    public User(Integer userSeq, String cponId, String useYn, ZonedDateTime lstLginDt, ZonedDateTime regDt, List<UserDvc> userDvcs) {
        super(userSeq, cponId, useYn, lstLginDt, regDt);
        this.userDvcs = userDvcs;
    }


    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_SEQ", referencedColumnName = "USER_SEQ")
    private List<UserDvc> userDvcs;


    public void addUserDvc(UserDvc userDvc){
        if(null==this.userDvcs){
            this.userDvcs = new ArrayList<>();
        }
        this.userDvcs.add(userDvc);
    }

}