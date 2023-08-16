package com.example.fashionblog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="likes")
public class Like {

    @Id
    private Long id;

    @ManyToOne
    private Post post;

    @OneToOne
    private Customer customer;
}
