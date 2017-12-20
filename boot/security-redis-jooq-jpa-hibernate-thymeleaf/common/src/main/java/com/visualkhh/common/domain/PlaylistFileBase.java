package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class PlaylistFileBase extends DomainBase implements Serializable {
    @Id
    @Column(name = "FILE_SEQ")
    private Integer fileSeq;

    @Column(name = "PLAYLIST_SEQ")
    private Integer playlistSeq;
}
