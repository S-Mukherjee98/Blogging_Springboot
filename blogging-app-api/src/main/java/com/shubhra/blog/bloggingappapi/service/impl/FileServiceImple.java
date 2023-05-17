package com.shubhra.blog.bloggingappapi.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.shubhra.blog.bloggingappapi.service.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImple implements FileService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadPostPic(MultipartFile multipartFile) throws IOException {
        // TODO Auto-generated method stub

        String postUrl;
        postUrl = cloudinary.uploader()
                .upload(multipartFile.getBytes(), Map.of("public_id", UUID.randomUUID().toString())).get("url")
                .toString();
        System.out.println("The Link is : " + postUrl);
        return postUrl;
    }

}
