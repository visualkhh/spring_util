package com.visualkhh.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.visualkhh.common.domain.CodeBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @EqualsAndHashCode(callSuper=false) @Entity @Table(name = "T_CODE")
@NamedEntityGraph(name = "Code.codes",attributeNodes = @NamedAttributeNode("codes"))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Code extends CodeBase {


//    @ManyToOne(optional = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "cd", referencedColumnName = "prntCd")
//    private Code prntCode;
//
//    @OneToMany(fetch = FetchType.LAZY)
    @OneToMany
    @JoinColumn(name = "PRNT_CD", referencedColumnName = "CD")
    private List<Code> codes;


//    @ManyToOne(fetch= FetchType.LAZY)
//    @JoinColumn(name="prntCd")
//    private Code prnt;

//    @OneToMany
//    @JoinColumn(name = "prntCd", referencedColumnName = "cd")
////    @JsonManagedReference
//    Set<Code> codes;



    public void addCode(Code child){
        if(null==this.codes){
            this.codes = new ArrayList<>();
        }
        this.codes.add(child);
    }
}
