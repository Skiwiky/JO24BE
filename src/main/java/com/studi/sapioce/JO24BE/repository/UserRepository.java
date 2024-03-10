package com.studi.sapioce.JO24BE.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studi.sapioce.JO24BE.pojo.User;

public interface UserRepository extends JpaRepository<User, Long>{

//	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
    boolean existsByUsername(String username);

	
}
