package com.example.fashionblog.services.serviceImpl;

import com.example.fashionblog.dto.requestDto.PostRequestDto;
import com.example.fashionblog.dto.responseDto.AdminResponseDto;
import com.example.fashionblog.entities.Admin;
import com.example.fashionblog.entities.Post;
import com.example.fashionblog.repository.AdminRepository;
import com.example.fashionblog.repository.PostRepository;
import com.example.fashionblog.services.PostService;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final AdminRepository adminRepository;

    public PostServiceImpl(PostRepository postRepository, AdminRepository adminRepository) {
        this.postRepository = postRepository;
        this.adminRepository = adminRepository;
    }
@Override
public GenericResponses createPost(PostRequestDto postRequestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
    System.out.println("###########################" + session.getAttribute("adminResponseDto"));
        AdminResponseDto adminResponseDto = (AdminResponseDto) session.getAttribute("adminResponseDto");
        Optional<Admin> admin1 = adminRepository.findById(adminResponseDto.getId());
        if (admin1.isEmpty()){
        return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
    }
        Post post = new Post();
        post.setContent(postRequestDto.getContent());
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        post.setAdmin(admin1.get());
        postRepository.save(post);
        return new GenericResponses("Request processed successfully","00",HttpStatus.CREATED,post);
    }
@Override
    public GenericResponses findPostById(Long postId, HttpServletRequest request){
        HttpSession session = request.getSession();
        AdminResponseDto adminResponseDto= (AdminResponseDto) session.getAttribute("AdminResponse");

        Optional<Admin> admin = adminRepository.findById(adminResponseDto.getId());
    if (admin.isEmpty()){
        return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
    }
    Optional<Post> newPost = postRepository.findById(postId);
    if(newPost.isEmpty()){
        return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
    }
    Post post = new Post();
    post.setId(newPost.get().getId());
    post.setContent(newPost.get().getContent());
    post.setCreatedAt(newPost.get().getCreatedAt());
        return new GenericResponses("Request successful","01",HttpStatus.CREATED,post);
    }


    @Override
    public GenericResponses updatePost(PostRequestDto postRequestDto, HttpServletRequest request, Long adminId, Post postId){
        HttpSession session = request.getSession();
        AdminResponseDto adminSession= (AdminResponseDto) session.getAttribute("AdminResponse");

        Optional<Admin> admin =adminRepository.findById(adminSession.getId());
        if(admin.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Optional<Post> post1= postRepository.findById(postId.getId());
        if(post1.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Post post = new Post();
        post.setContent(postRequestDto.getContent());
        post.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        post.setAdmin(admin.get());
        postRepository.save(post);
        return new GenericResponses("Request processed successfully","00",HttpStatus.CREATED,post);
        }
        @Override
        public GenericResponses deletePost(Long id, String email){
        Optional<Admin> admin1 =adminRepository.findAdminByEmail(email);
        if(admin1.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
        Optional<Post> exitingPost =postRepository.findById(id);
        if(exitingPost.isEmpty()){
            return new GenericResponses("Invalid Request", "01", HttpStatus.BAD_REQUEST);
        }
         postRepository.deleteById(id);
            return new GenericResponses("Request processed successfully","00",HttpStatus.GONE);

        }

    @Override
    public GenericResponses updatePost(Long id, HttpServletRequest request, PostRequestDto postRequestDto) {
        return null;
    }
}
