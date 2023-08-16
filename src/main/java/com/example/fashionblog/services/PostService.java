package com.example.fashionblog.services;

import com.example.fashionblog.dto.requestDto.PostRequestDto;
import com.example.fashionblog.entities.Post;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PostService {

    GenericResponses createPost(PostRequestDto postRequestDto, HttpServletRequest request);


    GenericResponses findPostById(Long postId, HttpServletRequest request);

    GenericResponses updatePost(PostRequestDto postRequestDto, HttpServletRequest request, Long adminId, Post postId);

    GenericResponses deletePost(Long id, String email);

    GenericResponses updatePost(Long id, HttpServletRequest request, PostRequestDto postRequestDto);
}
