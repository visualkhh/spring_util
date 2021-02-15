package com.clone.chat.service;

import com.clone.chat.code.MD5Generator;
import com.clone.chat.domain.File;
import com.clone.chat.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;
    private final S3Service s3Service;

    @Transactional
    public com.clone.chat.domain.File save(MultipartFile file){
        String originalFileName = "";
        String fileName = "";
        String filePath = "";
        Long fileSize = 0L;

        try {
            fileName = new MD5Generator(file.getInputStream()).toString();
            originalFileName = file.getOriginalFilename();
            fileSize = file.getSize();
            filePath = s3Service.upload(file,fileName);

        } catch(Exception e){
            e.getStackTrace();
        }
        File fileEntity = File.builder()
                .originalFileName(originalFileName)
                .fileName(fileName)
                .filePath(filePath)
                .fileSize(fileSize)
                .build();
        return fileRepository.save(fileEntity);
    }

    public com.clone.chat.domain.File
    findOne(Long id) {
        return fileRepository.getOne(id);
    }

}
