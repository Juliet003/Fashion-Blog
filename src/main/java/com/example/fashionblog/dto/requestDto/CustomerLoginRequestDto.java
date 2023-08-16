package com.example.fashionblog.dto.requestDto;

import lombok.Data;

@Data
public class CustomerLoginRequestDto {
    private String email;
    private String password;
}
