package com.example.fashionblog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @SequenceGenerator(name="comment_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Column(nullable = false,length  =1000)
    private String content;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Post post;

}
