package com.example.fashionblog.repository;

import com.example.fashionblog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository extends JpaRepository<Post,Long> {
}
