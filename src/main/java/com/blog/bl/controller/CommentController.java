package com.blog.bl.controller;

import com.blog.bl.entity.Comment;
import com.blog.bl.payload.CommentDto;
import com.blog.bl.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    private CommentService commentService;
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestParam("postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);


        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment is Deleted",HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable long postId){
        List<CommentDto> commentsByPostId = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentsByPostId,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
        List<CommentDto> allComments = commentService.getAllComments();
        return new ResponseEntity<>(allComments,HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public  ResponseEntity<CommentDto> updateComment(
            @PathVariable long commentId,
            @RequestBody CommentDto commentDto
    ){
        CommentDto dto=commentService.updateComment(commentId,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
