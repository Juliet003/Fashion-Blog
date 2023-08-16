package com.example.fashionblog.services;

import com.example.fashionblog.entities.Like;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {


    GenericResponses addLikePost(Long postId, Long customerId, HttpServletRequest request);

   // String count(Long postId);
}
