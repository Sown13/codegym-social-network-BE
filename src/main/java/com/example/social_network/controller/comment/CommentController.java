package com.example.social_network.controller.comment;

import com.example.social_network.dto.comment_dto.CommentDto;
import com.example.social_network.model.comment.Comment;
import com.example.social_network.service.comment.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @PostMapping("")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setTextContent(commentDto.getTextContent());
        comment.setDateCreated(new Date());
        comment.setPost(commentDto.getPost());
        comment.setUser(commentDto.getUser());
        try {
            Comment savedComment = commentService.save(comment);
            return ResponseEntity.ok().body(savedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("")
    private ResponseEntity<Iterable<Comment>>findAllComment(){
        Iterable<Comment>commentList=commentService.findAll();
        return new ResponseEntity<>(commentList,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Comment>deleteCommentById(@PathVariable Long id){
        Optional<Comment>comment=commentService.findById(id);
        if(comment.isPresent()){
            commentService.remove(comment.get().getCommentId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
