package com.example.fashionblog.services;

import com.example.fashionblog.dto.requestDto.CustomerLoginRequestDto;
import com.example.fashionblog.dto.requestDto.CustomerRequestDto;
import com.example.fashionblog.entities.Customer;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    GenericResponses createCustomer(CustomerRequestDto customerRequestDto, HttpServletRequest request);


    GenericResponses login(CustomerLoginRequestDto customerLoginRequestDto, HttpServletRequest request);


    GenericResponses findCustomer(Long customerId, HttpServletRequest request);

    List<Customer> findAllCustomer();

    GenericResponses updateCustomer(CustomerRequestDto customerRequestDto, HttpServletRequest request);

    GenericResponses deleteCustomer(Long customerId, HttpServletRequest request);
}
