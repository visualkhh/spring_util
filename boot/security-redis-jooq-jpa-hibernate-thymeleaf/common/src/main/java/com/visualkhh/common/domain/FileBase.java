package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class FileBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "FILE_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer fileSeq;

    @Column(name = "FILE_NM")
    private String fileNm;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "FILE_SIZE")
    private Integer fileSize;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "PLAY_TIME")
    private Integer playTime;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
