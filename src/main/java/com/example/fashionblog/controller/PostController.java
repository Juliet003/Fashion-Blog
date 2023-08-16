package com.example.fashionblog.controller;

import com.example.fashionblog.dto.requestDto.AdminRequestDto;
import com.example.fashionblog.dto.requestDto.PostRequestDto;
import com.example.fashionblog.services.PostService;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create_post")
    public ResponseEntity<GenericResponses> createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        GenericResponses genericResponses= postService.createPost(postRequestDto,request);
        return new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }

    @PutMapping("/update_post")
    public ResponseEntity<GenericResponses> updatePost(@RequestBody PostRequestDto postRequestDto, Long id, HttpServletRequest request) {
        GenericResponses genericResponses = postService.updatePost(id, request, postRequestDto );
        return new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }

    @GetMapping("/view_post")
    public ResponseEntity<GenericResponses> viewPost(Long id, HttpServletRequest request){
        GenericResponses genericResponse = postService.findPostById(id, request);
        return new  ResponseEntity<>(genericResponse,genericResponse.getHttpStatus());
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<GenericResponses>deletePost(@PathVariable("id") String id, HttpServletRequest request) throws Exception {
        GenericResponses genericResponses = postService.deletePost(Long.valueOf(id), String.valueOf(request));
        return  new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }
}
