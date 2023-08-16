package com.example.fashionblog.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@Getter
@Setter
public class GenericResponses {
    private String message;
    private String status;
    @JsonIgnore
    private HttpStatus httpStatus;
    private Object data;

    public GenericResponses(String message, String status) {
        this.message = message;
        this.status = status;
    }
    public GenericResponses(String message, String status,Object data) {
        this.message = message;
        this.status = status;
        this.data = data;

    }

    public GenericResponses(String message, String status, HttpStatus httpStatus) {
        this.message = message;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public GenericResponses(String message, String status, HttpStatus httpStatus, Object data) {
        this.message = message;
        this.status = status;
        this.httpStatus = httpStatus;
        this.data = data;
    }
}
