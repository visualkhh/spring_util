package com.clone.chat.domain;

import com.clone.chat.domain.base.UserBase;
import com.clone.chat.model.view.json.JsonViewFrontEnd;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER")
@NamedEntityGraph(name = "User.friends", attributeNodes = @NamedAttributeNode("friends"))
public class User extends UserBase {

    @Transient
    String token;

    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_no", referencedColumnName = "no", insertable = false, updatable = false)
//    @JoinColumn(name = "id")
    @JoinTable(name = "USER_FRIEND")
    @JsonView({JsonViewFrontEnd.class})
    private List<User> friends = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

//    @ManyToOne(optional = true, fetch = FetchType.LAZY)
//    @JoinTable(name = "ITEMINCENTIVESMAPPING",
//            joinColumns = { @JoinColumn(name = "INCENTIVEITEMID", referencedColumnName = "ITEMID", insertable = false, updatable = false) },
//            inverseJoinColumns = { @JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID", insertable = false, updatable = false) } )
//    private User parentItem;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "FF",
//            joinColumns = { @JoinColumn(name = "INCENTIVEITEMID", referencedColumnName = "ITEMID") },
//            inverseJoinColumns = { @JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID") } )
//    private Set<User> friends;

    public void addFirend(User... users) {
        if(null == friends) {
            friends = new ArrayList<>();
        }
        friends.addAll(Arrays.asList(users));
    }
}
