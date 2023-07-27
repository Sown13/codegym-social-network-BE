package com.example.social_network.controller.post;

import com.example.social_network.model.post.PostImage;
import com.example.social_network.service.post.post_image.IPostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/post-images")
public class PostImageController {
    @Autowired
    private IPostImageService postImageService;
    @PostMapping("")
    private ResponseEntity<?>createPostImage(@RequestBody PostImage postImage) throws Exception {
        Date date=new Date();
        postImage.setDateCreated(date);
        return new ResponseEntity<>(postImageService.save(postImage), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostImage(@PathVariable("id") Long id) {
        postImageService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Iterable<PostImage>> getImagesByPostId(@PathVariable Long id) {
        return new ResponseEntity<>(postImageService.findImagesByPostId(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateImagePost(@PathVariable Long id,@RequestBody PostImage postImage){
        Optional<PostImage> postImageOptional=postImageService.findById(id);
      if(postImageOptional.isPresent()){
          postImage.setPostImageId(id);
          return new ResponseEntity<>(postImage,HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
