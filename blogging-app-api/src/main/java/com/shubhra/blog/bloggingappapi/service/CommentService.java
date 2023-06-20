package com.shubhra.blog.bloggingappapi.service;

import com.shubhra.blog.bloggingappapi.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
