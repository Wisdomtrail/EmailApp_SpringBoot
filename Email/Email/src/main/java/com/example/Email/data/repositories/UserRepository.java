package com.example.Email.data.repositories;

import com.example.Email.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumber(String phoneNumber);
}
