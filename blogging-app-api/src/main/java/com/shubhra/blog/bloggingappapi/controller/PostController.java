package com.shubhra.blog.bloggingappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shubhra.blog.bloggingappapi.payload.ApiResponse;
import com.shubhra.blog.bloggingappapi.payload.PostDto;
import com.shubhra.blog.bloggingappapi.payload.PostResponse;
import com.shubhra.blog.bloggingappapi.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    // create

    @PostMapping("/user/{userId}/category/{catId}/post")
    ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer catId) {

        PostDto newpostDto = this.postService.createPost(postDto, userId, catId);
        return new ResponseEntity<PostDto>(newpostDto, HttpStatus.CREATED);
    }

    // get by User

    @GetMapping("/user/{userId}/post")

    ResponseEntity<List<PostDto>> getPostByuser(@PathVariable Integer userId) {
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    // get by category

    @GetMapping("/category/{categoryId}/post")

    ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    // get by post id

    @GetMapping("/post/{postId}")
    ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postId) {
        PostDto pdto = this.postService.getPostByPostId(postId);
        return new ResponseEntity<PostDto>(pdto, HttpStatus.OK);
    }

    // get all posts

    @GetMapping("/post")
    ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy) {

        PostResponse postres = this.postService.getAllPost(pageNumber, pageSize, sortBy);
        return new ResponseEntity<PostResponse>(postres, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}")

    ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto post = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(post, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/post/{postId}")
    ResponseEntity<ApiResponse> deletePostByPostId(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Post Successfully", true), HttpStatus.OK);
    }

    // search

    @GetMapping("/post/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        List<PostDto> results = this.postService.searchPost(keywords);
        return new ResponseEntity<List<PostDto>>(results, HttpStatus.OK);
    }

}
