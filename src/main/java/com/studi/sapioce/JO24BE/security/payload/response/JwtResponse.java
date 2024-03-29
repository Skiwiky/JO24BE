package com.studi.sapioce.JO24BE.security.payload.response;

import org.springframework.security.core.userdetails.UserDetails;

import com.studi.sapioce.JO24BE.pojo.User;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private UserDetails userDetails;
	private User user;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the userDetails
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}

	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public JwtResponse(String token, UserDetails userDetails, User user) {
		super();
		this.token = token;
		this.userDetails = userDetails;
		this.user = user;
	}
	
	
}
