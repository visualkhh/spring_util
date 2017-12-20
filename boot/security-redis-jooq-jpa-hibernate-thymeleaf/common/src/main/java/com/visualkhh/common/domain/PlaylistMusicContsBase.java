package com.visualkhh.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @MappedSuperclass @EqualsAndHashCode(callSuper=false)
public class PlaylistMusicContsBase extends DomainBase implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer playlistMusicContsSeq;

    @Column
    private Integer playlistSeq;

    @Column
    private Integer musicContsSeq;
}
