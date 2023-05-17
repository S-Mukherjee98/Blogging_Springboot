package com.shubhra.blog.bloggingappapi.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadPostPic(MultipartFile multipartFile) throws IOException;
}
