package com.visualkhh.api.domain;

import com.visualkhh.common.domain.FitBrainBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter @Entity @Table(name = "T_FIT_BRAIN")
public class FitBrain extends FitBrainBase {
    @OneToOne
    @JoinTable(name = "T_FIT_BRAIN_IMG",
            joinColumns = @JoinColumn(name = "FIT_BRAIN_SEQ"),
            inverseJoinColumns = @JoinColumn(name = "FILE_SEQ")
    )
    private File imgFile;

    @OneToOne
    @JoinTable(name = "T_FIT_BRAIN_ICON",
            joinColumns = @JoinColumn(name = "FIT_BRAIN_SEQ"),
            inverseJoinColumns = @JoinColumn(name = "FILE_SEQ")
    )
    private File iconFile;

    @OneToMany
    @JoinColumn(name = "FIT_BRAIN_SEQ", referencedColumnName = "FIT_BRAIN_SEQ")
    private List<FitBrainKeyword> fitBrainKeywordCd;
}
