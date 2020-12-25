package com.ceragem.iot.api.domain;

import com.ceragem.iot.api.code.UserRole;
import com.ceragem.iot.core.domain.CoreUserHasProduct;
import com.ceragem.iot.core.domain.base.UserBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user")
@NamedEntityGraph(name = "User.userHasProducts", attributeNodes = @NamedAttributeNode("userHasProducts"))
public class User extends UserBase {

    @OneToMany
    @JoinColumn(name = "user_no", referencedColumnName = "no", insertable = false, updatable = false)
    @JsonView({JsonViewFrontEnd.class})
    private List<CoreUserHasProduct> userHasProducts;


    @Transient
    public Set<SimpleGrantedAuthority> getRoles() {
        Set<SimpleGrantedAuthority> grantedAuthorities = UserRole.USER.getGrantedAuthorities();
        List<CoreUserHasProduct> list = Optional.ofNullable(getUserHasProducts()).orElse(Collections.EMPTY_LIST);
        list.stream().forEach(it -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("product:"+it.getNo()));
        });
        return grantedAuthorities;
    }

}
