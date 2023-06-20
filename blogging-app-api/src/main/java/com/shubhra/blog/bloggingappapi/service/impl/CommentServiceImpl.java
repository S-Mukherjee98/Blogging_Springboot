package com.shubhra.blog.bloggingappapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubhra.blog.bloggingappapi.entity.Comment;
import com.shubhra.blog.bloggingappapi.entity.Post;
import com.shubhra.blog.bloggingappapi.exception.ResourceNotFound;
import com.shubhra.blog.bloggingappapi.payload.CommentDto;
import com.shubhra.blog.bloggingappapi.repository.CommentRepo;
import com.shubhra.blog.bloggingappapi.repository.PostRepo;
import com.shubhra.blog.bloggingappapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        // TODO Auto-generated method stub
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "post Id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        CommentDto savCommentDto = this.modelMapper.map(savedComment, CommentDto.class);
        return savCommentDto;

    }

    @Override
    public void deleteComment(Integer commentId) {
        // TODO Auto-generated method stub
        Comment com = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFound("Comment", "comment Id ", commentId));
        this.commentRepo.delete(com);
    }

}
