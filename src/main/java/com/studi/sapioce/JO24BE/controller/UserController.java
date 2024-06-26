package com.studi.sapioce.JO24BE.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.pojo.Utils.ResponseMessage;
import com.studi.sapioce.JO24BE.services.UserService;


@RestController
@RequestMapping("/users/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> creatUser(@RequestBody User user) {
		User createdUser = userService.save(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	// Retroune tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
    	List<User> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Retourne un utilisateur grace à l'ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
    	  User user = userService.getUserById(userId);
          if (user != null) {
              return new ResponseEntity<>(user, HttpStatus.OK);
          } else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
    }
    
    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) {
    	 User updatedUser = userService.updateUser(userId, userDetails);
         if (updatedUser != null) {
             return new ResponseEntity<>(updatedUser, HttpStatus.OK);
         } else {
             return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
         }
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable(value = "id") Long userId) {
    	   ResponseMessage response = userService.deleteUser(userId);
    	    return ResponseEntity.ok(response);
    }
    
}
