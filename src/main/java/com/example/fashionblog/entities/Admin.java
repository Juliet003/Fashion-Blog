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
public class Admin {
    @SequenceGenerator(name = "Admin_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,length = 10)
    private String password;
}
