package com.studi.sapioce.JO24BE.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
        return UserDetailsImpl.build(user);


    }
}
