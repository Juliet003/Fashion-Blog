package com.example.fashionblog.services;

import com.example.fashionblog.dto.requestDto.CommentRequestDto;
import com.example.fashionblog.dto.requestDto.CustomerRequestDto;
import com.example.fashionblog.entities.Comment;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CommentService {
    GenericResponses createComment(Long postId, CommentRequestDto commentRequestDto, HttpServletRequest request, CustomerRequestDto customerRequestDto) ;

    GenericResponses getComment(Long commentId, HttpServletRequest request);

    GenericResponses updateComment(Long commentId, String email, Long postId, HttpServletRequest request);

    GenericResponses deleteComment(Long customerId, Long postId, Long commentId, HttpServletRequest request);

    GenericResponses updateComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest request);

    GenericResponses deleteComment(Long id, HttpServletRequest request);
}


