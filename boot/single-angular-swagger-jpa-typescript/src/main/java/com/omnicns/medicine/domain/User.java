package com.omnicns.medicine.domain;

import com.omnicns.medicine.code.ConformityCd;
import com.omnicns.medicine.domain.base.UserBase;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "T_USER")
//@NamedEntityGraph(name = "User.gameSetWeekRsts", attributeNodes = @NamedAttributeNode("gameSetWeekRsts"))
public class User extends UserBase {
//    @OneToMany
//    @JoinColumn(name="USER_SEQ" , referencedColumnName  = "USER_SEQ", insertable = false, updatable = false)
//    @OrderBy("regDt DESC")
//    List<GameSetRst> gameSetRsts;

//    public User(User user, String conformityCd) {
//        this.include(user);
//        setConformityCd(ConformityCd.valueOf(conformityCd));
//    }

//    @Transient
//    ConformityCd conformityCd;
//
//    @OneToMany
//    @JoinColumn(name = "USER_SEQ", referencedColumnName = "USER_SEQ", insertable = false, updatable = false)
//    @OrderBy("date DESC")
//    List<GameSetDayRst> gameSetDayRsts;

    @OneToMany
    @JoinColumn(name = "USER_SEQ", referencedColumnName = "USER_SEQ", insertable = false, updatable = false)
    @OrderBy("yearweek DESC")
    List<GameSetWeekRst> gameSetWeekRsts;
}
