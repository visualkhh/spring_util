package com.ceragem.iot.core.domain;

import com.ceragem.iot.core.domain.base.UserBase;
import com.ceragem.iot.core.model.view.json.JsonViewFrontEnd;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "user")
@NamedEntityGraph(name = "CoreUser.userHasProducts", attributeNodes = @NamedAttributeNode("userHasProducts"))
public class CoreUser extends UserBase {

    @OneToMany
    @JoinColumn(name = "user_no", referencedColumnName = "no", insertable = false, updatable = false)
    @JsonView({JsonViewFrontEnd.class})
    private List<CoreUserHasProduct> userHasProducts;
}
