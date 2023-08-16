package com.example.fashionblog.controller;

import com.example.fashionblog.dto.requestDto.PostRequestDto;
import com.example.fashionblog.services.LikeService;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/add_like/{postId}")
    public ResponseEntity<GenericResponses> addLike(@PathVariable Long postId, HttpServletRequest request, @RequestBody Long customerId){
        GenericResponses genericResponses= likeService.addLikePost(postId,customerId,request);
        return new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }

//    @GetMapping("/{postId}/count")
//    public ResponseEntity<Long> getTotalLikesForDesignPost(@PathVariable String postId) {
//        long totalLikes = Long.parseLong(likeService.count(Long.valueOf(postId)));
//        return new ResponseEntity<>(HttpStatusCode.valueOf(OK.value()));
//    }
}
