package com.studi.sapioce.JO24BE.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;

	public User save(User user) {
		User userSaved = user;
		userSaved.setPassword(passwordEncoder.encode(userSaved.getPassword()));
		userSaved.setUserKey(passwordEncoder.encode(userSaved.getFirstName() + "-" +userSaved.getLastName() + "-" + userSaved.getBirthDate()+ "-" + Instant.now().toEpochMilli()));
		
		return user;
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		return userList;
	}

	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
//		Optional<User> userOptional = Optional<User>;
//		return userOptional;
		return null;
	}

	public User updateUser(Long userId, User user) {
		// TODO Auto-generated method stub
		//regarder pour ouvrir l'optionnal
		//User userUpdate = userRepository.findById(userId);
		return null;
	}

	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		//pour supp l'utilisateur et tout ses billet et son espace du site
	}

	public boolean findUser(String email) {
		// TODO Auto-generated method stub
		//verifie et recupere le User a partir du mail 
		return false;
	}
	
}
