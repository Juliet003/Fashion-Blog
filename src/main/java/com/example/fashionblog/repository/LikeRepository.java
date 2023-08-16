package com.example.fashionblog.repository;

import com.example.fashionblog.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface LikeRepository extends JpaRepository<Like, Long> {
Optional<Like> findLikeByCustomer_IdAndPost_Id(Long customerId,Long postId);
    List<Like> findLikeByPostId(Long postId);
}
