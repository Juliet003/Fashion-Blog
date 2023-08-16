package com.example.fashionblog.services.serviceImpl;

import com.example.fashionblog.entities.Customer;
import com.example.fashionblog.entities.Like;
import com.example.fashionblog.entities.Post;
import com.example.fashionblog.repository.CustomerRepository;
import com.example.fashionblog.repository.LikeRepository;
import com.example.fashionblog.repository.PostRepository;
import com.example.fashionblog.services.LikeService;
import com.example.fashionblog.utils.GenericResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    private final PostRepository postRepository;
    private final CustomerRepository customerRepository;


    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, CustomerRepository customerRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public GenericResponses addLikePost(Long postId, Long customerId, HttpServletRequest request) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            return new GenericResponses("Invalid request", "00");
        }
        Optional<Customer> customer1 = customerRepository.findById(customerId);
        if (customer1.isEmpty()) {
            return new GenericResponses("Invalid request", "00");
        }
        Optional<Like> existingLike = likeRepository.findLikeByCustomer_IdAndPost_Id(customerId, postId);
        if (existingLike.isPresent()) {
            return new GenericResponses("Invalid Request", "00");
        }
        Like like = new Like();
        like.setCustomer(customer1.get());
        like.setPost(post.get());
        likeRepository.save(like);
        return new GenericResponses("Request Successful", "01", post);


//}@Override
//    public String count(Long postId){
//        List<Like> listCount = likeRepository.findLikeByPostId(postId);
//        return "Total" + listCount + "count is " + listCount.size();
//    }

    }
}

