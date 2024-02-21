package com.blog.bl.service.impl;

import com.blog.bl.entity.Comment;
import com.blog.bl.entity.Post;
import com.blog.bl.exception.ResourceNotFoundException;
import com.blog.bl.payload.CommentDto;
import com.blog.bl.repository.CommentRepository;
import com.blog.bl.repository.PostRepository;
import com.blog.bl.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found with this id : " + postId)
        );

        Comment comment=new Comment();
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        comment.setPost(post);
        Comment saved = commentRepository.save(comment);

        CommentDto dto=new CommentDto();
        dto.setName(saved.getName());
        dto.setBody(saved.getBody());
        dto.setId(saved.getId());
        dto.setEmail(saved.getEmail());


        return dto;
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment Not Found with This id : "+commentId)
        );
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
      List<Comment> comments= commentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> all = commentRepository.findAll();
        List<CommentDto> commentDtos = all.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto updateComment(long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("No comment found with this id: " + commentId)
        );
        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        commentRepository.save(comment);
        CommentDto dto = mapToDto(comment);
        return dto;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto=new CommentDto();
        dto.setName(comment.getName());
        dto.setBody(comment.getBody());
        dto.setId(comment.getId());
        dto.setEmail(comment.getEmail());
        return dto;
    }

}
