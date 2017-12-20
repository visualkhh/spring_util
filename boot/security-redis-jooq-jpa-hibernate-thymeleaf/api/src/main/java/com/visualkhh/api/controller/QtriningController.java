package com.visualkhh.api.controller;

import com.visualkhh.api.domain.Playlist;
import com.visualkhh.api.repository.PlaylistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@EnableCaching
@RestController
@RequestMapping("/api"+QtriningController.URI_PREFIX)
public class QtriningController {
    public static final String URI_PREFIX 		= "/qtraining";

    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping(value={"","/"})
    public Collection<Playlist> getAppVersion(){
        Sort s = new Sort(Sort.Direction.ASC, "playlistOrd");
        return playlistRepository.findAll(s);
    }

}
