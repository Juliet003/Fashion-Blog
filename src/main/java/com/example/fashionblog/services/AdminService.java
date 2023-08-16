package com.example.fashionblog.services;

import com.example.fashionblog.dto.requestDto.AdminLoginRequestDto;
import com.example.fashionblog.dto.requestDto.AdminRequestDto;
import com.example.fashionblog.dto.responseDto.AdminResponseDto;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    GenericResponses createAdmin(AdminRequestDto adminRequestDto);


    GenericResponses Login(AdminLoginRequestDto adminLoginRequestDto, HttpServletRequest request);

    GenericResponses fetchAdmin(Long adminId, HttpServletRequest request);

    GenericResponses updateAdmin(Long id, HttpServletRequest request, AdminRequestDto adminRequestDto);


    GenericResponses deleteAdmin(Long id, HttpServletRequest request) throws Exception;
}
