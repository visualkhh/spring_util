package com.ceragem.iot.core.domain.base;

import com.ceragem.iot.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class AdminBase extends ModelBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    Long no;

    @Column(name = "id")
    String id;

    @Column(name = "password")
    String password;


    @PrePersist
    protected void onCreate() {
    }

}
