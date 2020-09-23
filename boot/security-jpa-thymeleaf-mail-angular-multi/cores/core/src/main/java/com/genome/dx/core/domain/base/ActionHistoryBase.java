package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.code.ActionCd;
import com.genome.dx.core.model.ModelBase;
import com.genome.dx.core.model.view.json.JsonViewFrontEnd;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class ActionHistoryBase extends ModelBase implements Serializable {

    @Builder
    public ActionHistoryBase(Long admSeq, ActionCd actCd, Long urlSeq, String url, String ip, String request, String response, ZonedDateTime regDt) {
        this.admSeq = admSeq;
        this.actCd = actCd;
        this.urlSeq = urlSeq;
        this.url = url;
        this.ip = ip;
        this.request = request;
        this.response = response;
        this.regDt = regDt;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACT_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long actSeq;

    @Column(name = "ADM_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long admSeq;

    @Column(name = "ACT_CD")
    @JsonView({JsonViewFrontEnd.class})
    @Enumerated(EnumType.STRING)
    ActionCd actCd;

    @Column(name = "URL_SEQ")
    @JsonView({JsonViewFrontEnd.class})
    Long urlSeq;

    @Column(name = "URL")
    @JsonView({JsonViewFrontEnd.class})
    String url;

    @Column(name = "IP")
    @JsonView({JsonViewFrontEnd.class})
    String ip;

    @Column(name = "REQUEST")
    @JsonView({JsonViewFrontEnd.class})
    String request;

    @Column(name = "RESPONSE")
    @JsonView({JsonViewFrontEnd.class})
    String response;


    @Column(name = "REG_DT")
    @JsonView({JsonViewFrontEnd.class})
    private ZonedDateTime regDt;


    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }

}
