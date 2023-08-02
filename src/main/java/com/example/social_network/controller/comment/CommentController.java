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


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comment>> findById(@PathVariable Long id){
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()){
            return new ResponseEntity<>(commentOptional , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

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

    @GetMapping("/list/{id}")
    private ResponseEntity<?> getAllCommentsByPostId(@PathVariable Long id) {
        List<Comment> listCommentByPost = commentService.getAllCommentsByPostId(id);
        if (!listCommentByPost.isEmpty()) {
            return new ResponseEntity<>(listCommentByPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
