package com.visualkhh.api.controller;

import com.omnicns.java.property.PropertyUtil;
import com.visualkhh.common.code.Code;
import com.visualkhh.common.exception.ErrorException;
import com.visualkhh.common.model.error.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j @EnableCaching
@RestController
@RequestMapping(ResourceController.URI_PREFIX)
public class ResourceController {
        public static final String URI_PREFIX 		= "/resource";

    @Value("${project.properties.resource.music-file-path}")
    private String musicFilePath = null;
    @Value("${project.properties.resource.video-file-path}")
    private String videoFilePath = null;

    @GetMapping(value="/vidoes/{fileName:.+}")
    public FileSystemResource vidoes(@PathVariable(name = "fileName") String fileName){
        FileSystemResource fileSystemResource = new FileSystemResource(new File(videoFilePath+ PropertyUtil.getFileSeparator()+fileName));
        if(!fileSystemResource.exists()){
            throw  new ErrorException(new Error(Code.E10003));
        }
        return fileSystemResource;
    }
    @GetMapping(value="/musics/{fileName:.+}")
    public FileSystemResource musics(@PathVariable(name = "fileName") String fileName){
        FileSystemResource fileSystemResource = new FileSystemResource(new File(musicFilePath+ PropertyUtil.getFileSeparator()+fileName));
        if(!fileSystemResource.exists()){
            throw  new ErrorException(new Error(Code.E10003));
        }
        return fileSystemResource;
    }
}
