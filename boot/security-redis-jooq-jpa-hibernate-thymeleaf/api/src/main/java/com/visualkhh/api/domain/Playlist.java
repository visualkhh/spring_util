package com.visualkhh.api.domain;

import com.visualkhh.common.domain.PlaylistBase;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter @Entity @Table(name = "T_PLAYLIST")
public class Playlist extends PlaylistBase {
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "T_PLAYLIST_MUSIC_CONTS",
            joinColumns = @JoinColumn(name = "PLAYLIST_SEQ"),
            inverseJoinColumns = @JoinColumn(name = "MUSIC_CONTS_SEQ")
    )
    @OrderBy("musicOrd ASC")
    private Set<MusicConts> musicConts = new HashSet<>();

    @OneToOne
    @JoinTable(name = "T_PLAYLIST_IMG",
            joinColumns = @JoinColumn(name = "PLAYLIST_SEQ"),
            inverseJoinColumns = @JoinColumn(name = "FILE_SEQ")
    )
    private File imgFile;
}
