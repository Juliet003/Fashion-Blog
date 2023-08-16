package com.example.fashionblog.controller;

import com.example.fashionblog.dto.requestDto.CustomerLoginRequestDto;
import com.example.fashionblog.dto.requestDto.CustomerRequestDto;
import com.example.fashionblog.services.CustomerService;
import com.example.fashionblog.services.LikeService;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final LikeService likeService;

    public CustomerController(CustomerService customerService, LikeService likeService) {
        this.customerService = customerService;
        this.likeService = likeService;
    }

    @PostMapping("/signup")
    public ResponseEntity<GenericResponses> signup(@RequestBody CustomerRequestDto customerRequestDto, HttpServletRequest request) {
        GenericResponses genericResponses = customerService.createCustomer(customerRequestDto, request);
        return new ResponseEntity<>(genericResponses, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponses> login(@RequestBody CustomerLoginRequestDto customerLoginRequestDto, HttpServletRequest httpServletRequest) {
        GenericResponses genericResponses = customerService.login(customerLoginRequestDto, httpServletRequest);
        return new ResponseEntity<>(genericResponses, genericResponses.getHttpStatus());
    }

    @PutMapping("/update_customer")
    public ResponseEntity<GenericResponses> updateCustomer(@RequestBody  CustomerRequestDto customerRequestDto, Long id, HttpServletRequest request) {
        GenericResponses genericResponses = customerService.updateCustomer(customerRequestDto,request );
        return new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }

    @GetMapping("/view_customer")
    public ResponseEntity<GenericResponses> viewUser(Long id, HttpServletRequest request){
        GenericResponses genericResponse = customerService.findCustomer(id, request);
        return new  ResponseEntity<>(genericResponse,genericResponse.getHttpStatus());
    }

    @DeleteMapping("/deleteAdmin{id}")
    public ResponseEntity<GenericResponses>deleteUser(@PathVariable("id") Long id,HttpServletRequest request){
        GenericResponses genericResponses = customerService.deleteCustomer(id,request);
        return  new ResponseEntity<>(genericResponses,genericResponses.getHttpStatus());
    }
}