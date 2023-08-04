package com.example.social_network.controller.post;

import com.example.social_network.dto.post_image_dto.PostImageDTO;
import com.example.social_network.model.post.PostImage;
import com.example.social_network.service.post.post_image.IPostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @PostMapping("/list")
    private ResponseEntity<?>addMultiplePostImage(@RequestBody List<PostImageDTO> postImageDTOList) {
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        List<PostImage> postImageList = new ArrayList<>();
        for (PostImageDTO postImageDTO : postImageDTOList) {
            PostImage postImage = new PostImage();
            postImage.setDateCreated(now);
            postImage.setPost(postImageDTO.getPost());
            postImage.setImgUrl(postImageDTO.getImgUrl());
            postImageList.add(postImage);
        }
        return new ResponseEntity<>(postImageService.saveAll(postImageList),HttpStatus.CREATED);
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
    @GetMapping("/user/{id}")
    public ResponseEntity<?>getImagesByUserId(@PathVariable Long id){
        Iterable<PostImage>imageIterableList=postImageService.findImagesByUserId(id);
        return new ResponseEntity<>(imageIterableList,HttpStatus.OK);
    }
      @GetMapping("/user/not-public/{id}")
    public ResponseEntity<?>getImagesByUserIdNotPublic(@PathVariable Long id){
        Iterable<PostImage>imageIterableList=postImageService.findImagesByUserIdNotPublic(id);
        return new ResponseEntity<>(imageIterableList,HttpStatus.OK);
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
