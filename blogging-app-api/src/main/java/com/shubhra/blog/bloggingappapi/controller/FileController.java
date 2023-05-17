package com.shubhra.blog.bloggingappapi.controller;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shubhra.blog.bloggingappapi.entity.Post;
import com.shubhra.blog.bloggingappapi.exception.ResourceNotFound;
import com.shubhra.blog.bloggingappapi.payload.FileResponse;
import com.shubhra.blog.bloggingappapi.payload.PostDto;
import com.shubhra.blog.bloggingappapi.repository.PostRepo;
import com.shubhra.blog.bloggingappapi.service.FileService;
import com.shubhra.blog.bloggingappapi.service.PostService;

@RestController
@RequestMapping("/post")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PostRepo postRepo;

    @PostMapping("/image/{postId}")
    public ResponseEntity<String> uploadPostPic(@RequestParam("multipartFile") MultipartFile multipartFile,
            @PathVariable Integer postId)
            throws IOException {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Post Id: ", postId));

        if (multipartFile.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must Contain File");
        }

        // if (!multipartFile.getContentType().equals("image/jpg")) {
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Must be
        // a jpg file");
        // }
        System.out.println("file service called");
        String postUrl = this.fileService.uploadPostPic(multipartFile);
        System.out.println("post pic uploaded");
        System.out.println(postUrl);
        post.setImageName(postUrl);
        Post newpost = this.postRepo.save(post);
        return ResponseEntity.status(HttpStatus.OK).body(postUrl);
    }

}
