package com.example.fashionblog.repository;

import com.example.fashionblog.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
   Optional <Admin> findAdminByEmailAndPassword(String email,String password);

   Optional<Admin> findAdminByEmail(String email);

   Optional<Admin> deleteAdminByEmail(String email);
}
