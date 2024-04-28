package com.studi.sapioce.JO24BE.security.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPassword(),
                mapRolesToAuthorities(user.getRole())
            );
        }

        private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

            return Collections.singletonList(authority);
        }
}
