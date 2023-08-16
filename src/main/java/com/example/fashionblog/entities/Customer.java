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
public class Customer {
    //@SequenceGenerator(name="customers_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false,length = 10)
    private String name;

    @Column(nullable = false,unique = true,length = 50)
    private String email;

    @Column(nullable = false,length=10)
    private String password;


}
