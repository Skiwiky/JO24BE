package com.studi.sapioce.JO24BE.security.payload.response;

import com.studi.sapioce.JO24BE.pojo.User;

public class JwtResponse {
	
	   private String token;
	    private String type = "Bearer";
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

		public JwtResponse(String token, User user) {
			super();
			this.token = token;
			this.user = user;
		}
		
		
}



		


