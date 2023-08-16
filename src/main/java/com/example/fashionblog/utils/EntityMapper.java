package com.example.fashionblog.utils;

import com.example.fashionblog.dto.requestDto.AdminRequestDto;
import com.example.fashionblog.dto.requestDto.CustomerRequestDto;
import com.example.fashionblog.dto.responseDto.AdminResponseDto;
import com.example.fashionblog.dto.responseDto.CustomerResponseDto;
import com.example.fashionblog.entities.Admin;
import com.example.fashionblog.entities.Customer;
import org.springframework.stereotype.Service;

@Service
public class EntityMapper {

    public Customer dtoToCustomerMapper(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        customer.setEmail(customerRequestDto.getEmail());
        customer.setName(customerRequestDto.getName());
        customer.setPassword(customerRequestDto.getPassword());
        return customer;
    }

    public CustomerResponseDto customerToDtoMapper(Customer customer) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setName(customer.getName());
        return customerResponseDto;
    }

    public Admin dtoToAdminMapper(AdminRequestDto adminRequestDto) {
        Admin admin = new Admin();
        admin.setEmail(adminRequestDto.getEmail());
        admin.setName(adminRequestDto.getName());
        admin.setPassword(adminRequestDto.getPassword());
        return admin;
    }

    public AdminResponseDto adminToDtoMapper(Admin admin) {
        AdminResponseDto adminResponseDto = new AdminResponseDto();
        adminResponseDto.setId(admin.getId());
        adminResponseDto.setName(admin.getName());
        adminResponseDto.setEmail(admin.getEmail());
        return adminResponseDto;
    }
}


