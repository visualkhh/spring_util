package com.visualkhh.api.domain;

import com.visualkhh.common.domain.MusicContsBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @Entity @Table(name = "T_MUSIC_CONTS")
public class MusicConts extends MusicContsBase {

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "T_MUSIC_CONTS_IMG",
            joinColumns = @JoinColumn(name = "MUSIC_CONTS_SEQ"),
            inverseJoinColumns = @JoinColumn(name = "FILE_SEQ")
    )
    private File imgFile;

    @OneToOne
    @JoinTable(name = "T_MUSIC_CONTS_FILE",
            joinColumns = @JoinColumn(name = "MUSIC_CONTS_SEQ"),
            inverseJoinColumns = @JoinColumn(name = "FILE_SEQ")
    )
    private File file;
}
