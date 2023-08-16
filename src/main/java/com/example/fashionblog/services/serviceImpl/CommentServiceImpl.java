package com.example.fashionblog.services.serviceImpl;

import com.example.fashionblog.dto.requestDto.CommentRequestDto;
import com.example.fashionblog.dto.requestDto.CustomerRequestDto;
import com.example.fashionblog.dto.responseDto.CustomerResponseDto;
import com.example.fashionblog.entities.Comment;
import com.example.fashionblog.entities.Customer;
import com.example.fashionblog.entities.Post;
import com.example.fashionblog.repository.CommentRepository;
import com.example.fashionblog.repository.CustomerRepository;
import com.example.fashionblog.repository.PostRepository;
import com.example.fashionblog.services.CommentService;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CustomerRepository customerRepository;
    private final PostRepository postRepository;


    public CommentServiceImpl(CommentRepository commentRepository, CustomerRepository customerRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.customerRepository = customerRepository;
        this.postRepository = postRepository;
    }
    @Override
    public GenericResponses createComment(Long postId, CommentRequestDto commentRequestDto, HttpServletRequest request, CustomerRequestDto customerRequestDto) {
        HttpSession session = request.getSession();
        CustomerResponseDto customerSession = (CustomerResponseDto) session.getAttribute("customerResponse");

        Optional<Customer> customer = customerRepository.findCustomerByEmail(customerSession.getEmail());
        if (customer.isEmpty()) {
            return new GenericResponses("Account already exist", "01");
        }
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Comment comment = new Comment();
        comment.setContent(commentRequestDto.getContent());
        commentRepository.save(comment);
        return new GenericResponses("Request processed successfully", "00", HttpStatus.CREATED);

    }
    @Override
    public GenericResponses getComment(Long commentId, HttpServletRequest request){
        HttpSession session = request.getSession();
        CustomerResponseDto customerSession = (CustomerResponseDto) session.getAttribute("customerResponse");

        Optional<Customer> customer = customerRepository.findCustomerByEmail(customerSession.getEmail());
        if(customer.isEmpty()){
            return new GenericResponses("Invalid request","01");
        }
        Optional<Comment> comment1= commentRepository.findById(commentId);
    return  new GenericResponses("Request processed successfully","00", comment1);
    }
@Override
    public GenericResponses updateComment(Long commentId, String email, Long postId, HttpServletRequest request){
    HttpSession session = request.getSession();
    CustomerResponseDto customerSession = (CustomerResponseDto) session.getAttribute("customerResponse");

    Optional <Customer> customer = customerRepository.findCustomerByEmail(customerSession.getEmail());
        if(customer.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Optional<Comment> comment1 = commentRepository.findById(commentId);
        if(comment1.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Comment newComment = new  Comment();
        newComment.setContent(newComment.getContent());
        commentRepository.save(newComment);
        return new GenericResponses("Request processed successfully", "00", HttpStatus.OK, newComment);

    }

    @Override
    public GenericResponses deleteComment(Long customerId, Long postId, Long commentId, HttpServletRequest request){
        HttpSession session = request.getSession();
        CustomerResponseDto customerSession = (CustomerResponseDto) session.getAttribute("customerResponse");

        Optional <Customer> customerOptional = customerRepository.findCustomerByEmail(customerSession.getEmail());
        if(customerOptional.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Optional<Post> post1 = postRepository.findById(postId);
        if(post1.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Optional<Comment> comment1 = commentRepository.findById(commentId);
        if(comment1.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        commentRepository.deleteById(commentId);
        return new GenericResponses("Request processed successfully", "00", HttpStatus.GONE);

    }

    @Override
    public GenericResponses updateComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return null;
    }
}
