package com.example.fashionblog.dto.requestDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AdminLoginRequestDto {

    private String email;
    private String password;
}
