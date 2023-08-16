package com.example.fashionblog.services.serviceImpl;

import com.example.fashionblog.dto.requestDto.CustomerLoginRequestDto;
import com.example.fashionblog.dto.requestDto.CustomerRequestDto;
import com.example.fashionblog.dto.responseDto.CustomerResponseDto;
import com.example.fashionblog.entities.Customer;
import com.example.fashionblog.repository.CommentRepository;
import com.example.fashionblog.repository.CustomerRepository;
import com.example.fashionblog.repository.PostRepository;
import com.example.fashionblog.services.CustomerService;
import com.example.fashionblog.utils.EntityMapper;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityMapper entityMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository, EntityMapper entityMapper, CommentRepository commentRepository, PostRepository postRepository) {
        this.customerRepository = customerRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public GenericResponses createCustomer(CustomerRequestDto customerRequestDto, HttpServletRequest request) {
        Optional<Customer> customer = customerRepository.findCustomerByEmail(customerRequestDto.getEmail());
        if (customer.isPresent()) {
            return new GenericResponses("Account already exist", "01");
        }
        Customer customer1 = entityMapper.dtoToCustomerMapper(customerRequestDto);
        customerRepository.save(customer1);
        CustomerResponseDto customerResponseDto = entityMapper.customerToDtoMapper(customer1);
        return new GenericResponses("Request processed successfully", "00", customerResponseDto);
    }


    @Override
    public GenericResponses login(CustomerLoginRequestDto customerLoginRequestDto, HttpServletRequest request) {
        Optional<Customer> customer = customerRepository
                .findCustomerByEmailAndPassword(customerLoginRequestDto.getEmail(), customerLoginRequestDto.getPassword());
        if (customer.isEmpty()) {
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }

        CustomerResponseDto customerResponseDto = entityMapper.customerToDtoMapper(customer.get());
        HttpSession session = request.getSession();
        session.setAttribute("customerResponse", customerResponseDto);

        return new GenericResponses("Request processed successfully", "00", HttpStatus.OK);
    }
    @Override
    public GenericResponses findCustomer(Long customerId ,HttpServletRequest request){
        HttpSession session = request.getSession();
        CustomerResponseDto customerSession = (CustomerResponseDto) session.getAttribute("customerResponse");
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isEmpty()){
            return new GenericResponses("invalid request","01",HttpStatus.BAD_REQUEST);
        }
        Optional<Customer> customer1 = customerRepository.findById(customerId);
        return (new GenericResponses("Request processed successfully", "00", HttpStatus.OK, customer1));

    }
    @Override
    public List<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }
    @Override
    public GenericResponses updateCustomer(CustomerRequestDto customerRequestDto, HttpServletRequest request){
        Optional<Customer> customer = customerRepository.findCustomerByEmail(customerRequestDto.getEmail());
        if (customer.isEmpty()){
            return new GenericResponses("Invalid request","01",HttpStatus.BAD_REQUEST);
        }
        Customer customer1 = entityMapper.dtoToCustomerMapper(customerRequestDto);
        customerRepository.save(customer1);
        CustomerResponseDto customerResponseDto =entityMapper.customerToDtoMapper(customer1);
        return new GenericResponses("Request successfully processed","00",HttpStatus.OK,customerResponseDto);
    }
    @Override
    public GenericResponses deleteCustomer(Long customerId, HttpServletRequest request){
        customerRepository.deleteById(customerId);
        return null;
    }

}