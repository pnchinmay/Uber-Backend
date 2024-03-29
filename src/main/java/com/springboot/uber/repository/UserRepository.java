package com.springboot.uber.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.uber.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
