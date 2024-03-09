package com.studi.sapioce.JO24BE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studi.sapioce.JO24BE.pojo.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
	
	
}
