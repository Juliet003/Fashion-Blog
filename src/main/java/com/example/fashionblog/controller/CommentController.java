package com.example.fashionblog.controller;

import com.example.fashionblog.dto.requestDto.CommentRequestDto;
import com.example.fashionblog.dto.requestDto.CustomerRequestDto;
import com.example.fashionblog.services.CommentService;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create_comment")
    public ResponseEntity<GenericResponses> createComment(@RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request, Long postId, CustomerRequestDto customerRequestDto){
    GenericResponses genericResponses = commentService.createComment(postId,commentRequestDto,request,customerRequestDto);
    return new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
}
    @PutMapping("/update_comment")
    public ResponseEntity<GenericResponses> updateAdmin(@RequestBody CommentRequestDto commentRequestDto, Long id, HttpServletRequest request) {
        GenericResponses genericResponses = commentService.updateComment(id, commentRequestDto,request );
        return new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }

    @GetMapping("/view_user")
    public ResponseEntity<GenericResponses> viewUser(Long id, HttpServletRequest request){
        GenericResponses genericResponse = commentService.getComment(id, request);
        return new  ResponseEntity<>(genericResponse,genericResponse.getHttpStatus());
    }

    @DeleteMapping("/deleteAdmin{id}")
    public ResponseEntity<GenericResponses>deleteUser(@PathVariable("id") Long id,HttpServletRequest request){
        GenericResponses genericResponses = commentService.deleteComment(id,request);
        return  new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }
}

