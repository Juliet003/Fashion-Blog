package com.example.fashionblog.repository;

import com.example.fashionblog.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface CustomerRepository extends JpaRepository <Customer,Long>{
    Optional    <Customer> findCustomerByEmailAndPassword(String email,String password);

    Optional<Customer> findCustomerByEmail(String email);

}
