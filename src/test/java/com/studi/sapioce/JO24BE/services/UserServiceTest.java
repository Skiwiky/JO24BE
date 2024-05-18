package com.studi.sapioce.JO24BE.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.studi.sapioce.JO24BE.pojo.User;
import com.studi.sapioce.JO24BE.pojo.Utils.ResponseMessage;
import com.studi.sapioce.JO24BE.pojo.Utils.Utils;
import com.studi.sapioce.JO24BE.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
	 @Mock
	    private UserRepository userRepository;

	    @Mock
	    private BCryptPasswordEncoder passwordEncoder;

	    @Mock
	    private Utils utils;

	    @InjectMocks
	    private UserService userService;

	    private User user;

	    @BeforeEach
	    public void setup() {
	        user = new User();
	        user.setUsername("test@example.com");
	        user.setPassword("password");
	        user.setFirstName("Test");
	        user.setLastName("User");

	        when(utils.getFormatEmail()).thenReturn(".+@.+\\..+");
	        //when(utils.estValidFormatString(anyString(), anyString())).thenReturn(true);
	        when(passwordEncoder.encode(anyString())).thenReturn("encodedString");
	        when(userRepository.save(any(User.class))).thenReturn(user);
	    }

	    @Test
	    public void testSaveValidUser() {
	        User savedUser = userService.save(user);
	        assertNotNull(savedUser);
	        assertEquals("encodedString", savedUser.getPassword());
	        verify(userRepository, times(1)).save(any(User.class));
	    }
	    
	    @Test
	    void testGetAllUserShouldReturnUserList() {
	        List<User> expectedUsers = new ArrayList<User>();
	        expectedUsers.add(new User()); // Ajoutez des données factices selon le besoin
	        when(userRepository.findAll()).thenReturn(expectedUsers);

	        List<User> result = userService.getAllUser();

	        assertEquals(expectedUsers.size(), result.size());
	        verify(userRepository).findAll();
	    }
	    
	    @Test
	    void testGetUserByIdShouldReturnUserWhenExists() {
	        User expectedUser = new User();
	        expectedUser.setId(1L);
	        when(userRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

	        User result = userService.getUserById(1L);

	        assertEquals(expectedUser.getId(), result.getId());
	    }

	    @Test
	    void testGetUserByIdShouldThrowEntityNotFoundExceptionWhenNotFound() {
//	        Long nonExistentId = 999L;
//			when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());
//			assertThrows(EntityNotFoundException.class, () -> {
//				userService.getUserById(nonExistentId);
//			});
	    }

	    @Test
	    void testUpdateUserShouldUpdateDetails() {
	        User originalUser = new User();
	        originalUser.setId(1L);
	        originalUser.setUsername("original@example.com");

	        User updatedUser = new User();
	        updatedUser.setUsername("updated@example.com");

	        when(userRepository.findById(1L)).thenReturn(Optional.of(originalUser));
	        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

	        User result = userService.updateUser(1L, updatedUser);

	        assertEquals(updatedUser.getUsername(), result.getUsername());
	        verify(userRepository).save(any(User.class)); //on verifie que save a été appelé
	    }

	    @Test
	    void testDeleteUserShouldReturnSuccessMessageWhenExists() {
	        Long userId = 1L;
	        when(userRepository.existsById(userId)).thenReturn(true);

	        ResponseMessage result = userService.deleteUser(userId);

	        assertEquals("Utilisateur supprimé avec succès", result.getMessage());
	        verify(userRepository).deleteById(userId);
	    }

	    @Test
	    void testDeleteUserShouldReturnFailureMessageWhenNotExists() {
	        Long userId = 1L;
	        when(userRepository.existsById(userId)).thenReturn(false);

	        ResponseMessage result = userService.deleteUser(userId);

	        assertEquals("Utilisateur non trouvé avec l'ID : " + userId, result.getMessage());
	        verify(userRepository, never()).deleteById(anyLong());
	    }

}
